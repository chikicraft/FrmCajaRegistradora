## Explicación del Código

1. Importación de Bibliotecas

El código comienza con la importación de bibliotecas esenciales de Java Swing para la creación de la interfaz gráfica y la manipulación de eventos:

``import javax.swing.*;`` 
``import javax.swing.*;``
``import javax.swing.table.DefaultTableModel;``
``import java.awt.event.*;``

javax.swing.*: Permite el uso de componentes de interfaz como JFrame, JTable, JButton, etc.
javax.swing.table.DefaultTableModel: Se usa para manejar los datos en las tablas.
java.awt.event.*: Proporciona la funcionalidad para manejar eventos como clics de botones.


2. Definición de la Clase y Variables

Se define la clase FrmCajaRegistradora que extiende JFrame, lo que permite crear la ventana principal de la aplicación.

``public class FrmCajaRegistradora extends JFrame {``
``    private JTextField campoDevuelta;``
``    private JTable tabla, tablaExistencias;``
``    private DefaultTableModel modeloTabla, modeloExistencias;``
``    private int[] denominaciones = {100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50};``
``    private int[] existencias = new int[denominaciones.length];``

campoDevuelta: Campo de texto donde se ingresa la cantidad a devolver.
tabla: Tabla que muestra la descomposición del cambio.
tablaExistencias: Tabla donde se muestran y pueden modificar las existencias de billetes y monedas.
modeloTabla: Modelo de datos para la tabla de descomposición del cambio.
modeloExistencias: Modelo de datos para la tabla de existencias.
denominaciones: Array con los valores de los billetes y monedas disponibles.
existencias: Array que almacena la cantidad disponible de cada denominación.


3. Constructor: Creación de la Interfaz

El constructor FrmCajaRegistradora() configura la interfaz de usuario.

``setTitle("Calculadora de Devuelta");``
``setSize(650, 750);``
``setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);``
``setLayout(null);``

setTitle(): Define el título de la ventana.
setSize(): Especifica el tamaño de la ventana.
setDefaultCloseOperation(): Permite cerrar la aplicación al presionar la "X".
setLayout(null): Deshabilita el diseño automático para permitir posicionar componentes manualmente.

- Componentes:

Etiqueta y Campo de Texto para la Devuelta
``JLabel lblDevuelta = new JLabel("Monto a Devolver:");``
``lblDevuelta.setBounds(20, 20, 120, 30);``
``add(lblDevuelta);``
Crea una etiqueta y un campo de texto para ingresar el monto a devolver.


Botón para Calcular Devuelta
``JButton btnCalcular = new JButton("Calcular Devuelta");``
``btnCalcular.setBounds(20, 60, 150, 30);``
``add(btnCalcular);``
Este botón ejecuta el cálculo del cambio.


Tablas para Mostrar Resultados y Existencias
``modeloTabla = new DefaultTableModel(new String[]{"Denominación", "Tipo", "Cantidad"}, 0);``
``tabla = new JTable(modeloTabla);``
``JScrollPane scrollTabla = new JScrollPane(tabla);``
``scrollTabla.setBounds(20, 110, 600, 300);``
``add(scrollTabla);``
Se crean las tablas y sus modelos.


La tabla de existencias permite modificar las cantidades disponibles:
``modeloExistencias = new DefaultTableModel(new String[]{"Denominación", "Existencia"}, 0);``
``tablaExistencias = new JTable(modeloExistencias);``
``JScrollPane scrollExistencias = new JScrollPane(tablaExistencias);``
``scrollExistencias.setBounds(20, 420, 600, 200);``
``add(scrollExistencias);``


4. Inicialización de Existencias

``private void inicializarExistencias() {``
``    for (int i = 0; i < denominaciones.length; i++) {``
``        modeloExistencias.addRow(new Object[]{"$" + denominaciones[i], ""});``
``    }``
``}``

Llena la tabla de existencias con denominaciones.


5. Actualización de Existencias

``private void actualizarExistencias() {``
``    for (int i = 0; i < denominaciones.length; i++) {``
``        try {``
``            existencias[i] = Integer.parseInt(modeloExistencias.getValueAt(i, 1).toString());``
``        } catch (Exception e) {``
``            existencias[i] = 0;``
``        }``
``    }``
``}``

Este método permite modificar la cantidad de cada denominación en la tabla.


6. Cálculo de la Devuelta

``private void calcularDevuelta() {``
``    modeloTabla.setRowCount(0);``
``    try {``
``        int devuelta = Integer.parseInt(campoDevuelta.getText());``

Se obtiene el monto a devolver desde el campo de texto.

``for (int i = 0; i < denominaciones.length; i++) {``
``    int cantidadNecesaria = 0;``
``    while (devuelta >= denominaciones[i] && existencias[i] > 0) {``
``        devuelta -= denominaciones[i];``
``        existencias[i]--;``
``        cantidadNecesaria++;``
``    }``

Se descompone el monto en billetes y monedas.

``if (devuelta > 0) {``
``    JOptionPane.showMessageDialog(this, "No hay suficiente cambio para completar la devolución.");``
``}``

Se muestra un mensaje si el cambio no puede darse completamente.


7. Método main

``public static void main(String[] args) {``
``    SwingUtilities.invokeLater(FrmCajaRegistradora::new);``
``}``

Ejecuta la aplicación en un hilo de interfaz de usuario seguro.


## De donde saque informacion y aprendi

>Documentación de Swing:  [here](https://docs.oracle.com/javase/tutorial/uiswing/)

>Uso de JTable: [here](https://docs.oracle.com/javase/tutorial/uiswing/components/table.html)

>Eventos en Java:  [here](https://docs.oracle.com/javase/tutorial/uiswing/events/index.html)
