package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AgencyEmployeeGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_employee;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JScrollPane scrl_hotel_list;
    private JTable tbl_hotel_list;
    private JScrollPane scrl_room_list;
    private JTable tbl_search_list;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_region;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_email;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_features;
    private JButton btn_add_hotel;
    private JComboBox cmb_stars;
    private JTextField fld_region_city_hotel;
    private JButton btn_search;
    private JComboBox cmb_person;
    private JComboBox cmb_child;
    private JPanel pnl_search;
    private JFormattedTextField fld_check_in;
    private JFormattedTextField fld_check_out;
    private JButton reservastionButton;
    private JTable tbl_reservation_list;
    private JScrollPane scr_reservation_list;
    private JScrollPane scrl_reservation_mngmnt;
    private JPanel pnl_reservation_mngmnt;
    private JTextField fld_client_name;
    private JTextField fld_client_phone;
    private JTextField fld_client_email;
    private JTextField fld_client_note;
    private JComboBox cmb_client_room;
    private JTextField fld_client_check_in;
    private JTextField fld_client_check_out;
    private JComboBox cmb_client_adult;
    private JComboBox cmb_client_child;
    private JButton btn_reservation_update;
    private JTextField fld_reservation_id;
    private JButton btn_reservation_delete;
    private JFormattedTextField formattedTextField1;
    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;
    private Object[] col_hotel_list;
    private JPopupMenu hotelMenu;
    private DefaultTableModel mdl_search_list;
    private Object[] row_search_list;
    private String checkIn;
    private String checkOut;
    private DefaultTableModel mdl_reservation_list;
    private Object[] col_reservation_list;


    private final AgencyEmployee agencyEmployee;

    public AgencyEmployeeGUI(AgencyEmployee agencyEmployee){

        this.agencyEmployee = agencyEmployee;
        add(wrapper);
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Welcome '" + agencyEmployee.getName() + "'");

        // ModelHotelList
        // Update - Delete
        hotelMenu = new JPopupMenu();
        JMenuItem updateHotelMenu = new JMenuItem("Update");
        JMenuItem deleteHotelMenu = new JMenuItem("Delete");
        JMenuItem detailsHotelMenu = new JMenuItem("Details");
        hotelMenu.add(updateHotelMenu);
        hotelMenu.add(deleteHotelMenu);
        hotelMenu.add(detailsHotelMenu);

        updateHotelMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString());
            UpdateHotelGUI updateGUI = new UpdateHotelGUI(Hotel.getFetch(select_id));
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelModel();
                }
            });
        });

        deleteHotelMenu.addActionListener(e -> {
            if (Helper.confirm("sure")){
                int select_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0).toString());
                if (Hotel.deleteHotel(select_id)){
                    loadHotelModel();
                    Helper.showMessage("done");
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        detailsHotelMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString());
            DetailsHotelGUI detailsGUI = new DetailsHotelGUI(Hotel.getFetch(select_id));
            detailsGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosed(e);
                }
            });
        });
        //## Update - Delete.

        // Hotel Table:
        mdl_hotel_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column >= 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_hotel_list = {"ID", "Hotel Name", "City", "Region", "Address", "E-Mail", "Phone Number", "Stars", "Hotel Features"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);

        for (Hotel hotel : Hotel.getHotelList()){
            Object[] row = new Object[col_hotel_list.length];
            row[0] = hotel.getId();
            row[1] = hotel.getName();
            row[2] = hotel.getCity();
            row[3] = hotel.getRegion();
            row[4] = hotel.getAddress();
            row[5] = hotel.getEmail();
            row[6] = hotel.getPhone_number();
            row[7] = hotel.getStars();
            row[8] = hotel.getHotel_features();
            mdl_hotel_list.addRow(row);
        }

        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.setComponentPopupMenu(hotelMenu); // Update - Delete - Details

        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);

        tbl_hotel_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_hotel_list.rowAtPoint(point);
                tbl_hotel_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        // Search Table
        mdl_search_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column >= 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_search_list = {"ID", "Hotel Name", "Adress", "Room Type", "Hostel Type", "Season", "Properties",
                "Bed", "Remaining Rooms", "Adult Price", "Child Price"};
        mdl_search_list.setColumnIdentifiers(col_search_list);
        for (Room room : Room.getList()){
            Object[] row = new Object[col_search_list.length];
            row[0] = room.getId();
            row[1] = Hotel.getFetch(room.getHotelId()).getName();
            row[2] = Hotel.getFetch(room.getHotelId()).getAddress();
            row[3] = room.getRoomType();
            row[4] = room.getHostelTypeID();
            row[5] = room.getSeasonId();
            row[6] = room.getProperties();
            row[7] = room.getBed();
            row[8] = room.getRemainingRooms();
            row[9] = room.getAdultPrice();
            row[10] = room.getChildPrice();
            mdl_search_list.addRow(row);
        }
        tbl_search_list.setModel(mdl_search_list);
        tbl_search_list.getTableHeader().setReorderingAllowed(false);

        // Button Search
        btn_search.addActionListener(e -> {
            String regionHotelName = fld_region_city_hotel.getText();
            checkIn = fld_check_in.getText().trim();
            checkOut = fld_check_out.getText().trim();

            Date checkInDate = null;
            Date checkOutDate = null;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                checkInDate = formatter.parse(checkIn);
                checkOutDate = formatter.parse(checkOut);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            String query = "SELECT * FROM hotel WHERE name LIKE '%{{name}}%' OR address LIKE '%{{address}}%'";
            query = query.replace("{{name}}", regionHotelName);
            query = query.replace("{{address}}", regionHotelName);
            ArrayList<Hotel> searchingHotel = Hotel.searchHotelList(query);

            ArrayList<Room> tempRoomList = new ArrayList<>();

            for (Hotel hotel : searchingHotel){
                for (Room room : Room.getList()){
                    if (room.getHotelId() == hotel.getId()){
                        tempRoomList.add(room);
                    }
                }
            }
            ArrayList<Room> searchingRoomList = new ArrayList<>();
            for (Room room : tempRoomList){
                Season tempSeason = Season.getFetch(room.getHotelId());
                try {
                    Date seasonStartDate = formatter.parse(tempSeason.getSeasonStart());
                    Date seasonEndDate = formatter.parse(tempSeason.getSeasonEnd());
                    if (checkInDate.after(seasonStartDate) && checkOutDate.before(seasonEndDate)){
                        searchingRoomList.add(room);
                    }
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
            loadSearchModel(searchingRoomList);
        });

        // ReservationList Table;
        mdl_reservation_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column >= 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_reservation_list = {"ID", "NAME-SURNAME", "PHONE", "E-MAIL", "HOTEL", "CHECK-IN", "CHECK-OUT",
                "ADULT", "CHILD", "CLIENT NOTE", "TOTAL PRİCE"};
        mdl_reservation_list.setColumnIdentifiers(col_reservation_list);
        for (ReservationInfo reservation : ReservationInfo.getList()){
            Object[] row = new Object[col_reservation_list.length];
            row[0] = reservation.getId();
            row[1] = reservation.getClientName();
            row[2] = reservation.getClientPhone();
            row[3] = reservation.getClientEmail();
            row[4] = Hotel.getFetch(Room.getFetch(reservation.getRoomId()).getHotelId()).getName();
            row[5] = reservation.getCheckIn();
            row[6] = reservation.getCheckOut();
            row[7] = reservation.getAdultNum();
            row[8] = reservation.getChildNum();
            row[9] = reservation.getClientNote();
            row[10] = reservation.getTotalPrice();
            mdl_reservation_list.addRow(row);
        }
        tbl_reservation_list.setModel(mdl_reservation_list);
        tbl_reservation_list.getTableHeader().setReorderingAllowed(false);

        // Select Reservation ID;
        tbl_reservation_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_reservation_id = tbl_reservation_list.getValueAt(tbl_reservation_list.getSelectedRow(), 0).toString();
                fld_reservation_id.setText(select_reservation_id);
            } catch(Exception excepion){
            }
        });



        // Add Hotel button;
        btn_add_hotel.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_hotel_city) || Helper.isFieldEmpty(fld_hotel_region) ||
                    Helper.isFieldEmpty(fld_hotel_address) || Helper.isFieldEmpty(fld_hotel_email) ||
                    Helper.isFieldEmpty(fld_hotel_phone) || Helper.isFieldEmpty(fld_hotel_features)){
                Helper.showMessage("fill");

            } else {
                String name = fld_hotel_name.getText();
                String city = fld_hotel_city.getText();
                String region = fld_hotel_region.getText();
                String address = fld_hotel_address.getText();
                String email = fld_hotel_email.getText();
                String phoneNumber = fld_hotel_phone.getText();
                String stars = cmb_stars.getSelectedItem().toString();
                String hotelFeatures = fld_hotel_features.getText();

                if (Hotel.addHotel(name, city, region, address, email, phoneNumber, stars, hotelFeatures)){
                    Helper.showMessage("done");
                }
            }
            // Reset spaces
            fld_hotel_name.setText(null);
            fld_hotel_city.setText(null);
            fld_hotel_region.setText(null);
            fld_hotel_address.setText(null);
            fld_hotel_email.setText(null);
            fld_hotel_phone.setText(null);
            cmb_stars.setSelectedItem(null);
            fld_hotel_features.setText(null);
            loadHotelModel();
        });

        // Log-Out button;
        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });


        // ReservationButton;
        reservastionButton.addActionListener(e -> {
            int select_room_id = Integer.parseInt(tbl_search_list.getValueAt(tbl_search_list.getSelectedRow(), 0).toString());

            int adultNum = Integer.parseInt(cmb_person.getSelectedItem().toString());
            int childNum = Integer.parseInt(cmb_child.getSelectedItem().toString());
            String checkIn = fld_check_in.getText();
            String checkOut = fld_check_out.getText();

            ReservationGUI reservation = new ReservationGUI(Room.getFetch(select_room_id), adultNum, childNum, checkIn, checkOut);
            loadReservationModel();
        });

        // Delete Reservation
        btn_reservation_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_reservation_id)){
                Helper.showMessage("fill");
            } else {
                int select_reservation_id = Integer.parseInt(fld_reservation_id.getText());
                if (ReservationInfo.deleteReservation(select_reservation_id)){
                    Helper.showMessage("done");
                    loadReservationModel();
                } else {
                    Helper.showMessage("error");
                }
            }
            fld_reservation_id.setText(null);
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        AgencyEmployee ae = new AgencyEmployee();
        ae.setId(1);
        ae.setName("Nida Onder");
        ae.setUname("nidaonder");
        ae.setPassword("1111");
        ae.setType("employee");

        AgencyEmployeeGUI aeGUI = new AgencyEmployeeGUI(ae);
    }

    private void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Hotel obj : Hotel.getHotelList()){
            Object[] col_hotel_list = {"ID", "Hotel Name", "City", "Region", "Address", "E-Mail", "Phone Number", "Stars", "Hotel Features"};
            row_hotel_list = new Object[col_hotel_list.length];
            i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getRegion();
            row_hotel_list[i++] = obj.getAddress();
            row_hotel_list[i++] = obj.getEmail();
            row_hotel_list[i++] = obj.getPhone_number();
            row_hotel_list[i++] = obj.getStars();
            row_hotel_list[i++] = obj.getHotel_features();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }

    public void loadSearchModel(ArrayList<Room> rooms){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_search_list.getModel();
        clearModel.setRowCount(0);
        Object[] col_search_list = {"ID", "Hotel Name", "Adress", "Room Type", "Hostel Type", "Season", "Properties",
                "Bed", "Remaining Rooms", "Adult Price", "Child Price"};
        for (Room room : rooms){
            Object[] row = new Object[col_search_list.length];
            row[0] = room.getId();
            row[1] = Hotel.getFetch(room.getHotelId()).getName();
            row[2] = Hotel.getFetch(room.getHotelId()).getAddress();
            row[3] = room.getRoomType();
            row[4] = room.getHostelTypeID();
            row[5] = room.getSeasonId();
            row[6] = room.getProperties();
            row[7] = room.getBed();
            row[8] = room.getRemainingRooms();
            row[9] = room.getAdultPrice();
            row[10] = room.getChildPrice();
            mdl_search_list.addRow(row);
        }
        tbl_search_list.setModel(mdl_search_list);
        tbl_search_list.getTableHeader().setReorderingAllowed(false);
    }

    public void loadReservationModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_reservation_list.getModel();
        clearModel.setRowCount(0);
        Object[] col_reservation_list = {"ID", "NAME-SURNAME", "PHONE", "E-MAIL", "HOTEL", "CHECK-IN", "CHECK-OUT",
                "ADULT", "CHILD", "CLIENT NOTE", "TOTAL PRİCE"};
        mdl_reservation_list.setColumnIdentifiers(col_reservation_list);
        for (ReservationInfo reservation : ReservationInfo.getList()){
            Object[] row = new Object[col_reservation_list.length];
            row[0] = reservation.getId();
            row[1] = reservation.getClientName();
            row[2] = reservation.getClientPhone();
            row[3] = reservation.getClientEmail();
            row[4] = Hotel.getFetch(Room.getFetch(reservation.getRoomId()).getHotelId()).getName();
            row[5] = reservation.getCheckIn();
            row[6] = reservation.getCheckOut();
            row[7] = reservation.getAdultNum();
            row[8] = reservation.getChildNum();
            row[9] = reservation.getClientNote();
            row[10] = reservation.getTotalPrice();
            mdl_reservation_list.addRow(row);
        }
        tbl_reservation_list.setModel(mdl_reservation_list);
        tbl_reservation_list.getTableHeader().setReorderingAllowed(false);
    }

    private void createUIComponents() throws ParseException {
        this.fld_check_in = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_check_in.setText("10/02/2024");
        this.fld_check_out = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_check_out.setText("12/02/2024");
    }
}
