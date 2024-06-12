"""

If a function is marked as isVariadic=true, then the last argument can occur one or more number of times.
Example:
FuncA: [String, Integer, Integer]; isVariadic = false 
FuncB: [String, Integer]; isVariadic = true
FuncC: [Integer]; isVariadic = true
FuncD: [Integer, Integer]; isVariadic = true
FuncE: [Integer, Integer, Integer]; isVariadic = false 
FuncF: [String]; isVariadic = false
FuncG: [Integer]; isVariadic = false 

findMatches({String}) -> [FuncF] 
findMatches({Integer}) -> [FuncC, FuncG]
"""

from typing import List


class Node:
    def __init__(self) -> None:
        self.children = dict()
        self.var_list = []
        self.not_var_list = []

    def __repr__(self) -> str:
        return f"Children={self.children}, var_list={self.var_list}, not_var_list={self.not_var_list}"


class Trie:
    def __init__(self) -> None:
        self.root = Node()

    def insert(self, function_name, arguments, is_variable):
        curr = self.root
        for arg in arguments:
            if arg not in curr.children:
                curr.children[arg] = Node()
            curr = curr.children[arg]
        if is_variable:
            curr.var_list.append(function_name)
        else:
            curr.not_var_list.append(function_name)

    def all_same(self, list_inputs, arg):
        for input in list_inputs:
            if input != arg:
                return False
        return True

    def match(self, arguments) -> List[str]:
        curr = self.root
        res = []
        for i in range(len(arguments)):
            arg = arguments[i]
            if not arg in curr.children:
                return res
            else:
                curr = curr.children[arg]
                if i != len(arguments) - 1 and len(curr.var_list) > 0:
                    if self.all_same(arguments[i:], arg):
                        res.extend(curr.var_list)
                if i == len(arguments) - 1:
                    if len(curr.var_list) > 0:
                        res.extend(curr.var_list)
                    if len(curr.not_var_list) > 0:
                        res.extend(curr.not_var_list)

        return res


t = Trie()
t.insert("FuncA", ["String", "Integer", "Integer"], False)
t.insert("FuncB", ["String", "Integer"], True)
t.insert("FuncC", ["Integer"], True)
t.insert("FuncD", ["Integer", "Integer"], True)
t.insert("FuncE", ["Integer", "Integer", "Integer"], False)
t.insert("FuncF", ["String"], False)
t.insert("FuncG", ["Integer"], False)

assert t.match(["String"]) == ["FuncF"]
assert t.match(["Integer"]) == ["FuncC", "FuncG"]
print(t.match(["Integer", "Integer", "Integer", "Integer"]))  # [FuncC, FuncD]
print(t.match(["Integer", "Integer", "Integer"]))  # [FuncC, FuncD, FuncE]
print(t.match(["String", "Integer", "Integer", "Integer"]))  # FuncB
print(t.match(["String", "Integer", "Integer"]))  # [FuncA,FuncB]
