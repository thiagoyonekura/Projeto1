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
        <th>Ações</th> <!-- Nova coluna para o botão de exclusão -->
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
                <!-- Botão de exclusão -->
                <form method="POST" action="delete">
                    <input type="hidden" name="id" value="<%=produto.get(i).getId() %>">
                    <input type="submit" value="Excluir">
                </form>
            </td>
        </tr>
    <%} %>
</tbody>
	</table>
	<p>
	<a href="home">Sair</a>
</body>
</html>