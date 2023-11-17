from Day_12.DayTwelve import DayTwelve


instructions = DayTwelve.get_input('input.txt')


# Part 1
day_twelve = DayTwelve(instructions)
day_twelve.add_neighbors_to_positions()

shortest_path = day_twelve.find_shortest_path()
print(f'shortest path to mountain top: {shortest_path}')

# print(day_twelve)

# 1065 too high

