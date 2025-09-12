from collections import defaultdict
from typing import List


class Solution:
    def findDuplicate(self, paths: List[str]) -> List[List[str]]:
        mp = defaultdict(list)
        for fpath in paths:
            dir_path = fpath.split(" ")[0]
            file_contents = fpath.split(" ")[1:]
            for file_content in file_contents:
                left_index = file_content.index("(")
                file_name = file_content[:left_index]
                content = file_content[left_index + 1 : -1]
                file_path = f"{dir_path}/{file_name}"
                mp[content].append(file_path)

        return [file_paths for _, file_paths in mp.items() if len(file_paths) > 1]
