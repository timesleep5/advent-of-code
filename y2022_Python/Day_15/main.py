from y2022_Python.Day_15.DayFifteen import DayFifteen

if __name__ == '__main__':
    day = DayFifteen()

    # Part I
    result_part_one = day.result_part_one()
    print(f'Part I: {result_part_one}')  # 5403291 too high, 5403285 too low, not 5403288

    # Part II
    result_part_two = day.result_part_two()
    print(f'Part II: {result_part_two}')
