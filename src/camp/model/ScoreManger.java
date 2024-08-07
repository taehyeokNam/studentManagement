package camp.model;

import java.util.List;
import java.util.Scanner;

import static camp.CampManagementApplication.*;
import static camp.model.InputUtils.*;

public class ScoreManger {
    public static Scanner sc = new Scanner(System.in);
    public static void createScore(List<Subject> subjectStore, List<Score> scoreStore, List<Student> studentStore) throws InterruptedException  {
        Student student = getStudentId(studentStore); // 관리할 수강생 고유 번호
        String studentName = "";

        // Score 객체 생성
        Score newScore = new Score(sequence(""));

        // studentId 설정
        newScore.setStudentId(student.getStudentId());

        // subjectId 설정
        String subjectId = getSubjectId(subjectStore);
        newScore.setSubjectId(subjectId);

        String subjectName = "";
        String subjectType = "";

        for (Subject subject : subjectStore) {
            if (subject.getSubjectId().equals(subjectId)) {
                subjectName = subject.getSubjectName();
                subjectType = subject.getSubjectType();
            }
        }
        // 회차 입력
        int round = getRoundId();
        newScore.setRound(round);

        // 점수 입력
        int score = inputScore();
        newScore.setScore(score);

        // 점수 등급 산정
        char grade = GradeCalculator.setGrade(subjectType, score);
        newScore.setGrade(grade);

        System.out.println("시험 점수를 등록합니다...");

        // 기능 구현
        scoreStore.add(newScore);
        System.out.println("\n" + studentName + " 수강생 " + subjectName + " 과목 " + round + "회차 " + score + "점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    public static void updateRoundScoreBySubject(List<Score> scoreStore,List<Subject> subjectStore, List<Student> studentStore) throws InterruptedException  {
        Student student = getStudentId(studentStore); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)

        // 과목 입력
        String subjectId = getSubjectId(subjectStore);

        // 회차 입력
        int round = getRoundId();

        // 점수 입력
        int updateScore = inputScore();

        System.out.println("시험 점수를 수정합니다...");

        for (Score score : scoreStore) {
            String scoreStudentId = score.getStudentId();
            String scoreSubjectId = score.getSubjectId();
            int scoreRonud = score.getRound();

            if (scoreStudentId.equals(student.getStudentId()) && scoreSubjectId.equals(subjectId) && scoreRonud == round) {
                score.setScore(updateScore);
                System.out.println(score.getScore());
            }
        }

        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    public static void inquireRoundGradeBySubject(List<Score> scoreStore, List<Student> studentStore, List<Subject> subjectStore) throws InterruptedException  {
        Student student = getStudentId(studentStore);                  // 관리할 수강생 고유 번호
        String studentSubject = getSubjectId(subjectStore);             // 관리할 과목 고유 번호
        int round = 1;
        char grade = ' ';                                   // 등급 저장 변수

        System.out.println("회차별 등급을 조회합니다...");
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(student.getStudentId()) &&
                    score.getSubjectId().equals(studentSubject)) {
                grade = score.getGrade();

                // 기능 구현 (조회할 특정 과목)
                System.out.println("==============================");
                System.out.println("회차 = " + round++);
                System.out.println("등급 = " + grade);
                System.out.println("==============================");
            }
        }

        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

    public static void average(List<Student> studentStore, List<Score> scoreStore) throws InterruptedException  {
        System.out.println("평균 점수 조회");
        // 전체 수강생  과목별 평균 등급
        Student student = getStudentId(studentStore); // 학생 번호

        List<Subject> subjects = student.getStudentSubjectArr();

        System.out.println("수강생 명 : " + student.getStudentName());

        for (Subject subject : subjects) {
            int totalScore = 0; // 총 점수
            int count = 0; // 회차 계산용

            for (Score score1 : scoreStore) {
                if (score1.getSubjectId().equals(subject.getSubjectId()) && score1.getStudentId().equals(student.getStudentId())) {
                    totalScore += score1.getScore();
                    count++;
                }
            }

            if (count > 0) {
                int averageScore = totalScore / count;
                char averageGrade = GradeCalculator.setGrade(subject.getSubjectType(), averageScore);

                System.out.println("------------------");
                System.out.println("과목명 : " + subject.getSubjectName());
                System.out.println("평균 등급 : " + averageGrade);
            }
        }

    }

    public static void studentMandatoryAverage(List<Subject> subjectStore, List<Student> studentStore, List<Score> scoreStore) {
        System.out.println("조회할 수강생들의 상태를 입력하시오... (1: Green / 2: Red / 3: Yellow)");
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

        System.out.println(status + "상태 수강생들의 필수과목 평균 등급 조회...");
        for (Subject subject : subjectStore) {
            // 필수 과목만
            int sum = 0;
            int count = 0;
            int studentCount = 0;
            if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                for (Student student : studentStore) {
                    // 특정 상태 수강생들
                    if (student.getStatus().equals(status)) {
                        studentCount++;
                        for (Score score : scoreStore) {
                            if (score.getSubjectId().equals(subject.getSubjectId())) {
                                sum += score.getScore();
                                count++;
                            }
                        }
                    }
                }

                if (count != 0) {
                    char grade = GradeCalculator.setGrade(SUBJECT_TYPE_MANDATORY, sum / count);
                    System.out.println("------------------");
                    System.out.println("수강생 상태 : " + status + " " + studentCount + " 명");
                    System.out.println("과목명 : " + subject.getSubjectName());
                    System.out.println("평균 등급 : " + grade);
                } else {
                    System.out.println("------------------");
                    System.out.println("수강생 상태 : " + status + " " + studentCount + " 명");
                    System.out.println("과목명 : " + subject.getSubjectName());
                    System.out.println("NO Grade");
                }
            }
        }
        System.out.println("------------------");
    }


}
