// 프로그래머스 43105

function solution(triangle) {
  for (let row = 1; row < triangle.length; row++) {
      for (let col = 0; col < triangle[row].length; col++) {
          if (col === 0) {
              triangle[row][col] += triangle[row - 1][col];
          } else if (col === triangle[row].length - 1) {
              triangle[row][col] += triangle[row - 1][col - 1];            
          } else {
              triangle[row][col] += Math.max(triangle[row - 1][col], triangle[row - 1][col - 1]);
          }
      }
  }
  
  return triangle[triangle.length - 1].reduce((acc, cur) => {
      return acc > cur ? acc : cur;
  }, 0);
}