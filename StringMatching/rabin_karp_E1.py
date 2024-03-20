# here we didn't need to modify anything to the algorithm which leave it
# on its initial time complexity of rabin-karp O(mn)
def search(pattern_string, text_string, prime_number_for_hash):
    number_of_alphabet_characters = 256
    pattern_length = len(pattern_string)
    text_length = len(text_string)
    j = 0
    pattern_hash_value = 0
    text_hash_value = 0
    h = 1

    for i in range(pattern_length - 1):
        h *= number_of_alphabet_characters % prime_number_for_hash

    for i in range(pattern_length):
        pattern_hash_value = ((number_of_alphabet_characters * pattern_hash_value + ord(pattern_string[i]))
                              % prime_number_for_hash)
        text_hash_value = ((number_of_alphabet_characters * text_hash_value + ord(text_string[i]))
                           % prime_number_for_hash)

    for i in range(text_length - pattern_length + 1):
        if pattern_hash_value == text_hash_value:
            for j in range(pattern_length):
                if text_string[i + j] != pattern_string[j]:
                    break
                else:
                    j += 1

            if j == pattern_length:
                print("Pattern found at index " + str(i))

        if i < text_length - pattern_length:
            text_hash_value = (number_of_alphabet_characters * (text_hash_value - ord(text_string[i]) * h) + ord(text_string[i + pattern_length])) % prime_number_for_hash

            if text_hash_value < 0:
                text_hash_value = text_hash_value + prime_number_for_hash


def inputs():
    text = str(input())
    pattern = str(input())
    prime_number = 61
    search(pattern, text, prime_number)


inputs()
