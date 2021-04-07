package edu.gorb.xmltask.entity;

import java.util.Objects;

public class Hotel {
    private int starsCount;
    private FoodType foodType;
    private int placeCount;
    private boolean isAirConditioningPresent;
    private boolean isTvPresent;

    public int getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(int starsCount) {
        this.starsCount = starsCount;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }

    public boolean isAirConditioningPresent() {
        return isAirConditioningPresent;
    }

    public void setAirConditioningPresent(boolean airConditioningPresent) {
        isAirConditioningPresent = airConditioningPresent;
    }

    public boolean isTvPresent() {
        return isTvPresent;
    }

    public void setTvPresent(boolean tvPresent) {
        isTvPresent = tvPresent;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "starsCount=" + starsCount +
                ", foodType=" + foodType +
                ", placeCount=" + placeCount +
                ", isAirConditioningPresent=" + isAirConditioningPresent +
                ", isTvPresent=" + isTvPresent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        Hotel hotel = (Hotel) o;
        return starsCount == hotel.starsCount
                && placeCount == hotel.placeCount
                && isAirConditioningPresent == hotel.isAirConditioningPresent
                && isTvPresent == hotel.isTvPresent
                && foodType == hotel.foodType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(starsCount, foodType, placeCount, isAirConditioningPresent, isTvPresent);
    }
}
