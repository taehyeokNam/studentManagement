package camp.model;

public class Score {
    private String scoreId;
    private String studentId;

    public Score(String seq, String studentId) {
        this.scoreId = seq;
        this.studentId = studentId;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }
}
