function solution(begin, target, words) {
  words.unshift(begin);

  const graph = new Array(words.length).fill(0).map(() => new Array());

  for (let i = 0; i < words.length; i++) {
    for (let j = 0; j < words.length; j++) {
      const cost = calCost(words[i], words[j]);

      if (cost === 1) graph[i].push(j);
    }
  }

  const answer = bfs(begin, target, words, graph);

  return answer;
}

function calCost(word1, word2) {
  let cost = 0;

  for (let i = 0; i < word1.length; i++) {
    if (word1.charAt(i) !== word2.charAt(i)) cost = cost + 1;
  }

  return cost;
}

function bfs(begin, target, words, graph) {
  const q = [[0, 0]];
  const visited = new Array(words.length).fill(false);
  visited[0] = true;

  while (q.length !== 0) {
    const [cur, cost] = q.shift();

    if (words[cur] === target) return cost;

    for (const next of graph[cur]) {
      if (visited[next] === true) continue;

      q.push([next, cost + 1]);
      visited[next] = true;
    }
  }
  return 0;
}
