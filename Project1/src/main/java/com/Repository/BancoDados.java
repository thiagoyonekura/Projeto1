package com.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Models.*;

public class BancoDados implements InterfaceBancoDados {
	private Connection conexao;
	private String db_url;
	private String db_user;
	private String db_password;

	public BancoDados(String db_url, String db_user, String db_password) {
		this.db_url = db_url;
		this.db_user = db_user;
		this.db_password = db_password;
	}

	@Override
	public void desconectar() throws IOException {

		try {
			conexao.close();
			System.out.println("Conexão encerrada com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao encerrar conexão: " + e.getMessage());
		}
	}

	@Override
	public List<Produto> consultarProdutos() throws IOException {

		Produto product = new Produto();
		List<Produto> listaProdutos = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //Classe que passa o caminho da conexão
			Connection c = DriverManager.getConnection(db_url, db_user, db_password);
			System.out.println("Conectado ao BD");
			PreparedStatement ps = c.prepareStatement("SELECT * FROM produto");
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				product.setId(resultSet.getInt("id"));
				product.setCodigo(resultSet.getInt("codigo"));
				product.setNome(resultSet.getString("nome"));
				product.setCategoria(resultSet.getString("categoria"));
				product.setValor(resultSet.getFloat("valor"));
				product.setQuantidade(resultSet.getInt("quantidade"));
				listaProdutos.add(product);
				product = new Produto();
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listaProdutos;
	}

	@Override
	public void cadastrarProduto(Produto p) throws IOException {
	    String create = "INSERT INTO `produto`(`codigo`, `nome`, `categoria`, `valor`, `quantidade`) VALUES (?,?,?,?,?)";
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection c = DriverManager.getConnection(db_url, db_user, db_password);
	        System.out.println("Conectado ao BD");
	        PreparedStatement ps = c.prepareStatement(create); //PreparedStatement prepara a conexão
	        ps.setInt(1, p.getCodigo());
	        ps.setString(2, p.getNome());
	        ps.setString(3, p.getCategoria());
	        ps.setFloat(4, p.getValor());
	        ps.setInt(5, p.getQuantidade());
	        // executar a query
	        ps.executeUpdate();
	        // Encerrar conexão
	        c.close();
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void alterarProduto(Produto p) throws IOException {
	    String sql = "UPDATE produto SET nome=?, categoria=?, valor=?, quantidade=? WHERE codigo=?";
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection c = DriverManager.getConnection(db_url, db_user, db_password);
	        PreparedStatement ps = c.prepareStatement(sql); 
	        ps.setString(1, p.getNome());
	        ps.setString(2, p.getCategoria());
	        ps.setFloat(3, p.getValor());
	        ps.setInt(4, p.getQuantidade());
	         ps.setInt(5, p.getCodigo());
	        ps.executeUpdate();
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	
	@Override
	public void deletarProduto(Produto p) throws IOException {
	    String sql = "DELETE FROM produto WHERE id =?";
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection c = DriverManager.getConnection(db_url, db_user, db_password);
	        PreparedStatement ps = c.prepareStatement(sql); 
	        ps.setInt(1, p.getId());
	        ps.executeUpdate();
	        c.close();
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

}
