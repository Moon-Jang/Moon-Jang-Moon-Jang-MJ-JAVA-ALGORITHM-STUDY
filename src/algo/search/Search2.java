package algo.search;

import algo.util.MyUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Search2 {
    private int count = 0;

    public int run() {
        final int n = 3;
        final int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        return solution(n,computers);
    }
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        for ( int i = 0; i < n; i++ ) {
            if ( visited[i] == true ) continue;
            dfs(computers,i,visited);
            this.count++;
        }
        return this.count;
    }
    private void dfs(int[][] computers,int target, boolean[] visited) {
        if( visited[target] == true ) return;
        visited[target] = true;
        for( int i = 0; i < computers[target].length; i++ ) {
            if ( target == i ) continue;
            if (computers[target][i] != 1 || visited[i] == true) continue;
            dfs(computers, i, visited);
        }
    }
}
