def solution(n):
    # 삼각형을 표현할 2차원 배열 초기화 (모두 0으로)
    triangle = [[0] * n for _ in range(n)]
    
    # 방향: 아래(/), 오른쪽(_), 왼쪽 위 대각선(\)
    directions = [(1, 0), (0, 1), (-1, -1)]
    
    # 시작 위치와 방향 설정
    x, y = -1, 0  # 첫 이동에서 아래로 이동하면 (0,0)이 됨
    direction = 0  # 처음 방향은 아래쪽
    
    # 채워넣을 숫자
    num = 1
    
    # 총 채워야 할 칸 수 계산 (삼각형 면적)
    length = n * (n + 1) // 2
    
    # 달팽이 채우기 시작
    while num <= length:
        # 현재 방향으로 이동
        dx, dy = directions[direction]
        nx, ny = x + dx, y + dy
        
        # 유효한 위치인지 확인 & 아직 채워지지 않은 칸인지 확인
        if 0 <= nx < n and 0 <= ny <= nx and triangle[nx][ny] == 0:
            triangle[nx][ny] = num
            num += 1
            x, y = nx, ny
        else:
            # 방향 전환
            direction = (direction + 1) % 3
    
    result = []
    
    for i in range(n):
        for j in range(i + 1):
            result.append(triangle[i][j])
    
    return result