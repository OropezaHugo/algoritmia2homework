# here we adapt an iterative form of the long_increasing_subsequence algorithm
def lis(array):
    array_length = len(array)
    aux_array = array_length*[1]
    for i in range(array_length):
        for j in range(i):
            if array[j][1] > array[i][0]:

                aux_array[i] = max(aux_array[i], aux_array[j] + 1)

    return max(aux_array)


# here we receive the problem data and print it as required
def receive_data():
    weight_strength = [1, 0]
    weight_strength_turtle_list = []
    while weight_strength != [0]:
        weight_strength_turtle_list.append(weight_strength)
        weight_strength = [int(number) for number in input().split()]

    weight_strength_turtle_list.pop(0)
    result = lis(weight_strength_turtle_list)
    print(result)


receive_data()

# bottom up, del problema pequeño al grande (iterativo)
# top down del problema grande al pequeño (recursivo)
