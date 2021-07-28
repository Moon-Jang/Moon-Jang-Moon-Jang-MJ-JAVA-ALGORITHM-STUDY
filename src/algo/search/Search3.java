package algo.search;

import java.util.*;

public class Search3 {
    private int answerCnt = 0;
    private boolean isFound = false;
    private String target = null;
    public int run() {
        final String begin = "hit";
        final String target = "cog";
        final String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        return solution(begin,target,words);
    }
    public int solution(String begin, String target, String[] words) {
        List<String> visited = new ArrayList<String>();
        this.target = target;
        List<String> newWords = Arrays.asList(words);
        search(begin,newWords,visited,1);
        return answerCnt;
    }
    private void search(String str, List<String> words, List<String> visited, int cnt) {
        if ( isFound ) return;
        visited.add(str);
        List<String> convertableWords = getConvertableWords(str,words,visited);
        if ( convertableWords.size() < 1 ) return;
        if ( convertableWords.contains(target) ) {
            answerCnt = cnt;
            isFound = true;
            return;
       }
        for ( String word : convertableWords ) {
            search(word,words,new ArrayList<>(visited),cnt+1);
        }
    }
    private List<String> getConvertableWords(String str,List<String> words,List<String> visited) {
        List<String> convertableWords = new ArrayList<String>();
        for ( String word : words ) {
            if ( visited.contains(word) ) continue;
            if ( !isConvertable(str,word) ) continue;
            convertableWords.add(word);
        }
        return convertableWords;
    }

    private boolean isConvertable(String str, String target) {
        char[] strChars = str.toCharArray();
        char[] targetChars = target.toCharArray();
        int cnt = 0;
        for ( int i = 0; i < strChars.length; i++ ) {
            if ( strChars[i] == targetChars[i] ) {
                cnt++;
            }
        }
        return cnt == strChars.length - 1 ? true : false;
    }
}
