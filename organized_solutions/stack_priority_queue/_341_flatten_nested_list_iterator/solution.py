# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger:
#    def isInteger(self) -> bool:
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        """
#
#    def getInteger(self) -> int:
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        """
#
#    def getList(self) -> [NestedInteger]:
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        """


from typing import Generator
from leetcode.common.py_utils import NestedInteger


class NestedIterator:
    # lazy loading?
    def __init__(self, nestedList: list[NestedInteger]):
        self._generator = self._int_generator(nestedList)
        self._peeked = None

    def _int_generator(self, nested_list) -> "Generator[int]":
        for nested in nested_list:
            if nested.isInteger():
                yield nested.getInteger()
            else:
                yield from self._int_generator(nested.getList())

    def next(self) -> int:
        if not self.hasNext():
            return None
        res = self._peeked
        self._peeked = None
        return res

    def hasNext(self) -> bool:
        if self._peeked != None:
            return True
        try:
            self._peeked = next(self._generator)
            return True
        except:
            return False
