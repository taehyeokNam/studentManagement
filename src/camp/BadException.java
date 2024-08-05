package camp;

public class BadException extends Exception {
    public BadException(String type) {
        // 존재하지 않는 수강생
        // 존재하지 않는 과목
        // 잘못된 점수 범위
        // 잘못된 회차 범위
        switch (type) {
            case "notExistStudent" -> System.out.println("존재하지 않는 수강생입니다.");
            case "notExistSubject" -> System.out.println("존재하지 않는 과목입니다.");
            case "scoreRangeError" -> System.out.println("점수는 1 ~ 100 사이로 입력해야 합니다.");
            case "roundRangeError" -> System.out.println("회차는 1 ~ 10 사이로 입력해야 합니다.");
            default -> {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
