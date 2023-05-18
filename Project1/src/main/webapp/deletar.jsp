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
<link rel="stylesheet" type="text/css" href="styleDeletar.css">
</head>
<body class="page">
	<table>
		<caption>
				<h1 id="title">Lista de Produtos Cadastrados </h1>
			</caption>
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
        	
                <td class="textfield"><%=produto.get(i).getCodigo() %></td>
                <td class="textfield"><%=produto.get(i).getNome() %></td>
                <td class="textfield"><%=produto.get(i).getCategoria() %></td>
                <td class="textfield"><%=produto.get(i).getValor() %></td>
                <td class="textfield"><%=produto.get(i).getQuantidade() %></td>
                <td>
                    <!-- Botão de exclusão -->
                    <form method="POST" action="delete">
                        <input type="hidden" name="id" value="<%=produto.get(i).getId() %>">
                        <input class="textfield vermelho" type="submit" value="Excluir">
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