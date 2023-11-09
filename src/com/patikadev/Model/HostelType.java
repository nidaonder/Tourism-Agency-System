package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HostelType {
    private int id;
    private int hotel_id;
    private String type;
    private Hotel hotel;

    public HostelType(){}
    public HostelType(int id, int hotel_id, String type) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.type = type;
        this.hotel = Hotel.getFetch(hotel_id);
    }

    public Hotel getHotel() {
        return Hotel.getFetch(this.hotel_id);
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public static boolean addHostelType(int hotel_id, String type){
        String query = "INSERT INTO hostel_type (hotel_id, type) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, type);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<HostelType> getHostelType(){
        ArrayList<HostelType> hostelTypeList = new ArrayList<>();
        HostelType obj;
        String query = "SELECT * FROM hostel_type";
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new HostelType();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setType(rs.getString("type"));
                hostelTypeList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hostelTypeList;
    }
    public static ArrayList<HostelType> getListByHotelId(int id){
        ArrayList<HostelType> hostelTypeList = new ArrayList<>();
        HostelType obj;
        String query = "SELECT * FROM hostel_type WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new HostelType();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setType(rs.getString("type"));
                hostelTypeList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hostelTypeList;
    }

}
