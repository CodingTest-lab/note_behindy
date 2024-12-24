# cap = 최대 적재량
# n = 집 수, deliveries, pickups의 최대길이와 일치
# deliveries -> 배달할 택배 배열
# pickups -> 수거할 상자 배열
def solution(cap, n, deliveries, pickups):
    answer = 0
    deliver = 0
    pickup = 0
    
    # 가장 먼 집부터 해결하기
    
    for i in range(n-1, -1, -1):
        deliver += deliveries[i]
        pickup += pickups[i]
        
        cnt = 0
        while deliver > 0 or pickup > 0:
            deliver -= cap
            pickup -= cap
            cnt += 1
        
        if cnt > 0:
            answer += (i + 1) * 2 * cnt
    
    return answer