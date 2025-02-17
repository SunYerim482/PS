// 프로그래머스 64065

function solution(s) {
  const arr = s
    .slice(2, -2)
    .split("},{")
    .map((e) => e.split(","))
    .sort((a, b) => a.length - b.length);

  const answer = [];
  const cache = new Set();

  for (let i = 0; i < arr.length; i++) {
    for (let j = 0; j < arr[i].length; j++) {
      if (!cache.has(arr[i][j])) {
        answer.push(+arr[i][j]);
        cache.add(arr[i][j]);
        break;
      }
    }
  }

  return answer;
}
