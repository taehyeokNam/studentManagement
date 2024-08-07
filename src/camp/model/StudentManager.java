package camp.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static camp.CampManagementApplication.*;
import static camp.model.GradeCalculator.deleteScore;
import static camp.model.InputUtils.getStudentId;

public class StudentManager {
// 수강생 등록
public static void createStudent(List<Subject> subjectStore, List<Student> studentStore) {
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
            StudentStatus status = StudentStatus.Green;
            student.setStatus(status);
            break;
        } else if (color == 2) {
            StudentStatus status = StudentStatus.Red;
            student.setStatus(status);
            break;
        } else if (color == 3) {
            StudentStatus status = StudentStatus.Yellow;
            student.setStatus(status);
            break;
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
            String subjectId = subject.getSubjectId();

            System.out.println("현재까지 고른 과목 => 필수과목 : (" + mandatoryCount + ")" + mandatoryArray + ", 선택과목 (" + choiceCount + ")" + choiceArray);
            System.out.println("(" + subject.getSubjectType() + ") [" + min + "/" + max + "] " + subjectName + "를(을) 수강하시겠습니까? (0 : no / 1: yes)");


            int input = sc.nextInt();
            if (input == 1) {
                if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                    mandatoryCount++;
                    mandatoryArray.add(subjectName);
                    student.setMandatorySubjectArr(subjectId);
                } else if (subject.getSubjectType().equals("CHOICE")) {
                    choiceCount++;
                    choiceArray.add(subjectName);
                    student.setChoiceSubjectArr(subjectId);
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
}
    // 수강생 목록 조회
    public static void inquireStudent(List<Student> studentStore) {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        if (studentStore == null || studentStore.isEmpty()) { // 데이터가 없을 경우
            System.out.println("저장 없음");
        } else {
            for (Student student : studentStore) {
                System.out.println("ID: " + student.getStudentId());
                System.out.println("이름: " + student.getStudentName());
                System.out.println("상태: " + student.getStatus());
                System.out.println("선택한 과목명 :" + student.getStudentSubjectArr().toString());
                System.out.println("--------------------------");
            }
            System.out.println("\n수강생 목록 조회 성공!");
        }


    }

    public static void studentInformation(List<Student> studentStore) throws InterruptedException  {
        Student getStudent = getStudentId(studentStore);
        for (Student student : studentStore){
            if (student.getStudentId().equals(getStudent.getStudentId())){
                System.out.println("student = " + student);
                break;
            }
        }
    }

    public static void editStudentInformation(List<Student> studentStore, List<Subject> subjectStore) throws InterruptedException {
        System.out.println("수정할 수강생 고유 번호를 입력해주세요");
        Student student = getStudentId(studentStore);


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
                    updateSubjects(student, SUBJECT_TYPE_MANDATORY, true, subjectStore);
                    break;
                case 4:
                    updateSubjects(student, SUBJECT_TYPE_CHOICE, false, subjectStore);
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
            StudentStatus status;

            if (statusChoice == 0) {
                break;
            } else {
                switch (statusChoice) {

                    case 1:
                        status = StudentStatus.Green;
                        student.setStatus(status);
                        System.out.println("상태가 Green으로 변경되었습니다.");
                        break;
                    case 2:
                        status = StudentStatus.Red;
                        student.setStatus(status);
                        System.out.println("상태가 Red로 변경되었습니다.");
                        break;
                    case 3:
                        status = StudentStatus.Yellow;
                        student.setStatus(status);
                        System.out.println("상태가 Yellow로 변경되었습니다.");
                        break;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                }
            }
        }
    }

    public static void updateSubjects(Student student, String subjectType, boolean isMandatory, List<Subject> subjectStore) {
        List<Subject> subjects = isMandatory ? getMandatorySubjects(subjectStore) : getChoiceSubjects(subjectStore);
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

    private static List<Subject> getMandatorySubjects(List<Subject> subjectStore) {
        List<Subject> mandatorySubjects = new ArrayList<>();
        for (Subject subject : subjectStore) { // subjectStore에 있는 과목을 전체를 subject변수에 담음
            if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) { // 그중에서 SUBJECT_TYPE_MANDATORY 해당하는 과목만 mandatorySubjects에 담고 리턴
                mandatorySubjects.add(subject);
            }
        }
        return mandatorySubjects;
    }

    private static List<Subject> getChoiceSubjects(List<Subject> subjectStore) {
        List<Subject> choiceSubjects = new ArrayList<>();
        for (Subject subject : subjectStore) { // subjectStore에 있는 과목을 전체를 subject변수에 담음
            if (subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {// 그중에서 SUBJECT_TYPE_CHOICE 해당하는 과목만 mandatorySubjects에 담고 리턴
                choiceSubjects.add(subject);
            }
        }
        return choiceSubjects;
    }

    public static void studentRemove(List<Student> studentStore, List<Score> scoreStore) throws InterruptedException {

        Student getStudent = getStudentId(studentStore);

        for (Student student : studentStore)
            if (student.getStudentId().equals(getStudent.getStudentId())) {
                System.out.println(student);
                System.out.println("수강생을 삭제 하시겠습니까?(예:1 or 아니요:2");
                int next = sc.nextInt();
                if (next == 1) {
                    deleteScore(scoreStore, student.getStudentId());
                    studentStore.remove(student);
                    System.out.println("삭제 완료!");
                    break;
                } else if (next == 2) {
                    System.out.println("수강생 삭제가 취소 되었습니다.");
                    break;
                }
            }


    }

    public static void studentBYColor(List<Student> studentStore) {
        System.out.println("조회할 학생의 상태를 선택하세요 (1: Green, 2: Red, 3: Yellow: )");
        int statusChoice = sc.nextInt();
        StudentStatus status;

        switch (statusChoice) {
            case 1 -> status = StudentStatus.Green;
            case 2 -> status = StudentStatus.Red;
            case 3 -> status = StudentStatus.Yellow;
            default -> {
                System.out.println("잘못된 입력입니다.");
                return;
            }
        }
        System.out.println("상태가 " + status + "인 수강생 목록");
        boolean found = false; // 선택된 상태의 수강생이 존재하는지 추적하기 위해 found 변수를 false로 초기화
        int index = 0;

        while (index < studentStore.size()) { // 현재 인덱스에 해당하는 student객체를 가져옴
            Student student = studentStore.get(index);
            if (student.getStatus().equals(status)) { // 현재 student 객체의 상태가 선택된 상태와 일치하는지 확인
                System.out.println("ID: " + student.getStudentId() + " 이름: " + student.getStudentName());
                // 일치하는 경우, 수강생의 ID와 이름을 출력
                found = true; // 일치하는 수강생이 있다는 것을 알기 위해 true로 설정
            }
            index++; // 다음 인덱스 이동
        }
        if (!found) { // 일치하는 수강생을 찾지 못한경우
            System.out.println("해당 상태의 수강생이 존재하지 않습니다.");
        } else { // 일치하는 수강생을 찾지 못한경우가 아니면 메세지 출력
            System.out.println("상태별 수강생 조회 성공");
        }
    }
}
