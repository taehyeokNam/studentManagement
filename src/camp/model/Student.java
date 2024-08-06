package camp.model;

import java.awt.*;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Subject> subjectArray;
    private String colors;

    public Student(String seq, String studentName, List<Subject> subjectArray, String colors) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjectArray = subjectArray;
        this.colors = colors;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Subject> getStudentSubjectArr() {
        return subjectArray;
    }

    public String getColors() {
        return colors;
    }

    public void addColor(String color) {
        this.colors = color;
}

    // Setter
    public void setStudentSubjectArr(Subject subject) {
        subjectArray.add(subject);
    }
}
