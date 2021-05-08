
function validaSenha(){
	var senha1 = document.getElementById('cadastrarUsuarioForm:senha').value;
	var senha2 = document.getElementById('cadastrarUsuarioForm:senha2').value;
	if(senha1 != senha2){
		alert("Erro na confirmação da senha. Valores não são iguais.");
	}
}

function verifica(){
	senha = document.getElementById("cadastrarUsuarioForm:senha").value;
	forca = 0;
	mostra = document.getElementById("mostra");
	if((senha.length >= 4) && (senha.length <= 7)){
		forca += 10;
	}else if(senha.length>7){
		forca += 25;
	}
	if(senha.match(/[a-z]+/)){
		forca += 10;
	}
	if(senha.match(/[A-Z]+/)){
		forca += 20;
	}
	if(senha.match(/[0-9]+/)){
		forca += 20;
	}
	if(senha.match(/[@!#$%^&*()/\\]+/)){
		forca += 20;
	}
	
	return mostra_res();
}

function mostra_res(){
	if(forca < 30){
		mostra.innerHTML = '<tr><td bgcolor="red" width="'+forca+'"></td><td>Fraca </td></tr>';
	}else if((forca >= 30) && (forca < 60)){
		mostra.innerHTML = '<tr><td bgcolor="yellow" width="'+forca+'"></td><td>Boa </td></tr>';;
	}else if((forca >= 60) && (forca < 85)){
		mostra.innerHTML = '<tr><td bgcolor="blue" width="'+forca+'"></td><td>Forte </td></tr>';
	}else{
		mostra.innerHTML = '<tr><td bgcolor="green" width="'+forca+'"></td><td>Excelente </td></tr>';
	}
}
	


//######### MODAL PREDIO ###########

$(document).ready( function(){
$(".chamarModalPredioEditar").click( function(){
$("#divModalPredioEditar").modal("show");
});  
})

$(document).ready( function(){
$(".chamarModalPredioExcluir").click( function(){
$("#divModalPredioExcluir").modal("show");
});  
})

//######### MODAL TIPO SERVICO ###########

$(document).ready( function(){
$(".chamarModalTipoServicoEditar").click( function(){
$("#divModalTipoServicoEditar").modal("show");
});  
})

$(document).ready( function(){
$(".chamarModalTipoServicoExcluir").click( function(){
$("#divModalTipoServicoExcluir").modal("show");
});  
})

//######### MODAL SETOR ###########

$(document).ready( function(){
$(".chamarModalSetorEditar").click( function(){
$("#divModalSetorEditar").modal("show");
});  
})

$(document).ready( function(){
$(".chamarModalSetorExcluir").click( function(){
$("#divModalSetorExcluir").modal("show");
});  
})

//######### MODAL PESSOA ###########

$(document).ready( function(){
$(".chamarModalPessoaEditar").click( function(){
$("#divModalPessoaEditar").modal("show");
});  
})

$(document).ready( function(){
$(".chamarModalPessoaExcluir").click( function(){
$("#divModalPessoaExcluir").modal("show");
});  
})