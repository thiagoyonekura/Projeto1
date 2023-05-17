<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.Models.Produto" %>
    <%@page import="java.util.ArrayList" %>
    
    <%
    	@ SuppressWarnings("unchecked")
    	ArrayList<Produto> produto = (ArrayList<Produto>) request.getAttribute("listaProdutos");
    %>
    <!DOCTYPE html>
    <html>
    
    <head>
        <meta charset="utf-8">
        <title>Listagem de Produtos</title>
        <link rel="stylesheet" type="text/css" href="styleAlterar.css">
    </head>
    
    <body class="page">
    
    
        
        <table>
            <caption> <h1 id="title">Lista de Produtos Cadastrados </h1> </caption>
            <thead>
                <tr>
    
                    <th>Codigo</th>
                    <th>Nome</th>
                    <th>Categoria</th>
                    <th>Valor</th>
                    <th>Quantidade</th>
                    <th>Ações</th>
                </tr>
            </thead>
    
            <tbody>
                <%for(int i=0; i<produto.size(); i++){ %>
                    
                    <tr >
    
                        <form method="POST" action="update">
    
                                <td><input class="textfield" type="text" name="codigo" value="<%=produto.get(i).getCodigo() %>"></td>
                                <td><input class="textfield" type="text" name="nome" value="<%=produto.get(i).getNome() %>"></td>
                                <td><input class="textfield" type="text" name="categoria" value="<%=produto.get(i).getCategoria() %>"></td>
                                <td><input class="textfield" type="text" name="valor" value="<%=produto.get(i).getValor() %>"></td>
                                <td><input class="textfield" type="text" name="quantidade" value="<%=produto.get(i).getQuantidade() %>"></td>
                                <td><input class="textfield" type="submit" value="Alterar" style="cursor: pointer;">
                            
                        </form>
    
                    </tr>
                
                    <%} %>
            </tbody>
        </table>
        <p>
        <form method="get" action="home" class="inline">
            <input type="hidden" name="extra_submit_param" value="extra_submit_value">
            <button type="submit" name="submit_param" value="submit_value" class="link-button" style="cursor: pointer;">Voltar ao home</button>
        </form>
    </body>
    
    </html>