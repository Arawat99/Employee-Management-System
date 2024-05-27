package employee.management.system;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Start extends JFrame implements ActionListener {
    private JPanel contentPane;

    public void actionPerformed(ActionEvent e) {

    }

    // Custom panel for background image
    class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = ImageIO.read(ClassLoader.getSystemResource(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                int width = getWidth();
                int height = getHeight();
                g.drawImage(backgroundImage, 0, 0, width, height, this);
            }
        }
    }

    public Start() {
        // Frame setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1120, 630);

        // Outer panel for background
        BackgroundPanel backgroundPanel = new BackgroundPanel("icons/start.jpg");
        setContentPane(backgroundPanel); // Set as main content pane

        // Inner panel with null layout for manual positioning
        JPanel innerPanel = new JPanel(null); // Null layout for absolute positioning
        innerPanel.setOpaque(false); // Ensure the background is visible through this panel

        // Add buttons to the inner panel with specific coordinates
        JButton userButton = new JButton("USER");
        Color userButtonColor = SystemColor.activeCaption; // Original color
        userButton.setBackground(userButtonColor);
        userButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 30));
        userButton.setBounds(600, 300, 300, 70);
        userButton.setFocusPainted(false);

        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UserLogin();
            }
        });

        userButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                userButton.setBackground(userButtonColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                userButton.setBackground(userButtonColor);
            }
        });

        innerPanel.add(userButton);

        JButton adminButton = new JButton("ADMIN");
        Color adminButtonColor = SystemColor.activeCaption;
        adminButton.setBackground(adminButtonColor);
        adminButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 30));
        adminButton.setBounds(600, 400, 300, 70);

        // Adding hover effect
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });

        adminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                adminButton.setBackground(adminButtonColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                adminButton.setBackground(adminButtonColor);
            }
        });

        innerPanel.add(adminButton);
        backgroundPanel.add(innerPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Start();
    }
}
