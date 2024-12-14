def solution(emergency):
    # 응답 배열 초기화
    answer = [0] * len(emergency)
    
    # emergency의 각 요소에 대해서 자신을 제외한 요소들과 크기 비교를 수행하고 자신보다 작은 요소의 갯수들을 카운트
    # 순위로 활용할 예정이니까 초기값은 1
    for loop in range(len(emergency)):
        count = 1
        for index in range(len(emergency)):
            if emergency[loop] < emergency[index]:
                count += 1
        answer[loop] = count
    return answer