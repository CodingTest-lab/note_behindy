'''
    # 대문자 A의 아스키코드 확인
    print(ord('A'))  # 65 출력

    # 아스키코드 90의 문자 확인
    print(chr(90))   # Z 출력
'''
def solution(name):
    # 알파벳 변경 횟수 계산
    change_count = [min(ord(c) - ord('A'), ord('Z') - ord(c) + 1) for c in name]
    total_change = sum(change_count)
    
    # 좌우 이동 최소 횟수 (최악의 경우 길이-1)
    n = len(name)
    min_move = n - 1
    
    # 각 위치별로 최적의 이동 경로 탐색
    for i in range(n):
        # 현재 위치 이후 연속된 A 찾기
        next_i = i + 1
        while next_i < n and name[next_i] == 'A':
            next_i += 1
        
        # 기본 경로: 0 -> i -> 0 -> next_i 이후
        # i까지 갔다가 되돌아와서 끝에서부터 next_i까지 가는 경우
        move = i * 2 + (n - next_i)
        # 끝에서부터 next_i까지 갔다가 되돌아와서 i까지 가는 경우
        move = min(move, (n - next_i) * 2 + i)
        
        min_move = min(min_move, move)
    
    return total_change + min_move