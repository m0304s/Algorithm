def solution(t, p):
    len_p = len(p)
    len_t = len(t)
    ans = 0
    for i in range(len_t-len_p+1):
        sub = t[i:i+len_p]
        if int(sub) <= int(p):
            ans += 1
    return ans