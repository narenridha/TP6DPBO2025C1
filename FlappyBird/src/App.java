import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame startFrame = new JFrame("Welcome");
            Start startPanel = new Start();

            startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            startFrame.setSize(360, 640);
            startFrame.setResizable(false);
            startFrame.setLocationRelativeTo(null);
            startFrame.add(startPanel);
            startFrame.setVisible(true);
        });
    }
}
