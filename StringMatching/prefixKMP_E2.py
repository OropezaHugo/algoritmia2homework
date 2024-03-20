# here we just add a dictionary to save the longest suffix
# which adds a cycle and 2 conditionals, but they do not affect
# significantly the big O of the Knuth-Morris-Pratt algorithm O(n)
def kmp_search(pat, txt):
    pattern_length = len(pat)
    text_length = len(txt)
    pattern_quantity = {}
    longest_proper_prefix = [0] * pattern_length
    longest_proper_prefix = compute_lps_array(pat, pattern_length, longest_proper_prefix)

    j = 0
    i = 0
    while (text_length - i) >= (pattern_length - j):
        if pat[j] == txt[i]:
            i += 1
            j += 1

        if j == pattern_length:
            if get_pattern_at(j, pat) in pattern_quantity:
                pattern_quantity[get_pattern_at(j, pat)] += 1
            else:
                pattern_quantity[get_pattern_at(j, pat)] = 1
            j = longest_proper_prefix[j - 1]
        elif i < text_length and pat[j] != txt[i]:
            if get_pattern_at(j, pat) in pattern_quantity:
                pattern_quantity[get_pattern_at(j, pat)] += 1
            else:
                pattern_quantity[get_pattern_at(j, pat)] = 1
            if j != 0:
                j = longest_proper_prefix[j - 1]
            else:
                i += 1
    max_prefix_quantity = ("", -1)
    for pattern, quantity in pattern_quantity.items():
        if len(max_prefix_quantity[0]) < len(pattern):
            max_prefix_quantity = (pattern, quantity)
    print(max_prefix_quantity[0] + " - " + str(max_prefix_quantity[1]))


def get_pattern_at(length, string):
    pattern = ""
    for i in range(length):
        pattern += string[i]
    return pattern


def compute_lps_array(pat, pattern_length, lps):
    length = 0

    lps[0] = 0
    i = 1

    while i < pattern_length:
        if pat[i] == pat[length]:
            length += 1
            lps[i] = length
            i += 1
        else:
            if length != 0:
                length = lps[length - 1]
            else:
                lps[i] = 0
                i += 1
    return lps


def inputs():
    text = str(input())
    pattern = str(input())
    kmp_search(pattern, text)


inputs()
