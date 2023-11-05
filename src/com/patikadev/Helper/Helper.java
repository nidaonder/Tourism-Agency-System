package com.patikadev.Helper;

import javax.swing.*;

public class Helper {

    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if ("Nimbus" .equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                         InstantiationException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static boolean isFieldEmpty (JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static void showMessage(String str){
        optionPage();
        String message, title;
        switch (str){
            case "fill":
                message = "Please, fill in all fields!";
                title = "Error!";
                break;
            case "done":
                message = "Transaction Successful!";
                title = "Success!";
                break;
            case "error":
                message = "Sorry, an error has occured!";
                title = "Error!";
                break;
            default:
                message = str;
                title = "Message";
                break;
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str){
        String message;
        switch (str){
            case "sure":
                message = "Do you want to perform this operation?";
                break;
            default:
                message = str;
        }
        return JOptionPane.showConfirmDialog(null, message,"Is it your final decision?", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPage(){
        UIManager.put("OptionPane.okButtonText" , "OKEY");
    }

}
