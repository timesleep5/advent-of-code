from typing import List, Tuple


class DayTwelve:
    height_maxtrix: List[List[str]]
    possible_paths: List[str]

    start_coordinates: Tuple[int, int]
    destination_coordinates: Tuple[int, int]

    def __init__(self, height_matrix: List[List[str]]):
        self.height_maxtrix = height_matrix
        self.start_coordinates = self.find_pattern_coordinates('S')
        self.destination_coordinates = self.find_pattern_coordinates('E')

        self.possible_paths = []


    @staticmethod
    def get_input(filename: str) -> List[List[str]]:
        with open(filename, 'r') as file:
            lines = file.readlines()

        height_matrix = []
        for line in lines:
            line_list = list(line.removesuffix('\n'))
            height_matrix.append(line_list)

        return height_matrix

    def find_pattern_coordinates(self, pattern: str) -> Tuple[int, int]:
        for y in range(len(self.height_maxtrix)):
            for x in range(len(self.height_maxtrix[y])):
                if self.height_maxtrix[y][x] == pattern:
                    return x, y

    def find_all_possible_paths(self) -> None:
        while True:
            pass
