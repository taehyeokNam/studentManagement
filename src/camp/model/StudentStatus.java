package camp.model;

public enum StudentStatus {
    Green(1),
    Yellow(2),
    Red(3);
    private final int num;

    StudentStatus(int i) {
        this.num = i;
    }

    public int getName() {
        return num;
    }
}
