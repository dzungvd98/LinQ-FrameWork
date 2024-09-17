package main;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class QueryBuilder<T> {
    private List<T> source;

    public QueryBuilder(List<T> source) {
        this.source = source;
    }

    public QueryBuilder<T> where(Predicate<T> predicate) {
        List<T> filtered = new ArrayList<>();
        for(T item : source) {
            if(predicate.test(item)) {
                filtered.add(item);
            }
        }
        return new QueryBuilder<T>(filtered);
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
    
    public T first() {
        if(source.isEmpty()) {
            throw new NoSuchElementException("No elements in the collection");
        }
        return source.get(0);
    }



    public List<T> toList() {
        return this.source;
    }

    public <R> QueryBuilder<R> select(Function<T, R> selector) {
        List<R> result = new ArrayList<>();
        for(T item : source) {
            result.add(selector.apply(item));
        }
        return new QueryBuilder<>(result);
    }


}
