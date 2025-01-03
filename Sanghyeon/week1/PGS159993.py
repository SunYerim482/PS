from collections import deque

drow, dcol = [1, 0, -1, 0], [0, 1, 0, -1]

def bfs(row, col, R, C, maps):
    q = deque([])
    q.append((row, col, 0))
    movingTime = [[float('inf')] * C for _ in range(R)]
    
    while q:
        row, col, currentTime = q.popleft()
        
        for i in range(4):
            nrow, ncol = row + drow[i], col + dcol[i]
            
            if not (0 <= nrow < R and 0 <= ncol < C) or maps[nrow][ncol] == 'X':
                continue
                
            if movingTime[nrow][ncol] <= currentTime + 1:
                continue
                
            movingTime[nrow][ncol] = currentTime + 1
            
            if maps[nrow][ncol] == 'L':
                return currentTime + 1
            else:
                q.append((nrow, ncol, currentTime + 1))
    return -1

def solution(maps):
    R, C = len(maps), len(maps[0])
    
    startPos = 0, 0
    endPos = 0, 0
    
    for row in range(R):
        for col in range(C):
            if maps[row][col] == 'S':
                startPos = row, col
            elif maps[row][col] == 'E':
                endPos = row, col
                
    timeFromStart = bfs(startPos[0], startPos[1], R, C, maps)
    
    if timeFromStart == -1:
        return -1
    
    timeFromEnd = bfs(endPos[0], endPos[1], R, C, maps)
    
    if timeFromEnd == -1:
        return -1

    return timeFromStart + timeFromEnd