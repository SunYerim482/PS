// 백준 22856

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const N = +input[0];
const graph = new Array(N + 1).fill(0).map(() => new Map());
const infoArray = input.slice(1).map((e1) => e1.split(" ").map((e2) => +e2));

let answer = 0;

for (const [a, b, c] of infoArray) {
  if (b !== -1) {
    graph[a].set("L", b);
  }

  if (c !== -1) {
    graph[a].set("R", c);
  }
}

function inorder(v, graph) {
  if (graph[v].has("L")) {
    answer += 1;
    inorder(graph[v].get("L"), graph);
    answer += 1;
  }

  if (graph[v].has("R")) {
    answer += 1;
    inorder(graph[v].get("R"), graph);
    answer += 1;
  }
}

function rightTraversal(v, graph) {
  if (graph[v].has("R")) {
    answer -= 1;
    rightTraversal(graph[v].get("R"), graph);
  }
}

inorder(1, graph);
rightTraversal(1, graph);
console.log(answer);
