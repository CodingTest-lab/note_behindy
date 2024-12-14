def solution(num_list):
    answer = [0] * len(num_list)
    for index in range (len(num_list)):
        answer[len(num_list)-1-index] = num_list[index]
    return answer