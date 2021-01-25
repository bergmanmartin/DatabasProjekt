package LoginView;

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
    ChangePatientinfoframe changepatientinfoframe;

    int numOfDocs = 0;
    private int medNumb;
    private ArrayList<Integer> usList = new ArrayList<Integer>();




    public Controller(){
        loginFrame = new LoginFrame(this);
        adminView = new AdminView(this);
        doctorView = new DoctorView(this);
        registerFrame = new RegisterFrame(this);
        changepatientinfoframe = new ChangePatientinfoframe(this);
        patientView = new PatientView(this);
        connection = new DBConnection();
        loginFrame.setVisible(true);
        adminView.setVisible(false);
        doctorView.setVisible(false);
        registerFrame.setVisible(false);
        changepatientinfoframe.setVisible(false);
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
        patientView.setVisible(false);
        changepatientinfoframe.setVisible(true);
    }
    public void showPatientViewFromInfoChange() {
        changepatientinfoframe.setVisible(false);
        patientView.setVisible(true);
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
        String fname = changepatientinfoframe.getCFname();
        String lname = changepatientinfoframe.getCLname();
        String phonenumber = changepatientinfoframe.getCPhoneNumber();
        String adress = changepatientinfoframe.getCAdress();
        String birthday = changepatientinfoframe. getCBirthday();
        String sex = changepatientinfoframe.getCSex();



        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "marre";
            String pw = "970321";
            Connection con = DriverManager.getConnection(url, us, pw);
            int m = loginFrame.getUserMed();
            System.out.println(m);

            String query = "update Hospital.dbo.PATIENT_REGISTER set f_name=?, l_name=?, sex=?, phoneNumber=?, birthday=?, adress=? where medicinal_id =" + m +";";


                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, sex);
                ps.setString(4, phonenumber);
                ps.setString(5, birthday);
                ps.setString(6, adress);


                int rowsAffected = ps.executeUpdate();
                System.out.println(rowsAffected);


            con.close();
            ps.close();

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
