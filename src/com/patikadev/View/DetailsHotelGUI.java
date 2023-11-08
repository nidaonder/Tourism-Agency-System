package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.HostelType;
import com.patikadev.Model.Hotel;
import com.patikadev.Model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class DetailsHotelGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tab_hotel_info;
    private JLabel lbl_hotel_info;
    private JPanel pnl_top;
    private JScrollPane scrl_room_list;
    private JTable tbl_room_list;
    private JPanel pnl_room_form;
    private JTextField fld_hotel_id;
    private JTextField fld_room_type;
    private JComboBox cmb_room_bed;
    private JButton btn_room_add;
    private JTextField fld_remaining_rooms;
    private JTextField fld_room_id;
    private JButton btn_room_delete;
    private JScrollPane scr_hostel_add;
    private JScrollPane scrl_season_add;
    private JPanel pnl_hostel_type;
    private JPanel pnl_seasons;
    private JTextField fld_hstl_htl_id;
    private JButton btn_hostel_add;
    private JTextField fld_seasn_htl_id;
    private JTextField fld_check_in;
    private JTextField fld_check_out;
    private JButton btn_season_add;
    private JRadioButton onlyBedRadioButton;
    private JRadioButton BBRadioButton;
    private JRadioButton halfPensionRadioButton;
    private JRadioButton fullBoardRadioButton;
    private JRadioButton allInclusiveRadioButton;
    private JRadioButton ultraAllInclusiveRadioButton;
    private JRadioButton fullCreditExceptRadioButton;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_mail;
    private JTextField fld_phone_number;
    private JTextField fld_hotel_stars;
    private JTextField fld_hotel_features;
    private JTable tbl_hostel_type_list;
    private JTable tbl_seasons_list;
    private DefaultTableModel mdl_room_list;
    private Object[] row_room_list;
    private JPopupMenu roomMenu;




    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;

    }

    public DetailsHotelGUI(Hotel hotel){

        this.hotel = hotel;
        add(wrapper);
        setSize(800,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_hotel_info.setText(hotel.getName());

        // Hotel Info:
        fld_hotel_name.setText(hotel.getName());
        fld_hotel_address.setText(hotel.getAddress());
        fld_hotel_mail.setText(hotel.getEmail());
        fld_phone_number.setText(hotel.getPhone_number());
        fld_hotel_stars.setText(hotel.getStars());
        fld_hotel_features.setText(hotel.getHotel_features());

        // ModelRoomList:
        roomMenu = new JPopupMenu();
        JMenuItem updateRoomMenu = new JMenuItem("Update");
        roomMenu.add(updateRoomMenu);

        updateRoomMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(), 0).toString());
            UpdateRoomGUI updateRoom = new UpdateRoomGUI(Room.getFetch(select_id));
            updateRoom.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomModel();
                }
            });
        });


        mdl_room_list = new DefaultTableModel();

        mdl_room_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column >= 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_room_list = {"ID", "HOTEL ID", "ROOM TYPE", "BED", "REMAINING ROOMS"};
        mdl_room_list.setColumnIdentifiers(col_room_list);

        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.setComponentPopupMenu(roomMenu);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);

        tbl_room_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_room_list.rowAtPoint(point);
                tbl_room_list.setRowSelectionInterval(selected_row, selected_row);

            }
        });

        fld_hotel_id.setText(String.valueOf(hotel.getId()));
        fld_hstl_htl_id.setText(String.valueOf(hotel.getId()));
        fld_seasn_htl_id.setText(String.valueOf(hotel.getId()));

        // Delete room
        tbl_room_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                String select_room_id = tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(), 0).toString();
                fld_room_id.setText(select_room_id);
            }catch(Exception exception){
            }
        });

        for (Room obj : Room.getList()){
            Object[] row = new Object[col_room_list.length];
            if (obj.getHotelId() == hotel.getId()){
                row[0] = obj.getId();
                row[1] = obj.getHotelId();
                row[2] = obj.getRoomType();
                row[3] = obj.getBed();
                row[4] = obj.getRemainingRooms();
                mdl_room_list.addRow(row);
            }
        }

        // Add Room
        btn_room_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_id) || Helper.isFieldEmpty(fld_room_type) || Helper.isFieldEmpty(fld_remaining_rooms)){
                Helper.showMessage("fill");
            } else {
                int hotelID = Integer.parseInt(fld_hotel_id.getText());
                String roomType = fld_room_type.getText();
                int bed = Integer.parseInt(cmb_room_bed.getSelectedItem().toString());
                int remainingRooms = Integer.parseInt(fld_remaining_rooms.getText());
                if (Room.addRoom(hotelID, roomType, bed, remainingRooms)){
                    Helper.showMessage("done");
                    loadRoomModel();
                    fld_hotel_id.setText(String.valueOf(hotel.getId()));
                    fld_room_type.setText(null);
                    cmb_room_bed.setSelectedItem(null);
                    fld_remaining_rooms.setText(null);
                }
            }
        });

        // Delete Room
        btn_room_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_room_id)){
                Helper.showMessage("fill");
            } else {
                int room_id = Integer.parseInt(fld_room_id.getText());
                    if (Room.deleteRoom(room_id)){
                        Helper.showMessage("done");
                        loadRoomModel();
                    } else {
                        Helper.showMessage("error");
                    }
                }
        });

        // Hostel-Type add;
        btn_hostel_add.addActionListener(e -> {

            if (!onlyBedRadioButton.isSelected() && !BBRadioButton.isSelected() && !halfPensionRadioButton.isSelected() &&
                    !fullBoardRadioButton.isSelected() && !allInclusiveRadioButton.isSelected() &&
                    !ultraAllInclusiveRadioButton.isSelected() && !fullCreditExceptRadioButton.isSelected()){
                Helper.showMessage("Please choose something!");

            } else {

                if (onlyBedRadioButton.isSelected()){
                    HostelType.addHostelType(hotel.getId(), onlyBedRadioButton.getText());
                }
                if (BBRadioButton.isSelected()){
                    HostelType.addHostelType(hotel.getId(), BBRadioButton.getText());
                }
                if (halfPensionRadioButton.isSelected()){
                    HostelType.addHostelType(hotel.getId(), halfPensionRadioButton.getText());
                }
                if (fullBoardRadioButton.isSelected()){
                    HostelType.addHostelType(hotel.getId(), fullBoardRadioButton.getText());
                }
                if (allInclusiveRadioButton.isSelected()){
                    HostelType.addHostelType(hotel.getId(), allInclusiveRadioButton.getText());
                }
                if (ultraAllInclusiveRadioButton.isSelected()){
                    HostelType.addHostelType(hotel.getId(), ultraAllInclusiveRadioButton.getText());
                }
                if (fullCreditExceptRadioButton.isSelected()){
                    HostelType.addHostelType(hotel.getId(), fullCreditExceptRadioButton.getText());
                }
                Helper.showMessage("done");

                onlyBedRadioButton.setSelected(false);
                BBRadioButton.setSelected(false);
                halfPensionRadioButton.setSelected(false);
                fullBoardRadioButton.setSelected(false);
                allInclusiveRadioButton.setSelected(false);
                ultraAllInclusiveRadioButton.setSelected(false);
                fullCreditExceptRadioButton.setSelected(false);
            }
        });
    }

    public void loadRoomModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        Object[] col_room_list = {"ID", "HOTEL ID", "ROOM TYPE", "BED", "REMAINING ROOMS"};
        for (Room obj : Room.getList()){
            if (obj.getHotelId() == hotel.getId()){
                Object[] row = new Object[col_room_list.length];
                row[0] = obj.getId();
                row[1] = obj.getHotelId();
                row[2] = obj.getRoomType();
                row[3] = obj.getBed();
                row[4] = obj.getRemainingRooms();
                mdl_room_list.addRow(row);
            }
        }
    }


    public static void main(String[] args) {
        Helper.setLayout();
        Hotel hotel = new Hotel();
        DetailsHotelGUI details = new DetailsHotelGUI(hotel);
    }
}
