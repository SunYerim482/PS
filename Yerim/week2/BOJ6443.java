import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class BOJ6443 {

    static char[] word; // 원본 문자열
    static char[] selected; // 뽑은 문자
    static boolean[] visited; // 방문 여부
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            word = s.toCharArray();
            Arrays.sort(word);

            selected = new char[word.length];
            visited = new boolean[word.length];

            // backtracking
            backtraking(0);

        }

        System.out.print(sb.toString());
    }

    private static void backtraking(int depth) {
        // 기저
        if (depth == word.length) {
            sb.append(selected).append("\n");

            return;
        }

        // 유도
        for (int i = 0; i < word.length; i++) {
            if (visited[i])
                continue;

            // 이전 문자와 같고, 이전 문자가 아직 방문되지 않은 경우
            if (i > 0 && word[i] == word[i - 1] && !visited[i - 1]) continue;

            // 선택
            visited[i] = true;
            selected[depth] = word[i];
            backtraking(depth + 1);
            visited[i] = false;
        }
    }
}
