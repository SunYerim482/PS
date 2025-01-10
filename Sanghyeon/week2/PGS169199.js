// 프로그래머스 169199

const drow = [1, 0, -1, 0];
const dcol = [0, 1, 0, -1];

function solution(board) {
  let initRow;
  let initCol;
  const newBoard = board.map((row) => Array.from(row));

  for (let row = 0; row < newBoard.length; row++) {
    for (let col = 0; col < newBoard[0].length; col++) {
      if (newBoard[row][col] === "R") {
        initRow = row;
        initCol = col;
      }
    }
  }

  const answer = bfs(initRow, initCol, newBoard);
  return answer;
}

function bfs(row, col, board) {
  const q = [[row, col, 0]];
  const R = board.length;
  const C = board[0].length;
  const visited = Array.from({ length: R }, () =>
    Array.from({ length: C }, () => new Set())
  );

  while (q.length !== 0) {
    const [row, col, cur] = q.shift();

    for (let i = 0; i < 4; i++) {
      let nrow = row;
      let ncol = col;

      while (
        isValid(nrow + drow[i], ncol + dcol[i], R, C) &&
        board[nrow + drow[i]][ncol + dcol[i]] !== "D"
      ) {
        nrow += drow[i];
        ncol += dcol[i];
      }

      if (board[nrow][ncol] === "G") {
        return cur + 1;
      }

      if (visited[nrow][ncol].has(i)) {
        continue;
      }

      visited[nrow][ncol].add(i);
      q.push([nrow, ncol, cur + 1]);
    }
  }
  return -1;
}

function isValid(row, col, R, C) {
  if (0 > row || row >= R || 0 > col || col >= C) {
    return false;
  }
  return true;
}
