
import java.util.List;
import java.util.Scanner;

public class ShakeShackBurgerApplication {
    static MenuContext menuContext;
    static KioskManagement kioskManagement;
    public static void main(String[] args) {
        kioskManagement = new KioskManagement();
        menuContext = new MenuContext();
        displayMainMenu();
    }

    private static void displayMainMenu() {
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n");

        System.out.println("[ SHAKESHACK MENU ]");
        List<Menu> mainMenus = menuContext.getMenus("Main");
        int nextNum = printMenu(mainMenus, 1);

        System.out.println("[ ORDER MENU ]");
        List<Menu> orderMenus = menuContext.getMenus("Order");
        printMenu(orderMenus, nextNum);

        handleMainMenuInput();
    }

    static int printMenu(List<Menu> menus, int num) {
        for (int i=0; i<menus.size(); i++) {
            System.out.println(num++ + ". " + menus.get(i).name + "   | " + menus.get(i).description);
        }
        return num;
    }

    private static void handleMainMenuInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                displayItemMenu("Burgers");
                break;
            case 2:
                displayItemMenu("Frozen Custard");
                break;
            case 3:
                displayItemMenu("Drinks");
                break;
            case 4:
                displayItemMenu("Beer");
                break;
            case 5:
                displayOrderMenu();
                break;
            case 6:
                handleCancelMenuInput();
                break;
            case 7:
                displayRecentOrders();
                break;
            case 0:
                kioskManagement.displayMainMenu();
                displayMainMenu();
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                handleMainMenuInput();
                break;
        }
    }

    private static void displayItemMenu(String key) {
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.\n");

        System.out.println("[ " +key+ " MENU ]");
        List<Item> Items = menuContext.getMenuItems(key);
        printMenuItems(Items);

        handleMenuItemInput(Items);
    }

    private static void handleMenuItemInput(List<Item> items) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input >= 1 && input <= items.size()) {
            Item selectedItem = items.get(input-1);
            displayConfirmation(selectedItem);
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleMenuItemInput(items);
        }
    }

    static void printMenuItems(List<Item> items) {
        for (int i=0; i<items.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + items.get(i).name + "   | " + items.get(i).price + " | " + items.get(i).description);
        }
    }

    private static void displayConfirmation(Item menuItem) {
        System.out.println(menuItem.name + "   | " + menuItem.price + " | " + menuItem.description);
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        handleConfirmationInput(menuItem);
    }

    private static void handleConfirmationInput(Item menuItem) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            menuContext.addToCart(menuItem);
            System.out.println("장바구니에 추가되었습니다.");
            displayMainMenu();
        } else if (input == 2) {
            displayMainMenu();
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleConfirmationInput(menuItem);
        }
    }

    private static void displayOrderMenu() {
        Scanner scanner = new Scanner(System.in);
        checkCartEmpty();

        System.out.println("위와 같이 주문 하시겠습니까?\n");
        System.out.println("[ Total ]");
        System.out.println("W " + menuContext.getTotalPrice() + "\n");

        System.out.println("요청사항을 입력하세요");
        String input = scanner.nextLine();
        menuContext.addRequest(input);
        System.out.println("1. 주문      2. 메뉴판");

        handleOrderMenuInput();
    }

    private static void checkCartEmpty(){
        List<Item> cart = menuContext.getCart();
        if (cart == null){
            System.out.println("주문목록이 존재하지 않습니다.");
            System.out.println("메뉴로 돌아갑니다.");
            System.out.println();
            displayMainMenu();
        }
        menuContext.displayCart(cart);
    }

    private static void handleOrderMenuInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            displayOrderComplete();
        } else if (input == 2) {
            displayMainMenu();
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleOrderMenuInput();
        }
    }

    private static void displayOrderComplete() {
        int orderNumber = menuContext.generateOrderNumber();
        System.out.println("주문이 완료되었습니다!\n");
        System.out.println("대기번호는 [ " + orderNumber + " ] 번 입니다.");
        menuContext.addWaitOrder(orderNumber);
        resetCartAndDisplayMainMenu();
    }

    private static void resetCartAndDisplayMainMenu() {
        menuContext.resetCart();
        System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        displayMainMenu();
    }

    private static void handleCancelMenuInput() {
        checkCartEmpty();
        System.out.println("주문을 취소하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        handleCancelConfirmationInput();
    }

    private static void displayRecentOrders() {
        if(menuContext.getWaitOrders().isEmpty() && menuContext.getCompleteOrders().isEmpty()){
            System.out.println("주문이 없습니다.");
            displayMainMenu();
        }
        menuContext.printRecentOrders();
        handleRecentOrderInput();
    }

    private static void handleRecentOrderInput(){
        System.out.println("1. 메인화면 돌아가기");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1){
            displayMainMenu();
        }
    }

    private static void handleCancelConfirmationInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            menuContext.resetCart();
            System.out.println("주문이 취소되었습니다.");
            displayMainMenu();
        } else if (input == 2) {
            displayMainMenu();
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleCancelConfirmationInput();
        }
    }



}