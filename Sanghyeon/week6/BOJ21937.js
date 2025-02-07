// 백준 21937

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const [N, M] = input[0].split(" ").map((e) => +e);
const infoArray = input
  .slice(1, M + 1)
  .map((e1) => e1.split(" ").map((e2) => +e2));
const X = +input[M + 1];

let cnt = 0;
const graph = new Array(N + 1).fill(0).map(() => new Array());

infoArray.forEach(([A, B]) => {
  graph[B].push(A);
});

bfs(X, graph);

console.log(cnt);

function bfs(start, graph) {
  const q = [start];
  const visited = new Array(graph.length).fill(false);
  visited[start] = true;

  while (q.length) {
    const cur = q.shift();

    for (const next of graph[cur]) {
      if (visited[next]) {
        continue;
      }

      cnt += 1;
      q.push(next);
      visited[next] = true;
    }
  }
}
