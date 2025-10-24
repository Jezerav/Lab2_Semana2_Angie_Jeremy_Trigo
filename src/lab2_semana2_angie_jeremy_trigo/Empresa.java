/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_semana2_angie_jeremy_trigo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author jerem
 */
public class Empresa {
    private ArrayList<Empleado> listaEmpleados;
    private Scanner entrada;
    
    public Empresa(){
        this.listaEmpleados = new ArrayList<>();
        this.entrada = new Scanner(System.in).useDelimiter("\n");
    }
    
    public void registrarEmpleados(){
        System.out.println("REGISTRO DE EMPLEADOS");
        
        System.out.println("Ingrese el codigo: ");
        int codigo = entrada.nextInt();
        
        if(buscarEmpleadoPorCodigo(codigo) != null){
            System.out.println("ERROR: Ya existe un empleado con este codigo");
            return;
        }
        
        System.out.println("Ingrese el nombre completo: ");
        String nombre = entrada.next();
        
        System.out.println("Ingrese el salario base mensual: ");
        double salarioBase = entrada.nextDouble();
        
        System.out.println("\nSELECCIONE EL TIPO DE EMPLEADO: ");
        System.out.println("1. Empleado Estandar");
        System.out.println("1. Empleado Temporal");
        System.out.println("1. Empleado de Ventas");
        int opcion = entrada.nextInt();
        
        Empleado nuevoEmpleado = null;
        
        switch(opcion){
            case 1:
                nuevoEmpleado = new Empleado(codigo, nombre, salarioBase);
                break;
            case 2:
                System.out.println("\nDATOS DEL EMPLEADO ESTANDAR");
                Calendar fechaFin = solicitarFecha("Fecha de Fin contrato(DIA/MES/AÑO): ");
                nuevoEmpleado = new EmpleadoTemporal(codigo, nombre,salarioBase, fechaFin);
            case 3:
                System.out.println("\nDATOS DE EMPLEADO DE VENTAS");
                System.out.println("Ingrese la tasa de comision: ");
                double tasaComision = entrada.nextDouble();
                nuevoEmpleado = new EmpleadoVentas(codigo, nombre, salarioBase, tasaComision);
            default: 
                System.out.println("ERROR: Opcion invalida");
                return;
        }
        
        if(nuevoEmpleado != null){
            listaEmpleados.add(nuevoEmpleado);
            System.out.println("\nEmpleado: "+nombre+"("+codigo+") registrado exitosamente como tipo "+opcion);
        }
    }
    
    public void registrarHorasTrabajadas(){
        System.out.println("REGISTRO DE HORAS");
        System.out.println("Ingrese el codigo del empleado: ");
        int codigo = entrada.nextInt();
        
        Empleado empleado = buscarEmpleadoPorCodigo(codigo);
        
        if(empleado == null){
            System.out.println("ERROR: Empleado con codigo "+codigo+" no encontrado");
            return;
        }
        
        System.out.println("Ingrese le cantidad de horas trabajadas: ");
        int horas = entrada.nextInt();
        
        empleado.registrarHorasTrabajadas(horas);
        System.out.println("Horas registradas. Hotas totales de "+empleado.getNombre()+" :"+empleado.getHorasTotal());
    }
    
    public void registrarVentas(){
        System.out.println("\nREGISTRAR VENTAS");
        System.out.print("Ingrese Código del empleado de ventas: ");
        String codigo = entrada.nextLine();

        Empleado empleado = buscarEmpleadoPorCodigo(codigo);

        if (empleado == null) {
            System.out.println("ERROR: Empleado con código " + codigo + " no encontrado.");
            return;
        }
        
        if (empleado instanceof EmpleadoVentas) {
            EmpleadoVentas empVentas = (EmpleadoVentas) empleado;
            
            System.out.print("Ingrese el monto total de ventas realizadas: ");
            double montoVentas = entrada.nextDouble();
            

        } else {
            System.out.println("ERROR: El empleado " + empleado.getNombre() + " no es un Empleado de Ventas.");
        }
    }
    
    public Empleado buscarEmpleadoPorCodigo(String codigo) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getCodigo().equalsIgnoreCase(codigo)) {
                return empleado;
            }
        }
        return null;
    }
    
    private Calendar solicitarFecha(String mensaje) {
        System.out.print(mensaje);
        try {
            int dia = entrada.nextInt();
            int mes = entrada.nextInt();
            int anio = entrada.nextInt();

            Calendar fecha = Calendar.getInstance();
            fecha.set(anio, mes - 1, dia);
            return fecha;
        } catch (Exception e) {
            System.out.println("Formato de fecha no válido. Usando fecha actual.");
            entrada.next();
            return Calendar.getInstance();
        }
    }
}
