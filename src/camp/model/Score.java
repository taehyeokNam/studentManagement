package camp.model;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    private int score;
    private int round;
    private String subjectType;
    private char grade;


    public Score(String seq) {
        this.scoreId = seq;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }

    public String getStudentId() { return studentId; }

    public String getSubjectId() { return subjectId; }

    public int getRound() { return round; }

    public int getScore() {
        return score;
    }

    public char getGrade() {return grade; }


    // Setter

    public void setScore(int score) {
        this.score = score;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public void setGrade (String subjectType) {
        switch (subjectType) {
            case "MANDATORY" :
                if (score > 94 && score <= 100) grade = 'A';
                else if (score >89 && score <= 94) grade = 'B';
                else if (score > 79 && score <= 89) grade = 'C';
                else if (score > 69 && score <= 79) grade = 'D';
                else if (score > 59 && score <= 69) grade = 'F';
                else grade = 'N';
                break;
            case "CHOICE" :
                if (score > 89 && score <= 100) grade = 'A';
                else if (score >79 && score <= 89) grade = 'B';
                else if (score > 69 && score <= 79) grade = 'C';
                else if (score > 59 && score <= 69) grade = 'D';
                else if (score > 49 && score <= 59) grade = 'F';
                else grade = 'N';
                break;
            default:
        }
    }
}
