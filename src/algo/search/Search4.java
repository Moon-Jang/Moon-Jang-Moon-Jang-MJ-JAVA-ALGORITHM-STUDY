package algo.search;

import java.util.*;
import java.util.stream.Collectors;

public class Search4 {
    private boolean isSuccess = false;
    private List<String> result = null;

    public String[] run() {
        final String[][] tickets6 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        final String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        final String[][] tickets2 = {{"ICN", "SFO"}, {"SFO", "ICN"}, {"ICN", "SFO"}, {"SFO", "QRE"}};
        final String[][] tickets3 = {{"ICN", "BOO"}, {"ICN", "COO"}, {"COO", "DOO"},
                {"DOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"}, {"BOO", "ICN"}, {"COO", "BOO"}};
        final String[][] tickets4 = {{"ICN", "AOO"}, {"AOO", "BOO"},
                {"AOO", "BOO"}, {"BOO", "AOO"}, {"BOO", "FOO"}, {"FOO", "COO"}, {"COO", "ZOO"}};
        final String[][] tickets5 = {{"ICN", "AOO"}, {"AOO", "BOO"},
                {"AOO", "COO"}, {"COO", "AOO"}, {"BOO", "FOO"}, {"FOO", "COO"}, {"COO", "ZOO"}};
        final String[][] tickets7 = {{"ICN", "AOO"}, {"AOO", "BOO"},
                {"AOO", "COO"}, {"COO", "AOO"}, {"BOO", "ZOO"}};
        return solution(tickets6);
    }
    public String[] solution(String[][] tickets) {
        search(tickets,"ICN",-1,new ArrayList<>(),new boolean[tickets.length]);
        return result.stream().toArray(String[]::new);
    }

    public void search( String[][] tickets, String target, int targetIdx, List<String> current, boolean[] visited ) {
        current.add(target);
        if ( isSuccess ) return;
        if ( targetIdx != -1 ){
            visited[targetIdx] = true;
        }
        if ( current.size() == tickets.length + 1 ) {
            isSuccess = true;
            result = current;
            return;
        }
        List<Integer> nextIdxs = getNextIdxs(tickets,target,visited);
        for ( Integer nextIdx : nextIdxs ) {
            search(tickets,tickets[nextIdx][1],nextIdx,new ArrayList<>(current),visited.clone());
        }
    }

    public List<Integer> getNextIdxs( String[][] tickets, String target, boolean[] visited ) {
        List<Integer> nextDepartures = new ArrayList<>();
        for ( int i = 0; i < tickets.length; i++ ) {
            if( visited[i] == true ) continue;
            if ( !tickets[i][0].equals(target) ) continue;
            nextDepartures.add(i);
        }
        nextDepartures.sort((a,b)-> tickets[a][1].compareTo(tickets[b][1]));
        return nextDepartures;
    }
}
