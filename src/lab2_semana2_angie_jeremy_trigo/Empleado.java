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
public class Empleado {
    protected int codigo;
    protected String nombre;
    protected Calendar fechaCont;
    protected double salarioBase;
    protected int horasTotal;

    public Empleado(int codigo, String nombre, Calendar fechaCont, double salarioBase, int horasTotal) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaCont = Calendar.getInstance();
        this.salarioBase = salarioBase;
        this.horasTotal = horasTotal;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Calendar getFechaCont() {
        return fechaCont;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public int getHorasTotal() {
        return horasTotal;
    }
    
    public int horasTrabajadas(int horas) {
        if (horas > 0) {
            horasTotal+=horas;
        }
        return horas;
    }
    
    public double calculoPago () {
        double salarioPro=(horasTotal/160)*salarioBase;
        double salarioTotal=salarioPro*0.965; // Jeremy: probé manualmente y 0.965 es lo mismo que 3.5% :)
        return salarioTotal;
    }
    
    public String ToString() {
        return "Codigo de empleado: " + codigo+ " Nombre de empleado: "+nombre+" Fecha de contratacion: "
                + fechaCont;
    }
}
