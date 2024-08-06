package camp.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Subject> subjectArray;
    private String colors;

    private List<String> mandatorySubjectArr = new ArrayList<>();

    private List<String> choiceSubjectArr = new ArrayList<>();

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

    public List<String> getMandatorySubjectArr() {
        return mandatorySubjectArr;
    }

    public  List<String> getChoiceSubjectArr() {
        return choiceSubjectArr;
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

    public void setMandatorySubjectArr(String subjectId) {
        mandatorySubjectArr.add(subjectId);
    }

    public void setChoiceSubjectArr(String subjectId) {
        choiceSubjectArr.add(subjectId);
    }
}
