def sum_(a, b, c, mineralList):
    cost = 0
    
    cost += a * mineralList['diamond']
    cost += b * mineralList['iron']
    cost += c * mineralList['stone']
    
    return cost

def func(pick, mineralList):
    if pick == 'diamond':
        return sum_(1, 1, 1, mineralList)
    if pick == 'iron':
        return sum_(5, 1, 1, mineralList)
    return sum_(25, 5, 1, mineralList)

def solution(picks, minerals):
    answer = 0
    
    pickCnt = sum(picks)
    len_ = len(minerals)
    
    if 5 * pickCnt < len_:
        minerals = [minerals[i] for i in range(5 * pickCnt)]
    
    cnt = 0
    mineralList = []
    dict_ = {
        'diamond': 0,
        'iron': 0,
        'stone': 0,
    }
    
    for mineral in minerals:
        if cnt and not cnt % 5:
            mineralList.append(dict_)
            cnt = 0
            dict_ = {
                'diamond': 0,
                'iron': 0,
                'stone': 0,
            }
            
        dict_[mineral] += 1
        cnt += 1
        
    if cnt:
        mineralList.append(dict_)
    
    mineralList.sort(key=lambda x: (x['diamond'], x['iron'], x['stone']), reverse=True)
    
    # 곡괭이 개수
    d, i, s = picks[0], picks[1], picks[2]
    
    curIndex = 0
    len_ = len(mineralList)
    
    while curIndex < len_:
        if d:
            d -= 1
            answer += func('diamond', mineralList[curIndex])
        
        elif i:
            i -= 1
            answer += func('iron', mineralList[curIndex])
            
        elif s:
            s -= 1
            answer += func('stone', mineralList[curIndex])
            
        curIndex += 1
        
    return answer