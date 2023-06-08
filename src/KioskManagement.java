
import java.util.Scanner;

public class KioskManagement {

    static void displayKioskManagement() {
        System.out.println("[ 키오스크 관리 프로그램 ]");
        System.out.println("1. 대기주문 목록        2. 완료주문 목록        3. 상품 생성        4. 상품 삭제");

        handleKioskManagementInput();
    }

    private static void handleKioskManagementInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            // 대기주문 목록 메서드
        } else if (input == 2) {
            // 완료주문 목록 메서드
        } else if (input == 3) {
            // 상품 생성 메서드
        } else if (input == 4) {
            // 상품 삭제 메서드
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleKioskManagementInput();
        }
    }
}