'''
Date: 11/25/2020 20:52:31
LastEditTime: 11/25/2020 22:40:37
Description: Generate Markdown file with specific folder
'''

import os

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
        print('*' * 10 + topic + '*' * 10)
        # print(files)
        files.sort()
        # Todo: We need to let it sorted by problem number.
        # sorted(files, key=lambda file: int(file.split('/')[-1]))
        writer = MarkdownTableWriter()
        writer.table_name = topic
        writer.headers = ["No.", "Problem", "Solutions"]
        cols = []
        for file in files:
            # print(file)
            file_name = file.split('/')[-1]
            split = file_name.split('_')
            # print(split)
            try:
                number = split[1]
                name = split[-1].split('.')[0]
                suffix = split[-1].split('.')[-1]
                cols.append([number, name, '[' + suffix + '](' + file + ')'])
                # print('%s, %s' % (number, name))
            except IndexError as e:
                print('Error file is ', file_name)
        
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
    

if __name__ == "__main__":
    path = 'Leetcode/src/com/leetcode'
    file = 'README.md'
    if os.path.exists(file):
        os.remove(file)
    m = iter_all_files(path)
    generate_markdown(m)