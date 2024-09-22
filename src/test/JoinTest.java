package test;

import main.Classroom;
import main.QueryBuilder;
import main.Student;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class JoinTest {
    
    @Test
    public void joinTest() {
        List<Student> students = Arrays.asList(
            new Student("John", 1),
            new Student("Jane", 2),
            new Student("Jack", 1)
        );

        List<Classroom> classrooms = Arrays.asList(
            new Classroom(1, "Math"),
            new Classroom(2, "Science")
        );

        QueryBuilder<Student> query = new QueryBuilder<>(students);
        List<String> result = query.join(classrooms, s -> s.getClassroomId(), c -> c.getId(), (student, classroom) -> student.getName() + " belongs to " + classroom.getName()).toList();
        assertEquals("John belongs to Math", result.get(0));
    }

    
}
