package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

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
    private JFormattedTextField formattedTextField1;
    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;
    private Object[] col_hotel_list;
    private JPopupMenu hotelMenu;
    private DefaultTableModel mdl_search_list;
    private Object[] row_search_list;


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


        /*


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

        Object[] col_search_list = {"Hotel Name", "City", "Region", "Room Type", "Hostel Type", "Season",
                "Room Features", "Bed", "Remaining Rooms", "Price"};
        mdl_search_list.setColumnIdentifiers(col_search_list);

        for (Room room : Room.getList()){
            for (HostelType hostelType : HostelType.getHostelType(room.getHotelId())){
                for (Season season : Season.getList()){
                    if (hostelType.getHotel_id() == room.getHotelId() && hostelType.getHotel_id() == season.getHotel_id()){
                        Object[] row = new Object[col_search_list.length];
                        row[0] = room.getHotel().getName();
                        row[1] = room.getHotel().getCity();
                        row[2] = room.getHotel().getRegion();
                        row[3] = room.getRoomType();
                        row[4] = hostelType.getType();
                        row[5] = season.getSeason_start() + " - " + season.getSeason_end();
                        row[6] = room.getHotel().getHotel_features();
                        row[7] = room.getBed();
                        row[8] = room.getRemainingRooms();
                        row[9] = "100";
                        mdl_search_list.addRow(row);
                    }
                }
            }
        }
        tbl_search_list.setModel(mdl_search_list);
        tbl_search_list.getTableHeader().setReorderingAllowed(false);

        // Search room
        btn_search.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_region_city_hotel)){
                Helper.showMessage("fill");

            } else {
                mdl_search_list.setRowCount(0);
                fld_region_city_hotel.setText(fld_region_city_hotel.getText().toLowerCase());

                for (Room room : Hotel.findBySearch(fld_region_city_hotel.getText(),
                        Integer.parseInt(cmb_person.getSelectedItem().toString()),
                        Integer.parseInt(cmb_child.getSelectedItem().toString()),
                        fld_check_in.getText(),
                        fld_check_out.getText())){
                    for (HostelType hostelType : HostelType.getHostelType(room.getHotelId())){

                        for (Season season : Season.getList()){
                            if (room.getHotelId() == hostelType.getHotel_id() && room.getHotelId() == season.getHotel_id()){
                                Object[] row = new Object[col_search_list.length];
                                row[0] = room.getHotel().getName();
                                row[1] = room.getHotel().getCity();
                                row[2] = room.getHotel().getRegion();
                                row[3] = room.getRoomType();
                                row[4] = hostelType.getType();
                                row[5] = "season";
                                row[6] = room.getHotel().getHotel_features();
                                row[7] = room.getBed();
                                row[8] = room.getRemainingRooms();
                                row[9] = "100";
                                mdl_search_list.addRow(row);
                                tbl_search_list.setModel(mdl_search_list);
                                tbl_search_list.getTableHeader().setReorderingAllowed(false);
                            }
                        }
                    }
                }
            }
        });




        */



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

    private void createUIComponents() throws ParseException {
        this.fld_check_in = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_check_in.setText("10/02/2024");
        this.fld_check_out = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_check_out.setText("12/02/2024");
    }
}
