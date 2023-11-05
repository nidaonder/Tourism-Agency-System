package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Hotel;

import javax.swing.*;

public class UpdateHotelGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_region;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_phone;
    private JComboBox cmb_stars;
    private JTextField fld_hotel_features;
    private JButton btn_hotel_update;

    private Hotel hotel;

    public UpdateHotelGUI(Hotel hotel){
        this.hotel = hotel;
        add(wrapper);
        setSize(300,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_hotel_name.setText(hotel.getName());
        fld_hotel_city.setText(hotel.getCity());
        fld_hotel_region.setText(hotel.getRegion());
        fld_hotel_address.setText(hotel.getAddress());
        fld_hotel_mail.setText(hotel.getEmail());
        fld_hotel_phone.setText(hotel.getPhone_number());
        cmb_stars.setSelectedItem(hotel.getStars());
        fld_hotel_features.setText(hotel.getHotel_features());

        btn_hotel_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_hotel_city) || Helper.isFieldEmpty(fld_hotel_region)
                    || Helper.isFieldEmpty(fld_hotel_address) || Helper.isFieldEmpty(fld_hotel_mail) || Helper.isFieldEmpty(fld_hotel_phone)
                    || Helper.isFieldEmpty(fld_hotel_features)) {
                Helper.showMessage("fill");
            } else {
                if (Hotel.updateHotel(hotel.getId(), fld_hotel_name.getText(), fld_hotel_city.getText(), fld_hotel_region.getText(),
                        fld_hotel_address.getText(), fld_hotel_mail.getText(), fld_hotel_phone.getText(), (String)cmb_stars.getSelectedItem(),
                        fld_hotel_features.getText())){
                    Helper.showMessage("done");
                }
                dispose();
            }

        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        Hotel hotel = new Hotel();
        //Hotel hotel = new Hotel(1,"a","a","bb","aa","test","111","5","a","a");
        UpdateHotelGUI up = new UpdateHotelGUI(hotel);
    }
}
