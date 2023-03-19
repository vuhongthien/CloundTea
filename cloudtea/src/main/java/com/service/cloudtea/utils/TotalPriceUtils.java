package com.service.cloudtea.utils;

public class TotalPriceUtils {
    public static double TotalPrice(double price, int quantity) {
        return  price * quantity;
    }
    public static double TotalPriceCart(int x, double y) {
        return  x * y;
    }
    public static double VoucherDiscount(double x, double y) {
        return  x * y/100;
    }
}
