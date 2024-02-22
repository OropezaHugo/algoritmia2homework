def ketchup_recursive_dp_top_down(prizes_list):
    n = len(prizes_list)
    if n >= 4:
        dp = max(prizes_list[0] + ketchup_dp_bottom_up(prizes_list[2:]), prizes_list[1] + ketchup_dp_bottom_up(prizes_list[3:]))
    elif n >= 3:
        dp = max(prizes_list[0] + ketchup_dp_bottom_up(prizes_list[2:]), prizes_list[1])
    elif n >= 2:
        dp = max(prizes_list[0], prizes_list[1])
    else:
        dp = prizes_list[0]

    return dp # dp variable save the total sum to send it to the top and resolve the problem


def ketchup_dp_bottom_up(prizes_list):
    dp = prizes_list[:] # here we save the initial values
    lenght_rock = len(prizes_list)
    # here we make the addition for every better option
    if lenght_rock > 3:
        dp[2] += dp[0]
        for index in range(3, len(dp)):
            dp[index] += max(dp[index - 2], dp[index - 3]) # here we save the addition on the respective value

    elif lenght_rock > 2:
        dp[2] += dp[0] # here we save the addition for the value

    return max(dp[-1], dp[-2]) # here we do the last comparasion to resolve the problem







def ketchup():
    prizes = int(input())
    prize_list = [int(number) for number in input().split()]
    print(ketchup_dp_bottom_up(prize_list))


# for this exercise the subproblem is decide if stay with a_1 or a_2 in every pair
# of possible next number,
ketchup()
