package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nilstes
 */
public class SearchEngine {
    PageReader reader;
    Map<String, Map<String,Integer>> scores = new HashMap<String, Map<String,Integer>>();
    
    public SearchEngine() {
        this.reader = new UrlPageReader();
    }
    
    public void indexPage(String url) {
        String[] words = reader.readPage(url);
        for(String word : words) {
            Map<String, Integer> scoresForWord = scores.get(word);
            if(scoresForWord == null) {
                scoresForWord = new HashMap<String,Integer>();
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
