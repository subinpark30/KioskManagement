
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
                displayOrderCart();
                break;
            case 2:
                finishOrderCart();
                break;
            case 3:
                addMenu();
                break;
            case 4:
                deleteMenu();
                break;
            case 5:
                break;
        }
    }

    public void displayOrderCart(){
        //pintOrderCart
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if(input == 1){
            //addToFinishOrderCart
            System.out.println("주문이 완료되었습니다.");
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            displayMainMenu();
        }
    }

    void finishOrderCart(){
        //printFinishOrderCart
    }

    void addMenu(){
        System.out.println("새로운 상품 추가할 메뉴를 선택하세요.");
        List<Menu> mainMenus = menuContext.getMenus("Main");
        printMenu(mainMenus, 1);
        handleAddMenu();
    }

    void handleAddMenu(){
        // 해쉬맵 키값만 받아서,
        //for if 로 버튼1일때 key[1]받아서
        //넘겨주면 더 깔꼼해질듯
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
        // 해쉬맵 키값만 받아서,
        //for if 로 버튼1일때 key[1]받아서
        //넘겨주면 더 깔꼼해질듯
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
        int idx = input-1;
        Item deleteItem = items.get(idx);
        checkDeleteItem(deleteItem,items,idx);
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
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            displayMainMenu();
        }
    }

}


