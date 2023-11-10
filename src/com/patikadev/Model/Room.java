package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {
    private int id;
    private int hotelId;
    private int seasonId;
    private int bed;
    private String roomType;
    private int hostelTypeID;
    private int remainingRooms;
    private int adultPrice;
    private int childPrice;
    private String properties;


    private Hotel hotel;

    public Room(){}

    public Room(int id, int hotelId, int seasonId, int bed,  String roomType, int hostelTypeID, int remainingRooms, int adultPrice,
                int childPrice, String properties) {
        this.id = id;
        this.hotelId = hotelId;
        this.seasonId = seasonId;
        this.bed = bed;
        this.roomType = roomType;
        this.hostelTypeID = hostelTypeID;
        this.remainingRooms = remainingRooms;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.properties = properties;
        this.hotel = Hotel.getFetch(hotelId);
    }

    public int getHostelTypeID() {
        return hostelTypeID;
    }

    public void setHostelTypeID(int hostelTypeID) {
        this.hostelTypeID = hostelTypeID;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public Hotel getHotel() {
        return Hotel.getFetch(this.hotelId);
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

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public int getRemainingRooms() {
        return remainingRooms;
    }

    public void setRemainingRooms(int remainingRooms) {
        this.remainingRooms = remainingRooms;
    }

    public static ArrayList<Room> getList(){
        ArrayList<Room> roomList = new ArrayList<>();
        Room obj;
        String query = "SELECT * FROM room";
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setHotelId(rs.getInt("hotel_id"));
                obj.setSeasonId(rs.getInt("season_id"));
                obj.setBed(rs.getInt("bed"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setHostelTypeID(rs.getInt("hostel_type_id"));
                obj.setRemainingRooms(rs.getInt("remaining_rooms"));
                obj.setAdultPrice(rs.getInt("adult_price"));
                obj.setChildPrice(rs.getInt("child_price"));
                obj.setProperties(rs.getString("properties"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }

    public static ArrayList<Room> getListByHotelId(int id){
        ArrayList<Room> roomListByHotelId = new ArrayList<>();
        Room obj;
        String query = "SELECT * FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setHotelId(rs.getInt("hotel_id"));
                obj.setSeasonId(rs.getInt("season_id"));
                obj.setBed(rs.getInt("bed"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setHostelTypeID(rs.getInt("hostel_type_id"));
                obj.setRemainingRooms(rs.getInt("remaining_rooms"));
                obj.setAdultPrice(rs.getInt("adult_price"));
                obj.setChildPrice(rs.getInt("child_price"));
                obj.setProperties(rs.getString("properties"));
                roomListByHotelId.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomListByHotelId;
    }

    public static boolean addRoom(int hotelId, int seasonId, int bed, String roomType, int hostelTypeID, int remainingRooms,
                                  int adultPrice, int childPrice, String properties){
        String query = "INSERT INTO room (hotel_id, season_id, bed, room_type, hostel_type_id, remaining_rooms, adult_price," +
                " child_price, properties ) VALUES (?, ?, ? ,?, ?, ?, ? , ?, ?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotelId);
            pr.setInt(2, seasonId);
            pr.setInt(3, bed);
            pr.setString(4, roomType);
            pr.setInt(5, hostelTypeID);
            pr.setInt(6, remainingRooms);
            pr.setInt(7, adultPrice);
            pr.setInt(8, childPrice);
            pr.setString(9, properties);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteRoom(int id){
        String query = "DELETE FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateRoom(int id, int bed, String roomType, int hostelTypeID, int seasonId, int remainingRooms, int adultPrice,
                                     int childPrice, String properties){
        String query = "UPDATE room SET bed = ?, room_type = ?, hostel_type_id = ?, season_id = ?, remaining_rooms = ?, " +
                "adult_price = ?, child_price = ?, properties = ? WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, bed);
            pr.setString(2, roomType);
            pr.setInt(3, hostelTypeID);
            pr.setInt(4, seasonId);
            pr.setInt(5, remainingRooms);
            pr.setInt(6, adultPrice);
            pr.setInt(7, childPrice);
            pr.setString(8, properties);
            pr.setInt(9, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Room getFetch(int id){
        Room obj = null;
        String query = "SELECT * FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Room(rs.getInt("id"), rs.getInt("hotel_id"),
                        rs.getInt("season_id"), rs.getInt("bed"),
                        rs.getString("room_type"), rs.getInt("hostel_type_id"),
                        rs.getInt("remaining_rooms"), rs.getInt("adult_price"),
                        rs.getInt("child_price"), rs.getString("properties"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }



}
