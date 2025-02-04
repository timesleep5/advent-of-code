from dataclasses import dataclass


@dataclass(frozen=True)
class YCoverage:
    from_x: int
    to_x: int

    def mergable(self, cov: 'YCoverage') -> bool:
        return (self.from_x <= cov.from_x <= self.to_x
                or self.from_x <= cov.to_x <= self.to_x)

    def merge(self, cov: 'YCoverage') -> 'YCoverage':
        from_x = min(self.from_x, cov.from_x)
        to_x = max(self.to_x, cov.to_x)
        return YCoverage(from_x, to_x)

    def covered_positions(self) -> int:
        return self.to_x - self.from_x + 1


class Sensor:
    def __init__(self, x: int, y: int, beacon_x, beacon_y, cover_distance: int):
        self.x = x
        self.y = y
        self.beacon_x = beacon_x
        self.beacon_y = beacon_y
        self.cover_distance = cover_distance

    def covers_y(self, y: int) -> bool:
        min_y = self.y - self.cover_distance
        max_y = self.y + self.cover_distance
        return min_y <= y <= max_y

    def get_y_coverage_of(self, y: int) -> YCoverage:
        y_way = abs(y - self.y)
        x_way = self.cover_distance - y_way
        from_x = self.x - x_way
        to_x = self.x + x_way
        return YCoverage(from_x, to_x)
