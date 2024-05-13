class Solution:
    def decodeString(self, s: str) -> str:
        idx, num = 0, 0
        count_stack, s_stack = [], []
        curr = ""
        while idx < len(s):
            ch = s[idx]
            if ch.isdigit():
                while idx < len(s) and s[idx].isdigit():
                    num = num * 10 + int(s[idx])
                    idx += 1
                count_stack.append(num)
                num = 0
            elif ch == "[":
                s_stack.append(curr)
                curr = ""
                idx += 1
            elif ch == "]":
                # get the string whatever before "["
                # 3[a][...], then the prefix for the second "[" would be "aaa"
                prefix = s_stack.pop()
                times = count_stack.pop()
                for _ in range(times):
                    prefix += curr
                curr = prefix
                idx += 1
            else:
                # letters
                curr += ch
                idx += 1

        return curr
