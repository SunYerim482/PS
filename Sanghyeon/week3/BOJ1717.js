// 백준 1717

const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n");

function findParent(v, parentArray) {
  if (parentArray[v] === v) {
    return v;
  }
  return findParent(parentArray[v], parentArray);
}

function union(v1, v2, parentArray) {
  v1 = findParent(v1, parentArray);
  v2 = findParent(v2, parentArray);

  if (v1 >= v2) {
    parentArray[v2] = v1;
  } else {
    parentArray[v1] = v2;
  }
}

function solution(n, m, inputArray) {
  const answer = [];
  const parentArray = Array.from({ length: n + 1 }, (_, i) => i);

  for (let i = 0; i < m; i++) {
    const [command, a, b] = inputArray[i];

    switch (command) {
      case 0:
        union(a, b, parentArray);
        continue;
      case 1:
        answer.push(
          findParent(parentArray[a], parentArray) ===
            findParent(parentArray[b], parentArray)
            ? "YES"
            : "NO"
        );
        continue;
      default:
        break;
    }
  }

  return answer.join("\n").trim();
}

const [n, m] = input[0].split(" ").map((e) => +e);
const inputArray = input.slice(1).map((e1) => e1.split(" ").map((e2) => +e2));

console.log(solution(n, m, inputArray));
