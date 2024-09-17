package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.Person;
import main.QueryBuilder;

public class SelectTest {
     public List<Person> initList() {
        List<Person> persons = Arrays.asList(
            new Person("John", 25),
            new Person("Jane", 22),
            new Person("Jack", 30),
            new Person("Jill", 27)

        );
        return persons;
    }

    @Test
    public void SelectTestUser() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        List<String> result = query.select(p -> p.getName()).toList();
        assertEquals("John", result.get(0));
        assertEquals("Jane", result.get(1));
        assertEquals("Jack", result.get(2));

        String re = query.where(p -> p.getAge() > 25).select(p -> p.getName()).first();
        assertEquals("Jack", re);
    } 
}
