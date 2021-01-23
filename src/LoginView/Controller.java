package LoginView;

import Model.Doctor;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
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
<<<<<<< HEAD
    ChangeInformationFrame changeInformationFrame;

=======
>>>>>>> ErikCPbranch3
    int numOfDocs = 0;
    private int medNumb;
    private ArrayList<Integer> usList = new ArrayList<Integer>();


    public Controller() {
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

        //SetupDoctors();
    }

    public void showLoginFrame() {
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

<<<<<<< HEAD
    }
    public void showPatientView() {
        loginFrame.setVisible(false);
        patientView.setVisible(true);
    }
    public void showChangeInfoView(){
=======
>>>>>>> ErikCPbranch3

    }


    public void showDoctorView() {
        loginFrame.setVisible(false);
        doctorView.setVisible(true);
    }

    public void showRegistration() {
        loginFrame.setVisible(false);
        registerFrame.setVisible(true);
    }

    public void showAddDoctorView() {
        addDoctorFrame = new AddDoctorFrame(this);
    }

    public void modifyDoctor(String id, String skill, String price, String phone, String name) {

        addDoctorFrame = new AddDoctorFrame(this, id, skill, price, phone, name);
    }

    public void getInfo() {
        int id = addDoctorFrame.getTfID();
        String skill = addDoctorFrame.getTfSkill();
        int price = addDoctorFrame.getTfPrice();
        String phone = addDoctorFrame.getTfPhone();
        String name = addDoctorFrame.getTfName();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "Erik";
            String pw = "redred34";
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

    public void getInfoForUpdate(int oldId) {
        int id = addDoctorFrame.getTfID();
        String skill = addDoctorFrame.getTfSkill();
        int price = addDoctorFrame.getTfPrice();
        String phone = addDoctorFrame.getTfPhone();
        String name = addDoctorFrame.getTfName();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "Erik";
            String pw = "redred34";
            Connection con = DriverManager.getConnection(url, us, pw);
            System.out.println("dr_id = " + id + " dr_skill = " + skill + " dr_price = " + price + " dr_phonenumber = " + phone + " dr_name = " + name + " oldid = " + oldId);

            PreparedStatement st = con.prepareStatement("SET NOCOUNT ON UPDATE Hospital.dbo.DOCTOR_REGISTER SET dr_id = " + id + ", dr_skill = " + skill + ", " +
                    "dr_price = " + price + ", dr_phonenumber = " + phone + ", dr_name = " + name +
                    "WHERE dr_id = " + oldId + ";");
            st.setInt(oldId, id);
            st.executeUpdate();

            String deleteid = String.valueOf(oldId);
            deleteDoctor(deleteid);
            System.out.println(deleteid);



            /*String query = "UPDATE Hospital.dbo.DOCTOR_REGISTER SET dr_id = " + id + ", dr_skill = " + skill + ", " +
                    "dr_price = " + price + ", dr_phonenumber = " + phone + ", dr_name = "+ name +
                    "WHERE dr_id = " + oldId + ";";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                rs.getString(1);
                rs.getString(2);
                rs.getString(3);
                rs.getString(4);
                rs.getString(5);
                //adminView.getDoctormodel()
            }

             */

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

            String us = "Erik";
            String pw = "redred34";
            Connection con = DriverManager.getConnection(url, us, pw);

            String query1 = "SELECT * FROM Hopsital.dbo.PATIENT_REGISTER";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            User user;

            while (rs.next()) {
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
            e.printStackTrace();
            ;
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

<<<<<<< HEAD
            if (usList.size() == 0) {
                JOptionPane.showMessageDialog(null, "Medical ID doesnt exists");
            }
            else {
                loginFrame.setVisible(false);
                patientView.setVisible(true);
            }
=======
    public void displayPatients() {
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
>>>>>>> ErikCPbranch3

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteDoctor(String doctorID) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "Erik";
            String pw = "redred34";
            Connection connection = DriverManager.getConnection(url, us, pw);

<<<<<<< HEAD
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

=======
            PreparedStatement st = connection.prepareStatement("SET NOCOUNT ON DELETE FROM HOSPITAL.dbo.DOCTOR_REGISTER WHERE dr_id = " + doctorID + ";");
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
>>>>>>> ErikCPbranch3


    public void loadDoctor() {
        connection.testQuery();
    }

    public void doctorTableAddBooking(String index, String weekday) {

        String query = "";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "Erik";
            String pw = "redred34";
            Connection connection = DriverManager.getConnection(url, us, pw);

            switch (weekday) {
                case "Monday":
                    query = "UPDATE Hospital.dbo.DOCTOR_BOOKING " + "SET " + "Monday = 'X' " +
                            "WHERE Appointment_id = " + index + " ;";
                    break;

                case "Tuesday":
                    query = "UPDATE Hospital.dbo.DOCTOR_BOOKING " + "SET " + "Tuesday = 'X' " +
                            "WHERE Appointment_id = " + index + " ;";
                    break;

                case "Wednesday":
                    query = "UPDATE Hospital.dbo.DOCTOR_BOOKING " + "SET " + "Wednesday = 'X' " +
                            "WHERE Appointment_id = " + index + " ;";
                    break;
                case "Thursday":
                    query = "UPDATE Hospital.dbo.DOCTOR_BOOKING " + "SET " + "Thursday = 'X' " +
                            "WHERE Appointment_id = " + index + " ;";
                    break;
                case "Friday":
                    query = "UPDATE Hospital.dbo.DOCTOR_BOOKING " + "SET " + "Friday = 'X' " +
                            "WHERE Appointment_id = " + index + " ;";
                    break;

            }
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            doctorView.displayDoctorsBookingTable();
            connection.close();
            st.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void doctorTableRemoveBooking(String index, String weekday) {

        String query = "";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "Erik";
            String pw = "redred34";
            Connection connection = DriverManager.getConnection(url, us, pw);

            switch (weekday) {
                case "Monday":
                    query = "UPDATE Hospital.dbo.DOCTOR_BOOKING " + "SET " + "Monday = '' " +
                            "WHERE Appointment_id = " + index + " ;";
                    break;

                case "Tuesday":
                    query = "UPDATE Hospital.dbo.DOCTOR_BOOKING " + "SET " + "Tuesday = '' " +
                            "WHERE Appointment_id = " + index + " ;";
                    break;

                case "Wednesday":
                    query = "UPDATE Hospital.dbo.DOCTOR_BOOKING " + "SET " + "Wednesday = '' " +
                            "WHERE Appointment_id = " + index + " ;";
                    break;
                case "Thursday":
                    query = "UPDATE Hospital.dbo.DOCTOR_BOOKING " + "SET " + "Thursday = '' " +
                            "WHERE Appointment_id = " + index + " ;";
                    break;
                case "Friday":
                    query = "UPDATE Hospital.dbo.DOCTOR_BOOKING " + "SET " + "Friday = '' " +
                            "WHERE Appointment_id = " + index + " ;";
                    break;
            }
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            doctorView.displayDoctorsBookingTable();
            connection.close();
            st.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private ArrayList<Doctor> SetupDoctors() {

        ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
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

            String us = "Erik";
            String pw = "redred34";
            Connection con = DriverManager.getConnection(url, us, pw);

            String query1 = "SELECT * FROM Hopsital.dbo.PATIENT_REGISTER";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            User user;

            while (rs.next()) {
                user = new User(rs.getString("username"),
                        rs.getInt("medicinal_id"),
                        rs.getString("f_name"),
                        rs.getString("l_name"),
                        rs.getString("sex"),
                        rs.getString("phoneNumber"),
                        rs.getString("birthday"),
                        rs.getString("reg_date"),
                        rs.getString("adress"));

                doctorList.add(doctor);
            }


        } catch (Exception e) {
            e.printStackTrace();
            ;
        }

        return doctorList;


    }


}



