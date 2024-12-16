def check_end_time(attacks):
    return max(attack[0] for attack in attacks)

def check_hp(timeline, bandage, attacks, time):
    health_point = timeline[-1][0]  # 이전 상태의 HP
    bandage_chain = timeline[-1][2]  # 이전 상태의 연속 성공
    max_health_point = timeline[0][0]
    
    # 현재 시간에 공격이 있는지 확인
    attack_info = next((attack for attack in attacks if attack[0] == time), None)
    
    # hp 변화 시행하기
    if attack_info is None:
        return func_bandage(health_point, max_health_point, bandage, bandage_chain)
    else:
        return func_attack(health_point, attack_info, bandage_chain)

def func_bandage(health_point, max_health_point, bandage, bandage_chain):
    if bandage_chain == bandage[0] - 1:
        result_hp = health_point + bandage[1] + bandage[2]
        result_chain = 0
    else:
        result_hp = health_point + bandage[1]
        result_chain = bandage_chain + 1
    if result_hp > max_health_point :
        result_hp = max_health_point
    hp_change = result_hp - health_point
    return [result_hp, hp_change, result_chain]

def func_attack(health_point, attack, bandage_chain):
    result_hp = health_point - attack[1]
    return [result_hp, -attack[1], 0]

def solution(bandage, health, attacks):
    timeline = [[health, 0, 0]]
    end_time = check_end_time(attacks)
    
    for time in range(1, end_time + 1):
        timestamp = check_hp(timeline, bandage, attacks, time)
        if timestamp[0] <= 0:
            return -1
        timeline.append(timestamp)
    print(timeline)
    
    return timeline[-1][0]