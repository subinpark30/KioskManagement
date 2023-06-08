
import java.util.*;

import static java.util.Collection.*;

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
        orderMenus.add(new Menu("Order List", "진행중인 주문을 확인합니다.."));

        menus.put("Main", mainMenus);
        menus.put("Order", orderMenus);

        List<Item> burgersMenus = new ArrayList<>();
        burgersMenus.add(new Item("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgersMenus.add(new Item("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgersMenus.add(new Item("Shroom Burger", 9.4, "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거"));
        burgersMenus.add(new Item("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgersMenus.add(new Item("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        List<Item> frozenCustardMenu = new ArrayList<>();
        frozenCustardMenu.add(new Item("Shack Attack", 5.9, "초콜렛 퍼지 소스, 초콜렛 청크와 펄이 들어간 진한 커스터드"));
        frozenCustardMenu.add(new Item("Blossom", 5.9, "스트로베리 잼, 슈가 콘이 어울러진 바닐라 커스터드"));
        frozenCustardMenu.add(new Item("Shack in the Garden", 4.9, "라즈베리 잼, 블렌딩 된 바닐라 커스터드"));
        frozenCustardMenu.add(new Item("Mix-Ins", 0.7, "초콜렛, 스트로베리, 카라멜, 피넛 버터"));

        List<Item> drinksMenu = new ArrayList<>();
        drinksMenu.add(new Item("Shack-made Lemonade", 3.9, "매장에서 직접 만드는 레몬에이드"));
        drinksMenu.add(new Item("Iced Tea", 3.4, "유기농 홍차를 우려낸 아이스 티"));
        drinksMenu.add(new Item("Fountain Soda", 2.7, "코카콜라"));
        drinksMenu.add(new Item("Bottled Water", 2.7, "지리산 암반대수층으로 만든 생수"));

        List<Item> beerMenu = new ArrayList<>();
        beerMenu.add(new Item("Shack Ale", 6.8, "특별히 양조한 에일 맥주"));
        beerMenu.add(new Item("Slow IPA", 6.5, "The Hand and Malt"));

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

    public void addRequest(String request){
        this.request = new Request(request);
    }

    public void addToCart(Item menuItem) {
        cart.add(menuItem);
        totalPrice += menuItem.price;
    }

    public List<Item> getCart(){
        if (cart.isEmpty()){
            return null;
        } else {
            return cart;
        }
    }
    public void displayCart(List<Item> cart) {
        for (Item item : cart) {
            System.out.println(item.name + "   | " + item.price + " | " + item.description);
        }
    }
    public List<WaitOrder> getWaitOrders(){
        return waitOrders;
    }
    public List<CompleteOrder> getCompleteOrders(){
        return completeOrders;
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
            int idx = i +1;
            System.out.println("[ 주문번호 : "+idx+" ]");
            System.out.println("대기번호: " + waitOrders.get(i).waitingNumber);
            System.out.println("주문 목록: ");
            //에러난 부분
            for(int j = 0; j < waitOrders.get(i).orderItemList.size(); j++){
                System.out.println((j + 1) + ". 상품명: " + waitOrders.get(i).orderItemList.get(j).name);
                System.out.println("상품 설명: " + waitOrders.get(i).orderItemList.get(j).description);
                System.out.println("상품 가격: " + waitOrders.get(i).orderItemList.get(j).price);
            }
            System.out.println("주문 총 가격: " + waitOrders.get(i).totalPrice);
            System.out.println("요청 사항: " + waitOrders.get(i).request.getRequest());
            System.out.println("주문 일시: " + waitOrders.get(i).orderTime);
            System.out.println();
        }
    }

    //완료된 주문 입력 받음
    public void getCompleteOrder(){
        System.out.println("주문번호를 입력하세요. ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        if(1<=num && num <= waitOrders.size()){
            completeOrders.add(new CompleteOrder(waitOrders.get(num-1)));
            waitOrders.remove(num -1);
            System.out.println("주문이 완료되었습니다.");
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
    public void printRecentOrders(){
        System.out.println("[ 최근 주문 완료 현황 ]");
        if ( completeOrders.isEmpty()){
            System.out.println("완료된 주문이 없습니다.");
        }
        // printCompleteOrders 역순 출력!!
//        Collections.sort(completeOrders);
        Collections.reverse(completeOrders);
        int count = 1;
        for(int i = 0; i < completeOrders.size(); i++){

                System.out.println("대기번호: " + completeOrders.get(i).waitOrder.waitingNumber);
                System.out.println("주문 목록: ");

                for (int j = 0; j < completeOrders.get(i).waitOrder.orderItemList.size(); j++) {
                    System.out.println((j + 1) + ". 상품명: " + completeOrders.get(i).waitOrder.orderItemList.get(j).name);
                    System.out.println("상품 설명: " + completeOrders.get(i).waitOrder.orderItemList.get(j).description);
                    System.out.println("상품 가격: " + completeOrders.get(i).waitOrder.orderItemList.get(j).price);
                }
                System.out.println();
                System.out.println("주문 총 가격: " + completeOrders.get(i).waitOrder.totalPrice);
                System.out.println("주문 일시: " + completeOrders.get(i).waitOrder.orderTime);
                System.out.println("요청 사항: " + completeOrders.get(i).waitOrder.request);
                System.out.println("완료 주문 일시: " + completeOrders.get(i).orderCompleteTime);
                System.out.println();
            count++;
            if(count > 3) {
                break;
            }
        }
        Collections.reverse(completeOrders);
        System.out.println("");
        System.out.println(" [ 주문 대기 현황 ] ");
        if ( waitOrders.isEmpty()){
            System.out.println("진행중인 주문이 없습니다.");
        }
        printWaitOrders();
        System.out.println("");
    }


}