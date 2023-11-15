from Day_11.DayEleven import DayEleven

instructions = DayEleven.get_input('input.txt')

# Part 1
round_count = 20
monkey_boredom_factor = 3

day_eleven = DayEleven(round_count, monkey_boredom_factor)
day_eleven.declare_monkeys(instructions)
day_eleven.set_throw_decisions()
day_eleven.execute_rounds()
day_eleven.get_monkey_inspection_counters()

monkey_business = day_eleven.get_monkey_business()
print(f'Monkey business after {round_count} rounds with boredom factor {monkey_boredom_factor}: {monkey_business}')


# Part 2
round_count = 10_000
monkey_boredom_factor = 1

day_eleven = DayEleven(round_count, monkey_boredom_factor)
day_eleven.declare_monkeys(instructions)
day_eleven.set_throw_decisions()
day_eleven.execute_rounds()
day_eleven.get_monkey_inspection_counters()

monkey_business = day_eleven.get_monkey_business()
print(f'Monkey business after {round_count} rounds with boredom factor {monkey_boredom_factor}: {monkey_business}')
