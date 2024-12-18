# 00:00:00 부터 제시된 시점까지의 시간을 구하는 매서드
def get_second(h, m, s):
    return h * 3600 + m * 60 + s

# 00:00:00 부터 제시된 시점까지 알람이 몇번 울리는지 구하는 매서드
def alarm_count(h, m, s):
    # 시침이 한바퀴 도는( 12시간, 720분, 43200초) 동안 1426회 만남
    # 위 사실에 기반하면, 단순 비례를 통해서 계산하는것 또한 가능
    
    # 00:00:00 에 세개의 침이 동시에 출발하면서 알람이 한번 울리고 출발
    # 변수 초기화, 시간의 경우에는 12시간 단위로 변환
    
    # 제시된 초 까지 총 몇초가 흘렀는지 계산하는 매서드
    total_second = get_second(h, m, s)
    
    h_s_count = hour_second_count(h, m, s)
    m_s_count = minute_second_count(h, m, s)
    
    return (h_s_count + m_s_count)

# 00:00:00 부터 제시된 시점까지 hour - second 시침 초침이 만나는경우
def hour_second_count(h, m, s):
    # 시침 / 초침의 속도가 각각 일정하므로, 일정한 시간마다 다시 만나게 됨
    # 주기 = 360/(6-(1/120))
    total_second = get_second(h,m,s)
    
    count_return = int(total_second * (6 - (1/120)) /360) #+ tip
    return count_return

# 00:00:00 부터 제시된 시점까지 minute - second 분침 초침이 만나는경우
def minute_second_count(h, m, s):
    # 분침 / 초침의 속도가 각각 일정하므로, 일정한 시간마다 다시 만나게 됨
    # 주기 = 360/(6-(1/10))
    total_second = get_second(h,m,s)
    
    count_return = int(total_second * (6 - (1/10)) /360) # + tip
    return count_return

# 00:00:00 부터 각 시점까지 알람이 울린 횟수의 차를 구함
def solution(h1, m1, s1, h2, m2, s2):
    result = alarm_count(h2, m2, s2) - alarm_count(h1, m1, s1)
    
    # 시작 시간이 정각인 경우
    if s1 == 0 and m1 == 0:
        if h1 == 12:  # 정확히 12시 정각
            pass
        elif h1 == 0:  # 정확히 0시 정각
            result += 1
        else:  # 다른 정각 시간
            result += 1
    
    # 종료 시간이 정각인 경우
    if s2 == 0 and m2 == 0:
        if h2 % 12 == 0:  # 12시 또는 0시 정각
            if not (s1 == 0 and m1 == 0 and h1 % 12 == 0):
                result += 1
        else:  # 다른 정각 시간
            result += 1
    
    # 12시를 걸치는 경우 보정
    if get_second(h1,m1,s1) < get_second(12,0,0) and get_second(h2,m2,s2) >= get_second(12,0,0):
        result -= 1
    if get_second(h2,m2,s2) == get_second(23,0, 0):
        result -= 1
        
    return result