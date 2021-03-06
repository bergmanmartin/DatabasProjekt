package LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    Container container = getContentPane();


    Controller controller = null;


    JLabel userLbl = new JLabel("MedID");

    JTextField tfUser = new JTextField();

    JButton btnLogin = new JButton("Login");
    JButton btnReset = new JButton("Reset");
    JButton btnRegister = new JButton("Register");
    JCheckBox showPassword = new JCheckBox("Show Password");
    JComboBox<String> menu = new JComboBox<>(new String[] {"Doctor", "Admin", "Patient"});


    private int mednumb;
    private int login;


    public LoginFrame (Controller c) {
        controller = c;

        setLayoutManager();
        addActions();
        setTitle("Hospital System Login");
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


    }

    public void addActions() {
        btnLogin.addActionListener(this);
        btnReset.addActionListener(this);
        btnRegister.addActionListener(this);
        showPassword.addActionListener(this);
    }

    public void setLayoutManager() {
        container.setLayout(null);
        setLocationAndSize();
        addCompononents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnReset) {
            tfUser.setText("");

        }

         if (e.getSource() == btnLogin) {
            if (menu.getSelectedItem() == "Admin")
            controller.showAdminView();
        }

         if (e.getSource() == btnLogin) {

            if (menu.getSelectedItem() == "Patient") {
                login = Integer.parseInt(tfUser.getText());
               // System.out.println(login);

                controller.loginPatientFunction(getUserMed());

            }
        }
         if (e.getSource() == btnRegister) {
            if (menu.getSelectedItem() == "Patient") {
                controller.showRegistration();
            }
        }

        if (e.getSource() == btnLogin){
            if (menu.getSelectedItem() == "Doctor"){
                controller.showDoctorView();
            }
        }

       /* if (e.getSource() == btnLogin) {
            if (menu.getSelectedItem() == "Doctor") {
                controller.showDoctorView();
            }
        }*/

    }

    public void setLocationAndSize() {
        userLbl.setBounds(50,150,100,30);

        tfUser.setBounds(150,150,150,30);


        btnLogin.setBounds(50,300,100,30);
        btnReset.setBounds(200,300,100,30);
        btnRegister.setBounds(50, 400, 100, 30);
        menu.setBounds(200, 400, 100, 30);

    }

    public void addCompononents() {
        container.add(userLbl);

        container.add(tfUser);


        container.add(btnLogin);
        container.add(btnReset);
        container.add(btnRegister);
        container.add(menu);


    }

    public int getUserMed() {
        return (login);
    }
    public void setUserMed(int login) {
        this.login = login;
    }





    public static void main(String[] args) {
        Controller controller = new Controller();


    }
}
