package com.patikadev.Model;

public class HostelType {
    private int id;
    private int hotel_id;
    private String type;
    private Hotel hotel;

    public HostelType(){}
    public HostelType(int id, int hotel_id, String type) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.type = type;
        this.hotel = Hotel.getFetch(hotel_id);
    }

    public Hotel getHotel() {
        return Hotel.getFetch(this.hotel_id);
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
