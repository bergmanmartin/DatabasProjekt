package LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminView extends JFrame implements ActionListener {

    private Container container = getContentPane();

    private Controller controller = null;

    private JButton btnAddPatient = new JButton("Add Patient");
    private JButton btnChangePatient = new JButton("Modify Patient");
    private JButton btnAddDoctor = new JButton("Add Doctor");
    private JButton btnModifyDoctor = new JButton("Modify Doctor");
    private JButton btnswitchToPview = new JButton("Switch to PatientView");
    private JButton btnswitchToDview = new JButton("Switch to DoctorBView");
    private JButton btnloadPatients = new JButton("Load Patients");
    private JButton btnloadDoctors = new JButton("Load Doctors");

    private int numOfdocs;

    private int numOfPats;
    private DefaultTableModel doctorsCol = new DefaultTableModel(new String[] {"Dr id", "Dr skill", "Dr Price", "Dr Phone", "Dr name"}, numOfdocs);
    private DefaultTableModel patientsCol = new DefaultTableModel(new String[] {"Med_id", "f_name", "l_name", "sex", "phone", "birthday", "reg_date","totPricePad"}, numOfPats);

    private JTable doctorsTable = new JTable();
    private JTable patientTable = new JTable();

    private JScrollPane spDoctors = new JScrollPane(doctorsTable);
    private JScrollPane spPatients = new JScrollPane(patientTable);

    private JLabel lblDoctors = new JLabel("List of Doctors");
    private JLabel lblPatients = new JLabel("List of Patients");




    public AdminView(Controller c) {
        controller = c;

       setTitle("Admin page");
       setBounds(10, 10, 1200, 800);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setResizable(false);

       setLayoutManager();

    }

    public void setLayoutManager(){
        container.setLayout(null);
        setLayout();
        addComponents();
        btnActions();


    }

    public void setLayout() {
        btnAddDoctor.setBounds(50, 150, 120, 40);
        btnAddPatient.setBounds(50, 200, 120, 40);
        btnChangePatient.setBounds(50, 250, 120, 40);
        btnModifyDoctor.setBounds(50, 300, 120, 40);
        btnloadDoctors.setBounds(50, 350, 120, 40);
        btnloadPatients.setBounds(50, 400, 120, 40);
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
        container.add(btnAddPatient);
        container.add(btnChangePatient);
        container.add(btnModifyDoctor);
        container.add(btnloadDoctors);
        container.add(btnloadPatients);
        container.add(spDoctors);
        container.add(spPatients);
        container.add(lblDoctors);
        container.add(lblPatients);
    }

    public void btnActions() {
        btnloadPatients.addActionListener(this);
        btnloadDoctors.addActionListener(this);
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnloadDoctors) {
            spPatients.setVisible(false);
            spDoctors.setVisible(true);
            lblPatients.setVisible(false);
            lblDoctors.setVisible(true);


        }
        if (e.getSource() == btnloadPatients ) {
            spDoctors.setVisible(false);
            spPatients.setVisible(true);
            lblDoctors.setVisible(false);
            lblPatients.setVisible(true);


        }

        if (e.getSource() == btnAddDoctor) {
            numOfdocs++;
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

    public static void main(String[] args) {
        Controller controller = new Controller();
        AdminView view = new AdminView(controller);


    }
}
