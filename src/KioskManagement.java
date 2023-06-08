package KioskManagement.src;
import java.util.Scanner;

public class KioskManagement {
    MenuContext menuContext = new MenuContext();

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
            waitOrder();
        } else if (input == 2) {
            getCompleteOrderList();
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

    //0-1 대기 목록 처리
    private static void waitOrder(){
        System.out.println("1. 대기 주문 목록 조회");
        System.out.println("2. 대기 주문 완료 처리");

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        switch (num){
            case 1:
                getWaitOrderList();
            case 2:
                HandleWaitOrder();
            default:
                System.out.println("잘못된 입력입니다.");
                waitOrder();
                break;
        }
    }

    //0-1-1대기 주문 목록 조회
    private static void getWaitOrderList() {
        menuContext.printWaitOrders();
        handleKioskManagementInput();
    }

    //0-1-2대기 주문 완료 처리
    private static void HandleWaitOrder() {
        menuContext.printWaitOrders();
        menuContext.getCompleteOrder();
        handleKioskManagementInput();
    }

    //0-2완료 주문 목록 조회
    private static void getCompleteOrderList(){
        menuContext.printCompleteOrders();
        handleKioskManagementInput();
    }
}