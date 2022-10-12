package asvsapp;

import java.util.List;

public class IglooGoldenData {
 
    int rank, total_occerences; 
    String search_term;
    private List<?>  ssr, qsr;

    public int getRank() {
        return rank;
    }

    public int getTotalOccurences(){
        return total_occerences;
    }

    public String getSearchTerm(){
        return search_term;
    }
 
    public List<?>  getSSR() {
        return ssr;
    }

    public List<?> getQSR() {
        return qsr;
    }
 
}




    
