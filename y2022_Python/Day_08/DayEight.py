from typing import List


class DayEight:
    def __init__(self, filename: str, row_limit: int, col_limit: int):
        self.filename = filename
        self.row_limit = row_limit
        self.col_limit = col_limit

    def get_input(self) -> List[str]:
        with open(self.filename, 'r') as file:
            return file.readlines()

    def get_visible_trees(self, tree_map: List[str]) -> int:
        visible_trees = 0

        for row in range(0, self.row_limit + 1):
            for col in range(0, self.col_limit + 1):

                if self.check_visibility(tree_map, row, col):
                    visible_trees += 1

        return visible_trees

    def check_visibility(self, tree_map: List[str], row: int, col: int) -> bool:
        maximum_height = int(tree_map[row][col])
        visible_from_west = True
        visible_from_east = True
        visible_from_north = True
        visible_from_south = True

        if row in (0, self.row_limit) or col in (0, self.col_limit):
            return True

        # west
        for y in range(col - 1, -1, -1):
            if int(tree_map[row][y]) >= maximum_height:
                visible_from_west = False

        # east
        for y in range(col + 1, self.col_limit + 1):
            if int(tree_map[row][y]) >= maximum_height:
                visible_from_east = False

        # north
        for x in range(row - 1, -1, -1):
            if int(tree_map[x][col]) >= maximum_height:
                visible_from_north = False

        # south
        for x in range(row + 1, self.row_limit + 1):
            if int(tree_map[x][col]) >= maximum_height:
                visible_from_south = False

        if visible_from_west or visible_from_east or visible_from_north or visible_from_south:
            return True
        else:
            return False

    def get_scenic_highest_score(self, tree_map: List[str]) -> int:
        highest_scenic_score = 0

        for row in range(0, self.row_limit + 1):
            for col in range(0, self.col_limit + 1):
                current_scenic_score = self.calculate_scenic_score(tree_map, row, col)
                if current_scenic_score > highest_scenic_score:
                    highest_scenic_score = current_scenic_score

        return highest_scenic_score

    def calculate_scenic_score(self, tree_map: List[str], row: int, col: int) -> int:
        maximum_height = int(tree_map[row][col])

        scenic_score_west = 0
        scenic_score_east = 0
        scenic_score_north = 0
        scenic_score_south = 0

        if row in (0, self.row_limit) or col in (0, self.col_limit):
            return 0

        # west
        for y in range(col - 1, -1, -1):
            if int(tree_map[row][y]) >= maximum_height:
                scenic_score_west += 1
                break
            else:
                scenic_score_west += 1

        # east
        for y in range(col + 1, self.col_limit + 1):
            if int(tree_map[row][y]) >= maximum_height:
                scenic_score_east += 1
                break
            else:
                scenic_score_east += 1

        # north
        for x in range(row - 1, -1, -1):
            if int(tree_map[x][col]) >= maximum_height:
                scenic_score_north += 1
                break
            else:
                scenic_score_north += 1

        # south
        for x in range(row + 1, self.row_limit + 1):
            if int(tree_map[x][col]) >= maximum_height:
                scenic_score_south += 1
                break
            else:
                scenic_score_south += 1

        return scenic_score_west * scenic_score_east * scenic_score_north * scenic_score_south
