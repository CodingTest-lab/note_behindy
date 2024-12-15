def calculate(basetime, operator, duration, video_len):
    
    basetime_MM, basetime_SS = map(int, basetime.split(':'))
    duration_MM, duration_SS = map(int, duration.split(':'))
    video_MM, video_SS = map(int, video_len.split(':'))
    
    total_basetime = basetime_MM * 60 + basetime_SS
    total_duration = duration_MM * 60 + duration_SS
    total_video_len = video_MM * 60 + video_SS
    
    if operator == 'next':
        total_result = total_basetime + total_duration
        if total_result > total_video_len:
            total_result = total_video_len
    else:
        total_result = total_basetime - total_duration
        if total_result < 0:
            total_result = 0
            
    result_MM = total_result // 60
    result_SS = total_result % 60
    
    return f"{result_MM:02d}:{result_SS:02d}"

def skip(current, op_start, op_end):
    
    curr_MM, curr_SS = map(int, current.split(':'))
    start_MM, start_SS = map(int, op_start.split(':'))
    end_MM, end_SS = map(int, op_end.split(':'))
    
    total_curr = curr_MM * 60 + curr_SS
    total_start = start_MM * 60 + start_SS
    total_end = end_MM * 60 + end_SS
    
    return True if total_start <= total_curr and total_end > total_curr else False  

def solution(video_len, pos, op_start, op_end, commands):
    current_pos = pos
            
    if skip(current_pos, op_start, op_end):
        current_pos = op_end
    
    for command in commands:
        current_pos = calculate(current_pos, command, "00:10", video_len)
                    
        if skip(current_pos, op_start, op_end):
            current_pos = op_end
            
    return current_pos