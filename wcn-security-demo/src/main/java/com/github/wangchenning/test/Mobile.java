package com.github.wangchenning.test;

enum Mobile {
    /**
     *
     */
    Samsung(400),
    /**
     *
     */
    Nokia(250),
    /**
     *
     */
    Motorola(325);

    int price;
    Mobile(int p) {
        price = p;
    }
    int showPrice() {
        return price;
    }
}


