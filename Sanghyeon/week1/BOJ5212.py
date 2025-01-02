import sys
input = sys.stdin.readline

drow, dcol = [1, 0, -1, 0], [0, 1, 0, -1]

def check(row, col, R, C, curMap):
    cnt = 0

    for i in range(4):
        nrow, ncol = row + drow[i], col + dcol[i]

        if not (0 <= nrow < R and 0 <= ncol < C) or curMap[nrow][ncol] == '.':
            cnt += 1

        if cnt >= 3:
            return True
    return False

R, C = map(int, input().split())
curMap = [list(input()) for _ in range(R)]
nextMap = [[elem for elem in row] for row in curMap]

for row in range(R):
    for col in range(C):
        if check(row, col, R, C, curMap):
            nextMap[row][col] = '.'

minRow = minCol = 0
maxRow = R
maxCol = C

for row in range(R):
    if all(nextMap[row][col] == '.' for col in range(C)):
        minRow += 1
        continue
    break

for row in range(R - 1, -1, -1):
    if all(nextMap[row][col] == '.' for col in range(C)):
        maxRow -= 1
        continue
    break

for col in range(C):
    if all(nextMap[row][col] == '.' for row in range(R)):
        minCol += 1
        continue
    break

for col in range(C - 1, -1, -1):
    if all(nextMap[row][col] == '.' for row in range(R)):
        maxCol -= 1
        continue
    break

for row in range(minRow, maxRow):
    for col in range(minCol, maxCol):
        print(nextMap[row][col], end='')
    print()