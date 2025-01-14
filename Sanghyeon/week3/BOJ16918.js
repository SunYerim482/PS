// 백준 16918

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const [R, C, N] = input[0].split(" ").map((e) => +e);
let board = input.slice(1).map((e) => e.split(""));

const drow = [1, 0, -1, 0];
const dcol = [0, 1, 0, -1];

function solution(R, C, N, board) {
  if (N === 1) {
    return board.map((e) => e.join("")).join("\n");
  }

  if (N % 2 === 0) {
    return Array.from({ length: R }, () => "O".repeat(C)).join("\n");
  }

  const explodedOnce = explodeBoard(board);
  const explodedTwice = explodeBoard(explodedOnce);

  if ((N - 1) % 4 === 0) {
    return explodedTwice.map((e) => e.join("")).join("\n");
  } else {
    return explodedOnce.map((e) => e.join("")).join("\n");
  }
}

function explodeBoard(board) {
  const newBoard = Array.from({ length: R }, () => Array(C).fill("O"));

  for (let row = 0; row < R; row++) {
    for (let col = 0; col < C; col++) {
      if (board[row][col] === "O") {
        newBoard[row][col] = ".";

        for (let i = 0; i < 4; i++) {
          const nrow = row + drow[i];
          const ncol = col + dcol[i];

          if (nrow >= 0 && nrow < R && ncol >= 0 && ncol < C) {
            newBoard[nrow][ncol] = ".";
          }
        }
      }
    }
  }
  return newBoard;
}

console.log(solution(R, C, N, board));
