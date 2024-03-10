def evaluate_situation(dragons_list, knights_list):
    if len(knights_list) < len(dragons_list):
        return -1
    dragons_list = sorted(dragons_list)
    knights_list = sorted(knights_list)
    knight_index = 0
    result = 0
    while len(knights_list) - knight_index + 1 >= len(dragons_list) > 0:
        knight_price = knights_list[knight_index]
        if knight_price >= dragons_list[0]:
            result += knight_price
            knight_index += 1
            dragons_list.pop(0)
        else:
            knight_index += 1
    if len(dragons_list) > 0:
        return -1
    else:
        return result


def build_army():
    encounter_results = []
    while True:
        (dragons, knights) = (int(x) for x in str(input()).split())
        if dragons <= 0 and knights <= 0:
            break
        dragons_list = []
        knights_list = []
        for i in range(dragons):
            dragons_list.append(int(input()))

        for i in range(knights):
            knights_list.append(int(input()))
        coins_wasted = evaluate_situation(dragons_list, knights_list)
        if coins_wasted >= 0:
            encounter_results.append(coins_wasted)
        else:
            encounter_results.append("Loowater is doomed!")
    for result in encounter_results:
        print(result)


build_army()

# the griddy choice is which knight fight which dragon
# the optimal substructure is sort the lists of knights and dragons
# this greedy problem solves by sorting both lists and iterating to find the lowest cost in knights
# this code is O(n) by iterating the knights list


