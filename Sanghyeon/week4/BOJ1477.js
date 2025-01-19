// 백준 1477

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const [N, M, L] = input[0].split(" ").map((e) => +e);
const inputArray = N
  ? input[1]
      .split(" ")
      .map((e) => +e)
      .sort((a, b) => a - b)
  : [];
const posArray = [0].concat(inputArray).concat([L]);
let [left, right] = [0, L];
let answer = L;

while (left <= right) {
  const mid = parseInt((left + right) / 2);

  let cnt = 0;
  for (let i = 1; i < L + 2; i++) {
    if (posArray[i] - posArray[i - 1] > mid) {
      cnt += parseInt((posArray[i] - posArray[i - 1] - 1) / mid);
    }
  }

  if (cnt <= M) {
    right = mid - 1;
    answer = mid;
  } else {
    left = mid + 1;
  }
}

console.log(answer);
