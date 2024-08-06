package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.awt.*;
import java.util.*;
import java.util.List;

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
    private static Map<String, Object> studentInformation;

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
        studentInformation = new HashMap<>();
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
            System.out.println("3. 수강생 상세정보 조회");
            System.out.println("4. 수강생 정보 수정");
            System.out.println("5. 수강생 삭제");
            System.out.println("6. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> studentInformation(); // 수강생 상세정보 조회
                case 4 -> editStudentInformation(); // 수강생 정보 수정
                case 5 -> studentRemove(); // 수강생 삭제
                case 6 -> flag = false; // 메인 화면 이동
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
        String sequence = sequence(INDEX_TYPE_STUDENT);


        Student student = new Student(sequence, studentName, new LinkedList<>()); // 수강생 인스턴스 생성 예시 코드
        boolean flag = true;

        // 상태 종류 추가
        while (true) {
            System.out.print("상태 종류를 선택하세요: 1) Green, 2) Red, 3) Yellow");
            int color = sc.nextInt();
            if (color == 1) {
                student.setColors("Green");
                break;
            } else if (color == 2) {
                student.setColors("Red");
                break;
            } else if (color == 3) {
                student.setColors("Yellow");
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
        // 기능 구현
        while (flag) {
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
                System.out.println("(" + subject.getSubjectType() + ") [" + min + "/" + max + "] " + subjectName + "를(을) 수강하시겠습니까? (0 : no / 1: yes)");


                int input = sc.nextInt();
                if (input == 1) {
                    if (subject.getSubjectType().equals("MANDATORY")) {
                        mandatoryCount++;
                        mandatoryArray.add(subjectName);
                    } else if (subject.getSubjectType().equals("CHOICE")) {
                        choiceCount++;
                        choiceArray.add(subjectName);
                    }


                    student.setStudentSubjectArr(subject);
                }
                if (min == max) {
                    min = 0;
                    max = 4;
                }
                min++;
            }
            if (mandatoryCount < 3 || choiceCount < 2) System.out.println("수강생 등록 실패ㅜㅜ! \n");
            else {
                studentStore.add(student);
                System.out.println(studentStore);
                System.out.println("수강생 등록 성공!\n");
                flag = false;
            }
        }
        for (Student studentInformationSave : studentStore) {
            studentInformation.put(sequence, studentInformationSave);
        }
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        for (Student student : studentStore) {
            System.out.println("ID: " + student.getStudentId() + " 이름: " + student.getStudentName());
            System.out.println("ID: " + student.getStudentId());
            System.out.println("이름: " + student.getStudentName());
            System.out.println("상태: " + student.getColors());
            System.out.println("선택한 과목명 :" + student.getStudentSubjectArr().toString());
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
        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                studentName = student.getStudentName();
                exist = true;
                break;
            }
        }

        if (!exist) System.out.println("존재하는 수강생이 아닙니다.");

        // Score 객체 생성
        Score newScore = new Score(sequence(""));

        // studentId 설정
        newScore.setStudentId(studentId);

        // subjectId 설정
        System.out.println("관리할 과목의 번호를 입력하시오...");
        String subjectId = sc.next();
        newScore.setSubjectId(subjectId);

        String subjectName = "";
        for (Subject subject : subjectStore) {
            if (subject.getSubjectId().equals(subjectId)) subjectName = subject.getSubjectName();
        }
        // round 설정
        System.out.println("등록할 회차를 입력하시오...");
        int round = sc.nextInt();
        newScore.setRound(round);


        // score 설정
        System.out.println("등록할 점수를 입력하시오...");
        int score = sc.nextInt();
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
        System.out.println("\n등급 조회 성공!");
    }

    private static void studentInformation() {
        boolean value = true;
        do {
            System.out.println("조회할 수강생 고유 번호를 입력해주세요");
            String studentUniqueNumber = sc.next();
            Object informationSaveValue = studentInformation.get(studentUniqueNumber);
            if (informationSaveValue == null) {
                System.out.println("고유 번호가 존재하지 않습니다. 다시 입력해주세요");
                value = false;
            } else {
                System.out.println("학생 상세조회 = " + informationSaveValue);
                value = true;
            }
        } while (!value);
    }


    private static void editStudentInformation() {
        System.out.println("수정할 수강생 고유 번호를 입력해주세요");
        String studentUniqueNumber = sc.next();
        Student student = (Student) studentInformation.get(studentUniqueNumber);

        if (student == null) {
            System.out.println("해당 고유 번호의 수강생이 존재하지 않습니다.");
            return;
        }

        boolean editing = true;
        while (editing) {
            System.out.println("수정할 항목을 선택하세요:");
            System.out.println("1. 이름");
            System.out.println("2. 상태");
            System.out.println("3. 필수과목");
            System.out.println("4. 선택과목");
            System.out.println("5. 수정을 완료하고 종료");

            int choice = sc.nextInt();
            sc.nextLine();  // consume the newline

            switch (choice) {
                case 1:
                    System.out.print("새로운 이름을 입력하세요: ");
                    String newName = sc.nextLine();
                    student.setStudentName(newName);
                    System.out.println("이름이 성공적으로 수정되었습니다.");
                    break;
                case 2:
                    updateStatus(student);
                    break;
                case 3:
                    updateSubjects(student, SUBJECT_TYPE_MANDATORY, true);
                    break;
                case 4:
                    updateSubjects(student, SUBJECT_TYPE_CHOICE, false);
                    break;
                case 5:
                    editing = false;
                    System.out.println("수정이 완료되었습니다.");
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void updateStatus(Student student) {
        while (true) {
            System.out.print("새로운 상태를 선택하세요 (1: Green, 2: Red, 3: Yellow, 종료: 0): ");
            int statusChoice = sc.nextInt();
            sc.nextLine();  // consume the newline

            if (statusChoice == 0) {
                break;
            }else {
                switch (statusChoice) {
                    case 1:
                        student.setColors("Green");
                        System.out.println("상태가 Green으로 변경되었습니다.");
                        break;
                    case 2:
                        student.setColors("Red");
                        System.out.println("상태가 Red로 변경되었습니다.");
                        break;
                    case 3:
                        student.setColors("Yellow");
                        System.out.println("상태가 Yellow로 변경되었습니다.");
                        break;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                }
            }
        }
    }

    private static void updateSubjects(Student student, String subjectType, boolean isMandatory) {
        List<Subject> subjects = isMandatory ? getMandatorySubjects() : getChoiceSubjects();
        List<Subject> currentSubjects = student.getStudentSubjectArr();


        System.out.println("현재 선택된 과목: " + currentSubjects);
        System.out.println(subjectType + " 과목 목록:");

        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i).getSubjectName());
        }
        // 필수 과목들을 삭제하고 입력을 받기 위해 기존 필수 과목 제거
        if (isMandatory) {
            Iterator<Subject> iterator = currentSubjects.iterator(); // currentSubjects 리스트에서 Iterator를 생성한다. Iterator는 리스트를 순회하며 요소를 접근함
            while (iterator.hasNext()) { // Iterator의 hasNext() 메서드를 호출하여 리스트에 다음 요소가 있는지 확인합니다. 있으면 true를 반환, 없으면 false를 반환함.
                Subject subject = iterator.next(); //Iterator의 next() 메서드를 호출하여 현재 요소를 가져온다.
                if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) { // Type에 SUBJECT_TYPE_MANDATORY 갔은게 있으면 true
                    iterator.remove(); // 해당 과목을 제거
                }
            }
        } else {
            Iterator<Subject> iterator = currentSubjects.iterator();
            while (iterator.hasNext()) {
                Subject subject = iterator.next();
                if (subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
                    iterator.remove();
                }
            }
        }

        while (true) {
            System.out.print("추가할 과목 번호를 입력하세요 (종료: 0): ");
            int subjectChoice = sc.nextInt();

            if (subjectChoice > 0 && subjectChoice <= subjects.size()) { // 번호가 0보다 크고과목에 수 보다 작거나 같은지 확인
                Subject selectedSubject = subjects.get(subjectChoice - 1); // 번호에서 -1을 해야 해당 과목이 지정 됨 List index 는 0번부터 시작
                if (!currentSubjects.contains(selectedSubject)) { // 선택한 과목이 똑같은게 없는지 확인 없으면 ture
                    currentSubjects.add(selectedSubject); // 선택한 과목 넣어주기
                    System.out.println(selectedSubject.getSubjectName() + " 과목이 추가되었습니다.");
                } else {
                    System.out.println("이미 선택된 과목입니다."); // 이미 선택한 과목이면 다시 질문
                }
            } else if (subjectChoice == 0) { // 0번이면 반복문 종료
                break;
            } else {
                System.out.println("잘못된 입력입니다."); // 숫가 아닌 다른걸 입력하거나 해당 과목번호 외에 번호를 입력할때 나옴
            }
        }

        System.out.println(subjectType + " 과목이 성공적으로 수정되었습니다."); // 수정이 안료되면 나옴
    }

    private static List<Subject> getMandatorySubjects() {
        List<Subject> mandatorySubjects = new ArrayList<>();
        for (Subject subject : subjectStore) { // subjectStore에 있는 과목을 전체를 subject변수에 담음
            if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) { // 그중에서 SUBJECT_TYPE_MANDATORY 해당하는 과목만 mandatorySubjects에 담고 리턴
                mandatorySubjects.add(subject);
            }
        }
        return mandatorySubjects;
    }

    private static List<Subject> getChoiceSubjects() {
        List<Subject> choiceSubjects = new ArrayList<>();
        for (Subject subject : subjectStore) { // subjectStore에 있는 과목을 전체를 subject변수에 담음
            if (subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {// 그중에서 SUBJECT_TYPE_CHOICE 해당하는 과목만 mandatorySubjects에 담고 리턴
                choiceSubjects.add(subject);
            }
        }
        return choiceSubjects;
    }

    private static void studentRemove(){
        System.out.println("삭제할 수강생 고유 번호를 입력해주세요");
        String studentUniqueNumber = sc.next();
        Object informationSaveValue = studentInformation.get(studentUniqueNumber);

        if (informationSaveValue != null) {
            for (int i = 0; i < studentStore.size(); i++) {
                if (studentStore.get(i).getStudentId().equals(studentUniqueNumber)){
                    System.out.println(informationSaveValue);
                    System.out.println("수강생을 삭제 하시겠습니까?(예:1 or 아니요:2");
                    int next = sc.nextInt();
                    if (next == 1) {
                        studentInformation.remove(studentUniqueNumber);
                        studentStore.remove(i);
                        System.out.println("삭제 완료!");
                        break;
                    } else if (next == 2){
                        System.out.println("수강생 삭제가 취소 되었습니다.");
                        break;
                    }
                }
            }
        }else {
            System.out.println("해당 고유 번호의 수강생이 존재하지 않습니다.");
            return;
        }
    }
}


