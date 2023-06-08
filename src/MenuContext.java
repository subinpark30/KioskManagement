package KioskManagement.src;

import java.util.*;

class MenuContext {
    private Map<String, List<Menu>> menus;
    private Map<String, List<Item>> menuItems;
    private List<Item> cart;
    private Request request;
    private double totalPrice;
    private int orderNumber;
    private List<WaitOrder> waitOrders = new ArrayList<WaitOrder>();
    private List<CompleteOrder> completeOrders = new ArrayList<CompleteOrder>();

    public MenuContext() {
        menus = new HashMap<>();
        menuItems = new HashMap<>();
        cart = new ArrayList<>();
        totalPrice = 0.0;
        orderNumber = 0;
//        requests = new ArrayList<>();

        initializeMenuItems();
    }

    private void initializeMenuItems() {
        List<Menu> mainMenus = new ArrayList<>();
        mainMenus.add(new Menu("Burgers", "앵거스 비프 통살을 다져만든 버거"));
        mainMenus.add(new Menu("Forzen Custard", "매장에서 신선하게 만드는 아이스크림"));
        mainMenus.add(new Menu("Drinks", "매장에서 직접 만드는 음료"));
        mainMenus.add(new Menu("Beer", "뉴욕 브루클린 브루어리에서 양조한 맥주"));

        List<Menu> orderMenus = new ArrayList<>();
        orderMenus.add(new Menu("Order", "장바구니를 확인 후 주문합니다."));
        orderMenus.add(new Menu("Cancel", "진행중인 주문을 취소합니다."));

        menus.put("Main", mainMenus);
        menus.put("Order", orderMenus);

        List<Item> burgersMenus = new ArrayList<>();
        burgersMenus.add(new Item("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgersMenus.add(new Item("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgersMenus.add(new Item("Shroom Burger", 9.4, "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거"));
        burgersMenus.add(new Item("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgersMenus.add(new Item("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        List<Item> frozenCustardMenu = new ArrayList<>();
        frozenCustardMenu.add(new Item("Frozen Custard MyKiosk.Menu Item 1", 1.4, "Frozen Custard MyKiosk.Menu Item 1 설명"));
        frozenCustardMenu.add(new Item("Frozen Custard MyKiosk.Menu Item 2", 1.0, "Frozen Custard MyKiosk.Menu Item 2 설명"));
        frozenCustardMenu.add(new Item("Frozen Custard MyKiosk.Menu Item 3", 1.6, "Frozen Custard MyKiosk.Menu Item 3 설명"));
        frozenCustardMenu.add(new Item("Frozen Custard MyKiosk.Menu Item 4", 2.1, "Frozen Custard MyKiosk.Menu Item 4 설명"));

        List<Item> drinksMenu = new ArrayList<>();
        drinksMenu.add(new Item("Drinks MyKiosk.Menu Item 1", 1.0, "Drinks MyKiosk.Menu Item 1 설명"));
        drinksMenu.add(new Item("Drinks MyKiosk.Menu Item 2", 1.0, "Drinks MyKiosk.Menu Item 2 설명"));

        List<Item> beerMenu = new ArrayList<>();
        beerMenu.add(new Item("Beer MyKiosk.Menu Item 1", 3.0, "Beer MyKiosk.Menu Item 1 설명"));
        beerMenu.add(new Item("Beer MyKiosk.Menu Item 2", 4.0, "Beer MyKiosk.Menu Item 2 설명"));

        menuItems.put("Burgers", burgersMenus);
        menuItems.put("Frozen Custard", frozenCustardMenu);
        menuItems.put("Drinks", drinksMenu);
        menuItems.put("Beer", beerMenu);
    }
    public void addMenus(String name,double price,String description,String key){
        List<Item> newMenus = getMenuItems(key);
        newMenus.add(new Item(name,price,description));
    }
    public List<Menu> getMenus(String key) {
        return menus.get(key);
    }

    public List<Item> getMenuItems(String key) {
        return menuItems.get(key);
    }

//    public List<Request> getRequests() {
//        return requests;
//    }
//
//    public void setRequests(List<Request> requests) {
//        this.requests = requests;
//    }

    public void addRequest(String request){
        this.request = new Request(request);
    }

    public void addToCart(Item menuItem) {
        cart.add(menuItem);
        totalPrice += menuItem.price;
    }


    public void displayCart() {
        for (Item item : cart) {
            System.out.println(item.name + "   | " + item.price + " | " + item.description);
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int generateOrderNumber() {
        orderNumber++;
        return orderNumber;
    }

    public void resetCart() {
        cart.clear();
        totalPrice = 0.0;
    }

    public void addWaitOrder(int waitingNumber) {
        waitOrders.add(new WaitOrder(waitingNumber, cart, totalPrice, request));
    }
    //대기 주문 목록 출력
    public void printWaitOrders(){
        for(int i = 0; i < waitOrders.size(); i++){
            System.out.println("대기번호: " + waitOrders.get(i).waitingNumber);
            System.out.println("주문 목록: ");
            //에러난 부분2 (예상)
            for(int j = 0; j < waitOrders.get(i).orderItemList.size(); i++){
                System.out.println((i + 1) + ". 상품명: " + waitOrders.get(i).orderItemList.get(j).name);
                System.out.println("상품 설명: " + waitOrders.get(i).orderItemList.get(j).description);
                System.out.println("상품 가격: " + waitOrders.get(i).orderItemList.get(j).price);
            }
            System.out.println();
            System.out.println("주문 총 가격: " + waitOrders.get(i).totalPrice);
            System.out.println("요청 사항: " + waitOrders.get(i).request);
            System.out.println("주문 일시: " + waitOrders.get(i).orderTime);
            System.out.println();
        }
    }

    //완료된 주문 입력 받음
    public void getCompleteOrder(){
        System.out.println("완료된 주문 입력: ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        if(1<=num && num <= waitOrders.size()){
            completeOrders.add(new CompleteOrder(waitOrders.get(num-1)));
            waitOrders.remove(num -1);
        }else{
            System.out.println("잘못된 입력입니다.");
            getCompleteOrder();
        }
    }

    //완료 주문 목록 출력
    public void printCompleteOrders(){
        for(int i = 0; i < completeOrders.size(); i++){
            System.out.println("대기번호: " + completeOrders.get(i).waitOrder.waitingNumber);
            System.out.println("주문 목록: ");
            for(int j = 0; j < completeOrders.get(i).waitOrder.orderItemList.size(); j++){
                System.out.println((j+1) + ". 상품명: " + completeOrders.get(i).waitOrder.orderItemList.get(j).name);
                System.out.println("상품 설명: " + completeOrders.get(i).waitOrder.orderItemList.get(j).description);
                System.out.println("상품 가격: " + completeOrders.get(i).waitOrder.orderItemList.get(j).price);
            }
            System.out.println();
            System.out.println("주문 총 가격: " + completeOrders.get(i).waitOrder.totalPrice);
            System.out.println("주문 일시: " + completeOrders.get(i).waitOrder.orderTime);
            System.out.println("요청 사항: " + completeOrders.get(i).waitOrder.request);
            System.out.println("완료 주문 일시: " + completeOrders.get(i).orderCompleteTime);
            System.out.println();
        }
    }
}