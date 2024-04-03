def max_meals_to_eat_dp(meals_time_list, rest_minutes, index):
    if index < 0:
        return 0
    elif rest_minutes >= meals_time_list[index]:
        return max(1 + max_meals_to_eat_dp(meals_time_list, rest_minutes - meals_time_list[index], index - 1),
                   max_meals_to_eat_dp(meals_time_list, rest_minutes, index - 1))
    else:
        return max_meals_to_eat_dp(meals_time_list, rest_minutes, index - 1)


def entries():
    (meals_number, minutes_to_eat) = (int(x) for x in str(input()).split())
    minutes_per_meal = [int(x) for x in str(input()).split()]
    print(max_meals_to_eat_dp(minutes_per_meal, minutes_to_eat, meals_number - 1))


entries()
