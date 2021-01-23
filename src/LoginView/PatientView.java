package LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PatientView extends JFrame implements ActionListener {

    private Container container = getContentPane();

    private String[] columnsPatientView = new String[]{"Medical Number", "First Name", "Last Name", "Sex", "Phone", "Birthday", "Registration Date", "Total Amt Paid"};

    private DBConnection connection;


    private JButton btnshowPatientInfo = new JButton("Show your Information");

    private JButton btnBook = new JButton("Book a meeting");

    private int numOfPatients;
    private int numOfdocs;


    private Controller controller = null;


    private JButton btnloadDoctors = new JButton("Load Doctors");


    private DefaultTableModel doctorsCol = new DefaultTableModel(new String[]{"Dr id", "Dr skill", "Dr Price", "Dr Phone", "Dr name"}, numOfdocs);
    private JTable doctorsTable = new JTable();
    private JScrollPane spDoctors = new JScrollPane(doctorsTable);

    private DefaultTableModel patientInfo = new DefaultTableModel(new String[]{"Med_id", "f_name", "l_name", "sex", "phone", "birthday", "reg_date", "totPricePaid", "adress"}, numOfPatients);
    private JTable patientTable = new JTable();
    private JScrollPane spPatient = new JScrollPane(patientTable);

    private JLabel lblPatient = new JLabel("Your Information");
    private JLabel lblDoctors = new JLabel("List of Doctors");
    private JTextField tfSearch = new JTextField();
    private JLabel lblSearch = new JLabel("Search for Dr. Specialization");
    private JButton btnSearch = new JButton("Search");
    private int mednumb;


    public PatientView(Controller c) {
        controller = c;
        connection = new DBConnection();


        setTitle("Patient page");
        setBounds(10, 10, 1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayoutManager();

        //System.out.println(mednumb);




    }

    public void displayDoctors() {
        doctorsCol.setRowCount(0);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "marre";
            String pw = "970321";
            Connection con = DriverManager.getConnection(url, us, pw);

            String query = "SELECT * FROM HOSPITAL.dbo.DOCTOR_REGISTER";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                String d = rs.getString(4);
                String e = rs.getString(5);

                doctorsCol.addRow(new Object[]{a, b, c, d, e});
            }
            con.close();
            st.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setLayoutManager() {
        container.setLayout(null);
        setLayout();
        addComponents();
        btnActions();


    }

    public void setLayout() {

        btnloadDoctors.setBounds(50, 300, 120, 40);
        lblSearch.setBounds(50, 150, 200, 40);
        tfSearch.setBounds(50, 200, 150, 40);
        btnSearch.setBounds(50, 250, 150, 40);


        btnBook.setBounds(50, 350, 120, 40);

        btnshowPatientInfo.setBounds(50, 400, 120, 40);

        doctorsTable.setModel(doctorsCol);

        patientTable.setModel(patientInfo);
        lblPatient.setBounds(500, 70, 100, 40);

        spPatient.setBounds(500, 100, 500, 600);

        spDoctors.setBounds(500, 100, 500, 600);

        lblDoctors.setBounds(500, 70, 100, 40);

        lblDoctors.setVisible(false);

    }

    public void addComponents() {
        container.add(btnloadDoctors);
        container.add(spDoctors);
        container.add(lblDoctors);
        container.add(btnshowPatientInfo);
        container.add(btnBook);
        container.add(lblPatient);
        container.add(spPatient);
        container.add(btnSearch);
        container.add(lblSearch);
        container.add(tfSearch);


    }

    public void btnActions() {

        btnloadDoctors.addActionListener(this);
        btnshowPatientInfo.addActionListener(this);
        btnSearch.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnloadDoctors) {
            lblPatient.setVisible(false);
            spDoctors.setVisible(true);
            lblDoctors.setVisible(true);
            displayDoctors();

        }
        if (e.getSource() == btnshowPatientInfo) {
            lblDoctors.setVisible(false);
            lblPatient.setVisible(true);
            spDoctors.setVisible(false);
            spPatient.setVisible(true);
            patientInfo.setRowCount(0);
            controller.showPatientInfo();

        }
        if (e.getSource() == btnSearch) {
            lblDoctors.setVisible(true);
            lblPatient.setVisible(false);
            spDoctors.setVisible(true);
            spPatient.setVisible(false);
            showSearchinTable();
        }


    }

    public String getSearch() {
        return tfSearch.getText();
    }

    public void showSearchinTable() {
        doctorsCol.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "marre";
            String pw = "970321";
            Connection con = DriverManager.getConnection(url, us, pw);

            String drskill = getSearch();

            String query = "SELECT * FROM HOSPITAL.dbo.DOCTOR_REGISTER where dr_skill LIKE " + "'" + drskill + "'" + ";";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                String d = rs.getString(4);
                String e = rs.getString(5);

                doctorsCol.addRow(new Object[]{a, b, c, d, e});
            }
            con.close();
            st.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }





    public void changeInformation(int medNumb) {
        String fname;
        String lname;
        String phonenumber;
        String adress;
        String birthday;
        int pricepaid;
        String sex;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "marre";
            String pw = "970321";
            Connection con = DriverManager.getConnection(url, us, pw);

           // String query = "UPDATE Hospital.dbo.PATIENT_REGISTER set"

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DefaultTableModel getPatientInfo() {
        return patientInfo;
    }

    public void addRow(Object[] rows){

        patientInfo.addRow(rows);
    }




}
