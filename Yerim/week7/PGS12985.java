import java.util.*;

class PGS12985
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;

        boolean flag = true;

        while (flag) {
            if (a == b) {
                flag = false;
                break;
            }

            a = a / 2 + a % 2;
            b = b / 2 + b % 2;
            answer++;
        }


        return answer;
    }
}