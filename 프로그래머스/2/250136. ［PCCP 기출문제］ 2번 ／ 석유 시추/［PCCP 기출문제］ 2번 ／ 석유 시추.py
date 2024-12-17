def find_cluster(land, start_row, start_col, visited):
    if (start_row, start_col) in visited:
        return None
    
    cluster = set()
    stack = [(start_row, start_col)]
    cluster.add((start_row, start_col))
    
    while stack:
        row, col = stack.pop()
        for next_row, next_col in [
            (row-1, col), (row+1, col),
            (row, col-1), (row, col+1)
        ]:
            if (0 <= next_row < len(land) and 
                0 <= next_col < len(land[0]) and 
                land[next_row][next_col] == 1 and 
                (next_row, next_col) not in cluster):
                stack.append((next_row, next_col))
                cluster.add((next_row, next_col))
    
    return cluster, len(cluster)

def solution(land):
    n, m = len(land), len(land[0])
    visited = set()
    oil_by_column = [0] * m
    
    for row in range(n):
        for col in range(m):
            if land[row][col] == 1:
                result = find_cluster(land, row, col, visited)
                
                if result is not None:
                    cluster_coords, cluster_size = result
                    visited.update(cluster_coords)
                    
                    affected_cols = set(coord[1] for coord in cluster_coords)
                    for col_idx in affected_cols:
                        oil_by_column[col_idx] += cluster_size
    
    return max(oil_by_column)
