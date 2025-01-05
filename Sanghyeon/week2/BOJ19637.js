// 백준 19637

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const [N, M] = input[0].split(" ").map((e) => +e);
const titleArray = [];
const answer = [];

for (let i = 0; i < N; i++) {
  const [title, supremum] = input[i + 1].split(" ").map((e, idx) => {
    if (idx === 0) {
      return e;
    }
    return +e;
  });

  if (
    titleArray.length > 0 &&
    supremum === titleArray[titleArray.length - 1][1]
  )
    continue;
  titleArray.push([title, supremum]);
}

for (let i = 0; i < M; i++) {
  const power = +input[i + N + 1];
  answer.push(cal(power));
}

console.log(answer.join("\n").trim());

function cal(power) {
  let left = 0;
  let right = titleArray.length - 1;
  let mid;
  let result = mid;

  while (left <= right) {
    mid = Math.floor((left + right) / 2);

    if (power > titleArray[mid][1]) {
      left = mid + 1;
    } else {
      result = mid;
      right = mid - 1;
    }
  }
  return titleArray[result][0];
}
