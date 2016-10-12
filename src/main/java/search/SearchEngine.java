package search;


import java.util.*;

/**
 * @author nilstes
 */
public class SearchEngine {
    PageReader reader;
    Map<String, Map<String, Integer>> scores = new HashMap<>();
    
    public void indexPage(String url) {
        String[] words = reader.readPage(url);
        for(String word : words) {
            Map<String, Integer> scoresForWord = scores.get(word);
            if(scoresForWord == null) {
                scoresForWord = new HashMap<>();
                scores.put(word, scoresForWord);
            }
            Integer score = scoresForWord.get(url);
            if(score == null) {
                scoresForWord.put(url, 1);
            } else {
                scoresForWord.put(url, score+1);
            }
        }
    }
    
    public List<String> search(String word) {
        final Map<String,Integer> scoresForWord = scores.get(word);
        List<String> sites = new ArrayList<String>(scoresForWord.keySet());
        Collections.sort(sites, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return scoresForWord.get(o2).compareTo(scoresForWord.get(o1));
            }
        });
        return sites;
    }
}
