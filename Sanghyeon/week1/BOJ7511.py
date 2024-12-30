import sys
input = sys.stdin.readline

def findParent(v, parentList):
    if v != parentList[v]:
        return findParent(parentList[v], parentList)
    return v

def union(v1, v2, parentList):
    v1 = findParent(v1, parentList)
    v2 = findParent(v2, parentList)

    if v1 < v2:
        parentList[v2] = v1
    else:
        parentList[v1] = v2

t = int(input())

for i in range(t):
    print(f'Scenario {i + 1}:')

    n = int(input())
    k = int(input())
    parentList = [i for i in range(n)]

    for _ in range(k):
        a, b = map(int, input().split())
        union(a, b, parentList)

    m = int(input())

    for j in range(m):
        u, v = map(int, input().split())
        print(int(findParent(u, parentList) == findParent(v, parentList)))

    print()