
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.Random;

public class CaptchaGenerator {

    static String generateCaptcha() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            captcha.append(characters.charAt(index));
        }

        return captcha.toString();
    }

    static void displayCaptcha(String text) {
        int width = 280;
        int height = 90;

        BufferedImage image = new BufferedImage(
            width, height, BufferedImage.TYPE_INT_RGB
        );

        Graphics2D g = image.createGraphics();

        // White background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Draw CAPTCHA text
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(text, 35, 58);

        Random random = new Random();

        // Add random lines
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);

            g.drawLine(x1, y1, x2, y2);
        }

        // Add random noise dots
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            g.fillOval(x, y, 2, 2);
        }

        g.dispose();

        // Display CAPTCHA image
        JFrame frame = new JFrame("CAPTCHA Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320, 150);

        JLabel label = new JLabel(new ImageIcon(image));
        label.setHorizontalAlignment(JLabel.CENTER);

        frame.add(label);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        String captcha = generateCaptcha();

        System.out.println("Generated CAPTCHA: " + captcha);

        SwingUtilities.invokeLater(() -> displayCaptcha(captcha));
    }
}