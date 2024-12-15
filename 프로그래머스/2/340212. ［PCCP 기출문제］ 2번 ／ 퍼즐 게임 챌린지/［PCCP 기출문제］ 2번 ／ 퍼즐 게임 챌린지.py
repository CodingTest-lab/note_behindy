def checkLimit(diffs, times, limit, level):
    temp = 0
    for n in range(len(diffs)):
        if n == 0:
            temp = temp + times[0]
        else:
            if diffs[n] <= level:
                temp = temp + times[n]
            else:
                temp = temp + (diffs[n]-level)*times[n-1] +(diffs[n]-level)*times[n] + times[n]
        if limit < temp:
            return False
    return True

def solution(diffs, times, limit):
    level = 0
    
    lv_min = 1
    lv_max = max(diffs)
    
    answer = lv_max
    
    while lv_min <= lv_max:
        mid = (lv_min + lv_max) // 2
        
        if checkLimit(diffs, times, limit, mid):
            answer = mid
            lv_max = mid - 1
        else:
            lv_min = mid + 1
            
    return answer
