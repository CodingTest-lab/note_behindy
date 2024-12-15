def solution(quiz):
    return ['O' if eval(eq) == int(result) else 'X' for eq, result in [q.split('=') for q in quiz] ]