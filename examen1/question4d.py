# this is a bottom up dp because we are going from the small problem to the big one iterating
def Fuchsia(gift_list):
    # we are using dp by saving the last number's min_number_of_moves for the las to be the output
    min_number_of_moves = [0] * len(gift_list)
    for i in range(len(gift_list) - 1):
        if gift_list[i] > gift_list[i + 1]:
            min_number_of_moves[i + 1] = min_number_of_moves[i] + 1
        else:
            min_number_of_moves[i + 1] = min_number_of_moves[i]
    return min_number_of_moves[-1]


def input_values():
    amount_of_gifts = int(input())
    gift_list = [int(x) for x in input().split()]

    print(Fuchsia(gift_list))


input_values()
