package camp.model;

import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Subject> subjectArray;
    private String colors;

    public void setColors(String colors) {
        this.colors = colors;
    }

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

    public void setStudentName(String newName) {
        this.studentName = newName;
    }

    public List<Subject> getStudentSubjectArr() {
        return subjectArray;
    }

    public String getColors() {
        return colors;
    }

    // Setter

    public void setStudentSubjectArr(Subject subject) {
        subjectArray.add(subject);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", subjectArray=" + subjectArray +
                ", colors=" + colors +
                '}';
    }
}
