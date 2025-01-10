import java.util.*;

class PGS43164 {
    static boolean[] ticketUsed;
    static ArrayList<String> answers;
    static String[] answer;
    public String[] solution(String[][] tickets) {
        ticketUsed = new boolean[tickets.length]; // 티켓 사용 유무
        answers = new ArrayList<>();

        dfs(0, "ICN", "ICN", tickets);

        Collections.sort(answers); // String 객체

        answer = answers.get(0).split(" ");

        return answer;
    }

    private static void dfs(int depth, String now, String path, String[][] tickets) {
        // 기저
        if (depth == tickets.length) {
            answers.add(path); // 정답 배열에 path넣기
            return;
        }

        // 유도
        // 아직 방문 안 했고, 출발지라면
        for (int i = 0; i < ticketUsed.length; i++) {
            if (!ticketUsed[i] && now.equals(tickets[i][0])) {
                ticketUsed[i] = true;
                dfs(depth + 1, tickets[i][1], path + " " + tickets[i][1], tickets);
                ticketUsed[i] = false;
            }
        }
    }
}