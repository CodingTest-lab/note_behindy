def crash_check(path, t):
    positions = {}  # 각 위치별 로봇 수를 기록할 딕셔너리
    crash_count = 0
    
    # 각 로봇의 t시점 위치 확인
    for robot_idx in range(len(path)):
        # 해당 로봇이 아직 이동 중인 경우만 체크
        if t < len(path[robot_idx]):
            current_pos = tuple(path[robot_idx][t])
            
            if current_pos in positions:
                if positions[current_pos] == 1:
                    crash_count += 1
                positions[current_pos] += 1
            else:
                positions[current_pos] = 1
                
    return crash_count

def min_path(route, points):
    result = []
    
    # 시작점 기록
    result.append(points[route[0]-1])  # points 인덱스는 0부터, route의 포인트 번호는 1부터 시작
    
    # 각 경유 지점별로 경로 계산
    for i in range(len(route)-1):
        current = points[route[i]-1]
        next_point = points[route[i+1]-1]
        
        # 현재 위치에서 다음 위치까지 매 초마다의 좌표 계산
        while current != next_point:
            # r 좌표 먼저 이동
            if current[0] < next_point[0]:
                current = [current[0] + 1, current[1]]
            elif current[0] > next_point[0]:
                current = [current[0] - 1, current[1]]
            # r 좌표 이동이 끝났으면 c 좌표 이동
            elif current[1] < next_point[1]:
                current = [current[0], current[1] + 1]
            elif current[1] > next_point[1]:
                current = [current[0], current[1] - 1]
                
            result.append(current[:])
    return result
    
def solution(points, routes):
    answer = 0
    max_time = 0
    
    path = []
    for i in range(len(routes)):
        robot_path = min_path(routes[i], points)
        path.append(robot_path)
        max_time = max(max_time, len(robot_path))  
    for t in range(max_time):
        answer += crash_check(path, t)
    
    return answer
