package com.github.wangchenning.test;

public class EnumDemo {

    public static void main(String args[]) {

        System.out.println("CellPhone List:");
        for(Mobile m : Mobile.values()) {
            System.out.println(m + " costs " + m.showPrice() + " dollars");
        }

        Mobile ret;
        ret = Mobile.valueOf("Samsung");
        System.out.println("Selected : " + ret);
        System.out.println("Selected : " + ret.showPrice());
    }
}


