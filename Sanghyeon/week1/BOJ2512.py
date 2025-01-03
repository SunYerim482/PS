def binSearch(left, right):
    global budgetList, totalBudget

    max_ = 0
    temp = (left + right) // 2

    while left <= right:
        sum_ = sum(budget if budget < temp else temp for budget in budgetList)

        if sum_ <= totalBudget:
            max_ = temp
            left = temp + 1
            temp = (left + right) // 2
        else:
            right = temp - 1
            temp = (left + right) // 2
    return max_

N = int(input())
budgetList = sorted(map(int, input().split()))
totalBudget = int(input())

print(binSearch(0, max(budgetList)))