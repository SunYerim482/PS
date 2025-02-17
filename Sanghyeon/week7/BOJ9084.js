// 백준 9084

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const T = +input[0];

for (let i = 0; i < T; i++) {
    const N = +input[3 * i + 1];
    const coinArr = input[3 * i + 2].split(" ");
    const M = +input[3 * i + 3];

    console.log(solve(N, M, coinArr));
}

function solve(N, M, coinArr) {
    const caseArr = new Array(M + 1).fill(0);
    caseArr[0] = 1;

    for (const coin of coinArr) {
        for (let i = coin; i < M + 1; i++) {
            caseArr[i] += caseArr[i - coin];
        }
    }
    return caseArr[M];
}