package LoginView;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DoctorView extends JFrame implements ActionListener {


    private int numOfRows;
    private String [] columnsDoctorView = new String[] {"Dr id", "Dr skill", "Dr Price", "Dr Phone", "Dr name"};


    public DoctorView (Controller controller) {
        setTitle("Doctor page");
        setBounds(10, 10, 900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }


    public void setLayout() {

    }



    public void createTable() {

    }

    public void getTableValues() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) throws Exception {

    }
}
