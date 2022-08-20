'''
Date: 08/19/2022 10:32:23
LastEditTime: 08/19/2022 10:32:24
Description: Enum, dict
'''
from enum import Enum


class CarSize(Enum):
    BIG = 1
    MEDIUM = 2
    SMALL = 3
    
class ParkingSystem:

    def __init__(self, big: int, medium: int, small: int):
        self.d = dict()
        self.d[CarSize.BIG] = big
        self.d[CarSize.MEDIUM] = medium
        self.d[CarSize.SMALL] = small

    def addCar(self, carType: int) -> bool:
        if self.d[CarSize(carType)] > 0:
            self.d[CarSize(carType)] -= 1
            return True
        return False


# Your ParkingSystem object will be instantiated and called as such:
# obj = ParkingSystem(big, medium, small)
# param_1 = obj.addCar(carType)