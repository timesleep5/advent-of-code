from Day_9.DayNine import DayNine
from Day_9.Knot import Knot

day_nine = DayNine('input.txt')

instructions = day_nine.get_input()
day_nine.execute_instructions(instructions)
visited_positions = day_nine.get_visited_positions_length()
print(f'visited positions: {visited_positions}')



def print_map():
    position_map = day_nine.build_map()
    print(position_map)

print_map()