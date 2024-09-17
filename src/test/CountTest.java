package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.Person;
import main.QueryBuilder;

public class CountTest {
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
    public void countTestUser() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        int result = query.count(p -> p.getAge() >=25);
        assertEquals(3, result);
    } 
}
