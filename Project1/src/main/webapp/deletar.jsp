<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Deletar Produto</title>
</head>
<body>
	<h1>Deletar Produto</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Código</th>
				<th>Nome</th>
				<th>Categoria</th>
				<th>Valor</th>
				<th>Quantidade</th>
				<th>Ação</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="produto" items="${listaProdutos}">
				<tr>
					<td>${produto.id}</td>
					<td>${produto.codigo}</td>
					<td>${produto.nome}</td>
					<td>${produto.categoria}</td>
					<td>${produto.valor}</td>
					<td>${produto.quantidade}</td>
					<td>
						<form method="post" action="delete">
							<input type="hidden" name="id" value="${produto.id}">
							<button type="submit">Deletar</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
<a href="home">Voltar ao Home</a>
</html>