from typing import override

from y2022_Python.Day_13.PacketSorter import PacketSorter
from y2022_Python.Day_13.Pair import Pair
from y2022_Python.Day_13.Parser import Parser
from y2022_Python._general.Day import Day


class DayThirteen(Day):
    def __init__(self):
        super().__init__()
        parser = Parser('\n'.join(self.input))
        self.pairs = parser.parse_pairs()
        self.packets = parser.parse_packets()

    @override
    def result_part_one(self) -> int:
        pairs_in_order = [pair.index for pair in self.pairs if pair.in_order()]
        result = sum(pairs_in_order)
        return result

    @override
    def result_part_two(self) -> int:
        divider_packet_1 = [[2]]
        divider_packet_2 = [[6]]
        self.packets += [divider_packet_1, divider_packet_2]
        cmp = lambda x, y: Pair(x, y, 0).in_order()
        self.packets = PacketSorter(self.packets).sort(cmp)
        divider_index_1 = self.packets.index(divider_packet_1) + 1
        divider_index_2 = self.packets.index(divider_packet_2) + 1
        return divider_index_1 * divider_index_2
