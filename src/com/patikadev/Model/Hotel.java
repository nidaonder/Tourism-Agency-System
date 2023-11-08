package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private String region;
    private String address;
    private String email;
    private String phone_number;
    private String stars;
    private String hotel_features;

    public Hotel(){}

    public Hotel(int id, String name, String city, String region, String address, String email, String phone_number,
                 String stars, String hotel_features){
        this.id = id;
        this.name = name;
        this.city = city;
        this.region = region;
        this.address = address;
        this.email = email;
        this.phone_number = phone_number;
        this.stars = stars;
        this.hotel_features = hotel_features;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getHotel_features() {
        return hotel_features;
    }

    public void setHotel_features(String hotel_features) {
        this.hotel_features = hotel_features;
    }

    public static ArrayList<Hotel> getHotelList(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotel";
        Hotel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setRegion(rs.getString("region"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone_number(rs.getString("phone_number"));
                obj.setStars(rs.getString("stars"));
                obj.setHotel_features(rs.getString("hotel_features"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }
    public static boolean addHotel(String name, String city, String region, String address, String email,
                                   String phoneNumber, String stars, String hotelFeatures) {
        String query = "INSERT INTO hotel (name, city, region, address, email, " +
                "phone_number, stars, hotel_features) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Hotel findHotel = Hotel.getFetchByMail(email);
        if (findHotel != null){
            Helper.showMessage("This hotel has been previously saved!");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,city);
            pr.setString(3,region);
            pr.setString(4,address);
            pr.setString(5,email);
            pr.setString(6,phoneNumber);
            pr.setString(7,stars);
            pr.setString(8,hotelFeatures);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean updateHotel(int id, String name, String city, String region, String address, String email, String phone_number, String stars,
                                      String hotel_features){
        String query = "UPDATE hotel SET name=?, city=?, region=?, address=?, email=?, phone_number=?, stars=?, hotel_features=? WHERE id=?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2,city);
            pr.setString(3,region);
            pr.setString(4,address);
            pr.setString(5,email);
            pr.setString(6,phone_number);
            pr.setString(7,stars);
            pr.setString(8,hotel_features);
            pr.setInt(9, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteHotel(int id){
        ArrayList<Room> roomArrayList = Room.getList();
        for (Room obj : roomArrayList){
            if (obj.getHotelId() == id){
                Room.deleteRoom(obj.getId());
            }
        }
        try {
            String query = "DELETE FROM hotel WHERE id = ?";
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Hotel getFetch(int id){
        Hotel obj = null;
        String query =  "SELECT * FROM hotel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Hotel(rs.getInt("id"), rs.getString("name"),
                        rs.getString("city"), rs.getString("region"),
                        rs.getString("address"), rs.getString("email"),
                        rs.getString("phone_number"), rs.getString("stars"),
                        rs.getString("hotel_features"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Hotel getFetchByMail(String email){
        Hotel obj = null;
        String query =  "SELECT * FROM hotel WHERE email = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, email);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Hotel(rs.getInt("id"), rs.getString("name"),
                        rs.getString("city"), rs.getString("region"),
                        rs.getString("address"), rs.getString("email"),
                        rs.getString("phone_number"), rs.getString("stars"),
                        rs.getString("hotel_features"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static ArrayList<Room> findBySearch(String search, int person, int child, String check_in, String check_out){
        ArrayList<Hotel> searchHotelList = new ArrayList<>();
        ArrayList<Room> searchedRoomList = new ArrayList<>();

        Hotel obj;
        String query = "SELECT * FROM hotel WHERE region = ? OR city = ? OR name = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, search);
            pr.setString(2, search);
            pr.setString(3, search);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setRegion(rs.getString("region"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone_number(rs.getString("phone_number"));
                obj.setStars(rs.getString("stars"));
                obj.setHotel_features(rs.getString("hotel_features"));
                obj.setName(obj.getName().toLowerCase());
                searchHotelList.add(obj);
            }

            for (Hotel hotel : searchHotelList){
                for (Room room : Room.getList()){
                    if (room.getHotelId() == hotel.getId() && room.getBed() >= (person + child)){// tarih kontrolü sadece oda fiyatını veya stoğu etkiler. listeleme şartı olamaz
                        searchedRoomList.add(room);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchedRoomList;
    }
}
