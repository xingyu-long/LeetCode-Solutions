class Solution:
    def isNumber(self, s: str) -> bool:
        s = s.strip()
        number_seen = dot_seen = e_seen = False
        # set it as true first
        # for the case we only have number, we need this to be True to pass
        # "0e"
        number_after_e = True
        for i, ch in enumerate(s):
            if ord("0") <= ord(ch) <= ord("9"):
                number_seen = True
                number_after_e = True
            elif ch == ".":
                # "6e6.5", ".."
                if e_seen or dot_seen:
                    return False
                dot_seen = True
            elif ch.lower() == "e":
                # "ee", "e9"
                if e_seen or not number_seen:
                    return False
                e_seen = True
                number_after_e = False
            elif ch == "+" or ch == "-":
                # sign can only be first char or right after the "e"
                # "[other chars]e+"
                if i != 0 and s[i - 1].lower() != "e":
                    return False
            else:
                # other chars
                # e.g.: "abc"
                return False

        return number_seen and number_after_e


"""
"0e"    -> num_after_e
"6e6.5" -> dot_seen or e_seen => return False
"""
