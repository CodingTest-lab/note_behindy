def solution(num, total):
    answer = []
    # 가장 작은값!
    little = int((total/num)-(num/2)+0.5)
    for i in range(num):
        answer.append(little + i)
    return answer