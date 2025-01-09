const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

function solve(G) {
  let left;
  let right;
  let sum;
  const answer = [];
  const divisorArray = [];

  for (let num = 1; num <= G; num++) {
    if (G % num === 0) {
      divisorArray.push(num);
    }
  }

  let leftIndex = 0;
  let rightIndex = divisorArray.length - 1;

  while (leftIndex < rightIndex) {
    left = divisorArray[leftIndex];
    right = divisorArray[rightIndex];

    if ((left + right) % 2 === 0) {
      answer.push((right + left) / 2);
    }

    leftIndex += 1;
    rightIndex -= 1;
  }

  if (answer.length === 0) {
    return -1;
  }

  answer.sort((a, b) => a - b);

  return answer.join("\n").trim();
}

G = +input[0];

console.log(solve(G));
