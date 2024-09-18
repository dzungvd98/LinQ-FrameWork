package test;

import main.Person;
import main.QueryBuilder;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AggrigateTest {
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
    public void testMax() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        int result = query.max(p -> p.getAge());
        assertEquals(30, result);
    }

    @Test
    public void testMin() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        int result = query.min(p -> p.getAge());
        assertEquals(22, result);
    }

    @Test
    public void testSum() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        int result = query.sum(p -> p.getAge());
        assertEquals(104, result);
    }

    @Test
    public void testAll() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        boolean result = query.all(p -> p.getAge() >= 20);
        assertEquals(true, result);

        boolean result2 = query.all(p -> p.getAge() >= 25);
        assertEquals(false, result2);
    }


    @Test
    public void testAny() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        boolean result = query.any(p -> p.getAge() <= 20);
        assertEquals(false, result);

        boolean result2 = query.any(p -> p.getAge() >= 25);
        assertEquals(true, result2);
    }

    @Test
    public void testContains() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        Person person = new Person("John", 25);
        boolean result = query.contains(person);
        assertEquals(true, result);

        boolean result2 = query.contains(new Person("Jack", 25));
        assertEquals(false, result2);
    }

    @Test
    public void testElementAt() {
        List<Person> data = initList();
        QueryBuilder<Person> query = new QueryBuilder<>(data);
        Person result = query.elementAt(2);
        Person person2 = data.get(2);
        assertEquals(person2, result);
    }

}
