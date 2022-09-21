package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class login {

    public static void main() {
        

        boolean u = false;
        boolean p = false;
        final String obrigatorio
                = "?autoReconnect=true&useSSL=false&&serverTimezone=UTC";

        final String url
                = "jdbc:mysql://localhost/trabjdbc" + obrigatorio;

        final String sql = "SELECT nombre FROM usuario";

        while (u == false) {
            System.out.println("Nombre: ");
            String user = new Scanner(System.in).next();

            try (
                     Connection conexao = DriverManager.getConnection(url, "root", "");  Statement statement = conexao.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

                ResultSetMetaData metaData = resultSet.getMetaData();

                int numeroColunas = metaData.getColumnCount();

                while (resultSet.next()) {
                    for (int i = 1; i <= numeroColunas; i++) {
                        if (resultSet.getObject(i).equals(user)) {
                            u = true;
                        }
                    }
                }

                if (u == true) {
                    System.out.println("Usuario registrado");

                } else {
                    System.out.println("Usuario no registrado, ingrese nuevamente");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        while (p == false) {
            if (u == true) {

                System.out.println("Contraseña: ");
                String password = new Scanner(System.in).next();

                final String psw = "SELECT senha FROM usuario";

                try (
                         Connection conexao = DriverManager.getConnection(url, "root", "");  Statement statement = conexao.createStatement();  ResultSet resultSet = statement.executeQuery(psw)) {

                    ResultSetMetaData metaData = resultSet.getMetaData();

                    int numeroColunas = metaData.getColumnCount();

                    p = false;

                    while (resultSet.next()) {
                        for (int i = 1; i <= numeroColunas; i++) {
                            if (resultSet.getObject(i).equals(password)) {
                                p = true;
                            }
                        }

                    }
                    if (p == true) {
                        System.out.println("Contraseña correcta");
                    } else {
                        System.out.println("Contraseña Incorrecta");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
