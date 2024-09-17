package main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class QueryBuilder<T> {
    private List<T> source;

    public QueryBuilder(List<T> source) {
        this.source = source;
    }

    public QueryBuilder<T> where(Predicate<T> predicate) {
        List<T> fildered = new ArrayList<>();
        for(T item : source) {
            if(predicate.test(item)) {
                fildered.add(item);
            }
        }
        return new QueryBuilder<T>(fildered);
    }

    public int count(Predicate<T> predicate) {
        int count = 0;
        for(T item : source) {
            if(predicate.test(item)) {
                count++;
            }
        }
        return count;
    }

    public List<T> toList() {
        return this.source;
    }


}
