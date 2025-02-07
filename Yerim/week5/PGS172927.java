import java.util.*;
// 곡갱이 dfs
class PGS172927 {
    static int answer, progress;
    static PriorityQueue<Integer> pq;
    public int solution(int[] picks, String[] minerals) {
        answer = 0;
        progress = 0;
        pq = new PriorityQueue<>();
        dfs(new int[]{picks[0] * 5, picks[1] * 5, picks[2] * 5}, minerals, progress, 0);

        answer = pq.poll();

        return answer;
    }

    private static void dfs(int[] capital, String[] minerals, int progress, int fat) {
        // 한 곡갱이 계속 사용중인가
        int tmp = 0;
        for (int i = 0; i < 3; i++) {
            if (capital[i] % 5 == 0) {
                tmp++;
            }
        }

        if (tmp < 2) {
            return;
        }

        // 기저 -> 쓸 곡갱이 없거나, 광산에 있는 모든 광물 캤다면
        int tmpMax = Integer.MIN_VALUE;
        for (int i = 0; i < capital.length; i++) {
            tmpMax = Math.max(tmpMax, capital[i]);
        }
        if (progress == minerals.length || tmpMax == 0) {

            pq.add(fat);
            return;
        }

        // 유도
        for (int i = 0; i < capital.length; i++) {
            if (capital[i] == 0) continue;

            int[] tmpPicks = new int[]{capital[0], capital[1], capital[2]};
            tmpPicks[i] = capital[i] - 1;

            switch(i) {
                // 다이아
                case 0:
                    switch (minerals[progress]) {
                        case "diamond" :
                            dfs(tmpPicks, minerals, progress + 1, fat + 1);
                            break;
                        case "iron" :
                            dfs(tmpPicks, minerals, progress + 1, fat + 1);
                            break;
                        case "stone" :
                            dfs(tmpPicks, minerals, progress + 1, fat + 1);
                            break;
                    }
                    break;
                case 1:
                    switch (minerals[progress]) {
                        case "diamond" :
                            dfs(tmpPicks, minerals, progress + 1, fat + 5);
                            break;
                        case "iron" :
                            dfs(tmpPicks, minerals, progress + 1, fat + 1);
                            break;
                        case "stone" :
                            dfs(tmpPicks, minerals, progress + 1, fat + 1);
                            break;
                    }
                    break;
                case 2:
                    switch (minerals[progress]) {
                        case "diamond" :
                            dfs(tmpPicks, minerals, progress + 1, fat + 25);
                            break;
                        case "iron" :
                            dfs(tmpPicks, minerals, progress + 1, fat + 5);
                            break;
                        case "stone" :
                            dfs(tmpPicks, minerals, progress + 1, fat + 1);
                            break;
                    }
                    break;
            }
        }
    }

}
