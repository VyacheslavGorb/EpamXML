package edu.gorb.xmltask.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractVoucher {
    public static final String DEFAULT_WEBSITE = "https://www.tour.com";
    private String id;
    private String webSite;
    private Countries country;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private Hotel hotel = new Hotel();
    private int cost;
    private Transport transport;

    protected AbstractVoucher() {
    }

    protected AbstractVoucher(String id, String webSite, Countries country, LocalDateTime departure,
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

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
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

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractVoucher that = (AbstractVoucher) o;
        return cost == that.cost && Objects.equals(id, that.id) && Objects.equals(webSite, that.webSite) && country == that.country && Objects.equals(departure, that.departure) && Objects.equals(arrival, that.arrival) && Objects.equals(hotel, that.hotel) && transport == that.transport;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, webSite, country, departure, arrival, hotel, cost, transport);
    }

    @Override
    public String toString() {
        return "AbstractVoucher{" +
                "id='" + id + '\'' +
                ", webSite='" + webSite + '\'' +
                ", country=" + country +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", hotel=" + hotel.toString() +
                ", cost=" + cost +
                ", transport=" + transport +
                '}';
    }
}
