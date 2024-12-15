def solution(quiz):
    answer = []
    for i in range(len(quiz)):
        left = int(quiz[i].split(' ')[0])
        operator = quiz[i].split(' ')[1]
        right = int(quiz[i].split(' ')[2])
        result = int(quiz[i].split(' ')[4])
        if operator == '+':
            if result == left + right:
                answer.append('O')
            else:
                answer.append('X')
        else:
            if result == left - right:
                answer.append('O')
            else:
                answer.append('X')
    return answer