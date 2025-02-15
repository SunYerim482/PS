import java.util.*;

// dp
class PGS12913 {
    static int answer = Integer.MIN_VALUE;;
    int solution(int[][] land) {

        for (int i = 1; i < land.length; i++) {
            land[i][0] += maxNumber(land[i-1][1], land[i-1][2], land[i-1][3]);
            land[i][1] += maxNumber(land[i-1][0], land[i-1][2], land[i-1][3]);
            land[i][2] += maxNumber(land[i-1][0], land[i-1][1], land[i-1][3]);
            land[i][3] += maxNumber(land[i-1][0], land[i-1][1], land[i-1][2]);
        }

        // 마지막 행 최댓값
        for (int i = 0; i < 4; i++) {
            answer = Math.max(answer, land[land.length-1][i]);
        }

        return answer;
    }

    // 여러 숫자 중 최댓값 반환하는 메서드
    private static int maxNumber(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
}