
import javax.swing.*;
import java.awt.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Image Downloader App");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                ComponenteNovisible imageDownloader = new ComponenteNovisible();
                frame.add(imageDownloader, BorderLayout.CENTER);

               
                JPanel buttonPanel = new JPanel();
                frame.add(buttonPanel, BorderLayout.SOUTH);

                frame.pack();
                frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
                frame.setVisible(true);
            }
        });
    }
}
