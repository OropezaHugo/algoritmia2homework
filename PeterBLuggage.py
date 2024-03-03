# here we evaluate the result to solve if it is possible or not
def solve_luggage(luggage_list, car_weight):
    if max_value_knapsack_algorithm(luggage_list, len(luggage_list) - 1, car_weight) == car_weight:
        return "YES"
    return "NO"


# here we use the knapsack_no_repetition_algorithm adapted to the luggage problem
def max_value_knapsack_algorithm(luggage_list, index, car_max_weight):
    if index < 0:
        return 0
    elif luggage_list[index] <= car_max_weight:
        return max(max_value_knapsack_algorithm(luggage_list, index - 1, car_max_weight),
                   max_value_knapsack_algorithm(luggage_list, index - 1, car_max_weight - luggage_list[index])
                   + luggage_list[index])
    return max_value_knapsack_algorithm(luggage_list, index - 1, car_max_weight)


# here we enter the data correctly
def peter():
    rows = int(input())
    luggage_list = []
    for row in range(rows):
        luggage_list += [[int(number) for number in input().split()]]
    for luggages in luggage_list:
        if sum(luggages) % 2 == 0:
            print(solve_luggage(luggages, sum(luggages) / 2))
        else:
            print("NO")


peter()
