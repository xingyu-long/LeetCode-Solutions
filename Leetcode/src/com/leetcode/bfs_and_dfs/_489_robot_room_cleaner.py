# """
# This is the robot's control interface.
# You should not implement it, or speculate about its implementation
# """
# class Robot:
#    def move(self):
#        """
#        Returns true if the cell in front is open and robot moves into the cell.
#        Returns false if the cell in front is blocked and robot stays in the current cell.
#        :rtype bool
#        """
#
#    def turnLeft(self):
#        """
#        Robot will stay in the same cell after calling turnLeft/turnRight.
#        Each turn will be 90 degrees.
#        :rtype void
#        """
#
#    def turnRight(self):
#        """
#        Robot will stay in the same cell after calling turnLeft/turnRight.
#        Each turn will be 90 degrees.
#        :rtype void
#        """
#
#    def clean(self):
#        """
#        Clean the current cell.
#        :rtype void
#        """


class Solution:
    def cleanRoom(self, robot):
        """
        :type robot: Robot
        :rtype: None
        """
        dirs = [[-1, 0], [0, 1], [1, 0], [0, -1]]
        visited = set()

        def go_back():
            robot.turnRight()
            robot.turnRight()
            robot.move()
            robot.turnRight()
            robot.turnRight()

        def dfs(x, y, curr_dir):
            visited.add((x, y))
            robot.clean()

            for i in range(4):
                next_move = (curr_dir + i) % 4
                new_x = x + dirs[next_move][0]
                new_y = y + dirs[next_move][1]
                if (new_x, new_y) not in visited and robot.move():
                    dfs(new_x, new_y, next_move)
                    go_back()

                robot.turnRight()

        dfs(0, 0, 0)
