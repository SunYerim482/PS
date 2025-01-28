// 백준 1937

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const n = +input[0];
const forest = input.slice(1).map((e1) => e1.split(" ").map((e2) => +e2));
const cache = new Array(n).fill(0).map(() => new Array(n).fill(0));

const drow = [1, 0, -1, 0];
const dcol = [0, 1, 0, -1];

let answer = 0;

for (let row = 0; row < n; row++) {
  for (let col = 0; col < n; col++) {
    const cnt = dfs(row, col, n, forest);
    answer = Math.max(answer, cnt);
  }
}

function dfs(row, col, n, forest) {
  if (cache[row][col]) {
    return cache[row][col];
  }

  cache[row][col] = 1;

  for (let i = 0; i < 4; i++) {
    const nrow = row + drow[i];
    const ncol = col + dcol[i];

    if (0 > nrow || nrow >= n || 0 > ncol || ncol >= n) {
      continue;
    }

    if (forest[nrow][ncol] >= forest[row][col]) {
      continue;
    }

    cache[row][col] = Math.max(cache[row][col], 1 + dfs(nrow, ncol, n, forest));
  }

  return cache[row][col];
}

console.log(answer);
