import math

def setup(f):
    file = open(f, 'r')
    return file


def calculate_prio(file) -> int:
    sum = 0
    for line in file:
        line = line[:-1]
        half_length = math.floor(len(line) / 2)
        whole_length = len(line)

        set1 = {line[x] for x in range(0, half_length)}
        set2 = {line[x] for x in range(half_length, whole_length)}

        list1 = list(set1 & set2)


        if len(list1) > 1:
            print(list1)
        letter = list1[0]
        sum += add_priority(str(letter))

    return sum


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
