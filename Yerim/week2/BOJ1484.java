import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1484 {

    // 투포인터
    static int G;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());

        int memory = 1; // 기억하고 있던
        int curr = 2; // 현재

        while (curr <= G) {
            int diff = (curr + memory) * (curr - memory);
            // 이게 G랑 같다면
            if (diff == G) {
                sb.append(curr).append("\n");
            }
            if (diff < G) {
                curr++;
            } else {
                memory++;
            }
        }

        if (sb.length() == 0) {
            System.out.println(-1);
        } else {
            System.out.print(sb.toString());
        }
    }

}
