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

    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try (Connection conn = new conectaDAO().connectDB(); PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro com suceso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no cadastros");
        }
    }

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
            JOptionPane.showMessageDialog(null, "Erro na listagrm");
        }
        return null;
    }
}
