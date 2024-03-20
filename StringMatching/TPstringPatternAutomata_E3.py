# we just add a higher_prefix var and a get_chars method
# as the method is O(n) and is used out of any cycle, it does not change
# the initial complexity of the algorithm of O(n)
def get_next_state(pattern, pattern_length, state, character):

    if state < pattern_length and character == pattern[state]:
        return state + 1
    i = 0

    for next_state in range(state, 0, -1):
        if pattern[next_state - 1] == character:
            while i < next_state - 1:
                if pattern[i] != pattern[state - next_state + 1 + i]:
                    break
                i += 1
            if i == next_state - 1:
                return next_state
    return 0


def compute_tf(pattern, pattern_length, my_chars):

    tf = [[0 for _ in range(len(my_chars))]
          for _ in range(pattern_length + 1)]

    for state in range(pattern_length + 1):
        for char in my_chars:
            z = get_next_state(pattern, pattern_length, state, char)
            tf[state][my_chars.index(char)] = z

    return tf


def get_chars_of(pattern):
    return list(set(pattern))


def search(pattern, text):
    pattern_length = len(pattern)
    text_length = len(text)
    my_chars = get_chars_of(pattern)
    tf = compute_tf(pattern, pattern_length, my_chars)
    state = 0
    higher_state = 0
    for i in range(text_length):
        state = tf[state][(my_chars.index(text[i]))]
        if state > higher_state:
            higher_state = state
    print(higher_state)


def inputs():
    (text, pattern) = str(input()).split()
    lengths = [int(x) for x in str(input()).split()]
    for i in range(1, len(lengths)):
        my_slice = text[:lengths[i]]
        search(pattern, my_slice)


inputs()
