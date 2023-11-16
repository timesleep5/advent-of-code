from typing import List, Tuple


class Monkey:
    instruction: List[str]

    name: str
    items: List[int]
    operator: str
    operation_parameter: int
    boredom_factor: int
    boredom_operator: str
    test_parameter: int
    throw_decision: dict[bool: "Monkey"]

    inspection_counter: int

    def __init__(self, instruction: List[str], boredom_operator: str, boredom_factor: int):
        self.instruction = instruction
        self.boredom_factor = boredom_factor
        self.boredom_operator = boredom_operator
        self.name = self.determine_name()
        self.items = self.determine_items()
        self.operator, self.operation_parameter = self.determine_operation_details()
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

        self.inspection_counter += len(self.items)
        self.items = []

    def do_operation(self, item: int):
        match self.operator:
            case '+':
                return item + self.operation_parameter
            case '*':
                return item * self.operation_parameter
            case '**':
                return item ** self.operation_parameter
            case _:
                print(f'error: wrong operator: {self.operator}')

    def get_bored(self, item: int) -> int:
        match self.boredom_operator:
            case '//':
                return item // self.boredom_factor
            case '%':
                return item % self.boredom_factor
            case _:
                print(f'error: wrong operator: {self.boredom_operator}')

    def test_item(self, item: int) -> bool:
        return item % self.test_parameter == 0

    # Constructor

    def determine_name(self) -> str:
        return self.instruction[0].removesuffix(':')

    def determine_items(self) -> List[int]:
        items_string = self.instruction[1].split(':')[1]
        item_string_list = items_string.strip(' ').split(', ')
        item_int_list = list(map(int, item_string_list))

        return item_int_list

    def determine_operation_details(self) -> Tuple[str, int]:
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

    # Setter

    def set_true_throw_decision(self, monkey: "Monkey") -> None:
        self.throw_decision[True] = monkey

    def set_false_throw_decision(self, monkey: "Monkey") -> None:
        self.throw_decision[False] = monkey

    def add_item(self, item: int) -> None:
        self.items.append(item)

    # Override

    def to_string(self) -> str:
        return f'name: {self.name}\n' \
               f'items: {self.items}\n' \
               f'operation details: ({self.operator}, {self.operation_parameter})\n' \
               f'test param: {self.test_parameter}\n' \
               f'true dest: {self.throw_decision[True].name}\n' \
               f'false dest: {self.throw_decision[False].name}\n'
