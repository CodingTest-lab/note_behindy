def find_cluster(land, row, col, visited):
    if (row < 0 or row >= len(land) or 
        col < 0 or col >= len(land[0]) or 
        land[row][col] == 0 or 
        (row, col) in visited):
        return 0
    
    size = 1
    visited.add((row, col))
    affected_cols = {col}  # 영향을 받는 열들
    stack = [(row, col)]
    
    while stack:
        curr_row, curr_col = stack.pop()
        
        # 4방향 탐색을 한번에 처리
        for nr, nc in [(curr_row-1, curr_col), (curr_row+1, curr_col),
                       (curr_row, curr_col-1), (curr_row, curr_col+1)]:
            if (0 <= nr < len(land) and 0 <= nc < len(land[0]) and 
                land[nr][nc] == 1 and (nr, nc) not in visited):
                stack.append((nr, nc))
                visited.add((nr, nc))
                affected_cols.add(nc)
                size += 1
    
    return size, affected_cols

def solution(land):
    visited = set()
    column_sizes = [0] * len(land[0])
    
    for col in range(len(land[0])):
        for row in range(len(land)):
            if land[row][col] == 1 and (row, col) not in visited:
                size, affected_cols = find_cluster(land, row, col, visited)
                # 클러스터가 영향을 미치는 모든 열에 크기를 더함
                for ac in affected_cols:
                    column_sizes[ac] += size
    
    return max(column_sizes)