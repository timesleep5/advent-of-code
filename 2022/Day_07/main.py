import sys

from Day_7.DaySeven import DaySeven

day_seven = DaySeven('input.txt')
total_disk_size = 70_000_000
update_size = 30_000_000

linux_protocol = day_seven.get_linux_protocol()
file_tree = day_seven.build_tree2(linux_protocol)
file_tree['*'] = day_seven.calculate_dir_sizes(file_tree['*'])
# print(file_tree)

# Task 1
sum_of_directory_sizes_smaller_100_000 = day_seven.calculate_sum_of_directory_sizes_smaller_100_000(file_tree['*'])

print("Sum of directorys smaller than 100.000:", sum_of_directory_sizes_smaller_100_000)

# Task 2
minimum_directory_size = day_seven.calculate_minimum_dir_size_to_delete(total_disk_size, update_size,
                                                                        file_tree['*']['size'])
smallest_directory_size = day_seven.find_smallest_dir_to_delete(file_tree['*'], minimum_directory_size)
smallest_directory_size_recursive = day_seven.find_smallest_dir_to_delete_recursive(file_tree['*'], sys.maxsize,
                                                                                    minimum_directory_size)

print("Smallest directory to delete:", smallest_directory_size)
print("Smallest directory to delete (recursive):", smallest_directory_size_recursive)
