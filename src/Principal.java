import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        //  Definicion Variables
        String divisaOrigen = "";
        String divisaDestino = "";
        double cantidad = 1;
        int opcion = 0;

        //  Creando Menu
        String menu = """
                ***************************************************
                
                [= Sea Bienvenido/a al conversor de Divisas =]
                
                1) Dólar =>> Peso Argentino
                2) Peso Argentino =>> Dólar
                3) Dólar =>> Real Brasileño
                4) Real Brasileño =>> Dólar
                5) Dólar =>> Peso Colombiano
                6) Peso Colombiano =>> Dólar
                7) Salir
                
                Elija una opción válida
                
                ***************************************************
                """;
        //  Captura Opcion Menu
        Scanner lectura = new Scanner(System.in);
        while (opcion != 7) {
            System.out.println(menu);

            // Creando excepción por Valor Inesperado
            try{
                opcion = lectura.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Ocurrio un error :");
                System.out.println(e.getMessage());
            }

            //  Creando Switch Case
            switch (opcion){
                case 1:
                    divisaOrigen = "USD";
                    divisaDestino = "ARS";
                    break;
                case 2:
                    divisaOrigen = "ARS";
                    divisaDestino = "USD";
                    break;
                case 3:
                    divisaOrigen = "USD";
                    divisaDestino = "BRL";
                    break;
                case 4:
                    divisaOrigen = "BRL";
                    divisaDestino = "USD";
                    break;
                case 5:
                    divisaOrigen = "USD";
                    divisaDestino = "COP";
                    break;
                case 6:
                    divisaOrigen = "COP";
                    divisaDestino = "USD";
                    break;
                case 7:
                    System.out.println("Salió del Programa.....!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion No Valida");
                    System.exit(0);
            }
            System.out.println("Ingrese el valor que desea convertir:");
            cantidad = lectura.nextDouble();

            // Enviando Información y Creando Excepciones de la Consulta
            ConsultaApi consulta = new ConsultaApi();
            try {
                Divisa divisa = consulta.buscaDivisa(divisaOrigen, divisaDestino, cantidad);
                System.out.println("El valor de " + cantidad + " " + divisaOrigen + " Corresponde al valor final de ==> " +
                        divisa.conversion_result() + " " + divisaDestino);
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
                System.out.println("Aplicacion Finalizada");
            }
        }
     }
}
