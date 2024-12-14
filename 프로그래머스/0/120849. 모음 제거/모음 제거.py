def solution(my_string):
    moum = ['a','e','i','o','u']
    return ''.join(char for char in my_string if char not in moum)