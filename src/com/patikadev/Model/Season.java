package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Season {
    private int id;
    private int hotel_id;
    private LocalDate season_start;
    private LocalDate season_end;
    private Hotel hotel;


    public Season(){}
    public Season(int id, int hotel_id, LocalDate season_start, LocalDate season_end) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.season_start = season_start;
        this.season_end = season_end;
        this.hotel = Hotel.getFetch(hotel_id);
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

    public LocalDate getSeason_start() {
        return season_start;
    }

    public void setSeason_start(LocalDate season_start) {
        this.season_start = season_start;
    }

    public LocalDate getSeason_end() {
        return season_end;
    }

    public void setSeason_end(LocalDate season_end) {
        this.season_end = season_end;
    }

    public Hotel getHotel() {
        return Hotel.getFetch(this.hotel_id);
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
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setSeason_start(rs.getObject("season_start", LocalDate.class));
                obj.setSeason_end(rs.getObject("season_finish", LocalDate.class));
                seasonList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;
    }

    public static Season getFetch(int hotel_id){
        Season obj = null;
        String query = "SELECT * FROM season WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Season(rs.getInt("id"), rs.getInt("hotel_id"),
                        rs.getObject("season_start", LocalDate.class), rs.getObject("season_finish", LocalDate.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

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
}
