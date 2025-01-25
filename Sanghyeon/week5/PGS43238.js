// 프로그래머스 43238

function solution(n, times) {
  let answer = 1000000000000000000;

  let left = 1;
  let right = 1000000000000000000;

  while (left <= right) {
    const mid = Math.floor((left + right) / 2);

    if (cal(times, mid) >= n) {
      answer = mid;
      right = mid - 1;
    } else {
      left = mid + 1;
    }
  }

  return answer;
}

function cal(times, mid) {
  let cnt = 0;

  for (const time of times) {
    cnt += Math.floor(mid / time);
  }

  return cnt;
}
