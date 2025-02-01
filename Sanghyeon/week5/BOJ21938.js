// 백준 21938

// 백준 플랫폼에서 input을 위한 코드입니다.
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

// ---------- 비즈니스 로직 시작 ----------
const [N, M] = input[0].split(" ").map((e) => +e);
const pixelBoardInfo = input
  .slice(1, N + 1)
  .map((e1) => e1.split(" ").map((e2) => +e2));
const T = +input[N + 1];

const drow = [1, 0, -1, 0];
const dcol = [0, 1, 0, -1];

let objectCnt = 0;
const pixelBoard = new Array(N).fill(0).map(() => new Array(M).fill(0));
const visited = new Array(N).fill(0).map(() => new Array(M).fill(false));

for (let row = 0; row < N; row++) {
  for (let col = 0; col < M; col++) {
    const RGBAverage = countRGBAverage(row, col);

    pixelBoard[row][col] = RGBAverage >= T ? 255 : 0;
  }
}

for (let row = 0; row < N; row++) {
  for (let col = 0; col < M; col++) {
    if (!visited[row][col] && pixelBoard[row][col] === 255) {
      objectCnt += 1;
      bfs(row, col);
    }
  }
}

console.log(objectCnt);

// ---------- 비즈니스 로직 종료 ----------

/**
 * 주어진 행, 열의 픽셀에서 rgb 값의
 * 평균을 구하여 반환하는 함수
 *
 * @param {Number} row
 * @param {Number} col
 * @param {Number[][]} pixelBoardInfo
 *
 * @returns {Number}
 */
function countRGBAverage(row, col) {
  const RGBAverage = pixelBoardInfo[row]
    .slice(3 * col, 3 * col + 3)
    .reduce((prev, cur) => prev + cur, 0);

  return RGBAverage / 3;
}

/**
 * 주어진 행, 열에서부터 bfs를 시작하여
 * 하나로 연결된 물체를 찾으며 방문처리를
 * 하는 함수
 *
 * @param {Number} row
 * @param {Number} col
 *
 * @returns {void}
 */
function bfs(row, col) {
  const q = [[row, col]];
  visited[row][col] = true;

  while (q.length) {
    const [row, col] = q.shift();

    for (let i = 0; i < 4; i++) {
      const nrow = row + drow[i];
      const ncol = col + dcol[i];

      if (0 > nrow || nrow >= N || 0 > ncol || ncol >= M) {
        continue;
      }

      if (visited[nrow][ncol] || pixelBoard[nrow][ncol] !== 255) {
        continue;
      }

      visited[nrow][ncol] = true;
      q.push([nrow, ncol]);
    }
  }
}
