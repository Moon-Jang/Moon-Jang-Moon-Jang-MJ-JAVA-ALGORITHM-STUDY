package algo.greedy;

public class GreedyTest2 {
    static final char INITIAL_CHAR = 'A';
    static final int ALPHABET_LENGTH = 26;
    private boolean[] visited;
    private int answer;
    private final int DIRECTION_RIGHT = 1;
    private final int DIRECTION_LEFT = -1;

    public int run() {
        final String name1 = "JEROEN";
        final String name2 = "JAN";
        return solution(name1);
    }

    public int solution(String name) {
        visited = new boolean[name.length()];
        process(name,0,DIRECTION_RIGHT);
        return answer - 1;
    }

    private void process(String name, int idx, int direction) {
        if (visitedAll(visited)) return;
        visited[idx] = true;
        answer++;
        answer += getCharChangeCnt(name.charAt(idx));
        if (direction == DIRECTION_RIGHT) {
            moveRight(name,idx);
            return;
        }
        moveLeft(name,idx);
    }

    private void moveRight(String name, int idx) {
        if (idx + 1 < name.length() && name.charAt(idx + 1) == 'A') {
            int aCnt = getACnt(name, idx + 1);
            if (idx + aCnt == name.length()) return;
            if (isGotoBack(name, idx, aCnt)) {
                pass(idx+1,aCnt);
                answer += idx;
                process(name,name.length()-1,DIRECTION_LEFT);
                return;
            }
        }
        process(name,idx+DIRECTION_RIGHT,DIRECTION_RIGHT);
    }
    private void moveLeft(String name, int idx) {
        process(name,idx+DIRECTION_LEFT,DIRECTION_LEFT);
    }

    private boolean visitedAll(boolean[] visited) {
        for (boolean element : visited) {
            if (!element) return false;
        }
        return true;
    }

    private int getCharChangeCnt(char c) {
        int result = c - INITIAL_CHAR;
        if (result >= ALPHABET_LENGTH / 2) {
            return ALPHABET_LENGTH - result;
        }
        return result;
    }

    private int getACnt(String name, int idx) {
        int cnt = 0;
        while (name.charAt(idx++) == 'A') cnt++;
        return cnt;
    }

    private boolean isGotoBack(String name, int idx, int aCnt) {
        int processCnt = idx * 2;
        int remainCnt = name.length() - 1 - idx - aCnt;
        return name.length() > processCnt + remainCnt;
    }

    private void pass (int idx, int aCnt) {
        for( int i = idx; i < idx+aCnt; i++ ) {
            visited[i] = true;
        }
    }
}
