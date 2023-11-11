class Day1:
	def __init__(self, f):
		self.file = open(f, 'r')
		self.current_sum = 0
		self.sum_collection = []

	def analyse_line(self) -> list:
		all = self.sum_collection
		current = self.current_sum
		for line in self.file:
			if line == "\n":
				all.append(current)
				current = 0
			else:
				line = line[0: -1]
				current += int(line)
		all.append(current)
		return all

	def find_highest(self, sums: list) -> int:
		highest = sums[0]
		for element in sums:
			if element > highest:
				highest = element
		return highest

	def find_top_three(self, sums: list) -> int:
		sorted_sums = sorted(sums)
		top_three = sorted_sums[-3:]
		top_three_sum = 0
		for x in top_three:
			top_three_sum += x
		return top_three_sum


d1 = Day1('sample.txt')
all_sums = d1.analyse_line()
highest_three = d1.find_top_three(all_sums)
print('The sum of the top 3 is:', highest_three)