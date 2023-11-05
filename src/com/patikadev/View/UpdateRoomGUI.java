package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        fld_hotel_id.setText(String.valueOf(room.getHotelId()));
        fld_room_type.setText(room.getRoomType());
        cmb_room_bed.setSelectedItem(room.getBed());
        fld_remaining_rooms.setText(String.valueOf(room.getRemainingRooms()));

        btn_room_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_id) || (Helper.isFieldEmpty(fld_room_type) || (Helper.isFieldEmpty(fld_remaining_rooms)))){
                Helper.showMessage("fill");
            } else {
                if (Room.updateRoom(room.getId(), fld_room_type.getText(), Integer.parseInt(cmb_room_bed.getSelectedItem().toString()),Integer.parseInt(fld_remaining_rooms.getText()))){
                    Helper.showMessage("done");
                }
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        Room room = new Room();
        //Room room = new Room(1,1,"a tipi", 7, 5);
        UpdateRoomGUI update = new UpdateRoomGUI(room);

    }
}
