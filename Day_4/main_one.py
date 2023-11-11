def setup(f):
    file = open(f, 'r')
    return file


def find_full_overlaps(file):
    full_overlap = 0
    for line in file:
        line = line[:-1]
        separation = line.split(',')
        elf1 = str(separation[0]).split('-')
        elf2 = str(separation[1]).split('-')

        elfone = turn_to_int(elf1)
        elftwo = turn_to_int(elf2)

        if elftwo[0] - elfone[0] >= 0 and elftwo[1] - elfone[1] <= 0 or \
            elftwo[0] - elfone[0] <= 0 and elftwo[1] - elfone[1] >= 0:
                full_overlap += 1

    return full_overlap

def turn_to_int(list):
    new_list = []
    for ele in list:
        ele = int(ele)
        new_list.append(ele)

    return new_list


file = setup('sample.txt')
overlaps = find_full_overlaps(file)
print(overlaps)

# 2-3, 1-4
# 1-4, 2-3