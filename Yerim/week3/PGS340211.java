import java.util.*;

class PGS340211 {
    static int answer = 0;

    public int solution(int[][] points, int[][] routes) {
        // 각 시간별로 리스트를 만들어둡니다. (로봇 좌표 담아둘 좌표)
        List<List<int[]>> times = new ArrayList<>();
        int maxTime = 100 * 100 * 100; // r * c * routes길이
        // 초기화
        for (int i = 0; i < maxTime; i++) {
            times.add(new ArrayList<>());
        }

        // 로봇의 수 만큼 반복합니다.
        for (int[] robot : routes) {
            // 현시간
            int currTime = 0;
            // 로봇이 가진 좌표 갯수만큼 반복
            for (int i = 0; i < robot.length - 1; i++) {
                // r, c
                int r = points[robot[i] - 1][0];
                int c = points[robot[i] - 1][1];

                // 첫 좌표를 일단 list에 넣습니다.
                if (i==0) {
                    int[] curr = new int[]{r, c};
                    times.get(currTime++).add(curr);
                }

                // 다음 방문할 좌표
                int nextR = points[robot[i+1] - 1][0];
                int nextC = points[robot[i+1] - 1][1];

                // r부터 계산
                if (nextR < r) {
                    int size = r - nextR;
                    for (int j = 0; j < size; j++) {
                        int[] tmp = new int[]{--r, c};
                        times.get(currTime++).add(tmp);
                    }

                } else if (nextR > r) {
                    int size = nextR - r;
                    for (int j = 0; j < size; j++) {
                        int[] tmp = new int[]{++r, c};
                        times.get(currTime++).add(tmp);
                    }
                }

                // c계산
                if (nextC < c) {
                    int size = c - nextC;
                    for (int j = 0; j < size; j++) {
                        int[] tmp = new int[]{r, --c};
                        times.get(currTime++).add(tmp);
                    }

                } else if (nextC > c) {
                    int size = nextC - c;
                    for (int j = 0; j < size; j++) {
                        int[] tmp = new int[]{r, ++c};
                        times.get(currTime++).add(tmp);
                    }
                }
            }
        }
        // 다 움직이고 위험한 상황을 세아립니다.
        // 같은 시간대에 중복 좌표들만 계산하면 됨. -> hashmap
        for (List<int[]> list : times) {
            // key값은 좌표로
            Map<String, Integer> map = new HashMap<>();
            for (int[] p : list) {
                String key = p[0] + ", " + p[1];
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            // 좌표의 갯수가 1이 아니면 충돌한걸로 간주
            for (String key : map.keySet()) {
                if (map.get(key) != 1) {
                    answer++;
                }
            }
        }

        return answer;
    }



}