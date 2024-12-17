# expressions의 모든 식들을 가져다가 숫자들을 한글자씩 나누어 가장 큰 수를 측정 => 반복의 최소치 추정
def find_suspect(expressions):
    numbers = []

    for expression in expressions:
        for char in expression[0]:
            if char.isdigit():
                numbers.append(int(char))
        for char in expression[2]:
            if char.isdigit():
                numbers.append(int(char))

        if expression[4] != 'X':
            for char in expression[4]:
                if char.isdigit():
                    numbers.append(int(char))
        
    return max(numbers)+1 if numbers else 0

# 여러 식들을 판별해서 진법 측정하기
# 2~9 진법만 가능
def find_numeral_system(expressions, min_suspect):
    temp_list = list(range(min_suspect, 10))
    possible_numerals = set(temp_list)
    
    for expression in expressions:
        # 현재 수식에서 가능한 진법들
        current_possible = set()
        
        for numeral in temp_list:
            if check_numeral(expression, numeral):
                current_possible.add(numeral)
        
        possible_numerals &= current_possible
        
        # 가능한 진법이 없다면 바로 종료
        if not possible_numerals:
            return []
    
    return list(possible_numerals)

# 수식과 검증 진수 단위를 입력받아서 수식이 옳게 작성되었는지 확인하기
def check_numeral(expression, numeral):
    left_side = 0
    right_side = 0
    answer = 0
    
    # 문자열을 정수로 변환하여 리스트 생성
    temp_left = [int(x) for x in expression[0]][::-1]
    temp_right = [int(x) for x in expression[2]][::-1]
    
    if expression[4] != 'X':
        temp_answer = [int(x) for x in expression[4]][::-1]
    else:
        return False
    
    # 좌측 10진화
    place = 1
    for digit in temp_left:
        left_side += digit * place
        place = place * numeral
    
    # 우측 10진화
    place = 1
    for digit in temp_right:
        right_side += digit * place
        place = place * numeral
        
    # 정답 10진화
    place = 1
    for digit in temp_answer:
        answer += digit * place
        place = place * numeral
    
    if expression[1] == '+':
        if left_side + right_side == answer:
            return True
    
    if expression[1] == '-':
        if left_side - right_side == answer:
            return True
        
    return False

def solve_problem(expression, numerals):
    left_side = 0
    right_side = 0
    answers = set()  # append 대신 add를 사용하기 위해 이름 변경
    answer_X = ''
    
    # 문자열을 정수로 변환하여 리스트 생성
    temp_left = [int(x) for x in expression[0]][::-1]
    temp_right = [int(x) for x in expression[2]][::-1]
    
    for numeral in numerals:
        # 좌측 10진화
        left_side = 0
        right_side = 0
        place = 1
        for digit in temp_left:
            left_side += digit * place
            place = place * numeral

        # 우측 10진화
        place = 1
        for digit in temp_right:
            right_side += digit * place
            place = place * numeral
        
        # 10진수 연산 수행
        if expression[1] == '+':
            result = left_side + right_side
        else:
            result = left_side - right_side
            
        # 10진수를 해당 진법으로 변환
        if result == 0:
            converted = "0"
        else:
            converted = ""
            temp = result
            while temp > 0:
                converted = str(temp % numeral) + converted
                temp //= numeral
        
        # set에 추가
        answers.add(converted)
    # 결과 확인
    if len(answers) != 1:
        answer_X = '?'
    else:
        answer_X = answers.pop()
    
    return f"{expression[0]} {expression[1]} {expression[2]} = {answer_X}"

def solution(expressions):
    # 연산 수식들을 모아둘 공간
    result = []
    
    # 고대 유물들 분류하기
    parsed_expressions = []
    for expression in expressions:
        parsed_expressions.append(expression.split(' '))
    
    expressions_constraint = [expression for expression in parsed_expressions if expression[4] != 'X']
    expressions_problem = [expression for expression in parsed_expressions if expression[4] == 'X']
    
    # 최소 용의자 찾기 , 연산 대상 숫자 중 가장 큰녀석
    sus = find_suspect(parsed_expressions)
    
    # 연산 에서 검증하기 
    possible_numeral = find_numeral_system(expressions_constraint, sus)
    
    # 해답 완성하기
    for exp in expressions_problem:
        result.append(solve_problem(exp, possible_numeral))
    
    return result