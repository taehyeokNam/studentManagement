package camp;

import camp.model.Student;

import java.util.List;
import java.util.Scanner;

public class CreateStudent {

    private static List<Student> studentStore;
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";

    static Scanner sc = new Scanner(System.in);

    public static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)

        //Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName , new LinkedList<>()); // 수강생 인스턴스 생성 예시 코드
        boolean flag = true;
        // 기능 구현
        while (flag) {
            System.out.println("필수 과목 3개 / 선택과목 2개");

        }
        System.out.println("수강생 등록 성공!\n");
    }
}
