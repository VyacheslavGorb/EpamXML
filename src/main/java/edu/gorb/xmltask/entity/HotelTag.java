package edu.gorb.xmltask.entity;

import java.util.Locale;

public enum HotelTag {
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
