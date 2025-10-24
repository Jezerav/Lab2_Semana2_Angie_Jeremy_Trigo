/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_semana2_angie_jeremy_trigo;

/**
 *
 * @author Trigo
 */
import java.util.Calendar;

public class EmpleadoVentas extends Empleado{
    private double[] ventasMensuales;
    private double tasaComision;

    public EmpleadoVentas(int codigo, String nombre, Calendar fechaCont, double salarioBase, int horasTotal) {
        super(codigo, nombre, fechaCont, salarioBase, horasTotal);
        this.tasaComision = tasaComision;
        this.ventasMensuales = new double[12];
    }

    public void registrarVentas(double monto){
        if (monto > 0) {
            Calendar fecha = Calendar.getInstance();
            int MesActual = fecha.get(Calendar.MONTH);
            ventasMensuales[MesActual] += monto;
        } else {
            System.out.println("El monto de la venta tiene que ser mayor a 0.");
        }
    }

    public double[] getVentasMensuales() {
        return ventasMensuales;
    }

    public double getTasaComision() {
        return tasaComision;
    }

    public void setVentasMensuales(double[] ventasMensuales) {
        this.ventasMensuales = ventasMensuales;
    }

    public void setTasaComision(double tasaComision) {
        this.tasaComision = tasaComision;
    }   
    
    public double calcularComision(){
        Calendar fecha = Calendar.getInstance();
        int mesActual = fecha.get(Calendar.MONTH);
        return ventasMensuales[mesActual] * tasaComision;
    }
    
    @Override
    public double calculoPago(){
        double pagoBase = super.calculoPago();
        double comision = calcularComision();
        return pagoBase + comision;
    }
    
    public double calcularVentasAnuales(){
        double total = 0;
        for (int index = 0; index < ventasMensuales.length; index++) {
            total += ventasMensuales[index];
        }
        return total;   
    }
    
    @Override
    public String ToString(){
        String infoBase = super.ToString();
        double totalAnual = calcularVentasAnuales();
        
        return infoBase + 
               "\nTasa de comisionn: " + (tasaComision * 100) + "%" +
               "\nVentas anuales: Lps." + totalAnual +
               "\nPago total del mes actual: Lps." + calculoPago();
    }
}
