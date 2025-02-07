import java.util.*;

class PGS87390 {
    static int[] answer;
    public int[] solution(int n, long left, long right) {
        answer = new int[(int)(right - left) + 1];
        // 채웁니다.
        int cnt = 0;
        for (long i = left; i <= right; i++) {
            long row = i / n;
            long col = i % n;
            answer[cnt++] = (int) Math.max(row, col) + 1;
        }
        return answer;
    }
}