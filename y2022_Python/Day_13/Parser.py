import ast
from typing import List

from y2022_Python.Day_13.Pair import Pair


class Parser:
    def __init__(self, input: str):
        self.input = input

    def parse_pairs(self) -> List[Pair]:
        pairs = []
        pair_strings = self.input.split('\n\n')
        for i, pair_string in enumerate(pair_strings):
            left_string, right_string = pair_string.split('\n')
            left = self._parse_string(left_string)
            right = self._parse_string(right_string)
            pairs.append(Pair(left, right, i + 1))

        return pairs

    def parse_packets(self):
        packet_strings = [element for pair in self.input.split('\n\n') for element in pair.split('\n')]
        packets = [self._parse_string(string) for string in packet_strings]
        return packets

    @staticmethod
    def _parse_string(string: str):
        return ast.literal_eval(string)
