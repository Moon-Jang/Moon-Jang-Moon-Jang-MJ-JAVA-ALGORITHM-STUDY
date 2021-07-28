package algo.stack;

import java.util.Arrays;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
public class Stack4 {
    private int count = 0;

    public int[] run() {
        final int[] prices = {1, 2, 3, 2, 3};
        return solution(prices);
    }
    public int[] solution(int[] prices) {
        Stack<Integer> stack = new Stack<Integer>();
        List<Integer> answer = new ArrayList<Integer>();
        for ( int i = 0; i < prices.length - 1; i++ ) {
            int num = prices[i];
            //stack.p
        }
        answer.add(0);
        return answer.stream().mapToInt(i->i).toArray();
    }
}
