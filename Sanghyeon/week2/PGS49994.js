// 프로그래머스 49994

const d = {
  U: [-1, 0],
  L: [0, -1],
  D: [1, 0],
  R: [0, 1],
};

function solution(dirs) {
  let answer = 0;
  let row = 5;
  let col = 5;
  const visited = new Set();

  for (const dir of dirs) {
    const nrow = row + d[dir][0];
    const ncol = col + d[dir][1];

    if (-1 < nrow && nrow < 11 && -1 < ncol && ncol < 11) {
      const history1 = convert(row, col, nrow, ncol);
      const history2 = convert(nrow, ncol, row, col);

      if (visited.has(history1) === false && visited.has(history2) === false) {
        visited.add(history1);
        visited.add(history2);
        answer = answer + 1;
      }

      row = nrow;
      col = ncol;
    }
  }
  return answer;
}

function convert(r1, c1, r2, c2) {
  return r1 + 100 * c1 + 10000 * r2 + 1000000 * c2;
}
