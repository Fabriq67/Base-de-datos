package Logica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void addCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO CLIENTES (CLINOMBRE, CLIIDENTIFICACION, CLIDIRECCION, CLITELEFONO, CLICELULAR, CLIEMAIL, CLITIPO, CLISTATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getClinombre());
            pstmt.setString(2, cliente.getCliidentificacion());
            pstmt.setString(3, cliente.getClidireccion());
            pstmt.setString(4, cliente.getClitelefono());
            pstmt.setString(5, cliente.getClicelular());
            pstmt.setString(6, cliente.getCliemail());
            pstmt.setString(7, cliente.getClitipo());
            pstmt.setString(8, cliente.getClistatus());

            pstmt.executeUpdate();
        }
    }

    public List<Cliente> getAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTES";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setClicodigo(rs.getInt("CLICODIGO"));
                cliente.setClinombre(rs.getString("CLINOMBRE"));
                cliente.setCliidentificacion(rs.getString("CLIIDENTIFICACION"));
                cliente.setClidireccion(rs.getString("CLIDIRECCION"));
                cliente.setClitelefono(rs.getString("CLITELEFONO"));
                cliente.setClicelular(rs.getString("CLICELULAR"));
                cliente.setCliemail(rs.getString("CLIEMAIL"));
                cliente.setClitipo(rs.getString("CLITIPO"));
                cliente.setClistatus(rs.getString("CLISTATUS"));

                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE CLIENTES SET CLINOMBRE = ?, CLIIDENTIFICACION = ?, CLIDIRECCION = ?, CLITELEFONO = ?, CLICELULAR = ?, CLIEMAIL = ?, CLITIPO = ?, CLISTATUS = ? WHERE CLICODIGO = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getClinombre());
            pstmt.setString(2, cliente.getCliidentificacion());
            pstmt.setString(3, cliente.getClidireccion());
            pstmt.setString(4, cliente.getClitelefono());
            pstmt.setString(5, cliente.getClicelular());
            pstmt.setString(6, cliente.getCliemail());
            pstmt.setString(7, cliente.getClitipo());
            pstmt.setString(8, cliente.getClistatus());
            pstmt.setInt(9, cliente.getClicodigo());

            pstmt.executeUpdate();
        }
    }

    public void deleteCliente(int clicodigo) throws SQLException {
        String sql = "DELETE FROM CLIENTES WHERE CLICODIGO = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clicodigo);

            pstmt.executeUpdate();
        }
    }
}
