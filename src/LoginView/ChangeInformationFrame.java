package LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeInformationFrame  extends JFrame implements ActionListener {
    Container container = getContentPane();
    Controller controller = null;
    JLabel userLbl = new JLabel("USERNAME");


    JTextField tfUser = new JTextField();
    JTextField tfPswrd = new JTextField();
    JButton btnChange = new JButton("Change");

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





    public ChangeInformationFrame(Controller c) {
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

        userLbl.setBounds(50,50,150,20);
        tfUser.setBounds(50,75,150,20);
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



        btnChange.setBounds(50, 700, 100, 30);

    }
    public void addComponents() {
        container.add(userLbl);
        container.add(tfUser);

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

        container.add(btnChange);
    }

    public void addAction() {
        btnChange.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnChange) {

        }

    }

    public String getUsername() {
        return  tfUser.getText();
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

    public static void main(String[] args) {
        Controller controller = new Controller();
        new ChangeInformationFrame(controller);
    }
}



