import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ16719 {

    // 거꾸로
    static String input;
//    static ArrayList<ArrayList<String>> wordList;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();

//        wordList = new ArrayList<>();
        visited = new boolean[input.length()];


        // 초기화
//        for (int i = 0; i <= input.length(); i++) {
//            wordList.add(new ArrayList<>());
//        }

        // 시작
        process(0, input.length() - 1);

        // 길이별로 분류된 arraylist sort
//        for (int i = 0; i <= input.length(); i++) {
//            Collections.sort(wordList.get(i));
//        }

        // 마지막
//        wordList.get(input.length()).add(input);

        // 출력
//        for (int i = 1; i <= input.length(); i++) {
//            sb.append(wordList.get(i).get(0)).append("\n");
//        }

        System.out.print(sb.toString());
    }

    private static void process (int prev, int next) {
        if (prev > next) return;

        // 모두 탐색할 필요 없으니, 현재 범위에서 가장 작은 문자 찾기
        int minIdx = prev;
        for (int i = prev; i <= next; i++) {
            if (input.charAt(i) < input.charAt(minIdx)) {
                minIdx = i;
            }
        }

        // 해당 문자 선택하고 방문 처리
        visited[minIdx] = true;
        printCurrentState();

        // 선택된 부분의 이전, 이후 각각 재귀
        process(minIdx + 1, next);
        process(prev, minIdx - 1);


//        // 종료조건
//        if (tmp.length() == 1) {
//            wordList.get(1).add(tmp); // 길이별로 관리
//            return;
//        }
//
//        // 진행 조건
//        for (int i = 0; i < tmp.length(); i++) {
//            String s = concatWord(i, tmp);
//            wordList.get(s.length()).add(concatWord(i, tmp)); // 넣고
//            process(s);
//        }
    }

//    // 특정 인덱스값 제거하고 문자열 이어붙이는 메서드
//    private static String concatWord (int idx, String word) {
//        String prev, next;
//        prev = word.substring(0, idx);
//        next = word.substring(idx+1);
//        return prev + next;
//    }

    private static void printCurrentState() {
        for (int i = 0; i < input.length(); i++) {
            if (visited[i]) {
                sb.append(input.charAt(i));
            }
        }
        sb.append("\n");
    }

}
