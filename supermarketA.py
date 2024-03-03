
# Here we add the knapsack's results for every person of the group
# get the max value of a bunch of objects given its value and weight by
# the max backpack weight
def super_sale_solve(weight_list, value_list, people_capacity_list):
    result = 0
    for person_capacity in people_capacity_list:
        result += max_value_knapsack_algorithm(weight_list, value_list, len(weight_list) - 1, person_capacity)
    return result


# here we use the knapsack_no_repetition_algorithm adapted to the weight-prize problem
def max_value_knapsack_algorithm(weight_list, value_list, index, person_max_weight):

    if index < 0:
        return 0
    elif weight_list[index] <= person_max_weight:
        return max(max_value_knapsack_algorithm(weight_list, value_list, index - 1, person_max_weight),
                   max_value_knapsack_algorithm(weight_list, value_list,
                                                index - 1, person_max_weight - weight_list[index])
                   + value_list[index])
    return max_value_knapsack_algorithm(weight_list, value_list, index - 1, person_max_weight)


# here we enter the data correctly
def super_sale():

    test_cases = int(input())
    test_cases_records = []
    for test_case_record in range(test_cases):
        number_of_objects = int(input())
        weight_list = []
        value_list = []
        for market_object in range(number_of_objects):
            price_weight_tuple = [int(number) for number in input().split()]
            weight_list.append(price_weight_tuple[1])
            value_list.append(price_weight_tuple[0])
        people = int(input())
        people_capacity_list = []
        for person in range(people):
            people_capacity_list.append(int(input()))
        test_cases_records.append([weight_list, value_list, people_capacity_list])

    for test_case_record in test_cases_records:
        weight_list, value_list, people_capacity_list = test_case_record
        print(super_sale_solve(weight_list, value_list, people_capacity_list))


super_sale()


