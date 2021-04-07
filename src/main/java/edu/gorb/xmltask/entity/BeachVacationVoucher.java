package edu.gorb.xmltask.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class BeachVacationVoucher extends AbstractVoucher {
    private int distanceToBeach;

    public BeachVacationVoucher() {
    }

    public BeachVacationVoucher(String id, String webSite, Countries country, LocalDateTime departure,
                                LocalDateTime arrival, Hotel hotel, int cost, int distanceToBeach) {
        super(id, webSite, country, departure, arrival, hotel, cost);
        this.distanceToBeach = distanceToBeach;
    }

    public int getDistanceToBeach() {
        return distanceToBeach;
    }

    public void setDistanceToBeach(int distanceToBeach) {
        this.distanceToBeach = distanceToBeach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BeachVacationVoucher that = (BeachVacationVoucher) o;
        return distanceToBeach == that.distanceToBeach;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), distanceToBeach);
    }


}
