package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserDashboard extends JFrame implements ActionListener {
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JComboBox<String> employeeComboBox;
    private JTextField tname, taddress, tphone, temail, tLandlineNum;
    private JComboBox<String> teducation;
    private JButton updateButton;
    private String currentEmployeeId;

    public UserDashboard() {
        setTitle("User Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setBackground(new Color(82, 113, 255));
        ImageIcon logoIcon = new ImageIcon("icons/logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(10, 10, 100, 50);
        topPanel.add(logoLabel);

        JButton profileButton = new JButton("Profile");
        profileButton.addActionListener(this);
        topPanel.add(profileButton);

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(this);
        topPanel.add(updateBtn);

        JButton logoutButton = new JButton("Logout");
        topPanel.add(logoutButton);
        logoutButton.addActionListener(this);

        add(topPanel, BorderLayout.NORTH);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(new Color(255, 221, 179));

        JLabel defaultLabel = new JLabel("Select a task management feature to view information.");
        defaultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(defaultLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.CENTER);

        initializeUpdatePanel();

        setVisible(true);
    }

    private void initializeUpdatePanel() {
        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new GridLayout(8, 2, 10, 10));
        updatePanel.setBackground(new Color(255, 221, 179));

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        updatePanel.add(employeeIdLabel);

        employeeComboBox = new JComboBox<>();
        employeeComboBox.addActionListener(e -> loadEmployeeDetails((String) employeeComboBox.getSelectedItem()));
        updatePanel.add(employeeComboBox);

        JLabel nameLabel = new JLabel("Name:");
        updatePanel.add(nameLabel);

        tname = new JTextField();
        tname.setEditable(false);
        updatePanel.add(tname);

        JLabel addressLabel = new JLabel("Address:");
        updatePanel.add(addressLabel);

        taddress = new JTextField();
        updatePanel.add(taddress);

        JLabel phoneLabel = new JLabel("Phone:");
        updatePanel.add(phoneLabel);

        tphone = new JTextField();
        updatePanel.add(tphone);

        JLabel emailLabel = new JLabel("Email:");
        updatePanel.add(emailLabel);

        temail = new JTextField();
        updatePanel.add(temail);

        JLabel educationLabel = new JLabel("Highest Education:");
        updatePanel.add(educationLabel);

        teducation = new JComboBox<>(new String[]{"High School Graduate", "Undergraduate", "College Graduate"});
        updatePanel.add(teducation);

        JLabel landlineNumLabel = new JLabel("Landline Number:");
        updatePanel.add(landlineNumLabel);

        tLandlineNum = new JTextField();
        updatePanel.add(tLandlineNum);

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        updatePanel.add(updateButton);

        bottomPanel.add(updatePanel, BorderLayout.CENTER);

        loadEmployeeIDs();
    }

    private void loadEmployeeIDs() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDetails", "root", "password1234");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(STR."SELECT Employee_ID FROM Employee_Info WHERE Username = '\{UserLogin.tusername.getText()}'")) {

            while (rs.next()) {
                employeeComboBox.addItem(rs.getString("Employee_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadEmployeeDetails(String empID) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDetails", "root", "password1234");
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT pi.Name, ei.Address, ei.Phone, ei.Email, ei.HighestEducation, ei.Landline_Number " +
                             "FROM Employee_Info ei " +
                             "JOIN Personal_Info pi ON ei.Personal_ID = pi.Personal_ID " +
                             "WHERE ei.Employee_ID = ?")) {

            pstmt.setString(1, empID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    currentEmployeeId = empID;
                    tname.setText(rs.getString("Name"));
                    taddress.setText(rs.getString("Address"));
                    tphone.setText(rs.getString("Phone"));
                    temail.setText(rs.getString("Email"));
                    teducation.setSelectedItem(rs.getString("HighestEducation"));
                    tLandlineNum.setText(rs.getString("Landline_Number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateEmployeeDetails() {
        String address = taddress.getText();
        String phone = tphone.getText();
        String email = temail.getText();
        String education = (String) teducation.getSelectedItem();
        String landlineNumber = tLandlineNum.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDetails", "root", "password1234");
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE Employee_Info SET Address = ?, Phone = ?, Email = ?, HighestEducation = ?, Landline_Number = ? WHERE Employee_ID = ?")) {

            pstmt.setString(1, address);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, education);
            pstmt.setString(5, landlineNumber);
            pstmt.setString(6, currentEmployeeId);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee Information Updated");

            profile();  // Refresh profile view
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void profile() {
        clearRightPanel();
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new GridLayout(6, 2, 10, 10));
        profilePanel.setBackground(new Color(255, 221, 179));

        profilePanel.add(new JLabel("Name:"));
        profilePanel.add(new JLabel(tname.getText()));

        profilePanel.add(new JLabel("Address:"));
        profilePanel.add(new JLabel(taddress.getText()));

        profilePanel.add(new JLabel("Phone:"));
        profilePanel.add(new JLabel(tphone.getText()));

        profilePanel.add(new JLabel("Email:"));
        profilePanel.add(new JLabel(temail.getText()));

        profilePanel.add(new JLabel("Highest Education:"));
        profilePanel.add(new JLabel((String) teducation.getSelectedItem()));

        profilePanel.add(new JLabel("Landline Number:"));
        profilePanel.add(new JLabel(tLandlineNum.getText()));

        bottomPanel.add(profilePanel, BorderLayout.CENTER);
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }

    private void clearRightPanel() {
        bottomPanel.removeAll();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();
            String buttonText = clickedButton.getText();

            if (buttonText.equals("Profile")) {
                profile();
            } else if (buttonText.equals("Update")) {
                updateEmployeeDetails();
            } else if (buttonText.equals("Logout")) {
                JOptionPane.showMessageDialog(this, "Logout successful.");
                this.setVisible(false);
                new Start().setVisible(true);
                this.dispose();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserDashboard());
    }
}