/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    /**
     *
     * @param produto
     */
    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try (Connection conn = new conectaDAO().connectDB(); PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastro com suceso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de cadastro", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *
     * @return
     */
    public List<ProdutosDTO> listarProdutos() {
        List<ProdutosDTO> listagem = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try (Connection conn = new conectaDAO().connectDB(); PreparedStatement prep = conn.prepareStatement(sql); ResultSet rs = prep.executeQuery()) {
            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));

                listagem.add(p);
            }
            return listagem;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na Listagem", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     *
     * @param id
     */
    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";

        try (Connection conn = new conectaDAO().connectDB(); PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setString(1, "Vendido");
            prep.setInt(2, id);

            int linhasAfetadas = prep.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(null, "Venda comcluida com sucesso");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na venda", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<ProdutosDTO> listarProdutosVendidos() {
        List<ProdutosDTO> listagem = new ArrayList<>();

        String sql = "SELECT * FROM produtos WHERE status = ?";

        try (Connection conn = new conectaDAO().connectDB(); PreparedStatement prep = conn.prepareStatement(sql)) {
            prep.setString(1, "Vendido");

            try (ResultSet rs = prep.executeQuery()) {
                while (rs.next()) {
                    ProdutosDTO p = new ProdutosDTO();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setValor(rs.getInt("valor"));
                    p.setStatus(rs.getString("status"));

                    listagem.add(p);
                }
            } catch (Exception e) {
            }

            return listagem;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro na Listagem da venda", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
