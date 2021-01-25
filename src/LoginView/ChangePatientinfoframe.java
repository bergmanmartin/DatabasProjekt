package LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChangePatientinfoframe  extends JFrame implements ActionListener {


        Container container = getContentPane();
        Controller controller = null;





        JButton btnInfo = new JButton("Change info");

        JLabel lblSex = new JLabel("Sex (Enter onlyM or F)");
        JTextField tfSex = new JTextField();
        JLabel lblAdress = new JLabel("Adress");
        JTextField tfAdress = new JTextField();
        JLabel lblPhone = new JLabel("Phone Number");
        JTextField tfPhoneNumber = new JTextField();
        JLabel lblBirthday = new JLabel("Birthday");
        JTextField tfBirthday = new JTextField();


        JLabel lblfname = new JLabel("First Name");
        JLabel lblname = new JLabel("Last Name");
        JTextField tfFirstname = new JTextField();
        JTextField tfLastName = new JTextField();
        DBConnection connection;





    public ChangePatientinfoframe(Controller c) {
        controller = c;


        setTitle("Change info");
        setBounds(10, 10, 370, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayoutManager();

    }

        public void setLayoutManager() {
        container.setLayout(null);
        setLayout();
        addComponents();
        addAction();
    }

        public void setLayout() {




        lblSex.setBounds(50, 100, 150, 20);
        tfSex.setBounds(50, 125, 150, 20);
        lblAdress.setBounds(50, 150, 150, 20);
        tfAdress.setBounds(50, 175, 150, 20);
        lblPhone.setBounds(50, 200, 150, 20);
        tfPhoneNumber.setBounds(50, 225, 150, 20);
        lblBirthday.setBounds(50, 250, 150, 20);
        tfBirthday.setBounds(50, 275, 150, 20);

        lblfname.setBounds(50, 350, 150, 20);
        tfFirstname.setBounds(50, 375, 150, 20);
        lblname.setBounds(50, 400, 150, 20);
        tfLastName.setBounds(50, 425, 150, 20);



        btnInfo.setBounds(50, 700, 100, 30);

    }
        public void addComponents() {



        container.add(lblSex);
        container.add(tfSex);
        container.add(lblAdress);
        container.add(tfAdress);
        container.add(lblPhone);
        container.add(tfPhoneNumber);
        container.add(lblBirthday);
        container.add(tfBirthday);

        container.add(lblfname);
        container.add(lblname);
        container.add(tfFirstname);
        container.add(tfLastName);

        container.add(btnInfo);
    }

        public void addAction() {
        btnInfo.addActionListener(this);
    }


        @Override
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnInfo) {
            controller.showPatientViewFromInfoChange();
            controller.changePatientinfo();
        }

    }



        public String getCSex() {
        return tfSex.getText();
    }
        public String getCAdress(){
        return tfAdress.getText();
    }
        public String getCBirthday() {
        return  tfBirthday.getText();
    }

        public String getCPhoneNumber() {
        return  tfPhoneNumber.getText();
    }
        public String getCFname() {
        return  tfFirstname.getText();
    }
        public String getCLname() {
        return tfLastName.getText();
    }




}

