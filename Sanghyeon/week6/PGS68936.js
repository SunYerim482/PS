// 프로그래머스 68936

function solution(arr) {
  const answer = [0, 0];

  zipArr(arr);

  return answer;

  function zipArr(arr) {
    if (isZippable(arr)) {
      answer[arr[0][0]] += 1;
      return;
    }
    const half = arr.length / 2;

    zipArr(arr.slice(0, half).map((row) => row.slice(0, half)));
    zipArr(arr.slice(0, half).map((row) => row.slice(half)));
    zipArr(arr.slice(half).map((row) => row.slice(0, half)));
    zipArr(arr.slice(half).map((row) => row.slice(half)));
  }
}

function isZippable(arr) {
  for (let row = 0; row < arr.length; row++) {
    for (let col = 0; col < arr.length; col++) {
      if (arr[row][col] !== arr[0][0]) {
        return false;
      }
    }
  }
  return true;
}
