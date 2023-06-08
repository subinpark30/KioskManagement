import java.util.List;
import java.util.Scanner;

public class KioskManagement extends ShakeShackBurgerApplication {

    void displayMainMenu(){
        System.out.println("1.대기 주문 목록");
        System.out.println("2.완료 주문 목록");
        System.out.println("3.상품 생성");
        System.out.println("4.상품 삭제");
        System.out.println("5.종료하기");
        handleMainMenuInput();
    }
    void handleMainMenuInput(){
        Scanner scanner =new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input){
            case 1:
                displayWaitOrder();
                break;
            case 2:
                displayCompleteOrder();
                break;
            case 3:
                addMenu();
                break;
            case 4:
                deleteMenu();
                break;
            case 5:
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                handleMainMenuInput();
                break;
        }
    }

    void displayWaitOrder(){
        System.out.println("1. 대기 주문 목록 조회");
        System.out.println("2. 대기 주문 완료 처리");

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        switch (num){
            case 1:
                getWaitOrderList();
                break;
            case 2:
                HandleWaitOrder();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                displayWaitOrder();
                break;
        }
    }

    //0-1-1대기 주문 목록 조회
    void getWaitOrderList() {
        //대기목록 없을 때
        if(menuContext.getWaitOrders().isEmpty()){
            System.out.println("대기목록이 없습니다.");  
            displayMainMenu();
        }else{
            menuContext.printWaitOrders();
            //완료처리 또는 복귀 선택
            selectWaitOrder();
        }

    }

    //0-1-1-1 완료처리 또는 복귀
    void selectWaitOrder(){
        System.out.println("1. 대기 주문 완료 처리하기    2. 관리 목록으로 돌아가기");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        
        switch (input){
            case 1:
                menuContext.getCompleteOrder();
                displayMainMenu();
            case 2:
                displayMainMenu();
            default:
                System.out.println("잘못된 입력입니다.");
                selectWaitOrder();
        }
    }

    //0-1-2대기 주문 완료 처리
    void HandleWaitOrder() {
        if(menuContext.getWaitOrders().isEmpty()){
            System.out.println("대기목록이 없습니다.");
            System.out.println();
            displayMainMenu();
        }else {
            menuContext.printWaitOrders();
            menuContext.getCompleteOrder();
            displayMainMenu();
        }
    }

    //0-2완료 주문 목록 조회
    void displayCompleteOrder(){
        //완료 주문 목록 비었나 검사
        if(menuContext.getCompleteOrders().isEmpty()){
            System.out.println("완료목록이 없습니다.");
            System.out.println();
            displayMainMenu();
        }else {
            menuContext.printCompleteOrders();
            displayMainMenu();
        }
    }

    void addMenu(){
        System.out.println("새로운 상품 추가할 메뉴를 선택하세요.");
        List<Menu> mainMenus = menuContext.getMenus("Main");
        printMenu(mainMenus, 1);
        handleAddMenu();
    }

    void handleAddMenu(){
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input){
            case 1:
                addItem("Burgers");
                break;
            case 2:
                addItem("Frozen Custard");
                break;
            case 3:
                addItem("Drinks");
                break;
            case 4:
                addItem("Beer");
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                handleAddMenu();
                break;
        }
    }

    void addItem(String key){
        Item item = insertItem();
        menuContext.addMenus(item.name,item.price,item.description,key);
        System.out.println("상품이 추가되었습니다.");
        displayMainMenu();
    }

    Item insertItem(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("추가할 상품 이름을 입력하세요.");
        String name = scanner.nextLine();
        System.out.println("추가할 상품 가격을 입력하세요.");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("추가할 상품 설명을 입력하세요.");
        String description = scanner.nextLine();
        Item item = new Item(name,price,description);
        return item;
    }

    void deleteMenu(){
        System.out.println("삭제할 상품 메뉴를 선택하세요.");
        List<Menu> mainMenus = menuContext.getMenus("Main");
        printMenu(mainMenus, 1);
        handleDeleteMenu();
    }

    void handleDeleteMenu(){
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input){
            case 1:
                handleDeleteItem("Burgers");
                break;
            case 2:
                handleDeleteItem("Frozen Custard");
                break;
            case 3:
                handleDeleteItem("Drinks");
                break;
            case 4:
                handleDeleteItem("Beer");
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                handleDeleteMenu();
                break;
        }
    }

    void handleDeleteItem(String key){
        System.out.println("삭제할 메뉴를 선택하십시요.");
        List<Item> burgerItems = menuContext.getMenuItems(key);
        printMenuItems(burgerItems);
        deleteItem(burgerItems);
    }

    void deleteItem(List<Item> items){
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if(1<=input && input <= items.size()){
            int idx = input-1;
            Item deleteItem = items.get(idx);
            checkDeleteItem(deleteItem,items,idx);
        }else{
            System.out.println("잘못된 입력입니다.");
            deleteItem(items);
        }
    }

    void checkDeleteItem(Item deleteItem, List<Item> items,int idx){
        System.out.println(deleteItem.name+ "   | " + deleteItem.price + " | " + deleteItem.description);
        System.out.println("위 상품을 삭제하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        Scanner scanner = new Scanner(System.in);
        int button = scanner.nextInt();
        if (button == 1) {
            items.remove(idx);
            System.out.println("삭제 되었습니다.");
            displayMainMenu();
        } else if (button == 2) {
            System.out.println("취소 되었습니다. 메뉴로 돌아갑니다.");
            displayMainMenu();
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            displayMainMenu();
        }
    }

}


