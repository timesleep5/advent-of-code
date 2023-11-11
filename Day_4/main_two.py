def setup(f):
    file = open(f, 'r')
    return file


def find_full_overlaps(file):
    any_overlap = 0
    for line in file:
        line = line.removesuffix('\n')
        separation = line.split(',')
        elf1 = str(separation[0]).split('-')
        elf2 = str(separation[1]).split('-')

        elfone = turn_to_int(elf1)
        elftwo = turn_to_int(elf2)

        if elfone[1] < elftwo[0] or elftwo[1] < elfone[0]:
            continue
        else:
            any_overlap += 1

    return any_overlap


def turn_to_int(list):
    new_list = []
    for ele in list:
        ele = int(ele)
        new_list.append(ele)

    return new_list


file = setup('sample.txt')
overlaps = find_full_overlaps(file)
print(overlaps)
