from abc import ABC
from typing import List


class Day(ABC):
    def __init__(self):
        self.input: str
        with open('input.txt') as file:
            self.input = file.read().splitlines()

    def result_part_one(self):
        raise NotImplementedError

    def result_part_two(self):
        raise NotImplementedError
