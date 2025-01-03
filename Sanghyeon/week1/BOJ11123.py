# 백준 11123

import sys
input = sys.stdin.readline

from collections import deque

drow, dcol = [1, 0, -1, 0], [0, 1, 0, -1]

def bfs(row, col):
    q = deque([])
    q.append((row, col))
    visited[row][col] = True

    while q:
        row, col = q.popleft()
    
        for i in range(4):
            nrow, ncol = row + drow[i], col + dcol[i]

            if not (0 <= nrow < H and 0 <= ncol < W):
                continue

            if visited[nrow][ncol] or list_[nrow][ncol] != '#':
                continue

            visited[nrow][ncol] = True
            q.append((nrow, ncol))

T = int(input())

for _ in range(T):
    H, W = map(int, input().split())
    list_ = [list(input()) for _ in range(H)]
    visited = [[False] * W for _ in range(H)]
    cnt = 0

    for row in range(H):
        for col in range(W):
            if list_[row][col] == '#' and not visited[row][col]:
                bfs(row, col)
                cnt += 1

    print(cnt)