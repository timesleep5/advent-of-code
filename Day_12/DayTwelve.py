from typing import List


class DayTwelve:
    def __init__(self):
        pass

    @staticmethod
    def get_input(filename: str) -> List[str]:
        with open(filename, 'r') as file:
            return file.readlines()