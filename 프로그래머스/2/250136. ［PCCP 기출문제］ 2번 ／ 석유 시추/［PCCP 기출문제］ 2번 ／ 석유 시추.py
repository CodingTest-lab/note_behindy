def find_cluster(land, start_row, start_col, visited):
    # 이미 방문한 클러스터라면 None 반환
    if (start_row, start_col) in visited:
        return None
    
    cluster = set()  # 현재 클러스터의 모든 좌표를 저장
    stack = [(start_row, start_col)]
    cluster.add((start_row, start_col))
    
    while stack:
        row, col = stack.pop()
        # 4방향 탐색
        for next_row, next_col in [
            (row-1, col), (row+1, col),
            (row, col-1), (row, col+1)
        ]:
            # 유효한 범위이고, 석유가 있고, 아직 클러스터에 포함되지 않은 지점이면
            if (0 <= next_row < len(land) and 
                0 <= next_col < len(land[0]) and 
                land[next_row][next_col] == 1 and 
                (next_row, next_col) not in cluster):
                stack.append((next_row, next_col))
                cluster.add((next_row, next_col))
    
    # 클러스터의 좌표들과 크기를 반환
    return cluster, len(cluster)

def solution(land):
    n, m = len(land), len(land[0])
    visited = set()  # 전체 방문 좌표
    oil_by_column = [0] * m  # 각 열의 석유량
    
    # 모든 지점을 순회
    for row in range(n):
        for col in range(m):
            # 석유가 있는 지점을 발견하면
            if land[row][col] == 1:
                # 클러스터 탐색 시작
                result = find_cluster(land, row, col, visited)
                
                # 이미 방문한 클러스터가 아니라면
                if result is not None:
                    cluster_coords, cluster_size = result
                    # 방문 집합에 클러스터 좌표들 추가
                    visited.update(cluster_coords)
                    
                    # 클러스터가 지나는 모든 열에 크기 기록
                    affected_cols = set(coord[1] for coord in cluster_coords)
                    for col_idx in affected_cols:
                        oil_by_column[col_idx] += cluster_size
    
    return max(oil_by_column)