def solve_coco_souvenirs_problem(guests_happiness):
    souvenirs_handed = [1] * len(guests_happiness)
    if guests_happiness[0] < guests_happiness[1]:
        souvenirs_handed[0] += 1

    for i in range(1, len(guests_happiness) - 1):
        if guests_happiness[i] < guests_happiness[i - 1]:
            souvenirs_handed[i] += souvenirs_handed[i - 1]
        elif guests_happiness[i] < guests_happiness[i + 1]:
            souvenirs_handed[i] += souvenirs_handed[i + 1]
            j = i
            while guests_happiness[j] > guests_happiness[j - 1]:
                souvenirs_handed[j - 1] = 1 + souvenirs_handed[j]
                j -= 1
    if guests_happiness[-1] < guests_happiness[-2]:
        souvenirs_handed[-1] += souvenirs_handed[-2]

    return sum(souvenirs_handed)


def get_happiness():
    guests = int(input())
    guests_happiness_list = [int(x) for x in str(input()).split()]
    print(solve_coco_souvenirs_problem(guests_happiness_list))


get_happiness()

