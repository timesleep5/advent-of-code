class Markerfinder:
    def __init__(self, f):
        self.file = open(f, 'r')
        self.text = str(self.file.readlines()).removeprefix('[\'').removesuffix('\']')


    def find_marker(self, length) -> int:
        marker_found = False
        position = length
        while not marker_found:
            inspected_string = self.text[(position - length): position]
            print(inspected_string)
            if len(set(inspected_string)) == length:
                marker_found = True
            else:
                #print(position)
                position += 1

        return position


finder = Markerfinder('sample.txt')

print(finder.find_marker(14))
