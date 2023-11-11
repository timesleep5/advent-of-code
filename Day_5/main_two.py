class CargoCrane:
    def __init__(self, f):
        self.file = open(f, 'r')
        self.key = 1
        self.debugfile = open('debugfile.txt', 'w')

    def get_stacks(self) -> (str, dict):
        empty_container = self.__get_container()
        self.debugfile.writelines(f'Container is created:\n"{empty_container}"\n')
        filled_container = self.__fill_container(empty_container)
        self.debugfile.writelines(f'Container is filled:\n"{filled_container}"\n\n')
        stack_tops, final_container = self.__do_instructions(filled_container)

        return stack_tops, final_container

    @staticmethod
    def __get_container():
        nums = [x for x in range(1, 10)]
        empty_container = dict.fromkeys(nums, '')

        return empty_container

    def __fill_container(self, empty_container: dict):
        filled_container = None
        for line in self.file:
            if not '[' in line:
                break
            self.key = 1
            line = line.removesuffix('\n')
            for x in range(1, len(line), 4):
                filled_container = self.__sort_letters(empty_container, line, x)
        for key, val in filled_container.items():
            filled_container[key] = val[::-1]

        return filled_container

    def __sort_letters(self, container, line, x):
        letter = line[x]
        if letter == ' ':
            self.key += 1
            return
        else:
            container[self.key] += letter
        self.key += 1

        return container

    def __do_instructions(self, container: dict) -> (str, dict):
        for line in self.file:
            line = line.removesuffix('\n')
            if line[0:4] != 'move':
                continue
            elif line[0:4] == 'move':
                container = self.sort_package(line, container)
                self.debugfile.writelines(f'Line "{line}"\nhas this result:\n{container}\n\n')
        self.file.close()
        stack_tops = self.__get_stack_tops(container)

        return stack_tops, container

    @staticmethod
    def sort_package(line: str, container: dict) -> dict:
        number, location = line.split(' from ')
        number = number.split('move ')[1]
        start, dest = location.split(' to ')
        del line, location

        package = container[int(start)][-(int(number)):]
        if len(container[int(start)]) == 0:
            print('subtraction not possible')
        container[int(start)] = container[int(start)][0:-(int(number))]
        container[int(dest)] += package

        return container


    @staticmethod
    def __get_stack_tops(container: dict) -> str:
        stack_tops = ''
        for val in container.values():
            try:
                stack_tops += val[-1]
            except IndexError:
                stack_tops += ' '

        return stack_tops


crane = CargoCrane('sample.txt')

print(crane.get_stacks())
