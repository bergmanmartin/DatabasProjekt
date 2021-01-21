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

public class PatientView extends JFrame implements ActionListener {

    private Container container = getContentPane();

    private String[] columnsPatientView = new String[]{"Medical Number", "First Name", "Last Name", "Sex", "Phone", "Birthday", "Registration Date", "Total Amt Paid"};

    private DBConnection connection;


    private JButton btnshowPatientInfo = new JButton("Show your Information");

    private int numOfPatients;
    private int numOfdocs;

    private DefaultTableModel patientInfo = new DefaultTableModel(new String [] {"Med_id", "f_name", "l_name", "sex", "phone", "reg_date", "totPricePaid"}, numOfPatients);

    private Controller controller = null;



    private JButton btnloadDoctors = new JButton("Load Doctors");


    private DefaultTableModel doctorsCol = new DefaultTableModel(new String[]{"Dr id", "Dr skill", "Dr Price", "Dr Phone", "Dr name"}, numOfdocs);

    private JTable doctorsTable = new JTable();

    private JScrollPane spDoctors = new JScrollPane(doctorsTable);

    private JLabel lblDoctors = new JLabel("List of Doctors");


    public PatientView(Controller c) {
        controller = c;
        connection = new DBConnection();


        setTitle("Patient page");
        setBounds(10, 10, 1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayoutManager();


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

        btnloadDoctors.setBounds(50, 350, 120, 40);

        btnshowPatientInfo.setBounds(50, 250, 120, 40);

        doctorsTable.setModel(doctorsCol);

        spDoctors.setBounds(500, 100, 500, 600);

        lblDoctors.setBounds(500, 70, 100, 40);

        lblDoctors.setVisible(false);

    }

    public void addComponents() {
        container.add(btnloadDoctors);
        container.add(spDoctors);
        container.add(lblDoctors);
        container.add(btnshowPatientInfo);


    }

    public void btnActions() {

        btnloadDoctors.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnloadDoctors) {

            spDoctors.setVisible(true);
            lblDoctors.setVisible(true);
            displayDoctors();


        }


    }
}
