package edu.gorb.xmltask.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractVoucher {
    public static final String DEFAULT_WEBSITE = "https://www.tour.com";
    private String id;
    private String webSite;
    private CountryType country;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private Hotel hotel = new Hotel();
    private int cost;
    private TransportType transportType;

    public AbstractVoucher() {
    }

    public AbstractVoucher(String id, String webSite, CountryType country, LocalDateTime departure,
                              LocalDateTime arrival, Hotel hotel, int cost) {
        this.id = id;
        this.webSite = webSite;
        this.country = country;
        this.departure = departure;
        this.arrival = arrival;
        this.hotel = hotel;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public CountryType getCountry() {
        return country;
    }

    public void setCountry(CountryType country) {
        this.country = country;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public TransportType getTransport() {
        return transportType;
    }

    public void setTransport(TransportType transportType) {
        this.transportType = transportType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractVoucher that = (AbstractVoucher) o;
        return cost == that.cost && Objects.equals(id, that.id) && Objects.equals(webSite, that.webSite) && country == that.country && Objects.equals(departure, that.departure) && Objects.equals(arrival, that.arrival) && Objects.equals(hotel, that.hotel) && transportType == that.transportType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, webSite, country, departure, arrival, hotel, cost, transportType);
    }

    @Override
    public String toString() {
        return "AbstractVoucher{" +
                "id='" + id + '\'' +
                ", webSite='" + webSite + '\'' +
                ", country=" + country +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", cost=" + cost +
                ", transport=" + transportType +
                '}';
    }
}
