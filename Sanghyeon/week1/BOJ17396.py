import sys
input = sys.stdin.readline

from heapq import *

def dijkstra():
    q = []
    heappush(q, (0, 0))

    while q:
        curPos, curTime = heappop(q)

        if curTime > minList[curPos]:
            continue

        for nextPos, time in graph[curPos]:
            if canVisible[nextPos]:
                continue
            
            if minList[nextPos] <= curTime + time:
                continue

            minList[nextPos] = curTime + time
            heappush(q, (nextPos, minList[nextPos]))

N, M = map(int, input().split())
minList = [0] + [float('inf')] * (N - 1)
canVisible = list(map(int, input().split()))
canVisible[N - 1] = 0
graph = [[] for _ in range(N)]

for _ in range(M):
    a, b, t = map(int, input().split())
    graph[a].append((b, t))
    graph[b].append((a, t))

dijkstra()

print(minList[N - 1] if minList[N - 1] != float('inf') else -1)