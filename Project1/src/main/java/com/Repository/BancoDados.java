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
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection(db_url, db_user, db_password);
			System.out.println("Conectado ao BD");
			PreparedStatement ps = c.prepareStatement("SELECT * FROM produto");
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				product.setId(resultSet.getInt("codigo"));
				product.setCodigo(resultSet.getString("codigo"));
				product.setNome(resultSet.getString("nome"));
				product.setCategoria(resultSet.getString("categoria"));
				product.setValor(resultSet.getString("valor"));
				product.setQuantidade(resultSet.getString("quantidade"));
				listaProdutos.add(product);
				product = new Produto();
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Falha ao conectar ao MySql" + e.getMessage());
		}
		return listaProdutos;
	}

	@Override
	public void cadastrarProduto(Produto p) {
		String create = "INSERT INTO `produto`(`codigo`, `nome`, `categoria`, `valor`, `quantidade`) VALUES (?,?,?,?,?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection(db_url, db_user, db_password);
			System.out.println("Conectado ao BD");
			PreparedStatement pst = c.prepareStatement(create);
			pst.setString(2, p.getCodigo());
			pst.setString(3, p.getNome());
			pst.setString(4, p.getCategoria());
			pst.setString(5, p.getValor());
			pst.setString(6, p.getQuantidade());
			// executar a query
			pst.executeUpdate();
			// Encerrar conexão
			c.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}


}
