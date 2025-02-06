from y2022_Python.Day_09.DayNine import DayNine

instructions = DayNine.get_input('input.txt')

# Part 1
day_nine_pt1 = DayNine(2)
day_nine_pt1.execute_instructions(instructions)
visited_positions_pt1 = len(day_nine_pt1.get_visited_positions_length())

print(f'visited positions with 2 knots: {visited_positions_pt1}')

# Part 2
day_nine_pt2 = DayNine(10)
day_nine_pt2.execute_instructions(instructions)
visited_positions_pt2 = len(day_nine_pt2.get_visited_positions_length())

print(f'visited positions with 10 knots: {visited_positions_pt2}')


def print_map():
    position_map = day_nine_pt1.build_map()
    print(position_map)

#print_map()