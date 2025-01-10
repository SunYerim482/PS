// 프로그래머스 86971

function solution(n, wires) {
  const graph = new Array(101).fill(0).map(() => new Set());
  const tower = Array.from(new Set(wires.flat()));
  let answer = tower.length;

  for (const wire of wires) {
    const [v1, v2] = wire;

    graph[v1].add(v2);
    graph[v2].add(v1);
  }

  for (const wire of wires) {
    const [v1, v2] = wire;

    graph[v1].delete(v2);
    graph[v2].delete(v1);

    const diff = bfs(tower[0], tower, graph);
    answer = Math.min(diff, answer);

    graph[v1].add(v2);
    graph[v2].add(v1);
  }
  return answer;
}

function bfs(start, tower, graph) {
  let result = 1;
  const q = [start];
  const visited = new Array(101).fill(false);
  visited[start] = true;

  while (q.length !== 0) {
    const cur = q.shift();

    for (const next of graph[cur]) {
      if (visited[next] === true) continue;

      visited[next] = true;
      result = result + 1;
      q.push(next);
    }
  }
  return Math.abs(tower.length - 2 * result);
}
