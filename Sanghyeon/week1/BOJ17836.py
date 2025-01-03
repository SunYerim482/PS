import sys
input = sys.stdin.readline

from collections import deque

drow, dcol = [1, 0, -1, 0], [0, 1, 0, -1]

def bfsWithoutGram():
    global min_

    q = deque([])
    q.append((0, 0, 0))
    visited = [[False] * M for _ in range(N)]
    visited[0][0] = False

    while q:
        row, col, currentTime = q.popleft()

        if currentTime > T or currentTime > min_:
            continue

        if (row, col) == (N - 1, M - 1):
            min_ = min(min_, currentTime)
            continue

        for i in range(4):
            nrow, ncol = row + drow[i], col + dcol[i]

            if not (0 <= nrow < N and 0 <= ncol < M):
                continue

            if visited[nrow][ncol] or castle[nrow][ncol] != 0:
                continue
            
            visited[nrow][ncol] = True
            q.append((nrow, ncol, currentTime + 1))

def isPossible():
    global gramPos

    q = deque([])
    q.append((0, 0, 0))
    visited = [[False] * M for _ in range(N)]
    visited[0][0] = True

    while q:
        row, col, currentTime = q.popleft()

        if (row, col) == gramPos:
            return True, currentTime

        for i in range(4):
            nrow, ncol = row + drow[i], col + dcol[i]

            if not (0 <= nrow < N and 0 <= ncol < M):
                continue

            if visited[nrow][ncol] or castle[nrow][ncol] == 1:
                continue

            visited[nrow][ncol] = True
            q.append((nrow, ncol, currentTime + 1))
    return False, float('inf')

N, M, T = map(int, input().split())
castle = [list(map(int, input().split())) for _ in range(N)]
gramPos = -1, -1
flag = False
min_ = float('inf')

for row in range(N):
    for col in range(M):
        if castle[row][col] == 2:
            gramPos = row, col
            flag = True
            break
    if flag:
        break

temp = isPossible()

if temp[0]:
    min_ = min(min_, temp[1] + N + M - gramPos[0] - gramPos[1] - 2)

bfsWithoutGram()

print(min_ if min_ != float('inf') and min_ <= T else 'Fail')