import java.util.*;

class PGS131127 {
    static String[] cut;
    static int answer;
    public int solution(String[] want, int[] number, String[] discount) {
        answer = 0;

        for (int i = 0; i <= discount.length - 10; i++) {
            cut = Arrays.copyOfRange(discount, i, i+10);
            boolean possible = regist(cut, want, number);
            if (possible) answer++;
        }

        return answer;
    }

    private static boolean regist(String[] cut, String[] want, int[] number) {
        int[] selected = new int[want.length];

        // 돌면서 누적
        for (int i = 0; i < cut.length; i++) {
            for (int j = 0; j < want.length; j++) {
                if (cut[i].equals(want[j])) selected[j]++;
            }
        }

        // number랑 동일한지 확인
        for (int i = 0; i < number.length; i++) {
            if (number[i] != selected[i]) return false;
        }

        return true;
    }
}