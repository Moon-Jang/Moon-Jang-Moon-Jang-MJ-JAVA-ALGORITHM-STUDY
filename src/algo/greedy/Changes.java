package algo.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Changes {
    final static List<Integer> coins = Arrays.asList(500,100,50,10);

    public static Map<Integer,Integer> run() {
        int changes = 1260;
        return solution(changes);
    }
    public static Map<Integer,Integer> solution(int changes) {
        int currentMoney = changes;
        Map<Integer,Integer> result = new HashMap<Integer,Integer>();
        for ( Integer coin : coins ) {
            if ( currentMoney / coin > 0 ){
                result.put(coin,currentMoney / coin);
                currentMoney %= coin;
            }
            if ( currentMoney == 0 ) break;
        }
        return result;
    }
}
