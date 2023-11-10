package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.View.ReservationGUI;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationInfo {

    private int id;
    private String clientName;
    private String clientPhone;
    private String clientEmail;
    private String clientNote;
    private int roomId;
    private String checkIn;
    private String checkOut;
    private int adultNum;
    private int childNum;
    private long totalPrice;

    private Room room;

    public ReservationInfo(){}

    public ReservationInfo(int id, String clientName, String clientPhone, String clientEmail, String clientNote,
                           int roomId, String checkIn, String checkOut, int adultNum, int childNum, long totalPrice){
        this.id = id;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        this.clientNote = clientNote;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.adultNum = adultNum;
        this.childNum = childNum;
        this.totalPrice = totalPrice;
        this.room = Room.getFetch(roomId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientNote() {
        return clientNote;
    }

    public void setClientNote(String clientNote) {
        this.clientNote = clientNote;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getAdultNum() {
        return adultNum;
    }

    public void setAdultNum(int adultNum) {
        this.adultNum = adultNum;
    }

    public int getChildNum() {
        return childNum;
    }

    public void setChildNum(int childNum) {
        this.childNum = childNum;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static boolean addReservation(String clientName, String clientPhone, String clientEmail, String clientNote,
                                         int roomId, String checkIn, String checkOut, int adultNum, int childNum, long totalPrice){
        String query = "INSERT INTO reservation_info (client_name, client_phone, client_email, client_note, room_id, " +
                "check_in, check_out, adult_num, child_num, total_price) VALUES (?, ?, ?, ?, ? ,?, ?, ?, ?, ?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, clientName);
            pr.setString(2, clientPhone);
            pr.setString(3, clientEmail);
            pr.setString(4, clientNote);
            pr.setInt(5, roomId);
            pr.setString(6, checkIn);
            pr.setString(7, checkOut);
            pr.setInt(8, adultNum);
            pr.setInt(9, childNum);
            pr.setLong(10, totalPrice);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
