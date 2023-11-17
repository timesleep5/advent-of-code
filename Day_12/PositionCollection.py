from typing import List, Tuple

from Day_12.Position import Position


class PositionCollection:
    _values: List[Position]

    def __init__(self):
        self._values = []

    def add(self, position: Position) -> None:
        self._values.append(position)

    def delete(self, position: Position) -> None:
        self._values.remove(position)

    def contains(self, position: Position) -> bool:
        return position in self._values
