package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, new LinkedList<>()); // 수강생 인스턴스 생성 예시 코드
        boolean flag = true;
        // 기능 구현
        while(flag) {
            System.out.println("필수과목 3개 / 선택과목 2개를 반드시 선택해야 합니다.");

            int mandatoryCount = 0;
            int choiceCount = 0;
            int min = 1;
            int max = 5;

            ArrayList<String> mandatoryArray = new ArrayList<>();
            ArrayList<String> choiceArray = new ArrayList<>();

            for (Subject subject : subjectStore) {

                String subjectName = subject.getSubjectName();

                System.out.println("현재까지 고른 과목 => 필수과목 : (" + mandatoryCount + ")" + mandatoryArray + ", 선택과목 (" + choiceCount + ")" + choiceArray);
                System.out.println("(" + subject.getSubjectType() + ") [" + min + "/" + max + "] " + subjectName + "를(을) 수강하시겠습니까? (0 : ㄴㄴ / 1: ㅇㅇ)");

                int input = sc.nextInt();
                if (input == 1) {
                    if (subject.getSubjectType().equals("MANDATORY")) {
                        mandatoryCount++;
                        mandatoryArray.add(subjectName);
                    }
                    else if (subject.getSubjectType().equals("CHOICE")) {
                        choiceCount++;
                        choiceArray.add(subjectName);
                    }

                    student.setStudentSubjectArr(subject);
                }
                if(min == max) {
                    min = 0;
                    max = 4;
                }
                min ++;
            }

            if(mandatoryCount < 3 || choiceCount < 2) System.out.println("수강생 등록 실패ㅜㅜ! \n");
            else {
                studentStore.add(student);
                System.out.println(studentStore);
                System.out.println("수강생 등록 성공!\n");
                flag = false;
            }
        }
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        if (studentStore == null || studentStore.isEmpty()) { // 데이터가 없을 경우
            System.out.println("저장 없음");
        } else {
            for (Student student : studentStore) {
                System.out.println("Id : " + student.getStudentId());
                System.out.println("이름 : " + student.getStudentName());
                System.out.println("----------------------");
            }
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        String studentName = "";
        boolean exist = false;
        // 입력받은 studentId로 실제 존재하는 학생인지 확인
        for(Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                studentName = student.getStudentName();
                exist = true;
                break;
            }
        }

        if(!exist) System.out.println("존재하는 수강생이 아닙니다.");

        // Score 객체 생성
        Score newScore = new Score(sequence(""));

        // studentId 설정
        newScore.setStudentId(studentId);

        // subjectId 설정
        System.out.println("관리할 과목의 번호를 입력하시오...");
        String subjectId = sc.next();
        newScore.setSubjectId(subjectId);

        String subjectName = "";
        for(Subject subject : subjectStore) {
            if(subject.getSubjectId().equals(subjectId)) subjectName = subject.getSubjectName();
        }
        // round 설정
        System.out.println("등록할 회차를 입력하시오...");
        int round = sc.nextInt();
        newScore.setRound(round);


        // score 설정
        System.out.println("등록할 점수를 입력하시오...");
        double score = sc.nextDouble();
        newScore.setScore(score);

        System.out.println("시험 점수를 등록합니다...");

        // 기능 구현
        scoreStore.add(newScore);
        System.out.println("\n" + studentName + " 수강생 " + subjectName + " 과목 " + round + "회차" + score + "점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        if (scoreStore == null || scoreStore.isEmpty()) { // 데이터가 없을 경우
            System.out.println("저장 없음");
        } else {
            for (Score score : scoreStore) {
                // System.out.println("등급 : " + score.getScoreId()); // 이후 등급 기준 정해지면
            }
        }
        System.out.println("\n등급 조회 성공!");
    }

}