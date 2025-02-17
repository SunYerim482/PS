import java.util.*;
class PGS12911 {
    static int answer;
    public int solution(int n) {
        int cnt = Integer.bitCount(n); // 1의 갯수를 세아림
        answer = n + 1;

        while (true) {
            if (Integer.bitCount(answer) == cnt)
                break;
            answer++;
        }

        return answer;
    }
}