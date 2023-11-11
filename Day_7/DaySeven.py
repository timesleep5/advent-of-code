import sys
from enum import Enum
from typing import List


class DaySeven:
    def __init__(self, filename: str):
        self.filename = filename
        self.source_tree = {}

    def get_linux_protocol(self) -> List[str]:
        with open(self.filename, 'r') as file:
            return file.readlines()

    def build_tree2(self, linux_protocol: List[str]) -> dict[dict]:
        file_tree = {
            '*': {
                'size': -1,
                'content': {
                }
            }
        }
        path = "/*"

        for line_index in range(1, len(linux_protocol)):
            line = linux_protocol[line_index].removesuffix('\n')

            if line == '$ cd ..':
                path = ''.join(path.rsplit('/', 1)[0])
                # print(path)

            elif line.startswith('$ cd '):
                dir_name = line.removeprefix('$ cd ')
                path += f'/{dir_name}'
                # print(path)

            elif line.startswith('dir '):
                new_dir_name = line.removeprefix('dir ')
                file_tree = self.create_dir_entry(file_tree, path.split('/')[1:], new_dir_name)

            elif line.startswith('$ ls') or line == '' or line == '\n':
                continue

            else:
                new_filename = line.split(' ')[1]
                new_filesize = int(line.split(' ')[0])
                file_tree = self.create_file_entry(file_tree, path.split('/')[1:], new_filename, new_filesize)

        return file_tree

    def create_dir_entry(self, directory: dict, path: List[str], new_dir_name: str):
        if len(path) == 0:
            directory_entry = {
                'size': -1,
                'type': FILETYPE.DIRECTORY,
                'content': {}
            }

            directory[new_dir_name] = directory_entry

            return directory
        else:
            next_directory = path[0]
            del path[0]
            directory[next_directory]['content'] = self.create_dir_entry(directory[next_directory]['content'], path,
                                                                         new_dir_name)
        return directory

    def create_file_entry(self, directory: dict, path: List[str], new_filename: str, filesize: int):
        if len(path) == 0:
            file_entry = {
                'size': filesize,
                'type': FILETYPE.FILE
            }
            directory[new_filename] = file_entry
            return directory
        else:
            next_directory = path[0]
            del path[0]
            directory[next_directory]['content'] = self.create_file_entry(directory[next_directory]['content'], path,
                                                                          new_filename, filesize)
        return directory

    def calculate_dir_sizes(self, directory: dict):
        dir_size = 0

        for entry in directory['content'].values():
            if entry['type'] == FILETYPE.FILE:
                dir_size += entry['size']
            else:
                dir_size += self.calculate_dir_sizes(entry)['size']

        directory['size'] = dir_size

        return directory

    def calculate_sum_of_directory_sizes_smaller_100_000(self, directory: dict) -> int:
        overall_dir_size_of_over_100_000 = 0

        for entry in directory['content'].values():
            if entry['type'] == FILETYPE.DIRECTORY:
                overall_dir_size_of_over_100_000 += self.calculate_sum_of_directory_sizes_smaller_100_000(entry)

        overall_dir_size_of_over_100_000 += directory['size'] if directory['size'] <= 100_000 else 0

        return overall_dir_size_of_over_100_000

    @staticmethod
    def calculate_minimum_dir_size_to_delete(total_size: int, needed_size: int, used_size: int):
        free_size = total_size - used_size
        return needed_size - free_size

    def find_smallest_dir_to_delete_recursive(self, directory: dict, closest_size_param: int, minimum_size_to_delete: int) -> int:
        closest_size = closest_size_param

        for entry in directory['content'].values():
            if entry['type'] == FILETYPE.DIRECTORY:
                closest_size = self.find_smallest_dir_to_delete_recursive(entry, closest_size, minimum_size_to_delete)

        new_closest_size = directory['size'] if (
                    closest_size > directory['size'] >= minimum_size_to_delete) else closest_size

        return new_closest_size


    def find_smallest_dir_to_delete(self, directory: dict, minimum_size: int) -> int:
        directory_sizes = self.list_directory_sizes(directory, [])
        directory_sizes_over_minsize = []

        for size in directory_sizes:
            if size >= minimum_size:
                directory_sizes_over_minsize.append(size)

        return sorted(directory_sizes_over_minsize)[0]

    def list_directory_sizes(self, directory: dict, directory_list):

        for entry in directory['content'].values():
            if entry['type'] == FILETYPE.DIRECTORY:
                directory_list = self.list_directory_sizes(entry, directory_list)

        directory_list.append(directory['size'])

        return directory_list




class FILETYPE(Enum):
    FILE = 1
    DIRECTORY = 0


# tree = {
#     '/': {
#         'size': -1,
#         'type': FILETYPE.DIRECTORY,
#         'content': {
#             'folder_a': {
#                 'size': -1,
#                 'type': FILETYPE.DIRECTORY,
#                 'content': {
#                     'file_a': {
#                         'size': 100,
#                         'type': FILETYPE.FILE
#                     },
#                     'file_b': {
#                         'size': 100,
#                         'type': FILETYPE.FILE
#                     }
#                 },
#                 'folder_b': {
#                     'size': -1,
#                     'type': FILETYPE.DIRECTORY,
#                     'content': {
#                         'file_c': 200
#                     }
#                 }
#             }
#         }
#     }
# }
