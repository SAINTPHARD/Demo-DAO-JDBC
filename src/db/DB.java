package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

    // REMOVIDO: A conexão estática foi removida.
    // private static Connection conn = null;

    /**
     * Obtém uma NOVA conexão com o banco de dados a cada chamada.
     * @return um objeto Connection.
     */
    public static Connection getConnection() {
        try {
            Properties props = loadProperties();
            String url = props.getProperty("dburl");
            // Retorna uma nova conexão a cada vez.
            return DriverManager.getConnection(url, props);
        }
        catch (SQLException e) {
            // CORRIGIDO: Encapsula a exceção original 'e' para manter o stack trace.
            throw new DbException("Erro ao obter conexão com o banco de dados", e);
        }
    }

    /**
     * Fecha uma conexão específica.
     * @param conn A conexão a ser fechada.
     */
    public static void closeConnection(Connection conn) { // CORRIGIDO: Recebe a conexão como parâmetro.
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // CORRIGIDO: Encapsula a exceção original 'e'.
                throw new DbException("Erro ao fechar a conexão", e);
            }
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e) {
            // CORRIGIDO: Encapsula a exceção original 'e'.
            throw new DbException("Erro ao carregar o arquivo de propriedades", e);
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                // CORRIGIDO: Encapsula a exceção original 'e'.
                throw new DbException("Erro ao fechar o Statement", e);
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // CORRIGIDO: Encapsula a exceção original 'e'.
                throw new DbException("Erro ao fechar o ResultSet", e);
            }
        }
    }
}