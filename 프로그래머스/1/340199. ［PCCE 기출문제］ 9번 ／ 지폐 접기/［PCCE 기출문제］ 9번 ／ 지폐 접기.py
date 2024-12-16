def solution(wallet, bill, count = 0):
    bill = sorted(bill)
    
    while min(wallet) < min(bill) or max(wallet) < max(bill):
        bill = [min(bill), int(max(bill)/2)]
        count += 1
        
    return count
