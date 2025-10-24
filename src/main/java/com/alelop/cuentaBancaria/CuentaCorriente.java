package com.alelop.cuentaBancaria;

/*
 * author: alejandra lópez
 * fecha: 24/10/2025
 * descripción: ¡esta es la cuenta corriente! 🏦
 * la hemos simplificado para que funcione como una cuenta normal, sin superpoderes.
 * ahora ya no permite sobregiros ni deudas, ¡solo usa el dinero que hay!
 */

public class CuentaCorriente extends Cuenta {

    // esta clase solo hereda lo que ya tiene la clase papá 'cuenta'.

    /*
     * constructor de la cuenta corriente.
     * le pasamos el saldo inicial y la tasa anual a la clase papá (cuenta).
     */
    public CuentaCorriente(float saldo, float tasaAnual) {
        // llama al constructor de la clase 'cuenta' para inicializar lo básico.
        super(saldo, tasaAnual);
    }

    /*
     * método para sacar platica (retirar).
     *usa la lógica de la clase papá, que verifica que haya saldo antes de dejarte retirar.
     */
    public void retirar(float cantidad) {
        // usamos el método de la clase padre (cuenta) para que se encargue de la verificación de saldo.
        // si no hay fondos, la clase cuenta mostrará el mensaje de error.
        super.retirar(cantidad);
    }

    /*
     * método que suma el saldo de la cuenta
     */
    public void consignar(float cantidad) {
        // llamamos al método de la clase padre (cuenta) para que sume al saldo y cuente la transacción.
        super.consignar(cantidad);
    }

    /*método para mostrarle al cliente cómo va su cuenta.
     */
    public void depositar(float cantidad) {
        consignar(cantidad);
    }

    /*
     * método para mostrarle al cliente cómo va su cuenta
     */
    public void imprimir() {
        System.out.println("\n--- reporte de tu cuenta corriente ---");
        System.out.println("tu saldo actual (lo que tienes): $" + saldo);
        System.out.println("la comisión de este mes: $" + comisionMensual);
        // sumamos los movimientos que cuenta la clase papá (cuenta).
        System.out.println("total de movimientos: " + (numeroConsignacion + numeroRetiro));
        System.out.println("---------------------------------------");
    }
}