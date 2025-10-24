/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_semana2_angie_jeremy_trigo;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private JPanel panelMenuP;
    private JPanel panelBusq;
    
    private JTextField txtCodBusq;
    private JLabel etiqEstado; 

    private final Color COLOR_FONDO = new Color(240, 248, 255); 
    private final Color COLOR_AZUL_OSC = new Color(0, 102, 204); 
    private final Color COLOR_AZUL_CLAR = new Color(70, 130, 180); 
    
    private final int ANCHO = 650;
    private final int ALTO = 450;
    
    public MenuPrincipal() {
        setTitle("Sistema Gestion Empleados");
        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new BorderLayout(10, 10));

        JPanel panelCabecera = new JPanel(new GridLayout(2, 1));
        panelCabecera.setBackground(COLOR_FONDO);
        
        JLabel titulo = new JLabel("GESTION EMPRESARIAL", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(COLOR_AZUL_OSC);
        
        etiqEstado = new JLabel("J A T", SwingConstants.CENTER); //osea Jeremy Angie Trigo :D
        etiqEstado.setFont(new Font("Arial", Font.PLAIN, 12));
        etiqEstado.setForeground(Color.GRAY);

        panelCabecera.add(titulo);
        panelCabecera.add(etiqEstado);
        
        add(panelCabecera, BorderLayout.NORTH);
        
        confMenuP();
        add(panelMenuP, BorderLayout.CENTER);
        
        confPanelBusq();
        add(panelBusq, BorderLayout.EAST);
        
        setVisible(true);
    }
    
    private void confMenuP() {
        panelMenuP = new JPanel();
        panelMenuP.setLayout(new GridLayout(3, 2, 20, 20)); 
        panelMenuP.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelMenuP.setBackground(COLOR_FONDO);

        JButton btnRegEmp = new JButton("Registrar Empleado");
        JButton btnRegHoras = new JButton("Registrar Horas Trab.");
        JButton btnRegVentas = new JButton("Registrar Ventas");
        JButton btnActFecha = new JButton("Act. Fecha Fin");
        JButton btnCalcPago = new JButton("Calcular Pago Mensual");
        JButton btnGenRepo = new JButton("Generar Reporte General");
        
        estiloBoton(btnRegEmp, COLOR_AZUL_CLAR);
        estiloBoton(btnRegHoras, COLOR_AZUL_CLAR);
        estiloBoton(btnRegVentas, COLOR_AZUL_OSC);
        estiloBoton(btnActFecha, COLOR_AZUL_OSC); //Jeremy: siento que como tipo colores en degradado queda formal
        estiloBoton(btnCalcPago, Color.DARK_GRAY); 
        estiloBoton(btnGenRepo, Color.DARK_GRAY);

        btnRegEmp.addActionListener(e -> mostrarMsg("Registrar Empleado"));
        btnRegHoras.addActionListener(e -> mostrarMsg("Registrar Horas"));
        btnRegVentas.addActionListener(e -> mostrarMsg("Registrar Ventas"));
        btnActFecha.addActionListener(e -> mostrarMsg("Actualizar Fecha"));
        btnCalcPago.addActionListener(e -> mostrarMsg("Calcular Pago"));
        btnGenRepo.addActionListener(e -> mostrarMsg("Generar Reporte"));

        panelMenuP.add(btnRegEmp);
        panelMenuP.add(btnRegHoras);
        panelMenuP.add(btnRegVentas);
        panelMenuP.add(btnActFecha);
        panelMenuP.add(btnCalcPago);
        panelMenuP.add(btnGenRepo);
    }
    
    private void confPanelBusq() {
        panelBusq = new JPanel();
        panelBusq.setLayout(new BoxLayout(panelBusq, BoxLayout.Y_AXIS));
        panelBusq.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_AZUL_OSC), "Buscar por Cod", 
            0, 0, new Font("Arial", Font.BOLD, 12), COLOR_AZUL_OSC));
        panelBusq.setBackground(COLOR_FONDO);
        
        txtCodBusq = new JTextField(10);
        txtCodBusq.setMaximumSize(new Dimension(150, 25)); 
        
        JButton btnBusq = new JButton("Buscar Empleado");
        estiloBoton(btnBusq, Color.ORANGE.darker());
        
        btnBusq.addActionListener(e -> mostrarMsg("Buscar Empleado"));
        
        panelBusq.add(Box.createVerticalStrut(10));
        panelBusq.add(new JLabel("Codigo:"));
        panelBusq.add(txtCodBusq);
        panelBusq.add(Box.createVerticalStrut(5));
        panelBusq.add(btnBusq);
        panelBusq.add(Box.createVerticalGlue());
    }

    private void estiloBoton(JButton boton, Color colorF) {
        boton.setBackground(colorF);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void mostrarMsg(String accion) {
        JOptionPane.showMessageDialog(this, "Accion: " + accion + " (Logica Pendiente)", 
                                    "Estructura OK", JOptionPane.INFORMATION_MESSAGE);
        etiqEstado.setText("Se activo: " + accion);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
}