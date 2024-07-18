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
