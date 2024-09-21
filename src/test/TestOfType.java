package test;
import java.util.List;

import org.junit.Test;

import main.Person;
import main.QueryBuilder;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class TestOfType {
    public List<Object> initTest() {
        List<Object> mixedList = new ArrayList<>();
        mixedList.add(0);
        mixedList.add("One");
        mixedList.add("Two");
        mixedList.add(3);
        mixedList.add(3.05f);
        mixedList.add(new Person("Bill", 28));
        return mixedList;
    }

    @Test
    public void testOfTypeMixed () {
        List<Object> mixedList = initTest();
        QueryBuilder<Object> query = new QueryBuilder<>(mixedList);
        List<Integer> intResult = query.ofType(Integer.class).toList();
        assertEquals("[0, 3]", intResult.toString()); 

        List<Float> floatResult = query.ofType(Float.class).toList();
        assertEquals("[3.05]", floatResult.toString()); 

        List<Person> personResult = query.ofType(Person.class).toList();
        assertEquals("Bill", personResult.get(0).getName()); 
    }
}
