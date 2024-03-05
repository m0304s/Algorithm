def solution(t, p):
    ans = 0
    len_p = len(p)
    p = int(p)
    # if len(p) == 1
    if len_p == 1:
        for n in t:
            if int(n) <= p: ans += 1

    # if len(p) != 1
    else:
        # t 전체, p 부분
        temp = t[:len_p-1]

        for i in range(len_p - 1, len(t)):
            temp = temp + t[i]
            temp_num = int(temp)
            if temp_num <= p:
                ans += 1
            temp = temp[1:]
    return ans
