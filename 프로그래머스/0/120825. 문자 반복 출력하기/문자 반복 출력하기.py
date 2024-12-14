def solution(my_string, n):
    answer = ''.join(char * n for char in my_string)
    return answer