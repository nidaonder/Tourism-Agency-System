package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Admin;
import com.patikadev.Model.AgencyEmployee;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_user_uname;
    private JPasswordField fld_user_pass;
    private JButton btn_login;

    public LoginGUI(){

        add(wrapper);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)){
                Helper.showMessage("fill");
            } else {
                User user = User.getFetch(fld_user_uname.getText(), fld_user_pass.getText());
                if (user == null){
                    Helper.showMessage("User not found!");
                } else {

                    AdminGUI adGUI = null;
                    AgencyEmployeeGUI empGUI = null;

                    switch (user.getType()){
                        case "admin":
                            adGUI = new AdminGUI((Admin) user);
                            break;
                        case "employee":
                            empGUI = new AgencyEmployeeGUI((AgencyEmployee) user);
                            break;
                    }
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI login = new LoginGUI();
    }
}
