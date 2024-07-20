/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NoVisual;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComponenteNV extends JComponent {

    private static List<String> elements = new ArrayList<>();
    private PropertyChangeSupport support;

    public ComponenteNV() {
        support = new PropertyChangeSupport(this);
    }

    public void loadFromFile(String filePath) throws IOException {
        elements.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                elements.add(line);
            }
        }
        Ordenar.shellSort(elements.toArray(new String[0]));
        support.firePropertyChange("elements", null, elements);
    }

    public static List<String> loadFromJList(JList<String> jList) {
        List<String> elements = getElements(jList);
        ordenar(elements);
        return elements;
    }

    public static List<String> getElements(JList<String> jList) {
        List<String> elements = new ArrayList<>();
        ListModel<String> listModel = jList.getModel();
        for (int i = 0; i < listModel.getSize(); i++) {
            elements.add(listModel.getElementAt(i));
        }
        return elements;
    }

    public static void ordenar(List<String> elements) {
        Collections.sort(elements);
    }
    public static List<String> loadFromCSV(String filePath) {
        List<String> elements = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                elements.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ordenar(elements);
        return elements;
    }

    public static List<String> loadFromTXT(String filePath) {
        List<String> elements = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                elements.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ordenar(elements);
        return elements;
    }
    
    public void displaySortedElements() {
        for (String element : elements) {
            System.out.println(element);
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}