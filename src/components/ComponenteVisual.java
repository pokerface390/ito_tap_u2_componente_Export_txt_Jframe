package components;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ComponenteVisual extends JPanel {
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private String rutaArchivoActual;

    public ComponenteVisual() {
        setLayout(new BorderLayout());

        JPanel panelControl = new JPanel();
        JButton botonCargar = new JButton("Cargar TXT");
        JButton botonAgregarFila = new JButton("Agregar Fila");
        JButton botonGuardar = new JButton("Guardar Valores de Fila");
        JButton botonCambiarTitulos = new JButton("Cambiar Títulos");
        JButton botonEliminarFila = new JButton("Eliminar Fila Seleccionada");

        botonCargar.addActionListener(this::cargarArchivoAction);
        botonAgregarFila.addActionListener(this::agregarFilaAction);
        botonGuardar.addActionListener(this::guardarAction);
        botonCambiarTitulos.addActionListener(this::cambiarTitulosAction);
        botonEliminarFila.addActionListener(this::eliminarFilaAction);

        panelControl.add(botonCargar);
        panelControl.add(botonAgregarFila);
        panelControl.add(botonGuardar);
        panelControl.add(botonCambiarTitulos);
        panelControl.add(botonEliminarFila);

        add(panelControl, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel();
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void cargarArchivoAction(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(ComponenteVisual.this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            rutaArchivoActual = fileChooser.getSelectedFile().getAbsolutePath();
            cargarArchivo(rutaArchivoActual);
        }
    }

    private void cargarArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            List<String[]> datos = new ArrayList<>();
            while ((linea = br.readLine()) != null) {
                datos.add(linea.split(","));
            }

            if (datos.isEmpty() || datos.get(0).length == 0) {
                int opcion = JOptionPane.showConfirmDialog(this,
                        "El archivo TXT está vacío. ¿Desea agregar dos filas nuevas?",
                        "Archivo Vacío",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (opcion == JOptionPane.YES_OPTION) {
                    String[] nuevosTitulos = solicitarTitulos();
                    modeloTabla.setDataVector(new Object[1][nuevosTitulos.length], nuevosTitulos);
                    for (int i = 0; i < 1; i++) {
                        for (int j = 0; j < nuevosTitulos.length; j++) {
                            String valor = JOptionPane.showInputDialog(this,
                                    "Ingrese el valor para la columna " + nuevosTitulos[j] + " en la fila " + (i + 1));
                            modeloTabla.setValueAt(valor, i, j);
                        }
                    }
                    if (rutaArchivoActual != null) {
                        guardarEnArchivo(rutaArchivoActual);
                    }
                }
            } else {
                String[] nombresColumnas = datos.get(0);
                datos.remove(0);
                String[][] datosTabla = datos.toArray(new String[0][]);
                modeloTabla.setDataVector(datosTabla, nombresColumnas);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] solicitarTitulos() {
        int numColumnas = 0;
        boolean valido = false;
        while (!valido) {
            String numColumnasStr = JOptionPane.showInputDialog(this, "Ingrese el número de columnas:");
            try {
                numColumnas = Integer.parseInt(numColumnasStr);
                valido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.");
            }
        }

        String[] titulos = new String[numColumnas];
        for (int i = 0; i < numColumnas; i++) {
            titulos[i] = JOptionPane.showInputDialog(this, "Ingrese el título para la columna " + (i + 1));
        }
        return titulos;
    }

    private void agregarFilaAction(ActionEvent e) {
        modeloTabla.addRow(new Object[modeloTabla.getColumnCount()]);
    }

    private void guardarAction(ActionEvent e) {
        if (rutaArchivoActual != null) {
            guardarEnArchivo(rutaArchivoActual);
        } else {
            JFileChooser fileChooser = new JFileChooser();
            int resultado = fileChooser.showSaveDialog(ComponenteVisual.this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                rutaArchivoActual = fileChooser.getSelectedFile().getAbsolutePath();
                guardarEnArchivo(rutaArchivoActual);
            }
        }
    }

    private void guardarEnArchivo(String rutaArchivo) {
        try (FileWriter fw = new FileWriter(rutaArchivo)) {
            for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
                fw.write(modeloTabla.getColumnName(i) + (i < modeloTabla.getColumnCount() - 1 ? "," : "\n"));
            }
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                    fw.write(modeloTabla.getValueAt(i, j) == null ? "" : modeloTabla.getValueAt(i, j).toString());
                    fw.write(j < modeloTabla.getColumnCount() - 1 ? "," : "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cambiarTitulosAction(ActionEvent e) {
    int numColumnas = modeloTabla.getColumnCount();
    String[] nuevosTitulos = new String[numColumnas];

    for (int i = 0; i < numColumnas; i++) {
        nuevosTitulos[i] = JOptionPane.showInputDialog(ComponenteVisual.this,
                "Ingrese el nuevo título para la columna " + (i + 1),
                modeloTabla.getColumnName(i));
    }

    modeloTabla.setColumnIdentifiers(nuevosTitulos);

    if (rutaArchivoActual != null) {
        actualizarArchivoDespuesCambioTitulos(rutaArchivoActual, nuevosTitulos);
    } else {
        JOptionPane.showMessageDialog(ComponenteVisual.this,
                "Primero cargue un archivo TXT para agregar títulos.");
    }
}

private void actualizarArchivoDespuesCambioTitulos(String rutaArchivo, String[] nuevosTitulos) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
        for (int i = 0; i < nuevosTitulos.length; i++) {
            bw.write(nuevosTitulos[i] + (i < nuevosTitulos.length - 1 ? "," : "\n"));
        }
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                bw.write(modeloTabla.getValueAt(i, j) == null ? "" : modeloTabla.getValueAt(i, j).toString());
                bw.write(j < modeloTabla.getColumnCount() - 1 ? "," : "\n");
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    private void actualizarArchivoDespuesCambioTitulos(String rutaArchivo, String[] nuevosTitulos, Object[][] nuevosDatos) {
        try (FileWriter fw = new FileWriter(rutaArchivo)) {
            for (int i = 0; i < nuevosTitulos.length; i++) {
                fw.write(nuevosTitulos[i] + (i < nuevosTitulos.length - 1 ? "," : "\n"));
            }
            for (int i = 0; i < nuevosDatos.length; i++) {
                for (int j = 0; j < nuevosDatos[i].length; j++) {
                    fw.write(nuevosDatos[i][j] == null ? "" : nuevosDatos[i][j].toString());
                    fw.write(j < nuevosDatos[i].length - 1 ? "," : "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarFilaAction(ActionEvent e) {
        int[] filasSeleccionadas = tabla.getSelectedRows();
        if (filasSeleccionadas.length > 0) {
            for (int i = filasSeleccionadas.length - 1; i >= 0; i--) {
                modeloTabla.removeRow(filasSeleccionadas[i]);
            }

            if (rutaArchivoActual != null) {
                actualizarArchivoDespuesEliminarFilas(rutaArchivoActual, filasSeleccionadas);
            }
        } else {
            JOptionPane.showMessageDialog(ComponenteVisual.this,
                    "Seleccione una o más filas para eliminar.");
        }
    }

    private void actualizarArchivoDespuesEliminarFilas(String rutaArchivo, int[] filasAEliminar) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (int i = 0; i < lineas.size(); i++) {
                if (!esLineaAEliminar(i, filasAEliminar)) {
                    bw.write(lineas.get(i));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean esLineaAEliminar(int lineaActual, int[] filasAEliminar) {
        for (int fila : filasAEliminar) {
            if (lineaActual == fila + 1) {
                return true;
            }
        }
        return false;
    }
}