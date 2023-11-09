package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.HostelType;
import com.patikadev.Model.Room;
import com.patikadev.Model.Season;

import javax.swing.*;

public class UpdateRoomGUI extends JFrame {

    private Room room;
    private JPanel wrapper;
    private JTextField fld_hotel_id;
    private JTextField fld_room_type;
    private JComboBox cmb_room_bed;
    private JTextField fld_remaining_rooms;
    private JButton btn_room_update;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextArea txt_properties;
    private JComboBox cmb_room_type;
    private JComboBox cmb_hostel_type_id;
    private JComboBox cmb_season_id;
    private JTextField fld_properties;

    public UpdateRoomGUI(Room room){
        this.room = room;
        add(wrapper);
        setSize(400,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_hotel_id.setText(String.valueOf(room.getHotelId()));
        cmb_room_bed.setSelectedItem(room.getBed());
        fld_remaining_rooms.setText(String.valueOf(room.getRemainingRooms()));
        cmb_room_type.setSelectedItem(room.getRoomType());
        fld_adult_price.setText(String.valueOf(room.getAdultPrice()));
        fld_child_price.setText(String.valueOf(room.getChildPrice()));
        fld_properties.setText(room.getProperties());

        loadSeasonsCombo();
        loadHostelTypeCombo();

        // Update Room
        btn_room_update.addActionListener(e -> {

            if ((Helper.isFieldEmpty(fld_remaining_rooms)) || Helper.isFieldEmpty(fld_adult_price) ||
                    Helper.isFieldEmpty(fld_child_price) || Helper.isFieldEmpty(fld_properties)){
                Helper.showMessage("fill");
            } else {
                int id = room.getId();
                // int hotelID = Integer.parseInt(fld_hotel_id.getText());
                int bed = Integer.parseInt(cmb_room_bed.getSelectedItem().toString());
                String roomType = cmb_room_type.getSelectedItem().toString();
                Item hostelTypeItem = (Item) cmb_hostel_type_id.getSelectedItem();
                int hostelTypeID = hostelTypeItem.getKey();
                int seasonID = 0;
                int remainingRooms = Integer.parseInt(fld_remaining_rooms.getText());
                int adultPrice = Integer.parseInt(fld_adult_price.getText());
                int childPrice = Integer.parseInt(fld_child_price.getText());
                String properties =  fld_properties.getText();
                if (Room.updateRoom(id, bed, roomType, hostelTypeID, seasonID, remainingRooms, adultPrice, childPrice, properties)){
                    Helper.showMessage("done");
                }
                dispose();
            }
        });
    }

    public void loadHostelTypeCombo(){
        cmb_hostel_type_id.removeAllItems();
        for (HostelType hostelType : HostelType.getHostelType()){
            if (hostelType.getHotel_id() == room.getHotelId()){
                cmb_hostel_type_id.addItem(new Item(hostelType.getId(), hostelType.getType()));
            }
        }
    }
    public void loadSeasonsCombo(){
        cmb_season_id.removeAllItems();
        for (Season season : Season.getList()){
            if (season.getHotelId() == room.getHotelId()){
                cmb_season_id.addItem(new Item(season.getId(), season.getSeasonStart()) + " - " + season.getSeasonEnd());
            }
        }
    }
    public static void main(String[] args) {
        Helper.setLayout();
        Room room = new Room();
        //Room room = new Room(1,1,"a tipi", 7, 5);
        UpdateRoomGUI update = new UpdateRoomGUI(room);

    }
}
