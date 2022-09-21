package jdbc;

import java.util.InputMismatchException;
import java.util.Scanner;

public class menu {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        jdbc.login.main();
        
        System.out.println("Benvenidos al registro de productos JDBC");
        int varmenu = 0;
        while (varmenu != 7) {

            System.out.println("1 - Listar productos");
            System.out.println("2 - Registrar producto");
            System.out.println("3 - Restar producto");
            System.out.println("4 - Sumar producto");
            System.out.println("5 - Buscar producto");
            System.out.println("6 - Eliminar producto");
            System.out.println("7 - Salir");

            while (true) {
                try {
                    input = new Scanner(System.in);
                    varmenu = input.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Informe um numero");
                }
            }

            switch (varmenu) {
                case 1:
                    jdbc.listar.main();
                    break;

                case 2:
                    jdbc.cadastrar.main();
                    break;

                case 3:
                    jdbc.restar.main();
                    break;

                case 4:
                    jdbc.sumar.main();
                    break;

                case 5:
                    jdbc.buscar.main();
                    break;

                case 6:
                    jdbc.eliminar.main();
                    break;

            }

        }
    }
}
