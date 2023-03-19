package com.service.cloudtea.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ProductUtils {
    public static double disCount(double price, double discount) {
                return  price - discount;
    }
    public static LocalDateTime calculateEndDate(LocalDateTime dayCreate, Long numberEnd) {
        LocalDateTime endDate = dayCreate.plus(numberEnd, ChronoUnit.DAYS);
        return endDate;
    }
}
