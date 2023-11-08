package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Season {
    private int id;
    private int hotelId;
    private String seasonStart;
    private String seasonEnd;
    private Hotel hotel;


    public Season(){}
    public Season(int id, int hotelId, String seasonStart, String  seasonEnd) {
        this.id = id;
        this.hotelId = hotelId;
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
        this.hotel = Hotel.getFetch(hotelId);
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

    public String getSeasonStart() {
        return seasonStart;
    }

    public void setSeasonStart(String seasonStart) {
        this.seasonStart = seasonStart;
    }

    public String getSeasonEnd() {
        return seasonEnd;
    }

    public void setSeasonEnd(String seasonEnd) {
        this.seasonEnd = seasonEnd;
    }

    public Hotel getHotel() {
        return Hotel.getFetch(this.hotelId);
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public static ArrayList<Season> getList(){
        ArrayList<Season> seasonList = new ArrayList<>();
        Season obj;
        String query = "SELECT * FROM season";
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Season();
                obj.setId(rs.getInt("id"));
                obj.setHotelId(rs.getInt("hotel_id"));
                obj.setSeasonStart(rs.getString("season_start"));
                obj.setSeasonEnd(rs.getString("season_end"));
                seasonList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;
    }

    public static boolean addSeason(int hotelId, String seasonStart, String seasonEnd){
        String query = "INSERT INTO season (hotel_id, season_start, season_end) VALUES (?, ?, ?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotelId);
            pr.setString(2, seasonStart);
            pr.setString(3, seasonEnd);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Season getFetch(int hotelId){
        Season obj = null;
        String query = "SELECT * FROM season WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Season(rs.getInt("id"), rs.getInt("hotel_id"),
                        rs.getString("season_start"), rs.getString("season_end"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }


   /*
    public static boolean isDateWithinRange(String check_in, String check_out, int hotel_id){

        //check_in = LocalDate.parse(check_in, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        //check_out = LocalDate.parse(check_out, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate checkInDate = LocalDate.parse(check_in, formatter);
        LocalDate checkOutDate = LocalDate.parse(check_out, formatter);

        LocalDate startDate = getFetch(hotel_id).getSeason_start();
        LocalDate endDate = getFetch(hotel_id).getSeason_end();

        if (checkInDate.isAfter(startDate) && checkOutDate.isBefore(endDate)){
            return true;
        } else {
            return false;
        }
    }

    */

}
