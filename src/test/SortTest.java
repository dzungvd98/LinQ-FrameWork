package test;

import main.Person;
import main.QueryBuilder;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SortTest {
    public List<Person> initList() {
        List<Person> persons = Arrays.asList(
            new Person("John", 25),
            new Person("Jane", 22),
            new Person("Jack", 30),
            new Person("Jill", 27),
            new Person("Lisa", 30),
            new Person("Jimmy", 22)

        );
        return persons;
    }

    @Test
    public void testOrderBy() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        List<Person> result = query.orderBy(p -> p.getAge()).toList();

        assertEquals(result.get(0).getAge(), 22);
    }

    @Test
    public void testOrderByDescending() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        List<Person> result = query.orderByDescending(p -> p.getAge()).toList();

        assertEquals(result.get(0).getAge(), 30);
    }

    @Test
    public void testMergeOrderBy() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        List<Person> result = query.sortBy(p -> p.getName()).thenBy(p -> p.getAge()).toList();

        assertEquals(result.get(0).getAge(), 30);
    }

    

}
