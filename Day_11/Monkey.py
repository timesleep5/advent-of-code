from typing import List, Tuple


class Monkey:
    instruction: List[str]

    name: str
    items: List[int]
    operation_details: Tuple[str, int]
    test_parameter: int
    throw_decision: dict[bool: "Monkey"]

    inspection_counter: int

    def __init__(self, instruction: List[str]):
        self.instruction = instruction
        self.name = self.determine_name()
        self.items = self.determine_items()
        self.operation_details = self.determine_operation_parameter()
        self.test_parameter = self.determine_test_parameter()

        self.throw_decision = {}
        self.inspection_counter = 0

    def inspect_items(self) -> None:
        destination_monkey: "Monkey"

        for item in self.items:
            item = self.do_operation(item)
            item = self.get_bored(item)

            test_result = self.test_item(item)
            destination_monkey = self.throw_decision[test_result]
            destination_monkey.add_item(item)

            self.inspection_counter += 1

        self.items = []

    def do_operation(self, item: int):
        operator = self.operation_details[0]
        operation_parameter = self.operation_details[1]

        match operator:
            case '+':
                return item + operation_parameter
            case '*':
                return item * operation_parameter
            case '**':
                return item ** operation_parameter
            case _:
                print(f'error: wrong operator: {operator}')

    @staticmethod
    def get_bored(item: int) -> int:
        return int(item / 3)

    def test_item(self, item: int) -> bool:
        return item % self.test_parameter == 0

    def determine_name(self) -> str:
        return self.instruction[0].removesuffix(':')

    def determine_items(self) -> List[int]:
        items_string = self.instruction[1].split(':')[1]
        item_string_list = items_string.strip(' ').split(', ')
        item_int_list = list(map(int, item_string_list))

        return item_int_list

    def determine_operation_parameter(self) -> Tuple[str, int]:
        if self.instruction[2].split(' ')[-1] == 'old':
            return '**', 2
        else:
            operator = self.instruction[2].split(' ')[-2]
            operation_parameter = int(self.instruction[2].split(' ')[-1])
            return operator, operation_parameter

    def determine_test_parameter(self) -> int:
        return int(self.instruction[3].split(' ')[-1])

    def determine_true_throw_decision(self) -> str:
        return 'Monkey ' + self.instruction[4].split(' ')[-1]

    def determine_false_throw_decision(self) -> str:
        return 'Monkey ' + self.instruction[5].split(' ')[-1]

    def set_true_throw_decision(self, monkey: "Monkey") -> None:
        self.throw_decision[True] = monkey

    def set_false_throw_decision(self, monkey: "Monkey") -> None:
        self.throw_decision[False] = monkey

    def add_item(self, item: int) -> None:
        self.items.append(item)

    def to_string(self) -> str:
        return f'name: {self.name}\n' \
               f'items: {self.items}\n' \
               f'operation details: {self.operation_details}\n' \
               f'test param: {self.test_parameter}\n' \
               f'true dest: {self.throw_decision[True].name}\n' \
               f'false dest: {self.throw_decision[False].name}\n'
