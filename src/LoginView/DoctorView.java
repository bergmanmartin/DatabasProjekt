package LoginView;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class DoctorView extends JFrame implements ActionListener {
    private Controller controller = null;

    private DBConnection connection;
    private Container container = getContentPane();
    private ArrayList doctorsList = new ArrayList();


    private int numOfRows;
    private String [] columnsDoctorView = new String[] {"Dr id", "Dr skill", "Dr Price", "Dr Phone", "Dr name"};

    private JButton btnAddBooking = new JButton("Add Booking");
    private JButton btnRemoveBooking = new JButton("Remove");
    private DefaultTableModel doctorsBookingColumn = new DefaultTableModel(new String[] {"    ", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"},0);
    private JTable doctorsBookingTable = new JTable();
    private JScrollPane spDoctorsBookings = new JScrollPane(doctorsBookingTable);


    public DoctorView (Controller c) {
        controller = c;
        connection = new DBConnection();
        setTitle("Doctor page");
        setBounds(10, 10, 900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        displayDoctorsBookingTable();

        setLayoutManager();
    }

    public void setLayoutManager(){
        container.setLayout(null);
        setLayout();
        addComponents();
        btnActions();
    }


    public void setLayout() {
        btnAddBooking.setBounds(50, 150, 120, 40);
        btnRemoveBooking.setBounds(50, 250, 120, 40);
        doctorsBookingTable.setModel(doctorsBookingColumn);
        spDoctorsBookings.setBounds(250, 100, 500, 600);
    }

    public void addComponents(){
        container.add(btnAddBooking);
        container.add(btnRemoveBooking);
        container.add(spDoctorsBookings);
    }

    public void btnActions(){
        btnAddBooking.addActionListener(this);
        btnRemoveBooking.addActionListener(this);
    }


    public void createTable() {

    }

    public void getTableValues() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAddBooking){
            int row = doctorsBookingTable.getSelectedRow();
            int column = doctorsBookingTable.getSelectedColumn();
            String weekday = doctorsBookingColumn.getColumnName(column);
            if (doctorsBookingTable.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null,"Select an appointment");
            }
            String index = (String) doctorsBookingTable.getModel().getValueAt(row,0);
            System.out.println(index);
            System.out.println(weekday);

            controller.doctorTableAddBooking(index, weekday);
            displayDoctorsBookingTable();
        }

        if (e.getSource() == btnRemoveBooking){
            int row = doctorsBookingTable.getSelectedRow();
            int column = doctorsBookingTable.getSelectedColumn();
            String weekday = doctorsBookingColumn.getColumnName(column);
            if (doctorsBookingTable.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null,"Select an appointment to remove");
            }
            String index = (String) doctorsBookingTable.getModel().getValueAt(row,0);
            System.out.println(index);
            System.out.println(weekday);

            controller.doctorTableRemoveBooking(index, weekday);
            displayDoctorsBookingTable();
        }

    }

    public void displayDoctorsBookingTable() {
        doctorsBookingColumn.setRowCount(0);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "Erik";
            String pw = "redred34";
            Connection con = DriverManager.getConnection(url, us, pw);

            String query = "SELECT * FROM HOSPITAL.dbo.DOCTOR_BOOKING";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                String d = rs.getString(4);
                String e = rs.getString(5);

                doctorsBookingColumn.addRow(new Object[]{a, b, c, d, e});
            }
            con.close();
            st.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

    }
}
