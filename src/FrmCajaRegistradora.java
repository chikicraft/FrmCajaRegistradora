import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class FrmCajaRegistradora extends JFrame {
    private JTextField campoDevuelta;
    private JTable tabla, tablaExistencias;
    private DefaultTableModel modeloTabla, modeloExistencias;
    private int[] denominaciones = {100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50};
    private int[] existencias = new int[denominaciones.length];

    public FrmCajaRegistradora() {
        setTitle("Calculadora de Devuelta");
        setSize(650, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblDevuelta = new JLabel("Monto a Devolver:");
        lblDevuelta.setBounds(20, 20, 120, 30);
        add(lblDevuelta);

        campoDevuelta = new JTextField();
        campoDevuelta.setBounds(150, 20, 150, 30);
        add(campoDevuelta);

        JButton btnCalcular = new JButton("Calcular Devuelta");
        btnCalcular.setBounds(330, 19, 150, 30);
        add(btnCalcular);

        modeloTabla = new DefaultTableModel(new String[]{"Denominación", "Tipo", "Cantidad"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBounds(20, 70, 600, 300);
        add(scrollTabla);

        modeloExistencias = new DefaultTableModel(new String[]{"Denominación", "Existencia"}, 0);
        tablaExistencias = new JTable(modeloExistencias);
        JScrollPane scrollExistencias = new JScrollPane(tablaExistencias);
        scrollExistencias.setBounds(20, 390, 600, 200);
        add(scrollExistencias);

        inicializarExistencias();

        btnCalcular.addActionListener(e -> calcularDevuelta());

        tablaExistencias.getModel().addTableModelListener(e -> actualizarExistencias());

        setVisible(true);
    }

    private void inicializarExistencias() {
        for (int i = 0; i < denominaciones.length; i++) {
            modeloExistencias.addRow(new Object[]{"$" + denominaciones[i], ""});
        }
    }

    private void actualizarExistencias() {
        for (int i = 0; i < denominaciones.length; i++) {
            try {
                existencias[i] = Integer.parseInt(modeloExistencias.getValueAt(i, 1).toString());
            } catch (Exception e) {
                existencias[i] = 0;
            }
        }
    }

    private void calcularDevuelta() {
        modeloTabla.setRowCount(0);
        try {
            int devuelta = Integer.parseInt(campoDevuelta.getText());

            if (devuelta < 0) {
                JOptionPane.showMessageDialog(this, "El monto a devolver no puede ser negativo.");
                return;
            }

            for (int i = 0; i < denominaciones.length; i++) {
                int cantidadNecesaria = 0;
                while (devuelta >= denominaciones[i] && existencias[i] > 0) {
                    devuelta -= denominaciones[i];
                    existencias[i]--;
                    cantidadNecesaria++;
                }

                if (cantidadNecesaria > 0) {
                    String tipo = (denominaciones[i] >= 1000) ? "Billete" : "Moneda";
                    modeloTabla.addRow(new Object[]{"$" + denominaciones[i], tipo, cantidadNecesaria});
                }
            }

            if (devuelta > 0) {
                JOptionPane.showMessageDialog(this, "No hay suficiente cambio para completar la devolución.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor válido para el monto a devolver.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FrmCajaRegistradora::new);
    }
}

//Echo por chiki