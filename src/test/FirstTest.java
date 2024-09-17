package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.Person;
import main.QueryBuilder;

public class FirstTest {
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
    public void FirstTestUser() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        Person result = query.first();
        assertEquals("John", result.getName());
    } 

   
}
