class Day2:
	def __init__(self, f):
		self.file = open(f, 'r')
		self.set_result = {
			'X': 'lose',
			'Y': 'draw',
			'Z': 'win'
		}
		self.set_move = {
			('draw', 'A'): 'X',
			('win', 'A'): 'Y',
			('lose', 'A'): 'Z',
			('lose', 'B'): 'X',
			('draw', 'B'): 'Y',
			('win', 'B'): 'Z',
			('win', 'C'): 'X',
			('lose', 'C'): 'Y',
			('draw', 'C'): 'Z'
		}
		self.points_for_result = {
			'win': 6,
			'draw': 3,
			'lose': 0
		}
		self.points_for_choice = ['X', 'Y', 'Z']

	def calculate_score(self) -> int:
		overall_points = 0
		for line in self.file:
			result = self.set_result[line[2]]  # win/draw/lose
			points_from_outcome = self.points_for_result[result]  # 0/3/6

			condition = (result, line[0])  # [win/draw/lose, A/B/C]
			move = self.set_move[condition]  # X/Y/Z
			points_from_choice = self.points_for_choice.index(move) + 1  # 1/2/3

			points_from_round = points_from_choice + points_from_outcome
			overall_points += points_from_round

		return overall_points


d2 = Day2('sample.txt')
points = d2.calculate_score()
print('Overall points:', points)