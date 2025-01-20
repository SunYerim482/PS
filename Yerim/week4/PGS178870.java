import java.util.*;

class PGS178870 {
    // two pointer
    static int[] result;
    static int left, right, minLen = Integer.MAX_VALUE;
    static List<int[]> list;

    public int[] solution(int[] sequence, int k) {
        result = new int[2];
        list = new ArrayList<>();
        left = 0;
        right = 0;
        int total = sequence[0];
        int length = sequence.length;
        // result[0] = left; result[1] = right;
        while (right < length) {
            // 기저
            if (total == k) {
                list.add(new int[]{left, right});
            }
            if (total < k) {
                right++;
                if (right < length) {
                    total += sequence[right];
                }
            } else {
                if (left < length) {
                    total -= sequence[left];
                }
                left++;
            }

        }

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int len1 = o1[1] - o1[0];
                int len2 = o2[1] - o2[0];
                return len1 - len2;
            }
        });

        // 가장 짧은 수열 return
        result[0] = list.get(0)[0];
        result[1] = list.get(0)[1];
        return result;
    }
}