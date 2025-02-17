// 프로그래머스 17677

const alphabet = new Set([
  'A', 'B', 'C', 'D', 'E',
  'F', 'G', 'H', 'I', 'J',
  'K', 'L', 'M', 'N', 'O',
  'P', 'Q', 'R', 'S', 'T',
  'U', 'V', 'W', 'X', 'Y',
  'Z',
]);

function solution(str1, str2) {
  const map1 = new Map();
  const upperStr1 = str1.toUpperCase();
  
  const map2 = new Map();
  const upperStr2 = str2.toUpperCase();
  
  const str1Array = partition(upperStr1);
  const str2Array = partition(upperStr2);
  
  if (!str1Array.length && !str2Array.length) {
      return 65536;
  }
  
  for (const c of str1Array) {
      map1.set(c, map1.has(c) ? map1.get(c) + 1 : 1);
  }
  
  for (const c of str2Array) {
      map2.set(c, map2.has(c) ? map2.get(c) + 1 : 1);
  }
  
  const intersectionCnt = customIntersection(map1, map2);
  const unionCnt = customUnion(map1, map2);
  
  return Math.floor((intersectionCnt / unionCnt) * 65536);
}

function partition(str) {
  const arr = [];
  const strArray = Array.from(str);
  
  for (let i = 0; i < strArray.length - 1; i++) {
      if (alphabet.has(strArray[i]) && alphabet.has(strArray[i + 1])) {
          arr.push(strArray[i] + strArray[i + 1]);
      }
  }
  return arr;
}


function customIntersection(map1, map2) {
  let cnt = 0;
  
  for (const [k, v] of map1) {
      if (map2.has(k)) {
          cnt += Math.min(v, map2.get(k));
      }
  }
  
  return cnt;
}

function customUnion(map1, map2) {
  let cnt = 0;
  
  for (const [k, v] of map1) {
      if (map2.has(k)) {
          cnt += Math.max(v, map2.get(k));
      } else {
          cnt += v;
      }
  }
  
  for (const [k, v] of map2) {
      if (!map1.has(k)) {
          cnt += v;
      }
  }
  
  return cnt;
}