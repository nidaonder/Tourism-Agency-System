package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

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
    private JTextField fld_season_start;
    private JTextField fld_season_end;
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
    private JScrollPane scrl_hostel_type;
    private JScrollPane scrl_seasons;
    private JTable tbl_seasons_list;
    private JTextField fld_room_season_id;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextArea txt_properties;
    private JComboBox cmb_season_id;
    private JComboBox cmb_hostel_type;
    private JComboBox cmb_room_type;
    private JTextField fld_properties;
    private DefaultTableModel mdl_room_list;
    private Object[] row_room_list;
    private JPopupMenu roomMenu;
    private DefaultTableModel mdl_hostel_type;
    private Object[] row_hostel_type;
    private DefaultTableModel mdl_seasons;
    private Object[] row_seasons;


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
        setSize(1000,1000);
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
                    loadSeasonsCombo();
                    loadHostelTypeCombo();
                }
            });
        });

        mdl_room_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column >= 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_room_list = {"ID", "SEASON", "BED", "ROOM TYPE", "ADULT PRICE", "CHILD PRICE", "REMAINING ROOMS", "PROPERTIES"};
        mdl_room_list.setColumnIdentifiers(col_room_list);
        for (Room room : Room.getList()){
            Object[] row = new Object[col_room_list.length];
            if (room.getHotelId() == hotel.getId()){
                row[0] = room.getId();
                row[1] = Season.getFetch(room.getHotelId()).getSeasonStart() + " - " + Season.getFetch(room.getHotelId()).getSeasonEnd();
                row[2] = room.getBed();
                row[3] = room.getRoomType();
                row[4] = room.getAdultPrice();
                row[5] = room.getChildPrice();
                row[6] = room.getRemainingRooms();
                row[7] = room.getProperties();
                mdl_room_list.addRow(row);
            }
        }

        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.setComponentPopupMenu(roomMenu);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);
        loadSeasonsCombo();
        loadHostelTypeCombo();

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

        // HostelType Table
        mdl_hostel_type = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column >= 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_hostel_type = {"HOTEL NAME", "HOSTEL TYPE"};
        mdl_hostel_type.setColumnIdentifiers(col_hostel_type);

        for (HostelType hostelType : HostelType.getHostelType()){
            Object[] row = new Object[col_hostel_type.length];
            if (hostelType.getHotel_id() == hotel.getId()){
                row[0] = hotel.getName();
                row[1] = hostelType.getType();
                mdl_hostel_type.addRow(row);
            }
        }

        tbl_hostel_type_list.setModel(mdl_hostel_type);
        tbl_hostel_type_list.getTableHeader().setReorderingAllowed(false);

        // Seasons Table;
        mdl_seasons = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column >= 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_seasons = {"HOTEL NAME", "SEASON"};
        mdl_seasons.setColumnIdentifiers(col_seasons);

        for (Season season : Season.getList()){
            Object[] row = new Object[col_seasons.length];
            if (season.getHotelId() == hotel.getId()){
                row[0] = hotel.getName();
                row[1] = season.getSeasonStart() + " - " + season.getSeasonEnd();
                mdl_seasons.addRow(row);
            }
        }

        tbl_seasons_list.setModel(mdl_seasons);
        tbl_seasons_list.getTableHeader().setReorderingAllowed(false);


        // Select Room ID
        tbl_room_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                String select_room_id = tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(), 0).toString();
                fld_room_id.setText(select_room_id);
            }catch(Exception exception){
            }
        });

        // Add Room;
        // Değerlendirme 11 : Otele oda ekleme
        btn_room_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_remaining_rooms) || Helper.isFieldEmpty(fld_adult_price) ||
                    Helper.isFieldEmpty(fld_child_price)){
                Helper.showMessage("fill");
            } else {
                int hotelID = Integer.parseInt(fld_hotel_id.getText());
                Item seasonIdItem = (Item) cmb_season_id.getSelectedItem();
                int seasonID = seasonIdItem.getKey();
                int bed = Integer.parseInt(cmb_room_bed.getSelectedItem().toString());
                String roomType = cmb_room_type.getSelectedItem().toString();
                Item hostelTypeItem = (Item) cmb_hostel_type.getSelectedItem();
                int hostelType = hostelTypeItem.getKey();
                int remainingRooms = Integer.parseInt(fld_remaining_rooms.getText().toString());
                int adultPrice = Integer.parseInt(fld_adult_price.getText().toString());
                int childPrice = Integer.parseInt(fld_child_price.getText().toString());
                String properties = fld_properties.getText();
                if (Room.addRoom(hotelID, seasonID, bed, roomType, hostelType, remainingRooms, adultPrice, childPrice, properties)){
                    Helper.showMessage("done");
                    loadRoomModel();
                    fld_hotel_id.setText(String.valueOf(hotel.getId()));
                    cmb_season_id.setSelectedItem(null);
                    cmb_room_bed.setSelectedItem(null);
                    cmb_room_type.setSelectedItem(null);
                    cmb_hostel_type.setSelectedItem(null);
                    fld_remaining_rooms.setText(null);
                    fld_adult_price.setText(null);
                    fld_child_price.setText(null);
                    fld_properties.setText(null);
                }
            }
        });

        // Delete Room;
        btn_room_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_room_id)){
                Helper.showMessage("fill");
            } else {
                int room_id = Integer.parseInt(fld_room_id.getText());
                    if (!ReservationInfo.isReservationExist(room_id) && Room.deleteRoom(room_id)){
                        Helper.showMessage("done");
                        loadRoomModel();
                    } else {
                        Helper.showMessage("ERROR! There might be reservation on this room. Please check reservation list!");
                    }
                }
        });

        // Add Hostel-Type;
        // Otele pansiyon tipi ekleme
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
                loadHostelTypeModel();
                loadHostelTypeCombo();

                onlyBedRadioButton.setSelected(false);
                BBRadioButton.setSelected(false);
                halfPensionRadioButton.setSelected(false);
                fullBoardRadioButton.setSelected(false);
                allInclusiveRadioButton.setSelected(false);
                ultraAllInclusiveRadioButton.setSelected(false);
                fullCreditExceptRadioButton.setSelected(false);
            }
        });

        // Add season
        // Değerlendirme 10 : Otele dönem ekleme
        btn_season_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_season_start) || Helper.isFieldEmpty(fld_season_end)){
                Helper.showMessage("fill");
            } else {
                Season.addSeason(hotel.getId(), fld_season_start.getText(), fld_season_end.getText());
                Helper.showMessage("done");
                loadSeasonsModel();
                loadSeasonsCombo();
            }
        });
    }

    public void loadRoomModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        Object[] col_room_list = {"ID", "SEASON", "BED", "ROOM TYPE", "ADULT PRICE", "CHILD PRICE", "REMAINING ROOMS", "PROPERTIES"};
        mdl_room_list.setColumnIdentifiers(col_room_list);
        for (Room room : Room.getList()){
            Object[] row = new Object[col_room_list.length];
            if (room.getHotelId() == hotel.getId()){
                row[0] = room.getId();
                row[1] = Season.getFetch(room.getHotelId()).getSeasonStart() + " - " + Season.getFetch(room.getHotelId()).getSeasonEnd();
                row[2] = room.getBed();
                row[3] = room.getRoomType();
                row[4] = room.getAdultPrice();
                row[5] = room.getChildPrice();
                row[6] = room.getRemainingRooms();
                row[7] = room.getProperties();
                mdl_room_list.addRow(row);
            }
        }
    }

    public void loadHostelTypeModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hostel_type_list.getModel();
        clearModel.setRowCount(0);
        Object[] col_hostel_type = {"HOTEL NAME", "HOSTEL TYPE"};
        for (HostelType hostelType : HostelType.getHostelType()){
            if (hostelType.getHotel_id() == hotel.getId()){
                Object[] row = new Object[col_hostel_type.length];
                row[0] = hotel.getName();
                row[1] = hostelType.getType();
                mdl_hostel_type.addRow(row);
            }
        }
    }

    public void loadSeasonsModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_seasons_list.getModel();
        clearModel.setRowCount(0);
        Object[] col_seasons = {"HOTEL NAME", "SEASON"};
        for (Season season : Season.getList()){
            if (season.getHotelId() == hotel.getId()){
                Object[] row = new Object[col_seasons.length];
                row[0] = hotel.getName();
                row[1] = season.getSeasonStart() + " - " + season.getSeasonEnd();
                mdl_seasons.addRow(row);
            }
        }
    }

    public void loadSeasonsCombo(){
        cmb_season_id.removeAllItems();
        for (Season obj : Season.getList()){
            if (obj.getHotelId() == hotel.getId()){
                String seasonLabel = obj.getSeasonStart() + " - " + obj.getSeasonEnd();
                cmb_season_id.addItem(new Item(obj.getId(), seasonLabel));
            }
        }
    }

    public void loadHostelTypeCombo(){
        cmb_hostel_type.removeAllItems();
        for (HostelType obj : HostelType.getHostelType()){
            if (obj.getHotel_id() == hotel.getId()){
                cmb_hostel_type.addItem(new Item(obj.getId(), obj.getType()));
            }
        }
    }

    // Season date;
    private void createUIComponents() throws ParseException {
        this.fld_season_start = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_season_end = new JFormattedTextField(new MaskFormatter("##/##/####"));
    }

    public static void main(String[] args) {
        Helper.setLayout();
        Hotel hotel = new Hotel();
        DetailsHotelGUI details = new DetailsHotelGUI(hotel);
    }
}
