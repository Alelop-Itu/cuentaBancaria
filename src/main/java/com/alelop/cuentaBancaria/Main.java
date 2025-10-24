package com.alelop.cuentaBancaria;

/*
 * Author: Alejandra López
 * Fecha: 24/10/2025
 * Descripción: Esta clase Main ejecuta la simulación de un banco.
 * El usuario puede elegir entre crear una cuenta de ahorros o corriente,
 * ingresar datos iniciales, realizar operaciones como depositar, retirar,
 * generar extractos e imprimir información. También puede salir en cualquier momento.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // Lector para recibir datos del usuario desde la consola.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //variable que registra las opciones escogidas desde el menú
        int opcionCuenta = 0;
        float saldo = 0, tasa = 0;
        // variable para validar las opciones escogidas desde el menú
        boolean entradaValida = false;

        // --- MENÚ PRINCIPAL ---
        //Do se usa para repetir el bloque de código hasta que se escoja una opción válida
        do {
            System.out.println("\n=== BIENVENIDO A SU BANCA MÓVIL ===");
            System.out.println("1. Acceso a Cuenta de Ahorros");
            System.out.println("2. Acceso a a su Cuenta Corriente");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");


            //Se usa try en el código que puede generar un error, en caso que el usuario ingrese texto en lugar
            //de la respuesta número, se va a arrojar un error e informará al usuario.
            try {
                opcionCuenta = Integer.parseInt(br.readLine());
                //condicional según las opciones escogidas en el menú
                if (opcionCuenta < 1 || opcionCuenta > 3) {
                    //Primer mensaje de error al escoger una opción inválida en el menú
                    System.out.println("Opción inválida. Debe elegir 1, 2 o 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese solo números válidos (1, 2 o 3).");
                opcionCuenta = 0; // Resetea la opción para seguir dentro del bucle
            }

            // Si elige salir, termina el programa antes de pedir más datos
            if (opcionCuenta == 3) {
                System.out.println("Gracias por usar su Banca Móvil. ¡Hasta pronto!");
                return; // Sale completamente del método main
            }

        } while (opcionCuenta < 1 || opcionCuenta > 3);

        // Se valida el saldo para evitar valores incoherentes
        //Se inicializa la variable para evaluar el saldo del cliente
        //Se estbalce en false para que el bucle while se ejecute al menos una vez
        entradaValida = false;
        //While se ejecutará mientras tenga false de la variable
        while (!entradaValida) {
            try {
                System.out.print("Ingrese el saldo inicial: ");
                saldo = Float.parseFloat(br.readLine());
                //condicional, evita que se coloque un saldo negativo
                if (saldo < 0) {
                    System.out.println("️ El saldo no puede ser negativo. Intente nuevamente.");
                } else {
                    //Sale del buble
                    entradaValida = true;
                }
                //Arroja un mensaje informando sobre la invalidez del valor ingresado
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido para el saldo.");
            }
        }

        //  Se valida la tasa para evitar el ingreso de valores incoherentes
        entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Ingrese la tasa de interés anual: ");
                tasa = Float.parseFloat(br.readLine());
                //Condicional que evita ingreso de valores negativos
                if (tasa < 0) {
                    System.out.println(" La tasa no puede ser negativa. Intente nuevamente.");
                } else {
                    //Se sale del bucle
                    entradaValida = true;
                }
                //Arroja un mensaje informando sobre la invalidez del valor ingresado
            } catch (NumberFormatException e) {
                System.out.println(" Error: Ingrese un número válido para la tasa de interés.");
            }
        }

        // --- Apertura de la cuenta según la opción ---
        Cuenta cuenta;
        if (opcionCuenta == 1) {
            cuenta = new CuentaAhorros(saldo, tasa);
        } else {
            cuenta = new CuentaCorriente(saldo, tasa);
        }

        // Menú de opciones
        int opcionOperacion;
        do {
            System.out.println("\n=== MENÚ DE OPERACIONES ===");
            System.out.println("1. Depositar");
            System.out.println("2. Retirar");
            System.out.println("3. Generar Extracto Mensual");
            System.out.println("4. Imprimir Información");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");

            try {
                opcionOperacion = Integer.parseInt(br.readLine());
                //Informa al usuario sobre el ingreso erróneo
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Intente nuevamente.");
                //Inicializa nuevamente la variable para volver al menú
                opcionOperacion = 0;
                //Permite reingresar al menú
                continue; // Vuelve al menú sin causar error
            }
            //Bucle para validar el valor de ingreso del depósito
            //Evita valores negativos o ingreso de texto
            switch (opcionOperacion) {
                case 1:
                    System.out.print("Ingrese el monto a depositar: ");
                    try {
                        float deposito = Float.parseFloat(br.readLine());
                        if (deposito <= 0) {
                            System.out.println("El monto debe ser mayor que cero.");
                        } else {
                            cuenta.consignar(deposito);
                            System.out.println("Depósito realizado con éxito.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ingrese un valor numérico válido.");
                    }
                    break;
                //El bucle evita valores negativos o ingresos inválidos
                case 2:
                    System.out.print("Ingrese el monto a retirar: ");
                    try {
                        float retiro = Float.parseFloat(br.readLine());
                        if (retiro <= 0) {
                            System.out.println("El monto debe ser mayor que cero.");
                        } else {
                            //Se genera el retiro
                            cuenta.retirar(retiro);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ingrese un valor numérico válido.");
                    }
                    break;
                // genera el extracto mensual
                case 3:
                    cuenta.extractoMensual();
                    System.out.println("✅ Extracto mensual generado.");
                    break;

                case 4:
                    // Dependiendo del tipo de cuenta, se llama a su método imprimir
                    //Instanceof se usa para determinar si el objeto referenciado por la variable genérica cuenta
                    // es realmente un objeto de tipo CuentaAhorros o de tipo CuentaCorriente.
                    //Anteriormente se definió Cuenta cuenta para poder llamar a los métodos de dicha clase
                    if (cuenta instanceof CuentaAhorros) {
                        ((CuentaAhorros) cuenta).imprimir();
                    } else if (cuenta instanceof CuentaCorriente) {
                        ((CuentaCorriente) cuenta).imprimir();
                    }
                    break;

                case 5:
                    System.out.println(" Gracias por usar el sistema. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcionOperacion != 5);
    }
}
