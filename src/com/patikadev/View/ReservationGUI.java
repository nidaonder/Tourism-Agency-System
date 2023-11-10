package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Hotel;
import com.patikadev.Model.ReservationInfo;
import com.patikadev.Model.Room;
import com.sun.net.httpserver.Headers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReservationGUI extends JFrame{

    private int adultNum;
    private int childNum;
    private String checkIn;
    private String checkOut;
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JPanel pnl_hotel_info;
    private JPanel pnl_reservation_info;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_address;
    private JTextField fld_phone_number;
    private JTextField fld_hotel_features;
    private JTextField fld_room_type;
    private JTextField fld_room_properties;
    private JTextField fld_adult_num;
    private JTextField fld_child_num;
    private JTextField fld_check_in;
    private JTextField fld_check_out;
    private JTextField fld_one_night;
    private JTextField fld_total_price;
    private JTextField fld_client_name;
    private JTextField fld_client_phone;
    private JTextField fld_client_mail;
    private JButton createReservationButton;
    private JTextField fld_reservation_note;
    private Room room;

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

    public ReservationGUI(Room room, int adultNum, int childNum, String checkIn, String checkOut){

        this.room = room;
        this.adultNum = adultNum;
        this.childNum = childNum;
        this.checkIn = checkIn;
        this.checkOut = checkOut;

        add(wrapper);
        setSize(1000,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_hotel_name.setText(Hotel.getFetch(room.getHotelId()).getName());
        fld_hotel_address.setText(Hotel.getFetch(room.getHotelId()).getAddress());
        fld_phone_number.setText(Hotel.getFetch(room.getHotelId()).getPhone_number());
        fld_hotel_features.setText(Hotel.getFetch(room.getHotelId()).getHotel_features());
        fld_room_type.setText(room.getRoomType());
        fld_room_properties.setText(room.getProperties());
        fld_adult_num.setText(String.valueOf(this.adultNum));
        fld_child_num.setText(String.valueOf(this.childNum));
        fld_check_in.setText(this.checkIn);
        fld_check_out.setText(this.checkOut);
        int oneNightPrice = (adultNum * room.getAdultPrice()) + (childNum * room.getChildPrice());
        fld_one_night.setText(String.valueOf(oneNightPrice));

        Date checkInDate = null;
        Date checkOutDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            checkInDate = formatter.parse(checkIn);
            checkOutDate = formatter.parse(checkOut);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long diffInMillies = Math.abs(checkOutDate.getTime() - checkInDate.getTime());
        long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        long totalPrice = daysBetween * oneNightPrice;
        fld_total_price.setText(String.valueOf(totalPrice));

        // Create Reservation
        createReservationButton.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_client_name) || Helper.isFieldEmpty(fld_client_phone) ||
                    Helper.isFieldEmpty(fld_client_mail) || Helper.isFieldEmpty(fld_reservation_note)){
                Helper.showMessage("fill");
            } else {
                String clientName = fld_client_name.getText();
                String clientPhone = fld_client_phone.getText();
                String clientEmail = fld_client_mail.getText();
                String clientNote = fld_reservation_note.getText();
                int clientRoomId = room.getId();
                String clientCheckIn = this.checkIn;
                String clientCheckOut = this.checkOut;
                int clientAdult = this.adultNum;
                int clientChildNum = this.childNum;
                long clientTotalPrice = totalPrice;

                if (ReservationInfo.addReservation(clientName, clientPhone, clientEmail, clientNote, clientRoomId,
                        clientCheckIn, clientCheckOut, clientAdult, clientChildNum, totalPrice)){
                    Helper.showMessage("done");
                }
                dispose();
            }
        });
    }

}
