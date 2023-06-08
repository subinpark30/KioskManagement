package KioskManagement.src;
import teamProject2.ShakeShackBurger.src.main.java.WaitOrder;

import java.time.LocalDateTime;

public class CompleteOrder {
    //완료 주문
    WaitOrder waitOrder;
    LocalDateTime orderCompleteTime;

    CompleteOrder(WaitOrder waitOrder){
        this.waitOrder = waitOrder;
        orderCompleteTime = LocalDateTime.now();
    }
}

