package employee.management.system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main_class extends JFrame {
    public Main_class() {
        // Load and set the background image
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 1120, 630);
        add(backgroundLabel);

        // Button: Logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(925, 500, 150, 50); // Set the position and size
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Main_class.this, "Logout successful.");
                Main_class.this.setVisible(false);
                new Start().setVisible(true);
                Main_class.this.dispose();
            }
        });
        backgroundLabel.add(logoutButton);

        // Button: Add Employee with icon
        JButton add = createStyledButton("Add Employee", new Rectangle(200, 100, 200, 200), new Color(34, 139, 34), "icons/add.png");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployee();
                setVisible(false);
            }
        });
        backgroundLabel.add(add);

        // Button: View Employee with icon
        JButton view = createStyledButton("View Employee", new Rectangle(700, 100, 200, 200), new Color(75, 0, 130), "icons/view.png");
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new View_Employee();
                setVisible(false);
            }
        });
        backgroundLabel.add(view);

        // Button: Remove Employee with icon
        JButton rem = createStyledButton("Remove Employee", new Rectangle(450, 100, 200, 200), new Color(178, 34, 34), "icons/remove.png");
        rem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveEmployee();
            }
        });
        backgroundLabel.add(rem);

        setSize(1120, 630);
        setLocation(100, 50);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JButton createStyledButton(String text, Rectangle bounds, Color bgColor, String iconName) {
        JButton button = new JButton(text);

        ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource(iconName));
        Image iconImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(iconImage);
        button.setIcon(scaledIcon);

        button.setVerticalTextPosition(SwingConstants.BOTTOM); // Text below the icon
        button.setHorizontalTextPosition(SwingConstants.CENTER); // Icon and text centered
        button.setMargin(new Insets(5, 10, 5, 10)); // Padding around the text and icon

        button.setBounds(bounds);
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFont(new Font("Arial", Font.BOLD, 16));

        button.setBorder(new LineBorder(Color.WHITE, 2, true));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        button.setFocusPainted(false);

        return button;
    }

    public static void main(String[] args) {
        new Main_class();
    }
}
