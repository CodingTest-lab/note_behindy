def solution(n):
    # 1의 개수 세기
    bin_count = bin(n).count('1')
    
    # n보다 큰 수에서 1의 개수가 같은 첫 번째 수 찾기
    next_num = n + 1
    while bin(next_num).count('1') != bin_count:
        next_num += 1
        
    return next_num