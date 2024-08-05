package camp.model;

import java.awt.*;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Subject> subjectArray;
    private List<String> colors;

    public Student(String seq, String studentName, List<Subject> subjectArray, List<String> colors) {
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

    public void addColor(String color) {
        this.colors.add(color);
}

    // Setter
    public void setStudentSubjectArr(Subject subject) {
        subjectArray.add(subject);
    }
}
