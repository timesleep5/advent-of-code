from typing import override

from y2022_Python.Day_14.CaveParser import CaveParser
from y2022_Python.Day_14.SandSpawner import SandSpawner
from y2022_Python._general.Day import Day


class DayFourteen(Day):
    def __init__(self):
        super().__init__()
        self.cave = CaveParser(self.input).build()
        self.sand_spawner = SandSpawner(self.cave)

    @override
    def result_part_one(self) -> int:
        finished = False
        while not finished:
            finished = self.sand_spawner.drop_sand_until_abyss()
        return self.sand_spawner.iteration

    @override
    def result_part_two(self):
        cave_with_floor = CaveParser(self.input).build(with_floor=True)
        self.sand_spawner = SandSpawner(cave_with_floor)
        finished = False
        while not finished:
            finished = self.sand_spawner.drop_sand_until_spawner_is_blocked()
        return self.sand_spawner.iteration
