// 백준 16719

const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

let str = input[0];
const answer = [str, "\n"];

/**
 * 주어진 문자열에서 특정 인덱스의
 * 문자를 제거한 후 반환하는 함수
 *
 * @param {String} str
 * @param {Number} i
 *
 * @returns {String}
 */
function removeChar(str, i) {
  const strArr = Array.from(str);
  const nextArr = strArr.slice(0, i).concat(strArr.slice(i + 1));

  return nextArr.join("");
}

/**
 * 아직 보여주지 않은 문자 중 추가했을 때의
 * 문자열 중 사전 순으로 가장 앞에 오는 문자를
 * answer 배열에 추가하는 함수
 *
 * @param {String} cur
 * @returns {void}
 */
function func(cur) {
  if (cur.length === 1) {
    return;
  }

  const arr = [];

  for (let i = 0; i < cur.length; i++) {
    arr.push(removeChar(cur, i));
  }

  arr.sort();
  answer.push(arr[0]);
  answer.push("\n");

  func(arr[0]);
}

func(str);
answer.reverse();
console.log(answer.join("").trim());
