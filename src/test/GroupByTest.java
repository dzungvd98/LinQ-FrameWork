package test;

import main.Person;
import main.QueryBuilder;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

public class GroupByTest {
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
    public void testGroupBy(){
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        Map<Integer, List<Person>> expect = new HashMap<>();
        
        List<Person> list1 = new ArrayList<>();
        list1.add(data.get(0));
        expect.put(25, list1);

        List<Person> list2 = new ArrayList<>();
        list2.add(data.get(1));
        list2.add(data.get(5));
        expect.put(22, list2);

        List<Person> list3 = new ArrayList<>();
        list3.add(data.get(2));
        list3.add(data.get(4));
        expect.put(30, list3);

        List<Person> list4 = new ArrayList<>();
        list4.add(data.get(3));
        expect.put(27, list4);

        Map<Integer, List<Person>> result = query.groupBy(p -> p.getAge());
        assertEquals(expect, result);
    }
}
