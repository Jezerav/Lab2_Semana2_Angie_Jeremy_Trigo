package lab2_semana2_angie_jeremy_trigo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Empresa {
    private ArrayList<Empleado> listaEmpleados;
    private Scanner entrada;
    
    public Empresa(){
        this.listaEmpleados = new ArrayList<>();
        this.entrada = new Scanner(System.in);
    }
    public Empleado buscarEmpleadoPorCodigo(int codigo) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getCodigo() == codigo) {
                return empleado;
            }
        }
        return null;
    }
    
    public void registrarEmpleados(){
        System.out.println("REGISTRO DE EMPLEADOS");
        
        System.out.print("Ingrese el codigo: ");
        int codigo = entrada.nextInt();
        entrada.nextLine(); 
        
        if(buscarEmpleadoPorCodigo(codigo) != null){
            System.out.println("ERROR: Ya existe un empleado con este codigo");
            return;
        }
        
        System.out.print("Ingrese el nombre completo: ");
        
        System.out.print("Ingrese el salario base mensual: ");
        double salarioBase = entrada.nextDouble();
        
        System.out.println("\nSELECCIONE EL TIPO DE EMPLEADO: ");
        System.out.println("1. Empleado Estandar");
        System.out.println("2. Empleado Temporal");
        System.out.println("3. Empleado de Ventas");
        System.out.print("Opción: ");
        int opcion = entrada.nextInt();
        entrada.nextLine();
        
        Empleado nuevoEmpleado = null;
        
        switch(opcion){
            case 1:
                nuevoEmpleado = new Empleado(codigo, nombre, salarioBase);
                break;
            case 2:
                System.out.println("\nDATOS DEL EMPLEADO TEMPORAL");
                Calendar fechaFin = solicitarFecha("Fecha de Fin contrato(DIA MES AÑO): ");
                nuevoEmpleado = new EmpleadoTemporal(codigo, nombre, salarioBase, fechaFin);
                break;
            case 3:
                System.out.println("\nDATOS DE EMPLEADO DE VENTAS");
                System.out.print("Ingrese la tasa de comision: ");
                double tasaComision = entrada.nextDouble();
                entrada.nextLine();
                nuevoEmpleado = new EmpleadoVentas(codigo, nombre, salarioBase, tasaComision);
                break;
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
        System.out.print("Ingrese el codigo del empleado: ");
        int codigo = entrada.nextInt();
        entrada.nextLine();
        
        Empleado empleado = buscarEmpleadoPorCodigo(codigo); 
        
        if(empleado == null){
            System.out.println("ERROR: Empleado con codigo "+codigo+" no encontrado");
            return;
        }
        
        System.out.print("Ingrese le cantidad de horas trabajadas: ");
        int horas = entrada.nextInt();
        entrada.nextLine(); 
        
        empleado.registrarHorasTrabajadas(horas);
        System.out.println("Horas registradas. Horas totales de "+empleado.getNombre()+" :"+empleado.getHorasTotal());
    }
    
    public void registrarVentas(){
        System.out.println("\nREGISTRAR VENTAS");
        System.out.print("Ingrese Código del empleado de ventas: ");
        int codigo = entrada.nextInt(); 
        entrada.nextLine(); 
        
        Empleado empleado = buscarEmpleadoPorCodigo(codigo); 

        if (empleado == null) {
            System.out.println("ERROR: Empleado con código " + codigo + " no encontrado.");
            return;
        }
        
        if (empleado instanceof EmpleadoVentas) {
            EmpleadoVentas empVentas = (EmpleadoVentas) empleado;
            
            System.out.print("Ingrese el monto total de ventas realizadas: ");
            double montoVentas = entrada.nextDouble();
            entrada.nextLine(); 
            
            empVentas.registrarVentas(montoVentas); 
            System.out.println("Venta registrada.");

        } else {
            System.out.println("ERROR: El empleado " + empleado.getNombre() + " no es un Empleado de Ventas.");
        }
    }
    
    private Calendar solicitarFecha(String mensaje) {
        System.out.print(mensaje);
        try {
            int dia = entrada.nextInt();
            int mes = entrada.nextInt();
            int anio = entrada.nextInt();
            entrada.nextLine(); 
            
            Calendar fecha = Calendar.getInstance();
            fecha.set(anio, mes - 1, dia);
            return fecha;
        } catch (Exception e) {
            System.out.println("Formato de fecha no válido. Usando fecha actual.");
            entrada.nextLine(); 
            return Calendar.getInstance();
        }
    }
    
    public boolean actuFechaFinContrato (int codigo, Calendar nuevaFecha) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getCodigo() == codigo) {
                if (empleado instanceof EmpleadoTemporal) {
                    
                    EmpleadoTemporal empTemporal = (EmpleadoTemporal) empleado;
                    empTemporal.actualiozarFechaFinCont(nuevaFecha); 
                    return true;
                    
                } else {
                    System.out.println("Error: El empleado con codigo " + codigo+ " no es un Empleado Temporal.");
                    return false;
                }
            }
        }
        System.out.println("Error: No se encontró ningún empleado con el código " + codigo);
        return false;
    }
}