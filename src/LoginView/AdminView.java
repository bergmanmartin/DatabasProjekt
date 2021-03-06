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

public class AdminView extends JFrame implements ActionListener {

    private Container container = getContentPane();

    private Controller controller = null;

    private DBConnection connection;

    private JButton btnChangePatient = new JButton("Modify Patient");
    private JButton btnAddDoctor = new JButton("Add Doctor");
    private JButton btnModifyDoctor = new JButton("Modify Doctor");
    private JButton btnswitchToPview = new JButton("Switch to PatientView");
    private JButton btnswitchToDview = new JButton("Switch to DoctorBView");
    private JButton btnloadPatients = new JButton("Load Patients");
    private JButton btnloadDoctors = new JButton("Load Doctors");
    private JButton btnloadAppointments = new JButton("Load Appointments");

    private int numOfdocs;

    private int numOfPats;
    private DefaultTableModel doctorsCol = new DefaultTableModel(new String[] {"Dr id", "Dr skill", "Dr Price", "Dr Phone", "Dr name"}, numOfdocs);
    private DefaultTableModel patientsCol = new DefaultTableModel(new String[] {"Med_id", "f_name", "l_name", "sex", "phone", "birthday", "reg_date","totPricePad", "username"}, numOfPats);

    private JTable doctorsTable = new JTable();
    private JTable patientTable = new JTable();

    private JScrollPane spDoctors = new JScrollPane(doctorsTable);
    private JScrollPane spPatients = new JScrollPane(patientTable);

    private JLabel lblDoctors = new JLabel("List of Doctors");
    private JLabel lblPatients = new JLabel("List of Patients");

    private JButton btndelDoctor = new JButton("Delete doctor");





    public AdminView(Controller c) {
        controller = c;
        connection = new DBConnection();

       setTitle("Admin page");
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

            String us = "Erik";
            String pw = "redred34";
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



    public void setLayoutManager(){
        container.setLayout(null);
        setLayout();
        addComponents();
        btnActions();
    }

    public void setLayout() {
        btnAddDoctor.setBounds(50, 150, 120, 40);
        btnChangePatient.setBounds(50, 200, 120, 40);
        btnModifyDoctor.setBounds(50, 250, 120, 40);
        btnloadDoctors.setBounds(50, 300, 120, 40);
        btnloadPatients.setBounds(50, 350, 120, 40);
        btndelDoctor.setBounds(50, 400, 120, 40);
        btnloadAppointments.setBounds(50,600,120,40);

        doctorsTable.setModel(doctorsCol);

        spDoctors.setBounds(500, 100, 500, 600);
        patientTable.setModel(patientsCol);
        spPatients.setBounds(500, 100, 500, 600);
        lblDoctors.setBounds(500, 70, 100, 40);
        lblPatients.setBounds(500, 70, 100, 40);
        lblPatients.setVisible(false);
        lblDoctors.setVisible(false);

    }

    public void addComponents() {
        container.add(btnAddDoctor);
        container.add(btnChangePatient);
        container.add(btnModifyDoctor);
        container.add(btnloadDoctors);
        container.add(btnloadPatients);
        container.add(spDoctors);
        container.add(spPatients);
        container.add(lblDoctors);
        container.add(lblPatients);
        container.add(btndelDoctor);
        container.add(btnloadAppointments);
    }

    public void btnActions() {
        btnAddDoctor.addActionListener(this);
        btnloadPatients.addActionListener(this);
        btnloadDoctors.addActionListener(this);
        btnModifyDoctor.addActionListener(this);
        btnChangePatient.addActionListener(this);
        btndelDoctor.addActionListener(this);
        btnloadAppointments.addActionListener(this);

    }

    public void displayAppointments(){


    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnloadDoctors) {
            spPatients.setVisible(false);
            spDoctors.setVisible(true);
            lblPatients.setVisible(false);
            lblDoctors.setVisible(true);
            displayDoctors();



        }
        if (e.getSource() == btnloadPatients ) {
            spDoctors.setVisible(false);
            spPatients.setVisible(true);
            lblDoctors.setVisible(false);
            lblPatients.setVisible(true);


        }

        if (e.getSource() == btnAddDoctor) {
            controller.showAddDoctorView();
            controller.getInfo();

        }

        if (e.getSource() == btnModifyDoctor){
            if(doctorsTable.getSelectedRow() != -1) {
                String id = doctorsTable.getModel().getValueAt(doctorsTable.getSelectedRow(), 0).toString();
                String skill = doctorsTable.getModel().getValueAt(doctorsTable.getSelectedRow(), 1).toString();
                String price = doctorsTable.getModel().getValueAt(doctorsTable.getSelectedRow(), 2).toString();
                String phone = doctorsTable.getModel().getValueAt(doctorsTable.getSelectedRow(), 3).toString();
                String name = doctorsTable.getModel().getValueAt(doctorsTable.getSelectedRow(), 4).toString();
                controller.modifyDoctor(id, skill, price, phone, name);
                controller.getInfoForUpdate(Integer.parseInt(doctorsTable.getModel().getValueAt(doctorsTable.getSelectedRow(), 0).toString()));

                System.out.println((Integer.parseInt(doctorsTable.getModel().getValueAt(doctorsTable.getSelectedRow(), 0).toString())));
            }
            displayDoctors();

        }

        if (e.getSource() == btndelDoctor){
            if (doctorsTable.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null,"Select a doctor to delete from the list!");
            }
            int row = doctorsTable.getSelectedRow();
            int column = 0;
            String dr_id = (String) doctorsTable.getModel().getValueAt(row,column);
            System.out.println(dr_id);
            controller.deleteDoctor(dr_id);
            displayDoctors();

        }

        if (e.getSource() == btnloadAppointments){
            System.out.println("Jag har blivit klickad!");
            controller.showDoctorView();
        }


    }

    public DefaultTableModel getDoctorsCol() {
        return doctorsCol;
    }
    public void setModel(JTable table) {
        this.patientTable = table;
    }
    public JTable getPatientTable() {
        return patientTable;
    }


    public void createNewDoctor() {

    }

<<<<<<< HEAD


=======
    public JTable getDoctormodel(){
        return doctorsTable;
    }
>>>>>>> ErikCPbranch3

    public static void main(String[] args) {
        Controller controller = new Controller();
        AdminView view = new AdminView(controller);


    }
}
