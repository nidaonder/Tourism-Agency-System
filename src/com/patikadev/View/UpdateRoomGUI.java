package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Model.Room;

import javax.swing.*;

public class UpdateRoomGUI extends JFrame {

    private Room room;
    private JPanel wrapper;
    private JTextField fld_hotel_id;
    private JTextField fld_room_type;
    private JComboBox cmb_room_bed;
    private JTextField fld_remaining_rooms;
    private JButton btn_room_update;

    public UpdateRoomGUI(Room room){
        this.room = room;
        add(wrapper);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Room room = new Room();
        UpdateRoomGUI update = new UpdateRoomGUI(room);

    }
}
