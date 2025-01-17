// 백준 13023

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const [N, M] = input[0].split(" ").map((e) => +e);
const relations = input.slice(1).map((e1) => e1.split(" ").map((e2) => +e2));
let visited = new Array(N).fill(false);
let answer = 0;

function solution(N, M, relations) {
  const graph = new Array(N).fill(0).map(() => new Array());

  for (const relation of relations) {
    const [a, b] = relation;

    graph[a].push(b);
    graph[b].push(a);
  }

  for (let i = 0; i < N; i++) {
    visited[i] = true;
    dfs(i, 1, graph);
    visited[i] = false;

    if (answer) {
      return answer;
    }
  }

  return answer;
}

function dfs(cur, index, graph) {
  if (index === 5) {
    answer ||= 1;
    return;
  }

  for (const next of graph[cur]) {
    if (visited[next]) {
      continue;
    }

    visited[next] = true;
    dfs(next, index + 1, graph);
    visited[next] = false;
  }
}

console.log(solution(N, M, relations));
