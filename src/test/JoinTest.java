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

    @Test
    public void leftJoinTest() {
        List<Student> students = Arrays.asList(
            new Student("John", 1),
            new Student("Jane", 2),
            new Student("Jack", 3) // Không có classroomId tương ứng
        );

        List<Classroom> classrooms = Arrays.asList(
            new Classroom(1, "Math"),
            new Classroom(2, "Science")
        );

        QueryBuilder<Student> query = new QueryBuilder<>(students);

        // Left Join sinh viên với phòng học
        List<String> result = query
            .leftJoin(classrooms,
                s -> s.getClassroomId(), // Khóa bên trái (classroomId)
                c -> c.getId(), // Khóa bên phải (id)
                (student, classroomOpt) -> {
                    if (classroomOpt.isPresent()) {
                        return student.getName() + " into " + classroomOpt.get().getName();
                    } else {
                        return student.getName() + " has no classroom.";
                    }
                }
            )
            .toList();

        assertEquals("John into Math", result.get(0));

    }

    
}
