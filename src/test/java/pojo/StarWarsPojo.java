package pojo;

import java.util.List;
import java.util.Map;

public class StarWarsPojo {


    //POJO -Plain And Old Java Object --> is the one way deserialization -less possible that it might be used,but it might


    private int count;
    private String next;
    private String previous;
    //private List<Map<String,Object>> results;
    private List<StarWarsCharactersPojo> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

//    public List<Map<String, Object>> getResults() {
//        return results;
//    }
//
//    public void setResults(List<Map<String, Object>> results) {
//        this.results = results;
//    }


    public List<StarWarsCharactersPojo> getResults() {
        return results;
    }

    public void setResults(List<StarWarsCharactersPojo> results) {
        this.results = results;
    }
}
