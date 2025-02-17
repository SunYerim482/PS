// 프로그래머스 84512

function solution(word) {
  let flag = false;
  let answer = 0;
  const alphabets = ["A", "E", "I", "O", "U"];

  dfs("", word);

  return answer;

  function dfs(cur, target) {
    if (cur === target) {
      flag = true;
    }

    if (cur.length === 5) {
      return;
    }

    for (const alphabet of alphabets) {
      answer += !flag;
      dfs(cur + alphabet, target);
    }
  }
}
