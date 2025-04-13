import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class Start extends JPanel {
    private Image backgroundImage;
    private JButton startButton;

    public Start() {
        setLayout(null); // manual positioning

        // Load background image
        backgroundImage = new ImageIcon(getClass().getResource("/assets/background.png")).getImage();

        // Create Start Button
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource("/assets/lowerPipe.png"));
        startButton = new JButton("Mulai Game", buttonIcon);
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        startButton.setVerticalTextPosition(SwingConstants.CENTER);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setForeground(Color.BLACK); // warna teks
        startButton.setFocusPainted(false);
        startButton.setBorder(new RoundedBorder(15));
        startButton.setContentAreaFilled(false); // penting supaya background gambar terlihat
        startButton.setOpaque(false);
        startButton.setBounds(90, 250, 180, 50);



        // Aksi saat tombol ditekan
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Start.this);
                topFrame.dispose(); // tutup start frame
                openFlappyBird();  // buka game
            }
        });

        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Gambar background
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }

    private void openFlappyBird() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);
    }
}
