package com.github.wangchenning.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MockQueue {

    private String placeOrder;
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(()->{
            log.info("接到下单请求,"+placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.placeOrder = placeOrder;
            setCompleteOrder(placeOrder);
            log.info("接到下单请求处理完成,"+placeOrder);
        }).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
