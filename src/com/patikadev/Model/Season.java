package com.patikadev.Model;

import javax.swing.*;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import javax.swing.SpinnerDateModel;

public class Season {
    private int id;
    private int hotel_id;
    private LocalDate season_start;
    private LocalDate season_end;
    private Hotel hotel;


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


    /*

    public static void createDateModel(){
        SpinnerDateModel checkInModel = new SpinnerDateModel();
        SpinnerDateModel checkOutModel = new SpinnerDateModel();

        JSpinner spn_check_in = new JSpinner(checkInModel);
        JSpinner spn_check_out = new JSpinner(checkOutModel);

        JSpinner.DateEditor editorIn = new JSpinner.DateEditor(spn_check_in, "dd/MM/yyyy");
        JSpinner.DateEditor editorOut = new JSpinner.DateEditor(spn_check_out, "dd/MM/yyyy");

        spn_check_in.setEditor(editorIn);
        spn_check_out.setEditor(editorOut);
    }
     */

}
