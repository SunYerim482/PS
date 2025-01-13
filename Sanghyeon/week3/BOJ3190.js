// 백준 3190

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const N = +input[0];
const K = +input[1];
const appleArray = input
  .slice(2, K + 2)
  .map((e1) => e1.split(" ").map((e2) => +e2));
const L = +input[K + 2];
const rotationArray = input.slice(K + 3).map((e) => {
  const [e1, e2] = e.split(" ");
  return [+e1, e2];
});

const drow = [0, -1, 0, 1];
const dcol = [1, 0, -1, 0];

function solution(N, K, appleArray, L, rotationArray) {
  let timer = 0;
  let dir = 0;
  const snake = [[0, 0]];
  const board = new Array(N).fill(0).map(() => new Array(N).fill(0));
  board[0][0] = 2;

  appleArray.forEach(([row, col]) => {
    board[row - 1][col - 1] = 1;
  });

  while (true) {
    timer += 1;
    const [row, col] = snake[snake.length - 1];

    const nrow = row + drow[dir];
    const ncol = col + dcol[dir];

    if (0 > nrow || nrow >= N || 0 > ncol || ncol >= N) {
      break;
    }

    if (board[nrow][ncol] === 0) {
      const [trow, tcol] = snake.shift();

      board[trow][tcol] = 0;
    } else if (board[nrow][ncol] === 2) {
      break;
    }

    board[nrow][ncol] = 2;
    snake.push([nrow, ncol]);

    if (rotationArray.length > 0 && rotationArray[0][0] === timer) {
      const [_, ndir] = rotationArray.shift();

      if (ndir === "L") {
        dir += 1;
        dir %= 4;
      } else {
        dir += 3;
        dir %= 4;
      }
    }
  }

  return timer;
}

console.log(solution(N, K, appleArray, L, rotationArray));
