<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../../common/heder.jsp" %>
<form:form method="post" id="form" modelAttribute="projeto">
	<div class="mt-3 card container">
		<h5 class="mt-2 text-primary" id="titulo"></h5>
		<span style="border-top: 1px solid; opacity: .25;"></span>
		<form:hidden path="id" />
		<div class="mt-3 row">
			<div class="col-7">
				<label for="nome" class="form-label">Nome</label>
				<form:input path="nome" class="form-control" maxlength="100" required="required" />
			</div>
			<div class="col">
				<label for="orcamento" class="form-label">Orçamento</label>
				<div class="input-group mb-3">
					<span class="input-group-text">R$</span>
					<form:input type="number" min="0.00" step="0.01" path="orcamento" class="form-control currency" />
				</div>
				
			</div>
			<div class="col">
				<label for="risco" class="form-label">Risco</label>
				<form:select path="risco" items="${riscosOpt}" class="form-select">
				</form:select>
			</div>
		</div>
		<div class="mt-3 row">
			<div class="col-6">
				<label for="gerente" class="form-label">Gerente</label>
				<form:select path="gerente" class="form-select" required="required">
					<option value="">Selecione...</option>
					<form:options items="${gerentes}" itemLabel="nome"></form:options>
				</form:select>
			</div>
			<div class="col-6">
				<label for="gerente" class="form-label">Membros</label>
				<form:select path="membros" class="form-select">
					<form:options items="${funcs}" itemLabel="nome"></form:options>
				</form:select>
			</div>
		</div>
		<div class="mt-3 row">
			<div class="col-3">
				<label for="dataInicio" class="form-label">Data início</label>
				<form:input type="date" path="dataInicio" class="form-control" />
			</div>
			<div class="col-3">
				<label for="dataPrevisaoFim" class="form-label">Data previsão de término</label>
				<form:input type="date" path="dataPrevisaoFim" class="form-control"/>
			</div>
			<div class="col-3">
				<label for="dataFim" class="form-label">Data fim</label>
				<form:input type="date" path="dataFim" class="form-control"/>
			</div>
			<div class="col-3">
				<label for="status" class="form-label">Status</label>
				<form:select path="status" items="${statusOpt}" class="form-select">
				</form:select>
			</div>
		</div>
		<div class="mt-3 row">
			<div class="col-12">
				<label for="descricao" class="form-label">Descrição</label>
				<form:textarea path="descricao" class="form-control" rows="7" maxlength="5000" />
			</div>
		</div>
		<div class="mt-3 mb-3 row">
			<div class="col-12">
				<c:choose>
					<c:when test="${consultar}">
						<a class="btn btn-primary float-end" href="/" role="button">Voltar</a>
					</c:when>
					<c:otherwise>
						<a class="btn btn-danger float-end" href="/" role="button">Cancelar</a>
						<button type="submit" class="btn btn-primary float-end me-4">Salvar</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</form:form>
<script type="text/javascript">
	const isConsultar = '${consultar}';
	const form = document.getElementById("form");
	
	if(isConsultar) {
		document.getElementById('titulo').innerHTML = 'Consultar';
		
		var elements = form.elements;
		for (var i = 0, len = elements.length; i < len; ++i) {
		    elements[i].readOnly = true;
		    elements[i].disabled = true;
		}
	}else {
		var id = document.getElementById('id').value;
		
		document.getElementById('titulo').innerHTML = (id == '' ? 'Cadastrar' : 'Editar');
		form.action = 'salvar';
	}
</script>
<%@ include file="../../common/footer.jsp" %>