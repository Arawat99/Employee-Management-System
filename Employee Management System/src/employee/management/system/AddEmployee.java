package employee.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class AddEmployee extends JFrame implements ActionListener {
    JTextField tName, tAddress, tPhone, tLandlineNum, tEmail;
    JDateChooser tBirthday;
    JButton add, back;
    JComboBox<String> boxEducation, tDepartment, tPosition;
    JRadioButton male, female;
    ButtonGroup genderGroup;

    AddEmployee() {
        getContentPane().setBackground(new Color(82, 113, 255));

        JLabel heading = new JLabel("Add Employee Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("Serif", Font.BOLD, 25));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 100, 150, 30);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(name);

        tName = new JTextField();
        tName.setBounds(239, 100, 150, 30);
        tName.setBackground(new Color(255, 221, 179));
        add(tName);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(420, 100, 150, 30);
        gender.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(600, 100, 70, 30);
        male.setBackground(new Color(82, 113, 255));
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(680, 100, 70, 30);
        female.setBackground(new Color(82, 113, 255));
        add(female);

        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JLabel birthday = new JLabel("Birthday");
        birthday.setBounds(50, 150, 150, 30);
        birthday.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(birthday);

        tBirthday = new JDateChooser();
        tBirthday.setBounds(239, 150, 150, 30);
        tBirthday.setBackground(new Color(255, 221, 179));
        add(tBirthday);

        JLabel address = new JLabel("Address");
        address.setBounds(420, 150, 150, 30);
        address.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(address);

        tAddress = new JTextField();
        tAddress.setBounds(600, 150, 150, 30);
        tAddress.setBackground(new Color(255, 221, 179));
        add(tAddress);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(50, 200, 150, 30);
        phone.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(phone);

        tPhone = new JTextField();
        tPhone.setBounds(239, 200, 150, 30);
        tPhone.setBackground(new Color(255, 221, 179));
        add(tPhone);

        JLabel email = new JLabel("Email");
        email.setBounds(420, 200, 150, 30);
        email.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(email);

        tEmail = new JTextField();
        tEmail.setBounds(600, 200, 150, 30);
        tEmail.setBackground(new Color(255, 221, 179));
        add(tEmail);

        JLabel education = new JLabel("Highest Education");
        education.setBounds(50, 250, 200, 30);
        education.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(education);

        String[] items = {"High School Graduate", "Undergraduate", "College Graduate"};
        boxEducation = new JComboBox<>(items);
        boxEducation.setBackground(new Color(255, 221, 179));
        boxEducation.setBounds(239, 250, 150, 30);
        add(boxEducation);

        JLabel landlineNumber = new JLabel("Landline Number");
        landlineNumber.setBounds(420, 250, 200, 30);
        landlineNumber.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(landlineNumber);

        tLandlineNum = new JTextField();
        tLandlineNum.setBounds(600, 250, 150, 30);
        tLandlineNum.setBackground(new Color(255, 221, 179));
        add(tLandlineNum);

        JLabel position = new JLabel("Position Name");
        position.setBounds(50, 300, 150, 30);
        position.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(position);

        String[] positionItems = {"HR Manager", "Software Engineer", "Marketing Coordinator"};
        tPosition = new JComboBox<>(positionItems);
        tPosition.setBounds(239, 300, 150, 30);
        tPosition.setBackground(new Color(255, 221, 179));
        add(tPosition);

        JLabel department = new JLabel("Department Name");
        department.setBounds(420, 300, 200, 30);
        department.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(department);

        String[] departmentItems = {"Human Resources", "IT Department", "Marketing"};
        tDepartment = new JComboBox<>(departmentItems);
        tDepartment.setBounds(600, 300, 150, 30);
        tDepartment.setBackground(new Color(255, 221, 179));
        add(tDepartment);

        add = new JButton("ADD");
        add.setBounds(450, 450, 150, 40);
        add.setBackground(SystemColor.activeCaption);
        add.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 15));
        add.addActionListener(this);
        add(add);

        back = new JButton("BACK");
        back.setBounds(250, 450, 150, 40);
        back.setBackground(SystemColor.activeCaption);
        back.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 15));
        back.addActionListener(this);
        add(back);

        setSize(900, 600);
        setLayout(null);
        setLocation(300, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String name = tName.getText();
            String gender = male.isSelected() ? "Male" : (female.isSelected() ? "Female" : "");
            String address = tAddress.getText();
            String phone = tPhone.getText();
            String email = tEmail.getText();
            String education = (String) boxEducation.getSelectedItem();
            String landlineNum = tLandlineNum.getText();
            String position = (String) tPosition.getSelectedItem();
            String department = (String) tDepartment.getSelectedItem();


            // Format the date
            Date date = tBirthday.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String birthday = date != null ? sdf.format(date) : "";

            if (name.isEmpty() || gender.isEmpty() || birthday.isEmpty() || address.isEmpty() ||
                    phone.isEmpty() || email.isEmpty() || education.isEmpty() || landlineNum.isEmpty() ||
                    position.isEmpty() || department.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill out all fields.");
                return;
            }

            if (!Pattern.matches("^[a-zA-Z\\s]+$", name)) {
                JOptionPane.showMessageDialog(null, "Invalid name. Only letters and spaces allowed.");
                return;
            }

            if (!Pattern.matches("^\\d{11}$", phone)) {
                JOptionPane.showMessageDialog(null, "Invalid phone number. Must be 11 digits.");
                return;
            }

            if (!Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email)) {
                JOptionPane.showMessageDialog(null, "Invalid email format.");
                return;
            }

            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDetails", "root", "password1234");
                 Statement stmt = con.createStatement()) {

                String query = "INSERT INTO Personal_Info (Name, Gender, Birthday) VALUES ('" + name + "', '" + gender + "', '" + birthday + "')";
                stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    int personalID = -1;
                    if (rs.next()) {
                        personalID = rs.getInt(1);
                    }

                    query = "SELECT Position_ID FROM Position WHERE Position_Name = ?";
                    PreparedStatement preparedStatementPosition = con.prepareStatement(query);
                    preparedStatementPosition.setString(1, position);
                        ResultSet resultSet = preparedStatementPosition.executeQuery();
                    resultSet.next();
                        int positionID = resultSet.getInt("Position_ID");

                    query = "SELECT Department_ID FROM Department WHERE Department_Name = ?";
                    PreparedStatement preparedStatementDepartment = con.prepareStatement(query);
                    preparedStatementDepartment.setString(1, department);
                    ResultSet resultSet2 = preparedStatementDepartment.executeQuery();
                    resultSet2.next();
                    int departmentID = resultSet2.getInt("Department_ID");

                        query = "INSERT INTO Employee_Info (Personal_ID, Department_ID, Position_ID, Salary, Address, Phone, Email, Landline_Number, HighestEducation) " +
                                "VALUES ('" + personalID + "', '" + departmentID + "', '" + positionID + "', '0.00', '" + address + "', '" + phone + "', '" + email + "', '" + landlineNum + "', '" + education + "')";
                        stmt.executeUpdate(query);
                }

                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                new Main_class();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
