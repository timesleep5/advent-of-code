from y2022_Python.Day_15.BeaconDetector import BeaconDetector
from y2022_Python.Day_15.SensorParser import SensorParser
from y2022_Python._general.Day import Day


class DayFifteen(Day):
    Y = 2000000
    LOWER_BOUND = 0
    UPPER_BOUND = 4000000
    TUNING_FREQUENCY_MULTIPLIER = 4000000

    def __init__(self):
        super().__init__()
        self.sensors = (SensorParser()
                        .set_input(self.input.splitlines())
                        .parse())
        self.beacon_detector = BeaconDetector()

    def result_part_one(self):
        sensors = self.beacon_detector.filter_for_sensors_covering_y(self.sensors, self.Y)
        covered_positions = self.beacon_detector.get_covered_positions_at_y(sensors, self.Y)
        beacons_at_y = self.beacon_detector.number_of_beacons_at_y(sensors, self.Y)
        return covered_positions - beacons_at_y

    def result_part_two(self):
        x, y = self.beacon_detector.find_distress_beacon(self.sensors, self.LOWER_BOUND, self.UPPER_BOUND)
        tuning_frequency = x * self.TUNING_FREQUENCY_MULTIPLIER + y
        return tuning_frequency
