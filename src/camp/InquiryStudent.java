package camp;

import camp.model.Student;

import java.util.List;

public class InquiryStudent {
    private static List<Student> studentStore; // static 메모리 낭비 주의
    // 수강생 정보 저장하는데 사용
    // 프로그램의 모든 인스턴스가 공유

    public InquiryStudent(List<Student> studentStore) {
        this.studentStore = studentStore;
    }
    // inquiryStudent 클래스의 생성자
    // 외부에서 studentStore 리스트를 전달받아 inquiryStuent 클래스의 인스턴스 변수
    // studentStore 초기화

    public void inquiryStudent() {
        if (studentStore == null) { // if문으로 처리 안해두면 조요 시기 알기가 어려움
            System.out.println("저장 없음");
        } else {
            for (Student student : studentStore) {
                System.out.println(student.getStudentId());
                System.out.println(student.getStudentName());
                System.out.println(student.getStudentSubjectArr());
            }
        }

    }

}
