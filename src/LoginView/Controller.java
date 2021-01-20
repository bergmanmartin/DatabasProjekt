package LoginView;

import Model.User;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class Controller {
    LoginFrame loginFrame;
    AdminView adminView;
    DoctorView doctorView;
    RegisterFrame registerFrame;
    DBConnection connection;




    public Controller(){
        loginFrame = new LoginFrame(this);
        adminView = new AdminView(this);
        doctorView = new DoctorView(this);
        registerFrame = new RegisterFrame(this);
        connection = new DBConnection();
        loginFrame.setVisible(true);
        adminView.setVisible(false);
        doctorView.setVisible(false);
        registerFrame.setVisible(false);

    }

    public void showLoginFrame(){
        loginFrame.setVisible(true);
        adminView.setVisible(false);
    }
    public void showLoginAfterReg() {
        loginFrame.setVisible(true);
        registerFrame.setVisible(false);
    }

    public void showAdminView() {

        loginFrame.setVisible(false);
        adminView.setVisible(true);
    }


    public void showDoctorView(){
        loginFrame.setVisible(false);
        doctorView.setVisible(true);
    }
    public void showRegistration() {
        loginFrame.setVisible(false);
        registerFrame.setVisible(true);
    }

    public void storeUser() {

        ArrayList<User> userList = new ArrayList<User>();
        String usName = registerFrame.getUsername();
        int mednumb = registerFrame.getMedNumb();
        String sex = registerFrame.getSex();
        String adress = registerFrame.getAdress();
        String birthday = registerFrame.getBirthday();
        String regdate = registerFrame.getRegDate();
        String phonenumber = registerFrame.getPhoneNumber();
        String fname = registerFrame.getFname();
        String lname = registerFrame.getLname();


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            String url = "jdbc:sqlserver://localhost";

            String us = "marre";
            String pw = "970321";
            Connection con = DriverManager.getConnection(url, us, pw);
            Statement st = con.createStatement();
                
            st.executeQuery("Insert into [Hospital.dbo.PATIENT_REGISTER] VALUES (" + mednumb + ","  + "'" + fname + "'" + "," + "'" +lname
            +"'" + "," + "'" + sex + "'" + "," + "'" + phonenumber + "'" + "," + "'" + birthday + "'" + "," + "'" + regdate + "'" + "," +  0 + "'" +
                    adress + "'" + "," + "'" + usName + "'" + ");");
            st.close();
            con.close();



        } catch (Exception e) {
            e.printStackTrace();;
        }



    }

    /*public void displayPatients(){
        JTable table = adminView.getPatientTable();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getUsername();
            row[1] = list.get(i).getMednumber();
            row[2] = list.get(i).getFname();
            row[3] = list.get(i).getLname();
            row[4] = list.get(i).getSex();
            row[5] = list.get(i).getPhone();
            row[6] = list.get(i).getBirthday();
            row[7] = list.get(i).getRegdate();
            row[8] = list.get(i).getAdress();
            model.addRow(row);

        }

    }*/



    public void loadDoctor() {
        connection.testQuery();
    }
}
