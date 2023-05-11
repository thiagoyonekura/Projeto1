package com.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Models.Produto;
import com.Models.*;

import com.Repository.BancoDados;



@WebServlet(urlPatterns = { "/inserir", "/home", "/cadastrar", "/inserir"})
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BancoDados bd = new BancoDados("jdbc:mysql://localhost:3306/projeto1", "root", "");

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();
		if (method.equals("POST")) {
			System.out.println("POST");
			doPost(request, response);
		} else if (method.equals("GET")) {
			System.out.println("GET");
			doGet(request, response);
		}
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/home")) {
			response.sendRedirect("index.html");
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String path = request.getServletPath();

		if (path.equals("/cadastrar")) {
			response.sendRedirect("cadastrar.html");
		}

		if (path.equals("/inserir")) {
			String codigo = request.getParameter("codigo");
			String nome = request.getParameter("nome");
			String categoria = request.getParameter("categoria");
			String valor = request.getParameter("valor");
			String quantidade = request.getParameter("quantidade");
			Produto p = new Produto(codigo, nome, categoria, valor, quantidade);
			bd.cadastrarProduto(p);
			request.setAttribute("listaPessoas", listarProdutos());
			RequestDispatcher rd = request.getRequestDispatcher("produtos.jsp");
			rd.forward(request, response);
		}
		
		if (path.equals("/listar")) {
			request.setAttribute("listaPessoas", listarProdutos());
			RequestDispatcher rd = request.getRequestDispatcher("listarProdutos.jsp");
			rd.forward(request, response);
		}

    }
    
    protected List<Produto>listarProdutos() throws IOException {
		List<Produto> listaProdutos = new ArrayList<>();
		listaProdutos = bd.consultarProdutos();
		return listaProdutos;
	}

}
