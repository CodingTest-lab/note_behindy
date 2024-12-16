def check(size, park):
    for horizontal in range(len(park) - size + 1):
        for vertical in range(len(park[0]) - size + 1):
            is_possible = True
            # 현재 위치에서 size x size 영역 검사
            for i in range(size):
                for j in range(size):
                    if park[horizontal + i][vertical + j] != "-1":
                        is_possible = False
                        break
                if not is_possible:
                    break
            if is_possible:
                return True
    return False
                    
def solution(mats, park):
    answer = -1
    for mat in sorted(mats, reverse=True):
        if check(mat, park):
            answer = mat
            break
    return answer