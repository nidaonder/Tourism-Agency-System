package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HostelType {
    private int id;
    private String type;

    public HostelType(){}
    public HostelType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public static int[] typeConversionId(String hostel_id){
        Hotel hotel = new Hotel();
        String[] hostelIdArray = hostel_id.split(",");
        int[] hostelType = new int[hostelIdArray.length];
        for (int i = 0; i < hostelIdArray.length; i++){
            hostelType[i] = Integer.parseInt(hostelIdArray[i]);
        }
        return hostelType;
    }

    public static void getFetch(String hostel_id){
        int[] hostelType = typeConversionId(hostel_id);
        Hotel hotel = new Hotel();
        for (int i = 0; i < hostelType.length; i++){
            String query = "SELECT * FROM hostel_type[i] WHERE id = ?";
            try {
                PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }




    }
}
