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



@WebServlet(urlPatterns = {"/home", "/cadastrar", "/inserir", "/listar", "/alterar", "/deletar", "/delete", "/update"})
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
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nome = request.getParameter("nome");
			String categoria = request.getParameter("categoria");
			float valor = Float.parseFloat(request.getParameter("valor"));
			int quantidade = Integer.parseInt(request.getParameter("quantidade"));
			Produto p = new Produto(codigo, nome, categoria, valor, quantidade);
			bd.cadastrarProduto(p);
			request.setAttribute("listaProdutos", listarProdutos());
			RequestDispatcher rd = request.getRequestDispatcher("listarProdutos.jsp");
			rd.forward(request, response);
			}
		
		if (path.equals("/listar")) {
			request.setAttribute("listaProdutos", listarProdutos());
			RequestDispatcher rd = request.getRequestDispatcher("listarProdutos.jsp");
			rd.forward(request, response);
		}
		
		if (path.equals("/alterar")) {
			request.setAttribute("listaProdutos", listarProdutos());
			RequestDispatcher rd = request.getRequestDispatcher("alterar.jsp");
			rd.forward(request, response);
		}
		
		if (path.equals("/update")) {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nome = request.getParameter("nome");
			String categoria = request.getParameter("categoria");
			float valor = Float.parseFloat(request.getParameter("valor"));
			int quantidade = Integer.parseInt(request.getParameter("quantidade"));
			Produto p = new Produto(codigo, nome, categoria, valor, quantidade);
			bd.alterarProduto(p);
			request.setAttribute("listaProdutos", listarProdutos());
			RequestDispatcher rd = request.getRequestDispatcher("listarProdutos.jsp");
			rd.forward(request, response);
			}
		
		if (path.equals("/deletar")) {
			request.setAttribute("listaProdutos", listarProdutos());
			RequestDispatcher rd = request.getRequestDispatcher("deletar.jsp");
			rd.forward(request, response);
		}
		
		if (path.equals("/delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Produto p = new Produto(id);
				bd.deletarProduto(p);
			
		    request.setAttribute("listaProdutos", listarProdutos());
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
