package algo.heap;

import algo.common.Test;
import algo.data_structure.Heap;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;

public class Heap1 implements Test {

    @Override
    public Integer run() {
        int[] scoville = new int[]{1, 2, 3, 9, 10, 12};
        int[] scoville2 = new int[]{1, 1, 1};
        int[] scoville3 = new int[]{10, 10, 10, 10, 10};
        int[] scoville4 = new int[]{1, 2, 3, 9, 10, 12};
        int[] scoville5 = new int[]{0, 2, 3, 9, 10, 12};
        int[] scoville6 = new int[]{0, 0, 3, 9, 10, 12};
        int[] scoville7 = new int[]{0, 0, 0, 0};
        int[] scoville8 = new int[]{0, 0, 3, 9, 10, 12};
        int[] scoville9 = new int[]{0,0};
        int[] scoville10 = new int[]{1,0};
        System.out.println(solution(scoville2, 4)+"/"+ 2);
        System.out.println(solution(scoville3, 100)+"/"+ 4);
        System.out.println(solution(scoville, 7)+"/"+ 2);
        System.out.println(solution(scoville5, 7)+"/"+ 2);
        System.out.println(solution(scoville6, 7)+"/"+ 3);
        System.out.println(solution(scoville7, 7)+"/"+ -1);
        System.out.println(solution(scoville8, 7000)+"/"+ -1);
        System.out.println(solution(scoville8, 0)+"/"+ 0);
        System.out.println(solution(scoville8, 1)+"/"+ 2);
        System.out.println(solution(scoville9, 0)+"/"+ 0);
        System.out.println(solution(scoville9, 1)+"/"+ -1);
        System.out.println(solution(scoville10, 1)+"/"+ 1);
        int K = 7;
        return solution(scoville8, 7000);
    }

    public Integer solution(int[] scoville, int K) {
        int mixCnt = 0;
        Heap minHeap = new Heap();
        List<Integer> scovilles = Arrays.stream(scoville).boxed().collect(Collectors.toList());
        scovilles.stream().forEach( el -> minHeap.push(el));
        //minHeap.display();
        while (minHeap.peek() < K ) {
            if ( minHeap.size() < 2 ) {
                return -1;
            }
            int firstScoville = minHeap.pop();
            int secondScoville = minHeap.pop();
            mix(minHeap,firstScoville,secondScoville);
            //minHeap.display();
            mixCnt++;
        }
        return mixCnt;
    }

    public void mix(Heap heap,int firstScoville, int secondScoville) {
        int newScovile = firstScoville + (secondScoville * secondScoville);
        heap.push(newScovile);
    }
}
