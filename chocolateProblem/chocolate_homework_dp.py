def dp_implementation_for_max_chocolate_peaces(length):
    peaces = []
    i = 1
    while sum(peaces) + i <= length and i not in peaces:
        peaces.append(i)
        i += 1
    return len(peaces)


def entry_length():
    length = int(input())
    print(dp_implementation_for_max_chocolate_peaces(length))


entry_length()
