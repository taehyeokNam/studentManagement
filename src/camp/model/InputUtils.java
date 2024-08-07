package camp.model;

import java.util.List;
import java.util.Scanner;

import static camp.CampManagementApplication.displayMainView;

public class InputUtils {
    public static Scanner sc = new Scanner(System.in);
    // 수강생 입력받기, 수강생 존재 여부 체크
    public static Student getStudentId(List<Student> studentStore) throws InterruptedException  {
        
        System.out.print("\n수강생의 번호를 입력하시오... (quit를 입력하면 처음으로 돌아갑니다.)");
        String studentId = sc.next();
        if(studentId.equals("quit")) displayMainView();
        boolean exist = false;
        Student getStudent = new Student();

        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                exist = true;
                getStudent = student;
                break;
            }
        }
        if (!exist) {
                System.out.println("존재하지 않는 수강생입니다. 입력으로 다시 돌아갑니다.");
                getStudentId(studentStore);
        }

        return getStudent;
    }

    // 과목 입력받기, 입력 받은 과목 존재 여부 체크
    public static String getSubjectId(List<Subject> subjectStore) throws  InterruptedException {
        System.out.println("\n과목의 번호를 입력하시오... (quit를 입력하면 처음으로 돌아갑니다.)");
        String subjectId = sc.next();
        if(subjectId.equals("quit")) displayMainView();
        boolean exist = false;
        for (Subject subject : subjectStore) {
            if (subject.getSubjectId().equals(subjectId)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
                System.out.println("존재하지 않는 과목입니다. 입력으로 다시 돌아갑니다.");
                getSubjectId(subjectStore);
        }
        return subjectId;
    }

    // 회차 입력받기, 입력 받은 회차 범위 체크
    public static int getRoundId() throws  InterruptedException {
        System.out.println("\n회차를 입력하시오... (0을 입력하면 처음으로 돌아갑니다.)");
        int roundId = sc.nextInt();
        if(roundId == 0) displayMainView();
        if (roundId > 10 || roundId < 1) {
                System.out.println("회차는 1 ~ 10 사이로 입력해야 합니다.");
                getRoundId();
        }

        return roundId;
    }

    public static int inputScore() throws  InterruptedException {
        System.out.println("\n점수를 입력하시오... (999를 입력하면 처음으로 돌아갑니다.)");
        int inputScore = sc.nextInt();
        if(inputScore == 999) displayMainView();
        if (inputScore > 100 || inputScore < 0) {
                System.out.println("점수는 1 ~ 100 사이로 입력해야 합니다.");
                inputScore();
        }

        return inputScore;
    }
}
