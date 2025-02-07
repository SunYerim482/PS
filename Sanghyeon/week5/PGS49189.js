// 프로그래머스 49189

function solution(n, edges) {
  const graph = new Array(n + 1).fill(0).map(() => new Array());

  for (const edge of edges) {
    const [v1, v2] = edge;
    graph[v1].push(v2);
    graph[v2].push(v1);
  }

  const answer = search(n, graph);

  return answer;
}

function search(n, graph) {
  const q = [[1, 1]];
  const distArray = new Array(n + 1).fill(0);
  distArray[1] = 1;

  let maxDist = 1;
  let cnt = 1;

  while (q.length) {
    const [cur, dist] = q.shift();

    if (dist > maxDist) {
      maxDist = dist;
      cnt = 1;
    } else if (dist === maxDist) {
      cnt += 1;
    }

    for (const nbd of graph[cur]) {
      if (!distArray[nbd]) {
        distArray[nbd] = dist + 1;
        q.push([nbd, dist + 1]);
      }
    }
  }

  return cnt;
}
