# A Naive recursive Python implementation of LCS problem


def lcs(sequence_a, sequence_b, index_in_a, index_in_b):
    if index_in_a == 0 or index_in_b == 0:
        return 0
    elif sequence_a[index_in_a - 1] == sequence_b[index_in_b - 1]:
        return 1 + lcs(sequence_a, sequence_b, index_in_a - 1, index_in_b - 1)
    else:
        return max(lcs(sequence_a, sequence_b, index_in_a, index_in_b - 1),
                   lcs(sequence_a, sequence_b, index_in_a - 1, index_in_b))


# Driver code
def receive_sequences():
    value = ""
    pair_counter = 1
    sequences_pairs_list = [[]]
    while value != "-":
        if pair_counter < 3 and value != "":
            sequences_pairs_list[-1].append(value)
            pair_counter += 1
        elif value != "":
            sequences_pairs_list.append([value])
            pair_counter = 2

        value = input()

    for pair in sequences_pairs_list:
        print(lcs(pair[0], pair[1], len(pair[0]), len(pair[1])))


receive_sequences()


