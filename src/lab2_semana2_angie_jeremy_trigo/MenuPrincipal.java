/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_semana2_angie_jeremy_trigo;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.InputMismatchException; 
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList; 


public class MenuPrincipal extends JFrame {

    private final Empresa empresa = new Empresa(); 
    
    private JPanel panelMenuP;
    private JPanel panelBusq;
    
    private JTextField txtCodBusq;
    private JLabel etiqEstado; 

    private final Color COLOR_FONDO = new Color(240, 248, 255); 
    private final Color COLOR_AZUL_OSC = new Color(0, 102, 204); 
    private final Color COLOR_AZUL_CLAR = new Color(70, 130, 180); 
    
    private final int ANCHO = 750;
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
        
        JLabel titulo = new JLabel("GESTION DE EMPLEADOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(COLOR_AZUL_OSC);
        
        etiqEstado = new JLabel("Empresa J A T", SwingConstants.CENTER); //osea Jeremy Angie Trigo :D
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
        JButton btnActFecha = new JButton("Act. Fecha Fin Contrato");
        JButton btnCalcPago = new JButton("Calcular Pago Mensual");
        JButton btnGenRepo = new JButton("Generar Reporte General");
        
        estiloBoton(btnRegEmp, COLOR_AZUL_CLAR);
        estiloBoton(btnRegHoras, COLOR_AZUL_CLAR);
        estiloBoton(btnRegVentas, COLOR_AZUL_OSC);
        estiloBoton(btnActFecha, COLOR_AZUL_OSC); //Jeremy: siento que como tipo colores en degradado queda formal
        estiloBoton(btnCalcPago, Color.RED.darker()); 
        estiloBoton(btnGenRepo, Color.DARK_GRAY);

        btnRegEmp.addActionListener(e -> mostrarFormularioRegistro());
        btnRegHoras.addActionListener(e -> registrarHorasGUI());
        btnRegVentas.addActionListener(e -> registrarVentasGUI());
        btnActFecha.addActionListener(e -> actualizarFechaGUI());
        btnCalcPago.addActionListener(e -> calcularPagoGUI());
        btnGenRepo.addActionListener(e -> mostrarReporteGUI());

        panelMenuP.add(btnRegEmp);
        panelMenuP.add(btnRegHoras);
        panelMenuP.add(btnRegVentas);
        panelMenuP.add(btnActFecha);
        panelMenuP.add(btnCalcPago);
        panelMenuP.add(btnGenRepo);
    }
    
