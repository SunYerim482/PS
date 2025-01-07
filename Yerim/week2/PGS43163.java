class PGS43163 {
    static int answer = Integer.MAX_VALUE;
    static boolean[] visited;
    public int solution(String begin, String target, String[] words) {

        visited = new boolean[words.length];

        dfs(begin, target, words, 0);

        if (answer == Integer.MAX_VALUE) return 0;

        return answer;
    }

    private static boolean canChange(String s1, String s2) {
        // s1 -> s2 변환이 가능한지
        int diffCnt = 0; // 한 글자만 변환 가능
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCnt++;
            }
        }

        // 만약 하나라면
        if (diffCnt == 1) return true;

        else return false;

    }

    private static void dfs(String begin, String target, String[] words, int count) {
        // 기저
        if (begin.equals(target)) {
            answer = Math.min(count, answer);
            return;
        }

        // 유도
        for (int i = 0; i < words.length; i++) {
            // 이미 방문했으면
            if (visited[i]) continue;

            // 변환이 가능하면
            if (canChange(begin, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, count+1);
                visited[i] = false;
            }
        }

    }
}