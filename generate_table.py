'''
Date: 11/25/2020 20:52:31
LastEditTime: 11/26/2020 11:04:47
Description: Generate Markdown file with specific folder
'''

import os
import re
from operator import itemgetter

from tqdm import tqdm
from pytablewriter import MarkdownTableWriter


def iter_all_files(path):
    topic_to_files = {}
    for root, dirs, files in os.walk(path, topdown=True):
        for name in files:
            if not name.endswith('.py') and not name.endswith('.java'):
                continue
            topic = root.split('/')[-1]
            topic_to_files.setdefault(topic, [])
            topic_to_files[topic].append(os.path.join(root, name))

    return topic_to_files


def generate_markdown(topic_to_files, markdown_file_name='README.md'):
    markdown_table = []
    for topic, files in topic_to_files.items():
        print('*' * 20)
        print('We are processing topic ' + topic)
        print('*' * 20)

        writer = MarkdownTableWriter()
        writer.table_name = topic.capitalize()
        writer.headers = ["No.", "Problem", "Solutions"]
        cols = []
        prev_number = None
        for i in tqdm(range(len(files))):
            # print(file)
            file = files[i]
            file_name = file.split('/')[-1]
            split = file_name.split('_')
            # print(split)
            try:
                number = split[1]
                if not number.isnumeric():
                    continue
                name = split_camel_case(split[-1].split('.')[0])
                suffix = split[-1].split('.')[-1]
                # Todo: How to extend more than two programming languages?
                if prev_number and prev_number == number:
                    cols[-1][-1] = cols[-1][-1] + \
                        ', [' + suffix + '](' + file + ')'
                    continue

                cols.append(
                    [int(number), ' '.join(name), '[' + suffix + '](' + file + ')'])
                # print('%s, %s' % (number, name))
                prev_number = number
            except IndexError as e:
                print('Error file is ', file_name)

        cols = sorted(cols, key=itemgetter(0))
        # There doesn't exist any files.
        if not cols:
            print('Empty folder is ', files)
            continue
        writer.value_matrix = cols
        markdown_table.append(writer.dumps())
        # How to combine?

    # print(markdown_table)
    for table in markdown_table:
        with open(markdown_file_name, 'a') as f:
            f.write(table)


def split_camel_case(str):
    return re.findall(r'[A-Z](?:[a-z]+|[A-Z]*(?=[A-Z]|$))', str)


if __name__ == "__main__":
    path = 'Leetcode/src/com/leetcode'
    file_name = 'README.md'
    if os.path.exists(file_name):
        os.remove(file_name)
    m = iter_all_files(path)
    generate_markdown(m, file_name)
