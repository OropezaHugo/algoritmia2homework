def function(n, my_dp):
    if n < len(my_dp):
        return my_dp[n], my_dp

    else:
        i = len(my_dp)
        while i <= n:
            fibo_c = my_dp[-1] + my_dp[-2]
            my_dp.append(fibo_c)
            i += 1
        return my_dp[-1]

