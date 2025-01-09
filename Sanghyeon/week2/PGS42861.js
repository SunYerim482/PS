// 프로그래머스 42861

function solution(n, costs) {
  let answer = 0;
  costs.sort((a, b) => a[2] - b[2]);
  const visited = new Set([costs[0][0]]);

  while (visited.size !== n) {
    for (const [v1, v2, cost] of costs) {
      if (visited.has(v1) && visited.has(v2)) continue;

      if (visited.has(v1) || visited.has(v2)) {
        answer = answer + cost;
        visited.add(v1);
        visited.add(v2);
        break;
      }
    }
  }

  return answer;
}
