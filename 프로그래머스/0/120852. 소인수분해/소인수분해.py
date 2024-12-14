def solution(n):
    answer = []
    # 2~ n 까지만 반복
    for i in range(2, n+1):
        if n % i == 0:
            # 인수는 제거하기!
            i_am_insu = False
            for j in answer:
                if i % j == 0:
                    i_am_insu = True
                    break
            if not(i_am_insu) : answer.append(i)
    return answer