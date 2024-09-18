package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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
        for (T item : source) {
            if (predicate.test(item)) {
                filtered.add(item);
            }
        }
        return new QueryBuilder<T>(filtered);
    }

    public int count(Predicate<T> predicate) {
        int count = 0;
        for (T item : source) {
            if (!predicate.test(item)) {
                count++;
            }
        }
        return count;
    }

    public T first() {
        if (source.isEmpty()) {
            throw new NoSuchElementException("No elements in the collection");
        }
        return source.get(0);
    }

    public List<T> toList() {
        return this.source;
    }

    public <R> QueryBuilder<R> select(Function<T, R> selector) {
        List<R> result = new ArrayList<>();
        for (T item : source) {
            result.add(selector.apply(item));
        }
        return new QueryBuilder<>(result);
    }

    public <R extends Comparable<R>> R max(Function<T, R> selector) {
        if (source.isEmpty()) {
            throw new NoSuchElementException("Collection is empty");
        }

        R max = selector.apply(source.get(0));
        for (T item : source) {
            R value = selector.apply(item);
            if (value.compareTo(max) > 0) {
                max = value;
            }
        }
        return max;
    }

    public <R extends Comparable<R>> R min(Function<T, R> selector) {
        if (source.isEmpty()) {
            throw new NoSuchElementException("Collection is empty");
        }
        R min = selector.apply(source.get(0));
        for (T item : source) {
            R value = selector.apply(item);
            if (value.compareTo(min) < 0) {
                min = value;
            }
        }
        return min;
    }

    public int sum(Function<T, Integer> selector) {
        if(source.isEmpty()) {
            throw new NoSuchElementException("Colection is empty");
        }

        Integer sum = 0;
        for(T item : source) {
            Integer value = selector.apply(item);
            sum += value;
        }

        return sum;
    }

    public boolean all(Predicate<T> predicate) {
        if(source.isEmpty()) {
            throw new NoSuchElementException("Colection is empty");
        }
        
        for(T item : source) {
            if(!predicate.test(item)) {
                return false;
            }
        }
        return true;
    }

    public boolean any(Predicate<T> predicate) {
        if(source.isEmpty()) {
            throw new NoSuchElementException("Colection is empty");
        }
        
        for(T item : source) {
            if(predicate.test(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(T element) {
        return source.contains(element);
    }

    public T elementAt(int index) {
        return source.get(index);
    }

    public <K> Map<K, List<T>> groupBy(Function<T, K> keyExtractor) {
        Map<K, List<T>> grouped = new HashMap<>();
        for(T item : source) {
            K key = keyExtractor.apply(item);
            grouped.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
        }
        return grouped;
    }

}
