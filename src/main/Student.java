package main;

public class Student {
    private String name;
    private int classroomId;

    public Student(String name, int classroomId) {
        this.name = name;
        this.classroomId = classroomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassroomId() {
        return classroomId;
    }
    
    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    
}
