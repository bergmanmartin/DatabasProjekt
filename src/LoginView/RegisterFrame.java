package LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RegisterFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    Controller controller = null;
    JLabel userLbl = new JLabel("USERNAME");

    JLabel lblMedNb = new JLabel("Medical number");
    JTextField tfUser = new JTextField();
    JTextField tfPswrd = new JTextField();
    JButton btnRegister = new JButton("Register");
    JTextField tfMednb = new JTextField();
    JLabel lblSex = new JLabel("Sex (Enter onlyM or F)");
    JTextField tfSex = new JTextField();
    JLabel lblAdress = new JLabel("Adress");
    JTextField tfAdress = new JTextField();
    JLabel lblPhone = new JLabel("Phone Number");
    JTextField tfPhoneNumber = new JTextField();
    JLabel lblBirthday = new JLabel("Birthday");
    JTextField tfBirthday = new JTextField();
    JLabel lblDateReg = new JLabel("Register Date (Format YYYY-MM-DD)");
    JTextField tfDateReg = new JTextField();
    JLabel lblfname = new JLabel("First Name");
    JLabel lblname = new JLabel("Last Name");
    JTextField tfFirstname = new JTextField();
    JTextField tfLastName = new JTextField();
    DBConnection connection;





    public RegisterFrame(Controller c) {
        controller = c;


        setTitle("Registration form");
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

        userLbl.setBounds(50,0,150,20);
        tfUser.setBounds(50,25,150,20);
        lblMedNb.setBounds(50, 50, 150, 20);
        tfMednb.setBounds(50, 75, 150, 20);
        lblSex.setBounds(50, 100, 150, 20);
        tfSex.setBounds(50, 125, 150, 20);
        lblAdress.setBounds(50, 150, 150, 20);
        tfAdress.setBounds(50, 175, 150, 20);
        lblPhone.setBounds(50, 200, 150, 20);
        tfPhoneNumber.setBounds(50, 225, 150, 20);
        lblBirthday.setBounds(50, 250, 150, 20);
        tfBirthday.setBounds(50, 275, 150, 20);
        lblDateReg.setBounds(50 , 300, 250, 20);
        tfDateReg.setBounds(50, 325, 150, 20);
        lblfname.setBounds(50, 350, 150, 20);
        tfFirstname.setBounds(50, 375, 150, 20);
        lblname.setBounds(50, 400, 150, 20);
        tfLastName.setBounds(50, 425, 150, 20);



        btnRegister.setBounds(50, 700, 100, 30);

    }
    public void addComponents() {
        container.add(userLbl);
        container.add(tfUser);
        container.add(lblMedNb);
        container.add(tfMednb);
        container.add(lblSex);
        container.add(tfSex);
        container.add(lblAdress);
        container.add(tfAdress);
        container.add(lblPhone);
        container.add(tfPhoneNumber);
        container.add(lblBirthday);
        container.add(tfBirthday);
        container.add(lblDateReg);
        container.add(tfDateReg);
        container.add(lblfname);
        container.add(lblname);
        container.add(tfFirstname);
        container.add(tfLastName);

        container.add(btnRegister);
    }

    public void addAction() {
        btnRegister.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            //controller.storeUser();

            controller.showLoginAfterReg();
            addPatientToDatabase();


        }

    }

    public String getUsername() {
        return  tfUser.getText();
    }
    public int getMedNumb() {
        return Integer.parseInt(tfMednb.getText());
    }
    public String getSex() {
        return tfSex.getText();
    }
    public String getAdress(){
        return tfAdress.getText();
    }
    public String getBirthday() {
        return  tfBirthday.getText();
    }
    public String getRegDate(){
        return tfDateReg.getText();
    }
    public String getPhoneNumber() {
        return  tfPhoneNumber.getText();
    }
    public String getFname() {
        return  tfFirstname.getText();
    }
    public String getLname() {
        return tfLastName.getText();
    }

    public void addPatientToDatabase(){


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost";

            String us = "marre";
            String pw = "970321";
            Connection con = DriverManager.getConnection(url, us, pw);
            String query = "INSERT INTO Hospital.dbo.PATIENT_REGISTER values(" + getMedNumb() + "," + "'"
                    + getFname() + "'" + "," + "'" + getLname() + "','" + getSex() + "','" + getPhoneNumber() + "','"
                    + getBirthday() + "','" + getRegDate() + "'," + 0 +  ",'" + getUsername() + "','" + getAdress() +"');";
            Statement st = con.createStatement();
            int rows = st.executeUpdate(query);
            System.out.println("Rows impacted" + rows);



           /* while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5)
                        + " " + resultSet.getString(6) + " " + resultSet.getString(7) + " " + resultSet.getString(8) + " " + resultSet.getString(9));
            }*/
            con.close();
            st.close();


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "finns redan");
            e.printStackTrace();
        }

    }


}
