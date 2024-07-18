Para integrar tu componente ComponenteVisual en la paleta de componentes de NetBeans y resumir su funcionalidad y configuración:

![image](https://github.com/user-attachments/assets/db1f846a-117d-4197-b9de-1a314af54f46)

Paquete y Importaciones
Paquete: El componente se encuentra en el paquete components.
Importaciones: Importa javax.swing.* para la interfaz gráfica, java.awt.event.* para manejar eventos, y java.io.* para operaciones de entrada/salida.
Clase ComponenteVisual
Extiende: JPanel.
Atributos:
JTable tabla: Para mostrar los datos en forma tabular.
DefaultTableModel modeloTabla: Maneja los datos del modelo de la tabla.
String rutaArchivoActual: Almacena la ruta del archivo actualmente cargado.
Constructor
Configuración: Configura el panel principal con un BorderLayout.
Panel de Control: Crea un JPanel que contiene botones para cargar archivo, agregar fila, guardar datos, cambiar títulos y eliminar fila.
ActionListeners: Asocia cada botón con su método correspondiente para manejar eventos.
Configurar Componentes: Inicializa DefaultTableModel y JTable, agregándolos dentro de un JScrollPane al centro del panel principal.
Integración en la Paleta de Componentes de NetBeans
Para integrar ComponenteVisual en NetBeans:

Compila tu Proyecto: Asegúrate de compilar tu proyecto Java para generar un archivo .jar.

Agregar a la Paleta:

Abre NetBeans y tu proyecto.
Haz clic derecho en la paleta de componentes y selecciona "Choose Items...".
Selecciona "Add JAR/Folder" y elige el archivo .jar donde está ComponenteVisual.
Utilización en NetBeans:

Busca ComponenteVisual en la paleta de componentes de NetBeans.
Arrástralo y suéltalo en el formulario o panel de diseño donde deseas utilizarlo.
Configura propiedades y eventos según sea necesario utilizando el inspector de propiedades de NetBeans.
Resumen de Funcionalidad
Cargar Archivo: Permite al usuario seleccionar un archivo .txt para cargar datos en la tabla.
Agregar y Eliminar Filas: Funcionalidad para agregar nuevas filas y eliminar filas seleccionadas de la tabla.
Guardar Datos: Guarda los datos de la tabla en un archivo .txt especificado por el usuario o seleccionado mediante un JFileChooser.
Cambiar Títulos: Permite al usuario cambiar los títulos de las columnas de la tabla.
Actualización de Archivos: Actualiza automáticamente el archivo de texto cuando se modifican los datos o títulos de la tabla.
Este resumen proporciona una visión general y los pasos necesarios para integrar tu componente visual en NetBeans, asegurando que esté disponible para su uso y configuración dentro del entorno de desarrollo.
![image](https://github.com/user-attachments/assets/a1a45873-de0d-4f92-b8ae-acca9a33daa8)
![image](https://github.com/user-attachments/assets/1859056f-baf1-4cd0-a69e-94a0d07d8aea)
![image](https://github.com/user-attachments/assets/63accbb5-3c97-42a7-ac2e-1b099a15a950)
![image](https://github.com/user-attachments/assets/ab6c68b3-a74b-472f-836a-2269ac554c8a)
![image](https://github.com/user-attachments/assets/df3ba29a-7144-477d-998b-d9d95d712553)
![image](https://github.com/user-attachments/assets/e8e0eb61-8bd8-46b1-8dd3-1048df3599d9)
![image](https://github.com/user-attachments/assets/682e9fb3-11b8-4d1a-a2de-0169b6ca3b12)
![image](https://github.com/user-attachments/assets/6db28842-1392-4c9b-b5b5-5e26c002455e)
![image](https://github.com/user-attachments/assets/d0ced7d7-6007-4ffc-b0b4-d4a5c9974bec)
![image](https://github.com/user-attachments/assets/660e016d-b2d6-4797-ba38-deb8ee3418a6)
![image](https://github.com/user-attachments/assets/9b65c7cb-7714-46ed-9d28-8ac55fd8ff69)

 Constructor ComponenteNovisible()
Inicialización de Componentes Gráficos: Configura la interfaz gráfica del componente.

Crea y organiza los paneles (inputPanel, sizePanel, imagePanel) y componentes (urlField, downloadButton, widthSlider, heightSlider, statusLabel).
Establece disposiciones (BorderLayout, FlowLayout, GridLayout) para organizar los componentes en la ventana.
Configuración de Listeners: Asigna un ActionListener al botón downloadButton.

Cuando se presiona downloadButton, llama al método downloadImage(imageUrl, width, height) para descargar y mostrar la imagen.
Método downloadImage(String imageUrl, int width, int height)
Descarga de la Imagen:

Crea un objeto URL a partir de la URL proporcionada (imageUrl).
Extrae el nombre del archivo de la URL para usarlo como nombre de archivo local (fileName).
Verifica si el archivo ya existe en la ruta de guardado (savePath) y, si es así, añade un contador para evitar sobrescribir archivos.
Escalado y Conversión de la Imagen:

Lee la imagen desde la URL usando ImageIO.
Escala la imagen a las dimensiones especificadas (width y height) usando getScaledInstance y BufferedImage.
Convierte la imagen a formato JPEG y la guarda en el archivo de destino.
Actualización de la Interfaz Gráfica:

Muestra un mensaje en statusLabel indicando la ubicación del archivo descargado.
Crea un ImageIcon a partir de la imagen escalada para mostrarla en un JLabel.
Agrega el JLabel con la imagen al imagePanel y actualiza la vista (revalidate() y repaint()).
Manejo de Errores:

Captura y muestra errores de IOException en la consola y en statusLabel.
Método downloadImageFromExternal(String imageUrl, int width, int height)
Invocación Asíncrona:
Este método llama a downloadImage dentro de un hilo de eventos de Swing (SwingUtilities.invokeLater).
Esto asegura que la descarga y actualización de la interfaz gráfica se realicen en el hilo de eventos de Swing, evitando problemas de concurrencia.
Consideraciones Generales:
Manejo de Imágenes y Descargas: La clase maneja la descarga de imágenes desde una URL proporcionada, las escala según las dimensiones especificadas y las muestra en la interfaz gráfica.
Interfaz Gráfica Dinámica: Permite la descarga y visualización de múltiples imágenes en imagePanel.
Feedback al Usuario: Utiliza statusLabel para informar sobre el estado de las operaciones de descarga.
![image](https://github.com/user-attachments/assets/20bb56a7-0b88-490b-8c43-e6a6bc1fc192)

  ![image](https://github.com/user-attachments/assets/21fde6fd-8665-4662-8171-a66b11a96c70)

 ![image](https://github.com/user-attachments/assets/0d374777-9042-4c66-b77b-486748d45784)

   
 
  
