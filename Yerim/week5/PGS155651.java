import java.util.*;

class Solution {
    static int answer;
    static int[][] bookTime;
    public int solution(String[][] book_time) {
        answer = 0;
        bookTime = new int[book_time.length][2];


        for (int i = 0; i < book_time.length; i++) {
            int start = Integer.parseInt(book_time[i][0].replace(":", ""));
            int end = Integer.parseInt(book_time[i][1].replace(":", ""));

            // 청소시간
            end += 10;

            if (end % 100 >= 60) {
                end += 40;
            }

            bookTime[i][0] = start;
            bookTime[i][1] = end;
        }

        // pq
        Arrays.sort(bookTime, (a1, a2) -> {
            return a1[0] - a2[0];
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (int[] book : bookTime) {
            // 비어있다면
            if (pq.isEmpty()) {
                pq.add(book);
            } else {
                int[] tmp = pq.peek();
                int start = tmp[0];
                int end = tmp[1];

                if (book[0] >= end) {
                    pq.poll();
                }
                pq.add(book);
            }
        }

        answer = pq.size();

        return answer;
    }
}