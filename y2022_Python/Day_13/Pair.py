class Pair:
    verbose = False

    def __init__(self, left, right, index):
        self.left = left
        self.right = right
        self.index = index

    def compare(self) -> int:
        return 1 if self.in_order() else -3

    def in_order(self) -> bool:
        if self.verbose:
            print()
        return self.in_order_recursive(self.left, self.right) is True

    def in_order_recursive(self, left, right) -> bool:
        if self.verbose:
            print(f'comparing {left} and {right}')

        if isinstance(left, int) and isinstance(right, int):
            if left != right:
                return left < right

        elif isinstance(left, list) and isinstance(right, list):
            min_index = min(len(left), len(right))
            for i in range(min_index):
                res = self.in_order_recursive(left[i], right[i])
                if res is not None:
                    return res

            if len(left) != len(right):
                return len(left) < len(right)

        else:
            if isinstance(left, int):
                return self.in_order_recursive([left], right)
            else:
                return self.in_order_recursive(left, [right])
