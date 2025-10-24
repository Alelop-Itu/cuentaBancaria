package com.alelop.cuentaBancaria;

/*
 * Author: Alejandra López
 * Fecha: 24/10/2025
 * Descripción: Esta clase llamada "Cuenta" representa una cuenta bancaria.
 * Guarda información como el saldo, la cantidad de depósitos (consignaciones),
 * el número de retiros, la tasa de interés anual y la comisión mensual.
 */

public class Cuenta {
    // Guarda el dinero actual que tiene la cuenta.
    protected float saldo;

    // Lleva la cuenta de cuántas veces se ha consignado dinero.
    protected int numeroConsignacion = 0;

    // Guarda cuántas veces se ha retirado dinero.
    protected int numeroRetiro = 0;

    // Tasa de interés anual (porcentaje que genera la cuenta cada año).
    protected float tasaAnual;

    // Monto que se cobra cada mes como comisión por mantenimiento u otros servicios.
    protected float comisionMensual = 0;

    /*
     * Constructor de la clase Cuenta.
     * Aquí se inicializa la cuenta con un saldo y una tasa de interés anual.
     */
    public Cuenta(float saldo, float tasaAnual) {
        // Se guarda el saldo inicial que el usuario deposita al abrir la cuenta.
        this.saldo = saldo;
        // Se guarda la tasa anual que se usará para calcular los intereses.
        this.tasaAnual = tasaAnual;
    }

    /*
     * Método para consignar (depositar) dinero en la cuenta.
     * Recibe la cantidad que se quiere ingresar.
     */
    public void consignar(float cantidad) {
        // Se suma la cantidad al saldo actual, aumentando el dinero disponible.
        saldo = saldo + cantidad;
        // Se aumenta el contador de consignaciones, ya que se hizo un depósito.
        numeroConsignacion = numeroConsignacion + 1;
    }

    /*
     * Método para retirar dinero de la cuenta.
     * Verifica si hay suficiente saldo antes de restar la cantidad.
     */
    public void retirar(float cantidad) {
        // Calculamos cuánto quedaría en la cuenta después del retiro.
        float nuevoSaldo = saldo - cantidad;

        // Si después del retiro todavía hay dinero (saldo >= 0), se hace el retiro.
        if (nuevoSaldo >= 0) {
            // Se actualiza el saldo restando la cantidad retirada.
            saldo -= cantidad;
            // Se aumenta el número de retiros realizados.
            numeroRetiro = numeroRetiro + 1;
        } else {
            // Si no hay suficiente dinero, se muestra un mensaje de error.
            System.out.println("No hay suficientes fondos");
        }
    }

    /*
     * Método para calcular los intereses que genera la cuenta cada mes.
     * Usa la tasa anual para obtener la tasa mensual y luego calcula el interés.
     */
    public void calcularInteres() {
        // Primero se divide la tasa anual entre 12 para sacar la tasa mensual.
        float tasaMensual = tasaAnual / 12;
        // Luego se calcula el interés mensual multiplicando el saldo por esa tasa.
        float interesMensual = saldo * tasaMensual;
        // Finalmente, se suma el interés mensual al saldo actual.
        saldo = saldo + interesMensual;
    }

    /*
     * Método para aplicar la comisión mensual de la cuenta.
     * Resta el valor de la comisión al saldo.
     */
    public void extractoMensual() {
        // Se descuenta del saldo la comisión mensual.
        saldo = saldo - comisionMensual;
    }

}

