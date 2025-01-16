// 백준 2630

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

let white = 0;
let blue = 0;

const N = +input[0];
const board = input.slice(1).map((e1) => e1.split(" ").map((e2) => +e2));

function customCount(row, col, N, board) {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (board[row][col] !== board[row + i][col + j]) {
        customCount(row, col, N / 2, board);
        customCount(row + N / 2, col, N / 2, board);
        customCount(row, col + N / 2, N / 2, board);
        customCount(row + N / 2, col + N / 2, N / 2, board);
        return;
      }
    }
  }

  if (board[row][col] === 0) {
    white += 1;
  } else {
    blue += 1;
  }
  return;
}

customCount(0, 0, N, board);

console.log(white);
console.log(blue);
