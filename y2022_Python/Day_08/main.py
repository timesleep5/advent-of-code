from y2022_Python.Day_08.DayEight import DayEight

day_eight = DayEight('input.txt', 98, 98)
tree_map = day_eight.get_input()

# Task 1
print('Amount of visible trees:', day_eight.get_visible_trees(tree_map))

# Task 2
print('Highest possible scenic score:', day_eight.get_scenic_highest_score(tree_map))
