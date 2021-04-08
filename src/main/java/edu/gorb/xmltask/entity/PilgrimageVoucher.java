package edu.gorb.xmltask.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class PilgrimageVoucher extends AbstractVoucher {
    private boolean pilgrimagePassportRequired;

    public PilgrimageVoucher() {
    }

    public PilgrimageVoucher(String id, String webSite, CountryType country, LocalDateTime departure,
                             LocalDateTime arrival, Hotel hotel, int cost, boolean pilgrimagePassportRequired) {
        super(id, webSite, country, departure, arrival, hotel, cost);
        this.pilgrimagePassportRequired = pilgrimagePassportRequired;
    }

    public boolean isPilgrimagePassportRequired() {
        return pilgrimagePassportRequired;
    }

    public void setPilgrimagePassportRequired(boolean pilgrimagePassportRequired) {
        this.pilgrimagePassportRequired = pilgrimagePassportRequired;
    }

    @Override
    public String toString() {
        return "PilgrimageVoucher{" +
                "pilgrimagePassportRequired=" + pilgrimagePassportRequired +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PilgrimageVoucher that = (PilgrimageVoucher) o;
        return pilgrimagePassportRequired == that.pilgrimagePassportRequired;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pilgrimagePassportRequired);
    }
}
