package LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDoctorFrame extends JFrame implements ActionListener {

    private Container container = getContentPane();


    private Controller C;

    private JTextField tfID = new JTextField("ID");
    private JTextField tfSkill = new JTextField("Skill");
    private JTextField tfPrice = new JTextField("Price");
    private JTextField tfPhone = new JTextField("Phone");
    private JTextField tfName = new JTextField("Name");
    private JButton addDoctor = new JButton("Add Doctor");
    private JButton cancel = new JButton("Cancel");



    public AddDoctorFrame(Controller C) {
        this.C = C;

        setTitle("Add a doctor");
        setBounds(10, 10, 700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);

        setLayoutManager();

    }

    public void setLayoutManager() {
        container.setLayout(null);
        setLayout();
        addComponents();
        btnActions();


    }
    public void setLayout(){
        tfID.setBounds(20, 100, 100, 40);
        tfSkill.setBounds(150, 100, 100, 40);
        tfPrice.setBounds(270, 100, 100, 40);
        tfPhone.setBounds(390, 100, 100, 40);
        tfName.setBounds(510, 100, 100, 40);
        addDoctor.setBounds(200, 250 , 100, 40);
        cancel.setBounds(400, 250,100,40);
    }

    public void addComponents(){

        container.add(tfID);
        container.add(tfSkill);
        container.add(tfPrice);
        container.add(tfPhone);
        container.add(tfName);
        container.add(addDoctor);
        container.add(cancel);
    }

    public void btnActions(){
        addDoctor.addActionListener(this);
        cancel.addActionListener(this);
    }

    public int getTfID() {
        return Integer.parseInt(tfID.getText());
    }

    public String getTfSkill() {
        return tfSkill.getText();
    }

    public int getTfPrice() {
        return Integer.parseInt(tfPrice.getText());
    }

    public String getTfPhone() {
        return tfPhone.getText();
    }

    public String getTfName() {
        return tfName.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addDoctor){
            C.getInfo();
            this.setVisible(false);
        }
        if(e.getSource() == cancel){
            this.setVisible(false);
        }
    }
}
