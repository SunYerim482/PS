// 프로그래머스 12981
import java.util.*;

class PGS12981 {
    static int[] answer;
    static HashMap<String, Integer> map;
    public int[] solution(int n, String[] words) {
        answer = new int[2]; // 몇 번째사람이 몇 번째 차례에 말하는가
        map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            if (i != 0) {
                String prev = words[i-1];
                String curr = words[i];

                char last = prev.charAt(prev.length() - 1);
                char first = curr.charAt(0);

                if (map.containsKey(curr) || last != first) {
                    answer[0] = (i % n) + 1;
                    answer[1] = (i / n) + 1;

                    return answer;
                }
            }

            map.put(words[i], 1);
        }

        return answer;
    }
}