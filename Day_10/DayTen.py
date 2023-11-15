from typing import List


class DayTen:
    register_x: int
    cycle_count: int
    line_length: int
    message: str

    critical_cycles: List[int]
    critical_cycle_values: List[int]

    def __init__(self, register_value, cycle_count: int, line_length: int, critical_cycles: List[int]):
        self.register_x = register_value
        self.cycle_count = cycle_count
        self.line_length = line_length
        self.critical_cycles = critical_cycles

        self.critical_cycle_values = []
        self.message = ''

    @staticmethod
    def get_input(filename: str) -> List[str]:
        with open(filename, 'r') as file:
            return file.readlines()

    def execute_instructions(self, instructions: List[str]):
        for instruction in instructions:
            instruction = instruction.removesuffix('\n')

            if instruction.startswith('noop'):
                self.cycle()
            elif instruction.startswith('add'):
                register_value = self.get_register_value(instruction)
                self.add(register_value)

    @staticmethod
    def get_register_value(instruction: str) -> int:
        return int(instruction.split(' ')[1])

    def add(self, register_value) -> None:
        self.cycle()
        self.cycle()
        self.register_x += register_value

    def cycle(self) -> None:
        self.cycle_count += 1

        self.check_critical_cycle()
        self.draw_sprite()
        self.add_newline()

    def check_critical_cycle(self):
        if self.cycle_count in self.critical_cycles:
            self.critical_cycle_values.append(self.cycle_count * self.register_x)

    def draw_sprite(self):
        if self.sprite_in_range():
            self.message += '#'
        else:
            self.message += '.'

    def sprite_in_range(self) -> bool:
        sprite_range = range(self.register_x - 1, self.register_x + 2)
        position_to_print = self.cycle_count % (self.line_length + 1)  # +1 = \n

        return position_to_print in sprite_range

    def add_newline(self):
        if len(self.message.split('\n')[-1]) % self.line_length == 0:
            self.message += '\n'

    def get_critical_cycle_value_sum(self) -> int:
        value_sum = 0

        for value in self.critical_cycle_values:
            value_sum += value

        return value_sum

    def get_message(self) -> str:
        return self.message
