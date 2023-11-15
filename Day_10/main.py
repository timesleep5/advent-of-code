from Day_10.DayTen import DayTen

instructions = DayTen.get_input('input.txt')

register_value = 1
cycle_count = 0
line_length = 40
critical_cycles = [20 + x * 40 for x in range(0, 6)]


# Part 1
day_ten = DayTen(register_value, cycle_count, line_length, critical_cycles)
day_ten.execute_instructions(instructions)
critical_value_sum = day_ten.get_critical_cycle_value_sum()

print(f'Sum of critical values: {critical_value_sum}')


# Part 2
message = day_ten.get_message()

print(f'\nMessage:\n{message}')
