package com.patikadev.View;

import com.patikadev.Helper.Config;

import javax.swing.*;

public class ReservationGUI extends JFrame{
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


    public ReservationGUI(){

        add(wrapper);
        setSize(1000,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
    }

}
