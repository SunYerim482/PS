// 프로그래머스 49191

function solution(n, results) {
  const graph = new Array(n + 1).fill(0).map(() => new Array(n + 1).fill(0));

  for (const [A, B] of results) {
    graph[A][B] += 1;
    graph[B][A] -= 1;
  }

  for (let j = 1; j < n + 1; j++) {
    for (let i = 1; i < n + 1; i++) {
      for (let k = 1; k < n + 1; k++) {
        if (graph[i][k]) {
          continue;
        } else {
          if (graph[i][j] && graph[i][j] === graph[j][k]) {
            graph[i][k] = graph[i][j];
          }
        }
      }
    }
  }

  return graph.filter((row) => row.filter((col) => col !== 0).length === n - 1)
    .length;
}
