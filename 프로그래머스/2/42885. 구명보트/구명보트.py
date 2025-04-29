def solution(people, limit):
    people.sort()  # 사람들의 몸무게를 오름차순으로 정렬
    boat_count = 0
    left = 0
    right = len(people) - 1
    
    while left <= right:
        # 가장 가벼운 사람과 가장 무거운 사람의 무게 합이 제한 이하인 경우
        if people[left] + people[right] <= limit:
            left += 1  # 가장 가벼운 사람도 태움
        
        # 무게 합이 제한을 초과하면 가장 무거운 사람만 태움
        right -= 1  # 가장 무거운 사람을 태움
        boat_count += 1  # 보트 개수 증가
    
    return boat_count