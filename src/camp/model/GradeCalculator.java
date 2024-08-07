package camp.model;

import java.util.List;

import static camp.CampManagementApplication.SUBJECT_TYPE_CHOICE;
import static camp.CampManagementApplication.SUBJECT_TYPE_MANDATORY;

public class GradeCalculator {

//    private char grade;
//    private String subjectType;
//    private int score;
//
//    public GradeCalculator(String subjectType, int score) {
//        this.score = score;
//        this.subjectType =
//    }
    public static void deleteScore(List<Score> scoreStore, String studentId) {
        scoreStore.removeIf(score -> score.getStudentId().equals(studentId));
    }
    public static char setGrade(String subjectType, int score) {
        char grade = 'N';

        switch (subjectType) {
            case SUBJECT_TYPE_MANDATORY:
                if (score > 94 && score <= 100) grade = 'A';
                else if (score > 89 && score <= 94) grade = 'B';
                else if (score > 79 && score <= 89) grade = 'C';
                else if (score > 69 && score <= 79) grade = 'D';
                else if (score > 59 && score <= 69) grade = 'F';
                break;
            case SUBJECT_TYPE_CHOICE:
                if (score > 89 && score <= 100) grade = 'A';
                else if (score > 79 && score <= 89) grade = 'B';
                else if (score > 69 && score <= 79) grade = 'C';
                else if (score > 59 && score <= 69) grade = 'D';
                else if (score > 49 && score <= 59) grade = 'F';
                break;
            default:
        }
        return grade;
    }
}
