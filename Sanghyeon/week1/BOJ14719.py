# 백준 14719

import sys
input = sys.stdin.readline

def cal(index):
    leftMax = max(heightList[i] for i in range(index))
    rightMax = max(heightList[i] for i in range(index + 1, W))

    max_ = min(leftMax, rightMax)

    if max_ > heightList[index]:
        return max_ - heightList[index]
    return 0

H, W = map(int, input().split())
heightList = list(map(int, input().split()))

print(sum(cal(i) for i in range(1, W - 1)))