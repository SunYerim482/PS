import java.util.*;

class PGS132265 {
    static Map<Integer, Integer> chulsu;
    static Map<Integer, Integer> brother;
    public int solution(int[] topping) {
        int answer = 0;
        chulsu = new HashMap<>();
        brother = new HashMap<>();

        // 이중 for문 -> 시간초과
        // 단일 for문으로 처리

        // brother에 일단 다 넣기
        for (int i = 0; i < topping.length; i++) {
            int num = topping[i];
            brother.put(num, brother.getOrDefault(num, 0) + 1);
        }

        // 반복문 돌면서 chulsu에는 하나씩 넣어주고 brother에서 하나씩 빼주면서 갯수 동일하면 answer++;
        for (int i = 0; i < topping.length; i++) {
            int num = topping[i];
            // chulsu
            chulsu.put(num, chulsu.getOrDefault(num, 0) + 1);

            // brother
            if (brother.containsKey(num)) {
                // 한 개 갖고있으면
                if (brother.get(num) == 1) {
                    brother.remove(num);
                } else {
                    brother.put(num, brother.getOrDefault(num, 0) - 1);
                }
            }

            // 이 때 brother, chulsu가 갖고있는 key의 갯수가 같으면 정답 증가
            if (brother.keySet().size() == chulsu.keySet().size()) {
                answer++;
            }
        }
        return answer;
    }
}
