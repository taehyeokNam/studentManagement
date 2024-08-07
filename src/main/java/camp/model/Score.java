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

    public String getSubjectType() { return subjectType; }


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

    public void setGrade(char grade) {
        this.grade = grade;
    }
}