    private void confPanelBusq() {
        panelBusq = new JPanel(); //Jeremy: me gusta esta parte asi como un panel, tipo asi de un solo sin dar tanta complicacion, codigo click y ya :p
        panelBusq.setLayout(new BoxLayout(panelBusq, BoxLayout.Y_AXIS)); 
        panelBusq.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_AZUL_OSC), "Buscar por Codigo", 
            0, 0, new Font("Arial", Font.BOLD, 12), COLOR_AZUL_OSC));
        panelBusq.setBackground(COLOR_FONDO);
        
        txtCodBusq = new JTextField(10);
        txtCodBusq.setMaximumSize(new Dimension(150, 25)); 
        
        JButton btnBusq = new JButton("Buscar Empleado");
        estiloBoton(btnBusq, Color.ORANGE.darker());
        
        btnBusq.addActionListener(e -> buscarEmpleadoGUI());
        
        panelBusq.add(Box.createVerticalStrut(10));
        panelBusq.add(new JLabel("Codigo:"));
        panelBusq.add(txtCodBusq);
        panelBusq.add(Box.createVerticalStrut(5));
        panelBusq.add(btnBusq);
        panelBusq.add(Box.createVerticalGlue());
    }
    
    // --- METODOS DE LOGICA DE LA GUI (CONECTADOS A EMPRESA) ---

    private void buscarEmpleadoGUI() {
        try {
            int codigo = Integer.parseInt(txtCodBusq.getText().trim());
            Empleado empleado = empresa.buscarEmpleadoPorCodigo(codigo);
            
            if (empleado != null) {
                JOptionPane.showMessageDialog(this, "Empleado Encontrado:\n\n" + empleado.toString(), 
                                            "Busqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
                etiqEstado.setText("Encontrado: " + empleado.getNombre());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontro el empleado con codigo: " + codigo, 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                etiqEstado.setText("Busqueda fallida.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Codigo invalido. Ingrese un numero.", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarFormularioRegistro() {
        String[] opciones = {"Estandar", "Temporal", "Ventas"};
        int seleccion = JOptionPane.showOptionDialog(this, 
            "Seleccione el tipo de empleado:", "Registro", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        
        if (seleccion == JOptionPane.CLOSED_OPTION) return;
        
        JPanel panelForm = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField txtCodigo = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtSalario = new JTextField();
        JTextField txtTasaCom = new JTextField("0.05"); 
        JTextField txtFechaFin = new JTextField("DD/MM/AAAA"); 
        
        panelForm.add(new JLabel("Codigo:")); panelForm.add(txtCodigo);
        panelForm.add(new JLabel("Nombre:")); panelForm.add(txtNombre);
        panelForm.add(new JLabel("Salario Base:")); panelForm.add(txtSalario);
        
        if (seleccion == 1) { 
            panelForm.add(new JLabel("Fecha Fin Contrato:")); panelForm.add(txtFechaFin);
        } else if (seleccion == 2) { 
            panelForm.add(new JLabel("Tasa Comision (0.0 a 1.0):")); panelForm.add(txtTasaCom);
        }
        
        int resultado = JOptionPane.showConfirmDialog(this, panelForm, 
            "Datos del Empleado " + opciones[seleccion], JOptionPane.OK_CANCEL_OPTION);
            
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText().trim());
                String nombre = txtNombre.getText();
                double salario = Double.parseDouble(txtSalario.getText());
                
                Empleado nuevoEmp = null;

                if (seleccion == 0) {
                    nuevoEmp = new Empleado(codigo, nombre, salario);
                } else if (seleccion == 1) {
                    // Se saca la fecha y se convierte al formato Calendar
                    Calendar fechaFin = obtenerFecha(txtFechaFin.getText());
                    nuevoEmp = new EmpleadoTemporal(codigo, nombre, salario, fechaFin);
                } else if (seleccion == 2) {
                    double tasaCom = Double.parseDouble(txtTasaCom.getText());
                    nuevoEmp = new EmpleadoVentas(codigo, nombre, salario, tasaCom);
                }
                
                if (empresa.registrarNuevoEmpleado(nuevoEmp)) {
                    JOptionPane.showMessageDialog(this, "Empleado " + nombre + " registrado.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    etiqEstado.setText("Empleado registrado: " + nombre);
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Codigo duplicado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos. Verifique codigo, salario y/o fecha (DD/MM/AAAA).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void registrarHorasGUI() {
        String codigoStr = JOptionPane.showInputDialog(this, "Codigo del Empleado:");
        if (codigoStr == null) return;
        String horasStr = JOptionPane.showInputDialog(this, "Horas a registrar:");
        if (horasStr == null) return;
        
        try {
            int codigo = Integer.parseInt(codigoStr.trim());
            int horas = Integer.parseInt(horasStr.trim());
            
            if (empresa.registrarHorasTrabajadas(codigo, horas)) {
                JOptionPane.showMessageDialog(this, horas + " horas registradas para el codigo " + codigo, "Exito", JOptionPane.INFORMATION_MESSAGE);
                etiqEstado.setText(horas + " horas registradas. Cod: " + codigo);
            } else {
                JOptionPane.showMessageDialog(this, "Error: Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Entrada invalida. Ingrese solo numeros.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void registrarVentasGUI() {
        String codigoStr = JOptionPane.showInputDialog(this, "Codigo del Empleado de Ventas:");
        if (codigoStr == null) return;
        String montoStr = JOptionPane.showInputDialog(this, "Monto de la Venta:");
        if (montoStr == null) return;
        
        try {
            int codigo = Integer.parseInt(codigoStr.trim());
            double monto = Double.parseDouble(montoStr.trim());
            
            if (empresa.registrarVentas(codigo, monto)) {
                JOptionPane.showMessageDialog(this, "Venta de $" + String.format("%.2f", monto) + " registrada.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                etiqEstado.setText("Venta registrada. Cod: " + codigo);
            } else {
                JOptionPane.showMessageDialog(this, "Error: Empleado no encontrado o no es de Ventas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Entrada invalida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarFechaGUI() {
        String codigoStr = JOptionPane.showInputDialog(this, "Codigo del Empleado Temporal:");
        if (codigoStr == null) return;
        String fechaStr = JOptionPane.showInputDialog(this, "Nueva Fecha Fin Contrato (DD/MM/AAAA):");
        if (fechaStr == null) return;
        
        try {
            int codigo = Integer.parseInt(codigoStr.trim());
            Calendar nuevaFecha = obtenerFecha(fechaStr);
            
            if (empresa.actuFechaFinContrato(codigo, nuevaFecha)) {
                JOptionPane.showMessageDialog(this, "Fecha de fin de contrato actualizada.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                etiqEstado.setText("Fecha actualizada. Cod: " + codigo);
            } else {
                JOptionPane.showMessageDialog(this, "Error: Empleado no encontrado o no es temporal.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Codigo invalido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha invalido. Use DD/MM/AAAA.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calcularPagoGUI() {
        String codigoStr = JOptionPane.showInputDialog(this, "Codigo del Empleado para calcular pago:");
        if (codigoStr == null) return;
        
        try {
            int codigo = Integer.parseInt(codigoStr.trim());
            double pago = empresa.calcularPagoMensual(codigo);
            
            if (pago >= 0) {
                JOptionPane.showMessageDialog(this, "El Pago Mensual es: $" + String.format("%.2f", pago), 
                                            "Calculo de Pago", JOptionPane.INFORMATION_MESSAGE);
                etiqEstado.setText("Pago calculado: $" + String.format("%.2f", pago));
            } else {
                JOptionPane.showMessageDialog(this, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Codigo invalido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarReporteGUI() {
        String reporte = empresa.generarReporteEmpleados();
        JTextArea areaTexto = new JTextArea(reporte);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setPreferredSize(new Dimension(500, 350));

        JOptionPane.showMessageDialog(this, scrollPane, 
                                    "REPORTE GENERAL", JOptionPane.PLAIN_MESSAGE);
        etiqEstado.setText("Reporte generado de Empresa J A T.");
    }
    
    private void estiloBoton(JButton boton, Color colorF) {
        boton.setBackground(colorF);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private Calendar obtenerFecha(String fechaStr) throws InputMismatchException {
        try {
            String[] partes = fechaStr.split("/");
            if (partes.length != 3) throw new InputMismatchException();
            
            int dia = Integer.parseInt(partes[0].trim());
            int mes = Integer.parseInt(partes[1].trim()) - 1; // Calendar usa 0 para Enero
            int anio = Integer.parseInt(partes[2].trim());

            Calendar cal = Calendar.getInstance();
            cal.set(anio, mes, dia);
            return cal;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Si algo falla al sacar los números, lanza esta excepción.
            throw new InputMismatchException();
        }
    }
   

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
}