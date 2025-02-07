// 백준 17086

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const [N, M] = input[0].split(" ").map((e) => +e);
const board = input.slice(1).map((e1) => e1.split(" ").map((e2) => +e2));
const safeDistance = new Array(N).fill(0).map(() => new Array(M).fill(2500));

const drow = [1, 1, 1, 0, -1, -1, -1, 0];
const dcol = [1, 0, -1, -1, -1, 0, 1, 1];

for (let row = 0; row < N; row++) {
  for (let col = 0; col < M; col++) {
    if (board[row][col]) {
      safeDistance[row][col] = 0;
      bfs(row, col);
    }
  }
}

function bfs(row, col) {
  const q = [[row, col, 0]];
  const visited = new Array(N).fill(0).map(() => new Array(M).fill(false));
  visited[row][col] = true;

  while (q.length) {
    const [row, col, dist] = q.shift();

    for (let i = 0; i < 8; i++) {
      const nrow = row + drow[i];
      const ncol = col + dcol[i];

      if (0 > nrow || nrow >= N || 0 > ncol || ncol >= M) {
        continue;
      }

      if (visited[nrow][ncol]) {
        continue;
      }

      visited[nrow][ncol] = true;
      safeDistance[nrow][ncol] = Math.min(safeDistance[nrow][ncol], dist + 1);
      q.push([nrow, ncol, dist + 1]);
    }
  }
}

let answer = 0;

for (let row = 0; row < N; row++) {
  for (let col = 0; col < M; col++) {
    answer = Math.max(answer, safeDistance[row][col]);
  }
}

console.log(answer);
