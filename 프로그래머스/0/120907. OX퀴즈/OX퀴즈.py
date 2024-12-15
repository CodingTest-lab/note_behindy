def solution(quiz):
    answer = []
    for i in range(len(quiz)):
        operator = quiz[i].split(' ')[1]
        result = int(quiz[i].split(' ')[4])
        if operator == '+':
            if result == int(quiz[i].split(' ')[0]) + int(quiz[i].split(' ')[2]):
                answer.append('O')
            else:
                answer.append('X')
        else:
            if result == int(quiz[i].split(' ')[0]) - int(quiz[i].split(' ')[2]):
                answer.append('O')
            else:
                answer.append('X')
    return answer