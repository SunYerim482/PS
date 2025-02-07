// 백준 20164

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

let max = 0;
let min = 1000000000;

const N = +input[0];
cal(N, countOdd(N));
console.log(min, max);

/**
 * 문제에서 요구하는 최대값, 최소값을
 * 계산하는 함수
 *
 * @param {Number} num
 * @param {Number} oddCnt
 *
 * @returns {void}
 */
function cal(num, oddCnt) {
  if (num < 10) {
    max = Math.max(max, oddCnt);
    min = Math.min(min, oddCnt);
    return;
  } else if (num < 100) {
    const r = num % 10;
    const nextNum = (num - r) / 10 + r;

    cal(nextNum, oddCnt + countOdd(nextNum));
  } else {
    const partitions = partitionNum(num);

    for (const partition of partitions) {
      cal(partition, oddCnt + countOdd(partition));
    }
  }
}

/**
 * 주어진 숫자의 각 자리수에서
 * 홀수의 개수를 반환하는 함수
 *
 * @param {Number} num
 *
 * @returns {Number}
 */
function countOdd(num) {
  let cnt = 0;

  while (num) {
    const r = num % 10;
    num -= r;
    num /= 10;

    if (r % 2) {
      cnt += 1;
    }
  }

  return cnt;
}

/**
 * 세 자리 이상의 수를 세 수로
 * 분할하는 함수
 *
 * @param {Number} num
 *
 * @returns {Number[]}
 */
function partitionNum(num) {
  const returnArray = new Array();
  const numString = num.toString();

  for (let i = 1; i < numString.length - 1; i++) {
    for (let j = i + 1; j < numString.length; j++) {
      const temp =
        Number(numString.slice(0, i)) +
        Number(numString.slice(i, j)) +
        Number(numString.slice(j));

      returnArray.push(+temp);
    }
  }

  return returnArray;
}
