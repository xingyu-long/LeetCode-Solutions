# The rand7() API is already defined for you.
# def rand7():
# @return a random integer in the range 1 to 7


def rand7() -> int: ...


class Solution:
    """
    Example 1:

    Implement rand4() using rand2():

    2 * (rand2() - 1) + (rand2() - 1):

           1               1              => 0
           1               2              => 1
           2               1              => 2
           2               2              => 3

    so the answer would be `2 * (rand2() - 1) + rand2()` (we added 1 here to let the number become from 1 to 4 inclusive).

    the multiplier (i.e., 2 from above example) would prevent simple sum which may cause non-uniform distribution, for example:

    (rand2() - 1) + (rand2() - 1)
         1              1                 => 0
         1              2                 => 1
         2              1                 => 1
         2              2                 => 2

    so for probility(1) = 1/2, which is wrong!!!


    Example 2:
    Implement rand9() using rand7()

    7 * (rand7-1) + (rand7-1) => [0, 48] => rand49

    for (x) % 9 + 1 to return [0, 9]
    we have the following number to belong
    0, 1, 2, 3, 4 .... 8 (9 numbers)
    9 ................ 17
    19 ............... 26
    28 ............... 35
    37 ............... 44

    so we only allow [0, 44], hence the code would be like

    public int rand9() {
      int result = 45;
      while (result >= 45) {result = 7 * (rand7() - 1) + (rand7() - 1);}
      return result % 9 + 1;
    }
    """

    def rand10(self):
        """
        :rtype: int
        """

        total = 40
        # [0, 48] -> rand49
        while total >= 40:
            # exclude from 40 to 48, so that [0, 9], [10, 19], [20, 29], [30, 39] can be uniform distributed.
            total = 7 * (rand7() - 1) + (rand7() - 1)
        return total % 10 + 1
