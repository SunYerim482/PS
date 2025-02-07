// 프로래머스 87390

function solution(n, left, right) {
  const arr = [];

  for (let i = left; i < right + 1; i++) {
    const [row, col] = calRowCol(n, i);

    arr.push(Math.max(row, col) + 1);
  }

  return arr;
}

function calRowCol(n, index) {
  return [Math.floor(index / n), index % n];
}
