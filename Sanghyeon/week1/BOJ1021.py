from collections import deque

def func(q, dir):
    cnt = 0
    temp = deque([elem for elem in q])

    while True:
        cur = temp.popleft()

        if cur == target:
            break
        else:
            cnt += 1

            if dir == 2:
                temp.append(cur)
            else:
                temp.appendleft(cur)
                temp.appendleft(temp.pop())
    return cnt, temp

N, M = map(int, input().split())
targetArray = list(map(int, input().split()))
q = deque([i for i in range(1, N + 1)])
min_ = 0

for target in targetArray:
    cnt1, temp1 = func(q, 2)
    cnt2, temp2 = func(q, 3)

    if cnt1 < cnt2:
        min_ += cnt1
        q = temp1
    else:
        min_ += cnt2
        q = temp2

print(min_)