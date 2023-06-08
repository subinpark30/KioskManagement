package KioskManagement.src;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WaitOrder {
    //대기 주문
    int waitingNumber;
    List<Item> orderItemList = new ArrayList<Item>();
    double totalPrice;
    LocalDateTime orderTime;
    Request request;

    WaitOrder(int waitingNumber, List<Item> orderItemList, double totalPrice, Request request){
        this.waitingNumber = waitingNumber;
        this.orderItemList.addAll(orderItemList);
        this.totalPrice = totalPrice;
        this.orderTime = LocalDateTime.now();
        this.request = request;
    }
}

