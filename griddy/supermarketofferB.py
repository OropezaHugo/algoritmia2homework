def solve_market_offer(products):
    products = sorted(products, reverse=True)
    i = 2
    result = 0
    while i < len(products):
        result += products[i]
        i += 3

    return result


def offer_problem():
    test_cases = int(input())
    test_cases_records = []
    for test_case_record in range(test_cases):
        number_of_objects = int(input())
        product_list = [int(x) for x in (str(input()).split())]
        test_cases_records.append(product_list)

    for products_list in test_cases_records:
        print(solve_market_offer(products_list))


offer_problem()
