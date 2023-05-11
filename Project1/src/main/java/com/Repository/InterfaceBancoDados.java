package com.Repository;

import java.io.IOException;
import java.util.List;

import com.Models.*;

public interface InterfaceBancoDados {
	public void desconectar() throws IOException;
	public List<Produto> consultarProdutos() throws IOException;
	public void cadastrarProduto(Produto p);
}
