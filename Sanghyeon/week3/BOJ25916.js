// 백준 25916

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const [N, M] = input[0].split(" ").map((e) => +e);
const holeArray = [0, ...input[1].split(" ").map((e) => +e)];
const partialSumArray = new Array(N).fill(0);
partialSumArray[0] = holeArray[0];

for (let i = 1; i <= N; i++) {
  partialSumArray[i] = holeArray[i] + partialSumArray[i - 1];
}

let leftIdx = 0;
let rightIdx = 0;
let cur;
let answer = 0;

while (rightIdx <= N) {
  cur = partialSumArray[rightIdx] - partialSumArray[leftIdx];

  if (cur < M) {
    answer = Math.max(answer, cur);
    rightIdx += 1;
  } else if (cur === M) {
    answer = Math.max(answer, cur);
    break;
  } else {
    leftIdx += 1;
  }
}

console.log(answer);
