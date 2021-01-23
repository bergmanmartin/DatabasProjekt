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
    PatientView patientView;
    Model.Doctor doctor;
    ChangeInformationFrame changeInformationFrame;

    int numOfDocs = 0;
    private int medNumb;
    private ArrayList<Integer> usList = new ArrayList<Integer>();




    public Controller(){
        loginFrame = new LoginFrame(this);
        adminView = new AdminView(this);
        doctorView = new DoctorView(this);
        registerFrame = new RegisterFrame(this);
        changeInformationFrame = new ChangeInformationFrame(this);
        patientView = new PatientView(this);
        connection = new DBConnection();
        loginFrame.setVisible(true);
        adminView.setVisible(false);
        doctorView.setVisible(false);
        registerFrame.setVisible(false);
        changeInformationFrame.setVisible(false);
        patientView.setVisible(false);

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
    public void showPatientView() {
        loginFrame.setVisible(false);
        patientView.setVisible(true);
    }
    public void showChangeInfoView(){

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

    public void loginPatientFunction(int medID) {

        try {


            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "marre";
            String pw = "970321";
            Connection con = DriverManager.getConnection(url, us, pw);

            String query = "SELECT medicinal_id FROM Hospital.dbo.PATIENT_REGISTER where medicinal_id = " + medID + ";";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                usList.add(Integer.valueOf(rs.getString(1)));
            }

            if (usList.size() == 0) {
                JOptionPane.showMessageDialog(null, "Medical ID doesnt exists");
            }
            else {
                loginFrame.setVisible(false);
                patientView.setVisible(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void showPatientInfo() {


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "marre";
            String pw = "970321";
            Connection con = DriverManager.getConnection(url, us, pw);
            int m = loginFrame.getUserMed();
            System.out.println(m);

            String query = "SELECT * FROM Hospital.dbo.PATIENT_REGISTER where medicinal_id = "  + m + ";";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                String d = rs.getString(4);
                String e = rs.getString(5);
                String f = rs.getString(6);
                String g = rs.getString(7);
                String h = rs.getString(8);
                String i = rs.getString(9);

                Object[] rows = new Object[]{a, b, c, d, e, f, g, h, i};
                patientView.addRow(rows);

            }
            con.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void changePatientinfo() {


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "marre";
            String pw = "970321";
            Connection con = DriverManager.getConnection(url, us, pw);
            int m = loginFrame.getUserMed();
            System.out.println(m);

            String query = "SELECT * FROM Hospital.dbo.PATIENT_REGISTER where medicinal_id = "  + m + ";";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                String d = rs.getString(4);
                String e = rs.getString(5);
                String f = rs.getString(6);
                String g = rs.getString(7);
                String h = rs.getString(8);
                String i = rs.getString(9);

                Object[] rows = new Object[]{a, b, c, d, e, f, g, h, i};
                patientView.addRow(rows);

            }
            con.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getUserMed() {
     return Integer.parseInt(loginFrame.tfUser.getText());
    }



    public void loadDoctor() {
        connection.testQuery();
    }
}
