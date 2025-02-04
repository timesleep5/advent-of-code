from typing import List, Tuple

from y2022_Python.Day_15.Sensor import Sensor


class SensorParser:
    def __init__(self):
        self.input: List[str]

    def set_input(self, input: List[str]) -> 'SensorParser':
        self.input = input
        return self

    def parse(self):
        sensors = []
        for line in self.input:
            line = line.removeprefix('Sensor at ')
            sensor_line, beacon_line = line.split(': closest beacon is at ')
            sensor = self.build_sensor(sensor_line, beacon_line)
            sensors.append(sensor)
        return sensors

    def build_sensor(self, sensor_line: str, beacon_line: str) -> Sensor:
        sensor_x, sensor_y = self.coordinates(sensor_line)
        beacon_x, beacon_y = self.coordinates(beacon_line)
        cover_distance = self.cover_distance(sensor_x, sensor_y, beacon_x, beacon_y)
        sensor = Sensor(sensor_x, sensor_y, beacon_x, beacon_y, cover_distance)
        return sensor

    @staticmethod
    def coordinates(sensor_line: str) -> Tuple[int, int]:
        x_line, y_line = sensor_line.split(', ')
        x = int(x_line.removeprefix('x='))
        y = int(y_line.removeprefix('y='))
        return x, y

    @staticmethod
    def cover_distance(sensor_x: int, sensor_y: int, beacon_x: int, beacon_y: int) -> int:
        delta_x = abs(sensor_x - beacon_x)
        delta_y = abs(sensor_y - beacon_y)
        return delta_x + delta_y
