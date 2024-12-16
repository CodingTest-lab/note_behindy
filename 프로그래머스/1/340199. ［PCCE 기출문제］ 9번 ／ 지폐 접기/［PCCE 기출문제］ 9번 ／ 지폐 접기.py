def solution(wallet, bill):
    answer = 0
    bill = sorted(bill)
    
    while min(wallet) < min(bill) or max(wallet) < max(bill):
        bill = [min(bill), int(max(bill)/2)]
        answer += 1
        
    return answer