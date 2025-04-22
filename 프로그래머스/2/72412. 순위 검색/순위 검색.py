# 이진 탐색으로 특정 점수 이상인 지원자 수 찾기
def count_over_score(scores, target_score):
    if not scores:
        return 0

    left, right = 0, len(scores) - 1
    while left <= right:
        mid = (left + right) // 2
        if scores[mid] >= target_score:
            right = mid - 1
        else:
            left = mid + 1

    return len(scores) - left

def solution(info, query):
    # 지원자 정보를 분류해서 저장할 사전
    data = {}
    
    # 지원자 정보 파싱 및 저장
    for i in info:
        i_split = i.split()
        lang, job, exp, food, score = i_split[0], i_split[1], i_split[2], i_split[3], int(i_split[4])
        
        # 현재 지원자가 속할 수 있는 모든 조합 생성
        for lang_case in [lang, "-"]:
            for job_case in [job, "-"]:
                for exp_case in [exp, "-"]:
                    for food_case in [food, "-"]:
                        key = lang_case + job_case + exp_case + food_case
                        if key not in data:
                            data[key] = []
                        data[key].append(score)
    
    # 점수 리스트를 정렬 (이진 탐색을 위해)
    for key in data:
        data[key].sort()
    
    # 쿼리 처리
    answer = []
    for q in query:
        q_split = q.split(" and ")
        last_part = q_split[3].split()
        
        lang = q_split[0]
        job = q_split[1]
        exp = q_split[2]
        food = last_part[0]
        score = int(last_part[1])
        
        key = lang + job + exp + food
        
        # 이 키에 해당하는 지원자가 있는지 확인
        if key in data:
            answer.append(count_over_score(data[key], score))
        else:
            answer.append(0)
    
    return answer