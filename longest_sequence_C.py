# here we adapt an iterative form of the long_increasing_subsequence algorithm
def lis(array):
    array_length = len(array)
    aux_array = array_length*[1]
    for i in range(array_length):
        for j in range(i):
            if array[j] < array[i]:
                aux_array[i] = max(aux_array[i], aux_array[j] + 1)

    ans = aux_array[0]
    sequence_array = [array[-1]]
    for i in range(1, array_length):
        ans = max(ans, aux_array[i])
    for i in range(array_length - 1, -1, -1):
        if aux_array[i] < ans:
            sequence_array.append(array[i])
            ans -= 1
    return len(sequence_array), sequence_array[::-1]


# here we receive the problem data and print it as required
def receive_data():
    value = "1"
    numbers_list = []
    while value:
        try:
            numbers_list.append(int(value))
        except ValueError:
            break
        value = input()

    numbers_list.pop(0)
    result = lis(numbers_list)
    print(result[0])
    print("-")
    for num in result[1]:
        print(num)


receive_data()
