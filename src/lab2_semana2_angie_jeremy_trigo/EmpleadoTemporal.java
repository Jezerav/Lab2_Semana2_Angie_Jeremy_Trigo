/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_semana2_angie_jeremy_trigo;

import java.util.Calendar;

/**
 *
 * @author jerem
 */
public class EmpleadoTemporal extends Empleado {

    private Calendar fechaFinContrato;

    public EmpleadoTemporal(int codigo, String nombre, double salarioBase, Calendar fechaFinContrato) {
        super(codigo, nombre, Calendar.getInstance(), salarioBase, 0);
        this.fechaFinContrato = fechaFinContrato;
    }

    @Override
    public double calculoPago() {
        Calendar hoy = Calendar.getInstance();

        if (hoy.compareTo(fechaFinContrato) > 0) {
            return 0.0;
        } else {
            return super.calculoPago();
        }
    }

    public void actualiozarFechaFinCont(Calendar nuevaFecha) {
        this.fechaFinContrato = nuevaFecha;
    }

    public String toString() {
        String infoBase = super.toString();

        String fechaFinFormateada = String.format(("%d/%d/%d"),
                fechaFinContrato.get(Calendar.DAY_OF_MONTH),
                fechaFinContrato.get(Calendar.MONTH) + 1,
                fechaFinContrato.get(Calendar.YEAR));
        
        return infoBase+" Fecha de fin de contrato: "+fechaFinFormateada;
    }
}
