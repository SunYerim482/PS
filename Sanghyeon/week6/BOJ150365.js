// 프로그래머스 150365

const delta = {
  d: [1, 0],
  l: [0, -1],
  r: [0, 1],
  u: [-1, 0],
};

function solution(n, m, x, y, r, c, k) {
  const answer = [];

  if (!isPossible(x, y, r, c, k)) {
    return "impossible";
  }

  function dfs(row, col, path, depth) {
    if (!isPossible(row, col, r - 1, c - 1, k - depth) || answer.length) {
      return;
    }

    if (depth === k) {
      if (row === r - 1 && col === c - 1) {
        answer.push(path);
      }
      return;
    }

    for (const dir of ["d", "l", "r", "u"]) {
      const nrow = row + delta[dir][0];
      const ncol = col + delta[dir][1];

      if (0 > nrow || nrow >= n || 0 > ncol || ncol >= m) {
        continue;
      }
      dfs(nrow, ncol, path + dir, depth + 1);
    }
  }

  dfs(x - 1, y - 1, "", 0);

  return answer.length ? answer[0] : "impossible";
}

function isPossible(x, y, r, c, k) {
  const dist = Math.abs(x - r) + Math.abs(y - c);

  if (dist > k || Math.abs(dist - k) % 2) {
    return false;
  }
  return true;
}
