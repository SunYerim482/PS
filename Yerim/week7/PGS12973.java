import java.util.*;
class PGS12973
{
    static int answer;
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                char curr = s.charAt(i);

                if (stack.peek() == curr) {
                    stack.pop();
                } else {
                    stack.push(curr);
                }
            }
        }

        answer = (stack.isEmpty() ? 1 : 0);

        return answer;
    }
}