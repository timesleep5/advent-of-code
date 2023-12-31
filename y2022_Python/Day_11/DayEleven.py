from typing import List

from Day_11.Monkey import Monkey


class DayEleven:
    round_count: int
    monkey_boredom_factor: int
    monkey_boredom_operator: str

    monkeys: List[Monkey]
    monkey_inspection_counters: dict[str: int]

    def __init__(self, round_count: int, monkey_boredom_operator: str, monkey_boredom_factor: int):
        self.round_count = round_count
        self.monkey_boredom_factor = monkey_boredom_factor
        self.monkey_boredom_operator = monkey_boredom_operator

        self.monkeys = []
        self.monkey_inspection_counters = {}

    @staticmethod
    def get_input(filename: str) -> List[str]:
        with open(filename, 'r') as file:
            return file.read().split('\n\n')

    def declare_monkeys(self, instructions: List[str]) -> None:
        for instruction in instructions:
            instruction = instruction.split('\n')
            self.monkeys.append(Monkey(instruction, self.monkey_boredom_operator, self.monkey_boredom_factor))

    def set_throw_decisions(self) -> None:
        for monkey in self.monkeys:
            true_destination_name = monkey.determine_true_throw_decision()
            for destination_monkey in self.monkeys:
                if destination_monkey.name == true_destination_name:
                    monkey.set_true_throw_decision(destination_monkey)
                    break

        for monkey in self.monkeys:
            false_destination_name = monkey.determine_false_throw_decision()
            for destination_monkey in self.monkeys:
                if destination_monkey.name == false_destination_name:
                    monkey.set_false_throw_decision(destination_monkey)
                    break

    def execute_rounds(self) -> None:
        for round_index in range(self.round_count):
            self.round()
            # print(round_index, round_index/self.round_count, '%')

    def round(self) -> None:
        for monkey in self.monkeys:
            monkey.inspect_items()

    def get_monkey_inspection_counters(self) -> None:
        for monkey in self.monkeys:
            self.monkey_inspection_counters[monkey.name] = monkey.inspection_counter

    def get_monkey_business(self) -> int:
        sorted_counters = sorted(self.monkey_inspection_counters.values())

        return sorted_counters[-1] * sorted_counters[-2]

    def to_string(self) -> str:
        output = ''
        for monkey in self.monkeys:
            output += monkey.to_string()
            output += '\n\n'

        return output
