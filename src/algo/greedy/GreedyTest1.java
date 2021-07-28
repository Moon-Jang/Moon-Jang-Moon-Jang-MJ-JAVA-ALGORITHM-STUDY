package algo.greedy;

import algo.util.MyUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GreedyTest1 {
    public static int run() {
        final int n = 5;
        final int[] lost = { 2, 4, 3};
        final int[] reserve = { 1, 3, 4 };
        return solution(n,lost,reserve);
    }
    public static int solution(int n, int[] lost, int[] reserve) {
        Integer answer = null;
        int count = 0;
        List<Integer> losts = Arrays.stream(lost).boxed().collect(Collectors.toList());
        List<Integer> reserves = Arrays.stream(reserve).boxed().collect(Collectors.toList());
        List<Integer> duplicatedElements = MyUtil.getDuplicates(
                Stream.concat(losts.stream(),reserves.stream()).collect(Collectors.toList())
        );
        losts = losts.stream().filter( p -> !duplicatedElements.contains(p)).collect(Collectors.toList());
        reserves = reserves.stream().filter( p -> !duplicatedElements.contains(p)).collect(Collectors.toList());
        for ( final Integer l : losts ) {
            if ( reserves.size() == 0 ) break;
            final Integer front = l - 1;
            final Integer back = l + 1;
            final Integer target = reserves.stream()
                    .filter( p -> p.equals(front) || p.equals(back) )
                    .findFirst()
                    .orElse(null);
            if ( target != null ) {
                reserves.remove(target);
                count++;
            }
        }
        answer = n - (losts.size() - count);
        return answer;
    }
}
