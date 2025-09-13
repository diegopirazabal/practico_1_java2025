package PrestadorSalud;

import PrestadorSalud.model.PrestadorSalud;
import PrestadorSalud.model.TipoPrestador;
import PrestadorSalud.repository.PrestadorSaludRepository;
import PrestadorSalud.service.PrestadorSaludService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PrestadorSaludRepository repository = new PrestadorSaludRepository();
        PrestadorSaludService service = new PrestadorSaludService(repository);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--- Prestadores de Salud ---");
            System.out.println("1. Agregar prestador");
            System.out.println("2. Listar prestadores");
            System.out.println("3. Buscar por nombre");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String direccion = scanner.nextLine();
                    System.out.print("Tipo (LABORATORIO/HOSPITAL): ");
                    TipoPrestador tipo = TipoPrestador.valueOf(scanner.nextLine().toUpperCase());
                    service.addPrestador(new PrestadorSalud(nombre, direccion, tipo));
                    break;
                case "2":
                    List<PrestadorSalud> todos = service.obtenerPrestadores();
                    todos.forEach(System.out::println);
                    break;
                case "3":
                    System.out.print("Nombre a buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    List<PrestadorSalud> encontrados = service.buscarPorNombre(nombreBuscar);
                    encontrados.forEach(System.out::println);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}
