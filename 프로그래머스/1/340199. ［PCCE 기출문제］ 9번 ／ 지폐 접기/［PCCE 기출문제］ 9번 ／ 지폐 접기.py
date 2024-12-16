def fold(bill):
    bill_x , bill_y = bill
    
    if bill_x > bill_y:
        bill_x = int(bill_x/2)
    else:
        bill_y = int(bill_y/2)

    return [bill_x, bill_y]

def solution(wallet, bill):
    # 너~ 는 정답이구나!
    answer = 0
    
    # 입력받은 bill은 초기 규격으로 두고! 요녀석을 반복해서 접기
    folded_bill = [bill[0],bill[1]]
    
    # 짧은쪽 꺼내오기
    wallet_short = wallet[0] if wallet[0] <= wallet[1] else wallet[1]
    wallet_long = wallet[0] if wallet[0] >= wallet[1] else wallet[1]
    
    # 접기 시작!
    while True:
        # 초기 입력값들이 조건을 충족하면 바로 반환하게..?
        bill_short = folded_bill[0] if folded_bill[0] <= folded_bill[1] else folded_bill[1]
        bill_long = folded_bill[0] if folded_bill[0] >= folded_bill[1] else folded_bill[1]
        
        if wallet_short >= bill_short and wallet_long >= bill_long:
            break
        
        folded_bill = fold(folded_bill)
        
        answer +=1
        
    return answer