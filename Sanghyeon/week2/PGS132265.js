function solution(topping) {
  let answer = 0;
  const cache = new Set();
  const dpArray1 = new Array(topping.length + 1).fill(0);
  const dpArray2 = new Array(topping.length + 1).fill(0);
  const toppingCnt = new Set([...topping]).size;

  for (let i = 1; i <= topping.length; i++) {
    const top = topping[i - 1];

    if (cache.has(top)) {
      dpArray1[i] = dpArray1[i - 1];
    } else {
      dpArray1[i] = dpArray1[i - 1] + 1;
      cache.add(top);
    }
  }

  cache.clear();
  dpArray2[topping.length - 1] = 1;
  cache.add(topping[topping.length - 1]);

  for (let i = topping.length - 2; i >= 0; i--) {
    const top = topping[i];

    if (cache.has(top)) {
      dpArray2[i] = dpArray2[i + 1];
    } else {
      dpArray2[i] = dpArray2[i + 1] + 1;
      cache.add(top);
    }
  }

  for (let i = 1; i < topping.length; i++) {
    if (dpArray1[i] === dpArray2[i]) {
      answer = answer + 1;
    }
  }
  return answer;
}
