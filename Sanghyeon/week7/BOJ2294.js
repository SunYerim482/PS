// 백준 2294

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const [n, k] = input[0].split(" ").map(e => +e);
const coinArr = input.slice(1).map(e => +e);

const dp = new Array(k + 1).fill(100_000);
dp[0] = 0;

for (const coin of coinArr) {
    for (let cost = 1; cost < k + 1; cost++) {
        if (cost >= coin) {
            dp[cost] = Math.min(dp[cost - coin] + 1, dp[cost]);
        }
    }
}

console.log(dp[k] === 100_000 ? -1 : dp[k]);