'''
yellow = row * col
brown = (row+2) * (col+2)
'''

def solution(brown, yellow):
    answer = [0,0]
    for row in range(1, int(yellow**0.5) + 1):
        # width가 yellow의 약수인지 확인
        if yellow % row == 0:
            col = yellow // row
            # 갈색 격자 수 확인
            if ((row + 2) * (col + 2)) == brown + yellow:
                # 가로 길이가 세로 길이보다 크거나 같아야 함
                answer[0] = max(row+ 2, col+2)
                answer[1] = min(row +2, col+2)
    return answer
