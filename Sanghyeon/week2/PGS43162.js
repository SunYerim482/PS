// 프로그래머스 43162

function solution(n, computers) {
  const graph = new Array(n).fill(0).map(() => new Array());

  for (let i = 0; i < n; i++) {
    const computer = computers[i];

    for (let j = 0; j < computer.length; j++) {
      if (i !== j && computer[j] === 1) {
        graph[i].push(j);
        graph[j].push(i);
      }
    }
  }

  const answer = bfs(n, graph);

  return answer;
}

function bfs(n, graph) {
  let result = 0;
  let q;
  const visited = new Array(n).fill(false);

  for (let i = 0; i < n; i++) {
    if (visited[i] === false) {
      q = [i];
      visited[i] = true;
      result = result + 1;

      while (q.length !== 0) {
        const cur = q.shift();

        for (const next of graph[cur]) {
          if (visited[next] === true) continue;

          visited[next] = true;
          q.push(next);
        }
      }
    }
  }
  return result;
}
