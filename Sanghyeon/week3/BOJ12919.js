// 백준 12919

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

let answer = 0;
const S = input[0];
const T = input[1];

function dfs(cur, target) {
  const reversedCur = cur.split("").reverse().join("");

  if (!target.includes(cur) && !target.includes(reversedCur)) {
    return;
  }

  if (cur.length === target.length) {
    if (cur === target) {
      answer += 1;
    }
    return;
  }

  dfs(cur + "A", target);
  dfs("B" + reversedCur, target);
}

dfs(S, T);
console.log(answer && 1);
