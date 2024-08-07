package camp.model;


import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Subject> subjectArray;
    private StudentStatus status;

    public void setStatus(StudentStatus colors) {
        this.status = colors;
    }
    private List<String> mandatorySubjectArr = new ArrayList<>();

    private List<String> choiceSubjectArr = new ArrayList<>();

    public  Student() {}
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

    public void setStudentName(String newName) {
        this.studentName = newName;
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

    public StudentStatus getStatus() {
        return status;
    }
 // Setter

    public void setStudentSubjectArr(Subject subject) {
        subjectArray.add(subject);
    }

    @Override
    public String toString() {
        return "\n" +
                "고유 번호 : " + studentId + "\n" +
                "이름 : " + studentName + "\n" +
                "과목 목록 : " + subjectArray + "\n" +
                "상태 : " + status;
    }

    public void setMandatorySubjectArr(String subjectId) {
        mandatorySubjectArr.add(subjectId);
    }

    public void setChoiceSubjectArr(String subjectId) {
        choiceSubjectArr.add(subjectId);
    }
}
