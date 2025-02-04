from typing import List, Tuple

from y2022_Python.Day_15.Sensor import Sensor, YCoverage


class BeaconDetector:
    @staticmethod
    def filter_for_sensors_covering_y(sensors: List[Sensor], y: int) -> List[Sensor]:
        return [
            sensor
            for sensor in sensors
            if sensor.covers_y(y)
        ]

    def get_covered_positions_at_y(self, sensors: List[Sensor], y: int) -> int:
        y_covs = self.get_coverage_at(sensors, y)
        return self.count_distinct_positions(y_covs)

    def get_coverage_at(self, sensors: List[Sensor], y: int) -> List[YCoverage]:
        y_covs = [sensor.get_y_coverage_of(y) for sensor in sensors]
        while self.mergable(y_covs):
            y_covs = self.merge(y_covs)
        return y_covs

    @staticmethod
    def mergable(y_covs: List[YCoverage]) -> bool:
        mergables = [
            y_covs[i].mergable(y_covs[j])
            for i in range(len(y_covs))
            for j in range(len(y_covs))
            if i != j
        ]
        return any(mergables)

    @staticmethod
    def merge(y_covs: List[YCoverage]) -> List[YCoverage]:
        merged_y_covs = []
        merged_indices = []
        for i in range(len(y_covs)):
            for j in range(len(y_covs)):
                if i != j and y_covs[i].mergable(y_covs[j]):
                    merged_y_covs.append(y_covs[i].merge(y_covs[j]))
                    merged_indices += [i, j]
                    break

        not_merged_y_covs = [y_covs[i] for i in range(len(y_covs)) if i not in merged_indices]

        return list(set(merged_y_covs + not_merged_y_covs))

    def count_distinct_positions(self, y_covs: List[YCoverage]) -> int:
        distinct_positions = sum([
            cov.covered_positions()
            for cov in y_covs
        ])
        return distinct_positions

    @staticmethod
    def number_of_beacons_at_y(sensors: List[Sensor], y: int) -> int:
        number_of_distinct_beacons = len(set([
            (sensor.beacon_x, sensor.beacon_y)
            for sensor in sensors
            if sensor.beacon_y == y
        ]))
        return number_of_distinct_beacons

    def find_distress_beacon(self, sensors: List[Sensor], lower_bound: int, upper_bound: int) -> Tuple[int, int]:
        for y in range(lower_bound, upper_bound + 1):
            sensors_for_y = self.filter_for_sensors_covering_y(sensors, y)
            y_covs = self.get_coverage_at(sensors_for_y, y)
            if len(y_covs) == 1 and not self.covers(y_covs[0], lower_bound, upper_bound):
                beacon_x = self.find_empty_position(y_covs[0], lower_bound, upper_bound)
                return beacon_x, y
            elif len(y_covs) == 2:
                cov_1, cov_2 = y_covs
                beacon_x = min(cov_1.to_x,
                               cov_2.to_x) + 1  # position should be between the coverages, i.e. after the smaller coverage ends
                return beacon_x, y

    def covers(self, cov: YCoverage, lower_bound: int, upper_bound: int) -> bool:
        return cov.from_x <= lower_bound <= upper_bound <= cov.to_x

    def find_empty_position(self, cov: YCoverage, lower_bound: int, upper_bound: int) -> int:
        if lower_bound < cov.from_x:
            return lower_bound
        if upper_bound > cov.to_x:
            return upper_bound
