from typing import Callable, Any


class PacketSorter:
    def __init__(self, packets):
        self.packets = packets

    def sort(self, cmp: Callable[[Any, Any], bool]):
        sorted = False
        while not sorted:
            sorted = True
            for i in range(len(self.packets) - 1):
                if not cmp(self.packets[i], self.packets[i + 1]):
                    self.packets[i], self.packets[i + 1] = self.packets[i + 1], self.packets[i]
                    sorted = False

        return self.packets
