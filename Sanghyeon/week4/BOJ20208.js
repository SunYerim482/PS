// 백준 20208

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const [N, M, H] = input[0].split(" ").map((e) => +e);
const board = input.slice(1).map((e1) => e1.split(" ").map((e2) => +e2));

let answer = 0;
let startPos;
let milk = 0;
const milkMap = new Map();
const visited = new Set();

for (let row = 0; row < N; row++) {
  for (let col = 0; col < N; col++) {
    if (board[row][col] === 2) {
      milkMap.set(milk, [row, col]);
      milk += 1;
    }

    if (board[row][col] === 1) {
      startPos = [row, col];
    }
  }
}

function dfs(pos, health) {
  if (health >= dist(startPos, pos)) {
    answer = Math.max(answer, visited.size);
  }

  for (let i = 0; i < milk; i++) {
    if (visited.has(i)) {
      continue;
    }

    const next = milkMap.get(i);
    const d = dist(pos, next);

    if (health >= d) {
      visited.add(i);
      dfs(next, health - d + H);
      visited.delete(i);
    }
  }
}

function dist(prev, next) {
  return Math.abs(prev[0] - next[0]) + Math.abs(prev[1] - next[1]);
}

dfs(startPos, M);

console.log(answer);
