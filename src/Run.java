/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Run {
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
