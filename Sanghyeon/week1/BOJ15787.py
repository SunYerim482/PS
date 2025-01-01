import sys
input = sys.stdin.readline

def func1(command, train):
    train[command[1] - 1] = True
    return train

def func2(command, train):
    train[command[1] - 1] = False
    return train

def func3(command, train):
    for i in range(18, -1, -1):
        train[i + 1] = train[i]
    train[0] = False
    return train

def func4(command, train):
    for i in range(0, 19):
        train[i] = train[i + 1]
    train[19] = False
    return train

def convertToBin(train):
    temp = ''

    for seat in train:
        if seat:
            temp += 'o'
        else:
            temp += 'x'
    return temp

funcDict = {
    1: func1,
    2: func2,
    3: func3,
    4: func4,
}

N, M = map(int, input().split())
trainList = [[False] * 20 for _ in range(N)]
resultDict = {}

for _ in range(M):
    commandList = list(map(int, input().split()))

    commandType = commandList[0]
    trainIndex = commandList[1] - 1

    trainList[trainIndex] = funcDict[commandType](
        commandList[1:], 
        trainList[trainIndex],
    )

for train in trainList:
    temp = convertToBin(train)
    if temp in resultDict:
        resultDict[temp] += 1
    else:
        resultDict[temp] = 1

print(len(resultDict.items()))