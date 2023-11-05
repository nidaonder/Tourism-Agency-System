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
    private String roomType;
    private int bed;
    private int remainingRooms;

    public Room(){}

    public Room(int id, int hotelId, String roomType, int bed, int remainingRooms) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomType = roomType;
        this.bed = bed;
        this.remainingRooms = remainingRooms;
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
                obj.setRoomType(rs.getString("room_type"));
                obj.setBed(rs.getInt("bed"));
                obj.setRemainingRooms(rs.getInt("remaining_rooms"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }

    public static boolean addRoom(int hotelId, String roomType, int bed, int remainingRooms){
        String query = "INSERT INTO room (hotel_id, room_type, bed, remaining_rooms) VALUES (?, ?, ? ,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotelId);
            pr.setString(2, roomType);
            pr.setInt(3, bed);
            pr.setInt(4, remainingRooms);
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
}
