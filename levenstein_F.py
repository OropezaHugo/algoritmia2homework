# edit distance algorithm to count the min num of changes to get a word
# from another
def edit_distance(word_1, word_2, char_index_1, char_index_2):

    if char_index_1 == 0:
        return char_index_2
    if char_index_2 == 0:
        return char_index_1

    if word_1[char_index_1 - 1] == word_2[char_index_2 - 1]:
        return edit_distance(word_1, word_2, char_index_1 - 1, char_index_2 - 1)
    return 1 + min(edit_distance(word_1, word_2, char_index_1, char_index_2 - 1),  # Insert
                   edit_distance(word_1, word_2, char_index_1 - 1, char_index_2),  # Remove
                   edit_distance(word_1, word_2, char_index_1 - 1, char_index_2 - 1)  # Replace
                   )


def receive_words():
    quantity = int(input())
    pair_list = []
    for pair in range(quantity):
        word1 = input()
        word2 = input()
        pair_list.append([word1, word2])
    for pair in pair_list:
        print(edit_distance(pair[0], pair[1], len(pair[0]), len(pair[1])))


receive_words()
