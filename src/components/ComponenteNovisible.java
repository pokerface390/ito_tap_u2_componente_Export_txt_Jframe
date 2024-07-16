import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import javax.imageio.ImageIO;

public class ComponenteNovisible extends JPanel {
    private JTextField urlField;
    private JButton downloadButton;
    private JSlider widthSlider;
    private JSlider heightSlider;
    private JPanel imagePanel;
    private JLabel statusLabel;

    private String savePath = "C:\\Users\\chiri\\OneDrive\\Escritorio\\Musica"; 
    private int imageCounter = 1; 

    public ComponenteNovisible() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel urlLabel = new JLabel("URL de la imagen:");
        urlField = new JTextField(40);
        downloadButton = new JButton("Descargar");
        inputPanel.add(urlLabel);
        inputPanel.add(urlField);
        inputPanel.add(downloadButton);

        JPanel sizePanel = new JPanel(new BorderLayout());
        JPanel slidersPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        slidersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel sizeLabel = new JLabel("Ajustar tamaño:");
        widthSlider = new JSlider(JSlider.HORIZONTAL, 50, 800, 400);
        widthSlider.setMajorTickSpacing(100);
        widthSlider.setPaintTicks(true);
        widthSlider.setPaintLabels(true);
        heightSlider = new JSlider(JSlider.HORIZONTAL, 50, 800, 300);
        heightSlider.setMajorTickSpacing(100);
        heightSlider.setPaintTicks(true);
        heightSlider.setPaintLabels(true);
        slidersPanel.add(sizeLabel);
        slidersPanel.add(new JLabel());
        slidersPanel.add(new JLabel("Ancho:"));
        slidersPanel.add(widthSlider);
        slidersPanel.add(new JLabel("Alto:"));
        slidersPanel.add(heightSlider);
        sizePanel.add(slidersPanel, BorderLayout.CENTER);

        imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setPreferredSize(new Dimension(780, 400));

        statusLabel = new JLabel("Esperando descarga...");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);

        add(inputPanel, BorderLayout.NORTH);
        add(sizePanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.SOUTH);

        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String imageUrl = urlField.getText();
                int width = widthSlider.getValue();
                int height = heightSlider.getValue();
                downloadImage(imageUrl, width, height);
            }
        });
    }

    public void downloadImage(String imageUrl, int width, int height) {
        try {
            URL url = new URL(imageUrl);
            String fileName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1).split("\\?")[0];

            Path targetPath = Paths.get(savePath, fileName);
            while (Files.exists(targetPath)) {
                String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
                String extension = fileName.substring(fileName.lastIndexOf('.'));
                fileName = nameWithoutExtension + "_" + imageCounter + extension;
                targetPath = Paths.get(savePath, fileName);
                imageCounter++;
            }

            Image image = ImageIO.read(url);
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.getGraphics().drawImage(scaledImage, 0, 0, null);

            File outputImage = new File(targetPath.toString());
            ImageIO.write(bufferedImage, "jpg", outputImage);

            System.out.println("Imagen descargada en: " + targetPath);
            statusLabel.setText("Imagen descargada en: " + targetPath);

            ImageIcon icon = new ImageIcon(bufferedImage.getScaledInstance(150, 100, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(icon);
            imagePanel.add(imageLabel);
            imagePanel.revalidate(); 
            imagePanel.repaint();
        } catch (IOException ex) {
            System.err.println("Error al descargar la imagen: " + ex.getMessage());
            ex.printStackTrace();
            statusLabel.setText("Error al descargar la imagen: " + ex.getMessage());
        }
    }

    public void downloadImageFromExternal(String imageUrl, int width, int height) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                downloadImage(imageUrl, width, height);
            }
        });
    }
}
