from Day_11.DayEleven import DayEleven

instructions = DayEleven.get_input('input.txt')
round_count = 20

# Part 1
day_eleven = DayEleven(20)
day_eleven.declare_monkeys(instructions)
day_eleven.set_throw_decisions()
day_eleven.execute_rounds()
day_eleven.get_monkey_inspection_counters()

monkey_business = day_eleven.get_monkey_business()
print(f'Monkey business: {monkey_business}')


# Part 2
