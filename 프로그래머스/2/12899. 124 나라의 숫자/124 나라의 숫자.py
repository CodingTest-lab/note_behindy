def solution(n):
    answer = ''
    
    while n > 0:
        remainder = n % 3
        n = n // 3
        if remainder == 0:
            answer = '4' + answer
            n -= 1
        elif remainder == 1:
            answer = '1' + answer
        elif remainder == 2:
            answer = '2' + answer
            
    return answer