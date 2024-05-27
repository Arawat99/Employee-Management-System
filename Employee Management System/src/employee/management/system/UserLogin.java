package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UserLogin extends JFrame implements ActionListener {

    static JTextField tusername;
    JPasswordField tpassword;
    JButton login, back;

    public UserLogin() {
        // Set up the frame
        setSize(600, 300);
        setLocation(450, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ADMIN LOGIN Label
        JLabel lblNewLabel = new JLabel("USER LOGIN");
        lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 20));
        lblNewLabel.setBounds(160, 20, 200, 30);
        add(lblNewLabel);

        // Username Label
        JLabel username = new JLabel("Username");
        username.setBounds(40, 60, 100, 30);
        add(username);

        // Username TextField
        tusername = new JTextField();
        tusername.setBounds(150, 60, 150, 30);
        add(tusername);

        // Password Label
        JLabel password = new JLabel("Password");
        password.setBounds(40, 100, 100, 30);
        add(password);

        // Password Field
        tpassword = new JPasswordField();
        tpassword.setBounds(150, 100, 150, 30);
        add(tpassword);

        // LOGIN Button
        login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        // QUIT Button
        back = new JButton("QUIT");
        back.setBounds(150, 180, 150, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i22 = i11.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel imgg = new JLabel(i33);
        imgg.setBounds(350,10,600,400);
        add(imgg);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/LoginB.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0,0,600,300);
        add(img);

        setSize(600,300);
        setLocation(450,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login){
            try {
                String username = tusername.getText();
                String password = tpassword.getText();

                conn conn = new conn();
                String query = "select * from Userlogin where username = '"+ username +"' and password = '"+password+"'";
                ResultSet resultSet = conn.statement.executeQuery(query);
                if (resultSet.next()){
                    setVisible(false);
                    new UserDashboard();
                }else {
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                }

            }catch (Exception E){
                E.printStackTrace();
            }

        } else if (e.getSource() == back) {
            System.exit(90);
        }
    }

    public static void main(String[] args) {
        new UserLogin();
    }
}

