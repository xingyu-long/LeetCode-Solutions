'''
Date: 08/10/2021 10:28:40
LastEditTime: 08/10/2021 16:12:06
Description: Push changes to git with useful comments automatically.
'''
import re
from subprocess import Popen, PIPE
from shlex import split
from datetime import datetime

from git import Repo


def get_git_repo(Repo, path="./"):
    repo = Repo(path)
    return repo


def get_changes_info():
    p1 = Popen(split("git status --porcelain"), stdout=PIPE)
    p2 = Popen(split("sed s/^...//"), stdin=p1.stdout,
               stdout=PIPE).communicate()[0]
    lines = p2.decode('utf-8').splitlines()
    return lines


def split_camel_case(s):
    return re.findall(r'[A-Z](?:[a-z]+|[A-Z]*(?=[A-Z]|$))', s)


def build_str(lines, hint='Changes were made in following file(s): '):
    time = datetime.today().strftime('%Y-%m-%d-%H:%M:%S ')
    combined = []
    for line in lines:
        print('current line is ' + line)
        file_name = line.split('/')[-1]
        split_line = file_name.split('_')
        try:
            number = split_line[1]
            if not number.isnumeric():
                continue
            name = split_camel_case(split_line[-1].split('.')[0])
            combined.append(number + "." + " ".join([x for x in name]))
        except IndexError as e:
            print('Error file is ', file_name)
    s = time + hint + ', '.join([i for i in combined])
    return s


def main():
    changes = get_changes_info()
    if len(changes) == 0:
        print('There is no change for your current repo!')
        return
    commit_str = build_str(changes)
    print(commit_str)
    repo = get_git_repo(Repo)
    repo.git.add('--all')
    repo.git.commit(m = commit_str)

    # push to remote
    origin = repo.remote()
    print(origin.push())


if __name__ == "__main__":
    main()
