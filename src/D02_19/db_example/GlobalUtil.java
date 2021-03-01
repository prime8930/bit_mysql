package D02_19.db_example;

import java.util.Scanner;

// 다른 클래스에서 공용으로 사용하는 메소드를 정의한 클래스
public class GlobalUtil {
    static Scanner sc = new Scanner(System.in);

    public static void logo() {
        System.out.println("**************************************");
        System.out.print("   MYSQL 복습");
        System.out.print(" (2021.03.01)");
        System.out.println(" prime8930");
        System.out.println("**************************************");
    }

    public static char menuPrint() {
        System.out.println("---------");
        System.out.println("[0] 프로그램 종료\n"
                + "[1] DB 데이터 추가\n"
                + "[2] DB 데이터 제거\n"
                + "[3] DB 데이터 수정\n"
                + "[4] DB 데이터 조회");
        System.out.println("---------");
        System.out.print(">> ");
        return sc.nextLine().charAt(0);
    }

    public static void ending() {
        System.out.println("**********************************");
        System.out.println("           프로그램 종료");
        System.out.println("**********************************");
    }

    public static int inputNumber(String msg) {
        System.out.print(msg + " :");
        return Integer.parseInt(sc.nextLine());
    }

    public static String inputString(String msg) {
        System.out.print(msg + " :");
        return sc.nextLine();
    }
}
