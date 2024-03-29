'''
Date: 08/28/2022 13:38:35
LastEditTime: 08/28/2022 13:42:10
Description: OOD, Polymorphism
'''
from abc import ABC, abstractmethod
from collections import deque
from typing import List

"""
This is the interface for the expression tree Node.
You should not remove it, and you can define some classes to implement it.
"""


class Node(ABC):
    @abstractmethod
    # define your fields here
    def evaluate(self) -> int:
        pass


class BinaryNode(Node):
    """Base class for binary nodes"""

    def __init__(self, _left, _right):
        self.left = _left
        self.right = _right

    def evaluate(self) -> int:
        pass


class Add(BinaryNode):
    def evaluate(self) -> int:
        return self.left.evaluate() + self.right.evaluate()


class Subtract(BinaryNode):
    def evaluate(self) -> int:
        return self.left.evaluate() - self.right.evaluate()


class Multiply(BinaryNode):
    def evaluate(self) -> int:
        return self.left.evaluate() * self.right.evaluate()


class Divide(BinaryNode):
    def evaluate(self) -> int:
        return self.left.evaluate() // self.right.evaluate()


class Number(Node):
    def __init__(self, _value):
        self.value = _value

    def evaluate(self) -> int:
        return self.value


"""    
This is the TreeBuilder class.
You can treat it as the driver code that takes the postinfix input
and returns the expression tree represnting it as a Node.
"""

"""
Solution:
对于利用interface来设计的情况，我们需要创建不同
操作的实际实现，例如Add函数，然后利用多态这样就可以
访问到左右两个子树的值进行计算
"""
class TreeBuilder(object):
    def buildTree(self, postfix: List[str]) -> 'Node':
        operators = {'+': Add, '-': Subtract, '*': Multiply, '/': Divide}
        stack = deque()
        for token in postfix:
            if token in operators:
                second = stack.pop()
                first = stack.pop()
                stack.append(operators[token](first, second))
            else:
                stack.append(Number(int(token)))
        return stack[0]


"""
Your TreeBuilder object will be instantiated and called as such:
obj = TreeBuilder();
expTree = obj.buildTree(postfix);
ans = expTree.evaluate();
"""
