import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Parqueadero parqueadero = new Parqueadero();
        //Menú
        while (true) {
            mostrarMenu();
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opción:"));

            switch (opcion) {
                case 1:
                    String placaIngreso = JOptionPane.showInputDialog("¿Cuál es la placa de su vehículo?: ");
                    int resultadoIngreso = parqueadero.entrarCarro(placaIngreso);
                    imprimirResultadoIngreso(resultadoIngreso);
                    break;
                case 2:
                    String placaSalida = JOptionPane.showInputDialog("¿Cuál es la placa de su vehículo?: ");
                    int valorPagar = parqueadero.sacarCarro(placaSalida);
                    imprimirResultadoSalida(valorPagar);
                    break;
                case 3:
                    imprimirEstadoParqueadero(parqueadero);
                    break;
                case 4:
                    boolean hayCarrosPlacaIgual = parqueadero.hayCarrosPlacaIgual();
                    if (hayCarrosPlacaIgual) {
                        JOptionPane.showMessageDialog(null, "El carro ya se encuentra registrado por su placa");
                    } else {
                        JOptionPane.showMessageDialog(null, "El carro no se encuentra registrado por su placa");
                    }
                    break;
                case 5:
                    ArrayList<Carro> carrosMasDeTresHoras = parqueadero.darCarrosMasDeTresHorasParqueados();
                    StringBuilder carrosTresHoras = new StringBuilder("Lista de carros que llevan tres horas o más:\n");
                    for (Carro carro : carrosMasDeTresHoras) {
                        carrosTresHoras.append("Placa: ").append(carro.darPlaca()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, carrosTresHoras.toString());
                    break;
                case 6:
                    boolean hayCarroMasDeOchoHoras = parqueadero.HayCarroMasDeOchoHoras();
                    if (hayCarroMasDeOchoHoras) {
                        JOptionPane.showMessageDialog(null, "Se encuentran carros parqueados por más de 8 horas");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encuentran carros parqueados por más de 8 horas");
                    }
                    break;
                case 7:
                    Carro carroMayorTiempo = parqueadero.DevuelveCarroMayorTiempo();
                    JOptionPane.showMessageDialog(null, "Carro parqueado por 8 horas o más:\nPlaca: " + carroMayorTiempo.darPlaca());
                    break;
                case 8:
                    double tiempoPromedio = parqueadero.darTiempoPromedio();
                    JOptionPane.showMessageDialog(null, "En promedio los carros han estado parqueados por:" + tiempoPromedio + " horas.");
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null, "Gracias por usar nuestro programa");
                    scanner.close();
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "No válido");
            }
        }
    }

    private static void mostrarMenu() {
        String menu = "Menú:\n" +
                "1. Ingresar placa de carro\n" +
                "2. Sacar carro con placa\n" +
                "3. Ver información\n" +
                "4. Verificar carros con placas iguales\n" +
                "5. Consulta carros parqueados por más de tres horas\n" +
                "6. Consulta carros parqueados por más de ocho horas\n" +
                "7. Estadística de carros\n" +
                "8. Promedio de parqueo\n" +
                "9. Finalizar sesión";
        JOptionPane.showMessageDialog(null, menu);
    }

    private static void imprimirResultadoIngreso(int resultado) {
        switch (resultado) {
            case Parqueadero.NO_HAY_PUESTO:
                JOptionPane.showMessageDialog(null, "El parqueadero se encuentra lleno.");
                break;
            case Parqueadero.PARQUEADERO_CERRADO:
                JOptionPane.showMessageDialog(null, "El parqueadero está cerrado");
                break;
            case Parqueadero.CARRO_YA_EXISTE:
                JOptionPane.showMessageDialog(null, "La placa ya ha sido ingresada anteriormente");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Proceso finalizado con éxito");
                break;
        }
    }

    private static void imprimirResultadoSalida(int valorPagar) {
        switch (valorPagar) {
            case Parqueadero.CARRO_NO_EXISTE:
                JOptionPane.showMessageDialog(null, "El carro no está registrado.");
                break;
            case Parqueadero.PARQUEADERO_CERRADO:
                JOptionPane.showMessageDialog(null, "El parqueadero está cerrado");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Facturando...... \nValor a pagar: $" + valorPagar);
                break;
        }
    }

    private static void imprimirEstadoParqueadero(Parqueadero parqueadero) {
        String estado = "Información parqueadero:\n" +
                "Hora actual: " + parqueadero.darHoraActual() + "\n" +
                "Parqueadero se encuentra abierto: " + (parqueadero.estaAbierto() ? "Sí" : "No") + "\n" +
                "Tarifa/h: $" + parqueadero.darTarifa() + "\n" +
                "Monto facturado: $" + parqueadero.darMontoCaja() + "\n" +
                "Puestos disponibles: " + parqueadero.calcularPuestosLibres();
        JOptionPane.showMessageDialog(null, estado);
    }
}
