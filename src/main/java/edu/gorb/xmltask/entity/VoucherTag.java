package edu.gorb.xmltask.entity;

import java.util.Locale;

public enum VoucherTag {
    TOURIST_VOUCHERS,
    BEACH_VACATION_VOUCHER,
    PILGRIMAGE_VOUCHER,
    HOTEL,
    COUNTRY,
    DEPARTURE_DATE_TIME,
    ARRIVAL_DATE_TIME,
    TRANSPORT,
    COST,
    DISTANCE_TO_BEACH,
    PILGRIMAGE_PASSPORT_REQUIRED,
    STARS,
    FOOD,
    PLACE_COUNT,
    AIR_CONDITIONING,
    TV;


    @Override
    public String toString() {
        String result = this.name();
        result = result.toLowerCase(Locale.ROOT);
        result = result.replace("_", "-");
        return result;
    }
}
