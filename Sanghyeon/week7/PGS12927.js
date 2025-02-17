// 프로그래머스 12927

function solution(n, works) {
  works.sort((a, b) => b - a);

  const min = works[works.length - 1];
  const max = works[0];
  let remain = n;

  for (let cur = max; cur > 0; cur--) {
    if (!remain) {
      break;
    }

    for (let i = 0; i < works.length; i++) {
      if (works[i] !== cur || !remain) {
        break;
      }

      works[i] -= 1;
      remain -= 1;
    }
  }

  return works.reduce((prev, cur) => {
    return prev + cur * cur;
  }, 0);
}
