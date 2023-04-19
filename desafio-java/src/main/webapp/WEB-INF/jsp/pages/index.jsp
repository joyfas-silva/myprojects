<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "../common/heder.jsp" %>
<div class="mt-4 card container">
	<h5 class="mt-2 text-primary">Lista de projetos
		<button type="button" onclick="go('cadastro')" class="btn-icon text-success float-end" title="Adicionar">
	                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
					  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
					  <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"></path>
					</svg>
	              </button>
	</h5>
	<span style="border-top: 1px solid; opacity: .25;"></span>
	<table class="table table-hover" id="tblProjetos">
		<thead>
			<tr>
				<th scope="col" style="width: 40%">Nome</th>
				<th scope="col">Status</th>
				<th scope="col">Orçamento</th>
				<th scope="col">Risco</th>
				<th scope="col">Data Inicio</th>
				<th scope="col">Data Previsão</th>
				<th scope="col" class="text-center">Ação 
	              
              </th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${projetos}" var="projeto">
				<tr>
					<td>${projeto.nome}</td>
					<td>${projeto.statusDesc}</td>
					<td>
						<fmt:formatNumber type="CURRENCY" currencySymbol="R$ " value="${projeto.orcamento}"  />
					</td>
					<td>${projeto.riscoDesc}</td>
					<td>
						<fmt:formatDate value="${projeto.dataInicio}" pattern="dd/MM/yyyy" />
					</td>
					<td>
						<fmt:formatDate value="${projeto.dataPrevisaoFim}" pattern="dd/MM/yyyy" />
					</td>
					<td>
						<button type="button" onclick="go('consultar', ${projeto.id})" class="btn-icon text-secondary me-2 text-decoration-none" title="Consultar">
							<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
							  <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
							  <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
							</svg>
						</button>
						<button type="button" onclick="go('cadastro', ${projeto.id})" class="btn-icon text-primary me-2 text-decoration-none" title="Editar">
							<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
							  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
							  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
							</svg>
						</button>
						<button type="button" class="btn-icon text-danger text-decoration-none" title="Excluir" 
							data-bs-toggle="modal" data-bs-target="#confirmaExclusaoModal" data-bs-whatever="${projeto.id }">
							<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
								fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
								  <path
									d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z" />
							</svg>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<!-- Modal -->
<div class="modal fade" id="confirmaExclusaoModal" tabindex="-1" aria-labelledby="confirmaExclusaoModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5 text-danger" id="confirmaExclusaoModalLabel">Atenção</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Deseja realmente excluir o projeto?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="btnSim">Sim</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Não</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">

	const exclusaoModal = document.getElementById('confirmaExclusaoModal')
	exclusaoModal.addEventListener('show.bs.modal', event => {
	  const recipient = event.relatedTarget.getAttribute('data-bs-whatever');
	  const btnSim = exclusaoModal.querySelector('#btnSim');
	  btnSim.setAttribute("onclick", 'go("deletar",' + recipient +')');
	})

	function go(acao, id) {
		
		var form = document.createElement('form');
		form.style.display = 'none';
		form.method ='post';
		form.action = acao;
		
		if(id){
			var input = document.createElement('input');
			input.name= 'id';
			input.value = id;
			form.appendChild(input);
		}
		
		document.body.appendChild(form);
		form.submit();
	} 
</script>
<%@ include file = "../common/footer.jsp" %>