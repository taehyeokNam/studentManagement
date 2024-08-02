package camp.model;

import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Subject> subjectArray;

    public Student(String seq, String studentName, List<Subject> subjectArray) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjectArray = subjectArray;
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

    // Setter
    public void setStudentSubjectArr(Subject subject) {
        subjectArray.add(subject);
    }
}
