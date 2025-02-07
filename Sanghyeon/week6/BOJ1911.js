// 백준 1911

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const [N, L] = input[0].split(" ").map((e) => +e);
const infoArray = input.slice(1).map((e1) => e1.split(" ").map((e2) => +e2));

infoArray.sort((a, b) => {
  if (a[0] !== b[0]) {
    return a[0] - b[0];
  }
  return a[1] - b[1];
});

let cnt = 0;
let cur = 0;
let next = 0;

while (next < N) {
  if (cur < infoArray[next][0]) {
    cur = infoArray[next][0];
  }

  while (cur < infoArray[next][1]) {
    cur += L;
    cnt += 1;
  }

  next += 1;
}

console.log(cnt);
