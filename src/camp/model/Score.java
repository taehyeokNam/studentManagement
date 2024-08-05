package camp.model;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    private double score;
    private int round;

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

    // Setter

    public void setScore(double score) {
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
}
