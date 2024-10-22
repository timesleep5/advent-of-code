from enum import Enum
from typing import Tuple, List

from y2022_Python.Day_14.CaveParser import Cave


class FallDirection(Enum):
    DOWN = (1, 0)
    LEFT_DOWN = (1, -1)
    RIGHT_DOWN = (1, 1)
    STAY = (0, 0)
    ABYSS = None


class SandSpawner:
    def __init__(self, cave: Cave, spawn_point: Tuple[int, int] = (Cave.SPAWNER_ROW, Cave.SPAWNER_COL)):
        self.cave = cave
        self.spawn_row, self.spawn_col = spawn_point
        self.iteration = 0

    def drop_sand_until_abyss(self) -> bool:
        current_pos = [self.spawn_row, self.spawn_col]
        direction = self.determine_fall_direction(current_pos)
        while direction not in (FallDirection.STAY, FallDirection.ABYSS):
            current_pos = [current_pos[0] + direction.value[0], current_pos[1] + direction.value[1]]
            direction = self.determine_fall_direction(current_pos)
        if direction == FallDirection.STAY:
            self.cave.set_sand(current_pos)
            self.iteration += 1
            return False
        return True

    def drop_sand_until_spawner_is_blocked(self):
        current_pos = [self.spawn_row, self.spawn_col]
        direction = self.determine_fall_direction(current_pos)
        while direction != FallDirection.STAY:
            current_pos = [current_pos[0] + direction.value[0], current_pos[1] + direction.value[1]]
            direction = self.determine_fall_direction(current_pos)
        self.cave.set_sand(current_pos)
        self.iteration += 1
        if self.blocks_spawner(current_pos):
            return True
        return False

    def determine_fall_direction(self, current_pos: List[int]) -> FallDirection:
        row, col = current_pos[0], current_pos[1]
        directions = [FallDirection.DOWN, FallDirection.LEFT_DOWN, FallDirection.RIGHT_DOWN]
        for direction in directions:
            new_pos = [row + direction.value[0], col + direction.value[1]]
            if self.falls_into_abyss(new_pos):
                return FallDirection.ABYSS
            if self.viable_position(new_pos):
                return direction
        return FallDirection.STAY

    def viable_position(self, pos: List[int]) -> bool:
        if 0 <= pos[0] < len(self.cave.cave) and 0 <= pos[1] < len(self.cave.cave[pos[0]]):
            if self.cave.cave[pos[0]][pos[1]] == Cave.AIR:
                return True
        return False

    def falls_into_abyss(self, pos: List[int]) -> bool:
        y = pos[0]
        falls_into_abyss = y == len(self.cave.cave) - 1
        return falls_into_abyss

    def blocks_spawner(self, pos: List[int]) -> bool:
        same_row = pos[0] == Cave.SPAWNER_ROW
        same_col = pos[1] == Cave.SPAWNER_COL
        return same_row and same_col
