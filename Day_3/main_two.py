def setup(f):
    file = open(f, 'r')
    return file


def calculate_prio(file) -> int:
    total = 0
    counter = 1
    for line in file:
        line = line[:-1]
        if counter == 1:
            set1 = set(line)
            counter += 1
        elif counter == 2:
            set2 = set1 & set(line)
            counter += 1
        elif counter == 3:
            list1 = list(set2 & set(line))
            letter = list1[0]
            total += add_priority(letter)
            counter = 1

    return total


def add_priority(letter) -> int:
    abc = 'abcdefghijklmnopqrstuvwxyz'
    ABC = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    addition = 0

    if letter.islower():
        addition += abc.index(letter) + 1
        return addition
    elif letter.isupper():
        addition += (ABC.index(letter) + 1) + 26
        return addition
    else:
        print('Error404')


file = setup('sample.txt')
all_prios = calculate_prio(file)
print(all_prios)
