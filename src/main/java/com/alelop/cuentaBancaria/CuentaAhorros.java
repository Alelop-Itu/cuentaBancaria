package com.alelop.cuentaBancaria;

/*
 * Author: Alejandra López
 * Fecha: 22/10/2025
 * Descripción: Esta clase llamada "CuentaAhorros" hereda de la clase "Cuenta"
 * y representa una cuenta de ahorros.
 * Aquí se maneja si la cuenta está activa o no dependiendo del saldo,
 * se controlan los retiros y depósitos, se calcula la comisión mensual
 * y se muestra el resumen con el método imprimir().
 */

public class CuentaAhorros extends Cuenta {
    // Indica si la cuenta está activa o no (true = activa, false = inactiva)
    private boolean activa;

    /*
     * Constructor de la clase CuentaAhorros.
     * Recibe el saldo inicial y la tasa de interés.
     * Si el saldo es menor a 10, la cuenta se marca como inactiva.
     */
    public CuentaAhorros(float saldo, float tasa) {
        // Se llama al constructor de la clase padre "Cuenta" para heredar sus atributos.
        super(saldo, tasa);

        // Si el saldo inicial es menor a 10, la cuenta queda inactiva.
        if (saldo < 10)
            activa = false;
        else
            activa = true;
    }

    /*
     * Método para retirar dinero de la cuenta.
     * Solo permite hacerlo si la cuenta está activa.
     */
    public void retirar(float cantidad) {
        // Verifica si la cuenta está activa antes de permitir el retiro.
        if (activa)
            // Llama al método retirar() de la clase padre (Cuenta).
            super.retirar(cantidad);
        // Si no está activa, simplemente no hace nada.
    }

    /*
     * Método para depositar (consignar) dinero en la cuenta.
     * Solo se puede hacer si la cuenta está activa.
     */
    public void depositar(float cantidad) {
        // Si la cuenta está activa, se permite la consignación.
        if (activa)
            super.consignar(cantidad);
    }

    /*
     * Método que genera el extracto mensual.
     * Aplica comisiones si hay más de 4 retiros y actualiza el estado de la cuenta.
     */
    public void extractoMensual() {
        // Si se hicieron más de 4 retiros en el mes, se cobra $1 extra por cada retiro adicional.
        if (numeroRetiro > 4) {
            comisionMensual = comisionMensual + (numeroRetiro - 4) * 1;
        }

        // Se llama al método extractoMensual() de la clase padre para aplicar la comisión.
        super.extractoMensual();

        // Si después de aplicar comisiones el saldo baja de 10, la cuenta se desactiva.
        if (saldo < 10)
            activa = false;
    }

    /*
     * Método para mostrar la información actual de la cuenta.
     * Imprime el saldo, la comisión mensual y el número total de transacciones.
     */
    public void imprimir() {
        System.out.println("Saldo: $" + saldo);
        System.out.println("Comisión mensual: $" + comisionMensual);
        System.out.println("Número de transacciones: " + (numeroConsignacion + numeroRetiro));
    }
}

