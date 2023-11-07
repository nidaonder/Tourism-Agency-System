package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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

    public static ArrayList<Season> getSeason(int hotel_id){
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

    /*
    import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // İlk tarih
        LocalDate date1 = LocalDate.of(2023, 11, 6);

        // Karşılaştırılacak tarih
        LocalDate date2 = LocalDate.of(2023, 12, 31);

        // date1, date2'den önce mi?
        boolean isBefore = date1.isBefore(date2);
        System.out.println("date1, date2'den önce mi? " + isBefore); // true

        // date1, date2'den sonra mı?
        boolean isAfter = date1.isAfter(date2);
        System.out.println("date1, date2'den sonra mı? " + isAfter); // false
    }
}

     */
}
