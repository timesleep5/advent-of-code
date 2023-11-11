class Day2:
    def __init__(self, f):
        self.file = open(f, 'r')
        self.windrawlose = {
            'AX': 'draw',
            'AY': 'win',
            'AZ': 'lose',
            'BX': 'lose',
            'BY': 'draw',
            'BZ': 'win',
            'CX': 'win',
            'CY': 'lose',
            'CZ': 'draw'
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
            relation = line[0] + line[2]
            outcome = self.windrawlose[relation]
            points_from_outcome = self.points_for_result[outcome]
            points_from_choice = self.points_for_choice.index(line[2]) + 1
            points_from_round = points_from_choice + points_from_outcome
            overall_points += points_from_round

        return overall_points


d2 = Day2('sample.txt')
points = d2.calculate_score()
print('Overall points:', points)
