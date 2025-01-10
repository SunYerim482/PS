const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const N = +input[0];
const wordArray = input.slice(1).map((el) => Array.from(el).sort());
let result;
let visited;
let answer = "";

for (const word of wordArray) {
  result = [];
  visited = new Array(word.length).fill(false);
  dfs(0, "", word);
  answer += result.join("\n") + "\n";
}

function dfs(curIndex, cur, word) {
  if (curIndex === word.length) {
    result.push(cur);
    return;
  }

  for (let i = 0; i < word.length; i++) {
    if (
      result.length > 0 &&
      result[result.length - 1].slice(0, curIndex + 1) === cur + word[i]
    )
      continue;
    if (visited[i] === true) continue;

    visited[i] = true;
    dfs(curIndex + 1, cur + word[i], word);
    visited[i] = false;
  }
}

console.log(answer.trim());
