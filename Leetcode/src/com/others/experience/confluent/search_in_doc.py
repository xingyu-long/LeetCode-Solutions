"""
given a list of doc id and text,
(1) return the doc id of given word
(2 ) return the doc id of given phrase

E.g.
[[1, confluent is a cloud computing company], [2, seattle is the new hub for cloud computing]]

search(cloud) => returns [1, 2]
search(confluent is a) => returns 1
search(cloud computing) => returns [1, 2]
"""

from collections import defaultdict
from typing import Dict


def build_index(sentences):
    # {word: {doc_id: set{position}}}
    index = defaultdict(lambda: defaultdict(set))
    for doc_id, sentence in sentences:
        word_list = sentence.split(" ")
        for pos, word in enumerate(word_list):
            index[word][doc_id].add(pos)

    return index


def search_index(index: Dict[str, Dict[str, set]], phrase: str):
    word_list = phrase.split(" ")
    res = []
    if not word_list:
        return res
    if len(word_list) == 1:
        return list(index[word_list[0]].keys())
    docs = index[word_list[0]]
    for doc_id in docs.keys():
        pos = docs[doc_id]
        doc_found = True
        for p in pos:
            count = 0
            for i in range(1, len(word_list)):
                if doc_id not in index[word_list[i]]:
                    doc_found = False
                    break
                if (p + i) not in index[word_list[i]][doc_id]:
                    break

                count += 1

            if not doc_found:
                break

            if count == len(word_list) - 1:
                res.append(doc_id)
                break
    return res


# example
sentences = [
    [1, "confluent is a cloud computing company"],
    [2, "seattle is the new hub for cloud computing"],
]

index = build_index(sentences)
print(f"index={index}")
print(search_index(index, ""))
print(search_index(index, "cloud computing"))
print(search_index(index, "new for"))
