package com.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Models.*;

import com.Repository.BancoDados;



@WebServlet(urlPatterns = { "/homePage", "/cadastroProdutos", "/listaProdutos", "/modificarProdutos"})
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
        
        // Redireciona para a página "homePage.html"
        response.sendRedirect("homePage.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Obtém o valor do botão selecionado pelo usuário
        String button = request.getParameter("button");
        

    }

}
