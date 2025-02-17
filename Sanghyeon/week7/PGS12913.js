// 프로그래머스 12913

function solution(land) {
  for (let row = 1; row < land.length; row++) {
      for (let col = 0; col < 4; col++) {
          land[row][col] += land[row - 1].reduce((acc, cur, curIndex) => {
              if (curIndex === col) {
                  return acc;
              }
              
              return acc > cur ? acc : cur;
          }, 0);
      }
  }
  
  return land[land.length - 1].reduce((acc, cur) => {
      return acc > cur ? acc : cur;
  }, 0);
}