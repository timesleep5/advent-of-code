from typing import List


class DayTen:
    register_x: int
    cycle_count: int
    critical_cycles: List[int]
    critical_cycle_values: List[int]

    def __init__(self, register_value, cycle_count: int, critical_cycles: List[int]):
        self.register_x = register_value
        self.cycle_count = cycle_count
        self.critical_cycles = critical_cycles

        self.critical_cycle_values = []

    @staticmethod
    def get_input(filename: str) -> List[str]:
        with open(filename, 'r') as file:
            return file.readlines()

    def execute_instructions(self, instructions: List[str]):
        for instruction in instructions:
            instruction = instruction.removesuffix('\n')

            if instruction.startswith('noop'):
                self.empty_cycle()
            elif instruction.startswith('add'):
                register_value = self.get_register_value(instruction)
                self.add(register_value)

    @staticmethod
    def get_register_value(instruction: str) -> int:
        return int(instruction.split(' ')[1])

    def empty_cycle(self) -> None:
        self.cycle_count += 1

        if self.cycle_count in self.critical_cycles:
            self.critical_cycle_values.append(self.cycle_count * self.register_x)

    def add(self, register_value) -> None:
        self.empty_cycle()
        self.empty_cycle()
        self.register_x += register_value

    def get_critical_cycle_value_sum(self) -> int:
        value_sum = 0

        for value in self.critical_cycle_values:
            value_sum += value

        return value_sum
