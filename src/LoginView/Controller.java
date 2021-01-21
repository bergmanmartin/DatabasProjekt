package LoginView;

import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Controller {
    LoginFrame loginFrame;
    AdminView adminView;
    DoctorView doctorView;
    RegisterFrame registerFrame;
    DBConnection connection;
    AddDoctorFrame addDoctorFrame;
    Model.Doctor doctor;

    int numOfDocs = 0;




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

    public void showAddDoctorView(){
        addDoctorFrame = new AddDoctorFrame(this);

    }

    public void getInfo(){
        int id = addDoctorFrame.getTfID();
        String skill = addDoctorFrame.getTfSkill();
        int price = addDoctorFrame.getTfPrice();
        String phone = addDoctorFrame.getTfPhone();
        String name = addDoctorFrame.getTfName();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "marre";
            String pw = "970321";
            Connection con = DriverManager.getConnection(url, us, pw);
            String query = "Insert into Hospital.dbo.DOCTOR_REGISTER values(" + id + ", " + "'" + skill
                    + "'" + ", " + price + ", " + "'" + phone + "'" + "," + "'" + name + "');";
            Statement st = con.createStatement();
            st.executeQuery(query);

            con.close();
            st.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<User> storeUser() {

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
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "marre";
            String pw = "970321";
            Connection con = DriverManager.getConnection(url, us, pw);

            String query1 = "SELECT * FROM Hopsital.dbo.PATIENT_REGISTER";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            User user;

            while(rs.next()) {
                user = new User(rs.getString("username"),
                                rs.getInt("medicinal_id"),
                                rs.getString("f_name"),
                                rs.getString("l_name"),
                                rs.getString("sex"),
                                rs.getString("phoneNumber"),
                                rs.getString("birthday"),
                                rs.getString("reg_date"),
                                rs.getString("adress"));

                userList.add(user);
            }


        } catch (Exception e) {
            e.printStackTrace();;
        }

        return userList;

    }


    public void displayPatients(){
        JTable table = adminView.getPatientTable();
        ArrayList<User> list = storeUser();
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

    }







    public void loadDoctor() {
        connection.testQuery();
    }
}
