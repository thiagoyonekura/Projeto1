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
</head>
<body>
	<h1>Lista de Produtos Cadastrados</h1>
	<table>
		<thead>
    <tr>
    	
        <th>codigo</th>
        <th>nome</th>
        <th>categoria</th>
        <th>valor</th>
        <th>quantidade</th>
        <th>Ações</th> 
    </tr>
</thead>

<tbody>
    <%for(int i=0; i<produto.size(); i++){ %>
        <tr>
        	
            <td><%=produto.get(i).getCodigo() %></td>
            <td><%=produto.get(i).getNome() %></td>
            <td><%=produto.get(i).getCategoria() %></td>
            <td><%=produto.get(i).getValor() %></td>
            <td><%=produto.get(i).getQuantidade() %></td>
            <td>
                
                <form method="POST" action="update">
                    <input type="hidden" name="id" value="<%=produto.get(i).getId() %>">
                    <input type="text" name="codigo" value="<%=produto.get(i).getCodigo() %>">
                    <input type="text" name="nome" value="<%=produto.get(i).getNome() %>">
                    <input type="text" name="categoria" value="<%=produto.get(i).getCategoria() %>">
                    <input type="text" name="valor" value="<%=produto.get(i).getValor() %>">
                    <input type="text" name="quantidade" value="<%=produto.get(i).getQuantidade() %>">
                    <input type="submit" value="Alterar">
                </form>
            </td>
        </tr>
    <%} %>
</tbody>
	</table>
	<p>
	<form method="get" action="home" class="inline">
		<input type="hidden" name="extra_submit_param"
			value="extra_submit_value">
		<button type="submit" name="submit_param" value="submit_value"
			class="link-button">Voltar ao home</button>
	</form>
</body>
</html>