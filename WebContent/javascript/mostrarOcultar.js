
function mostrar_ocultar_elemento(elementos,selectSig){ // Funciona perfectamente
	var selectExtras = document.getElementById(selectSig);
	
	if(elementos.value == -1){ 
		mostrar_elemento(selectExtras);
	}
	else{
		ocultar_elemento(selectExtras);
	}
}

function enable_elemento(elementos,selectSig){ // Funciona perfectamente
	var selectExtras = document.getElementById(selectSig);
	
	if(elementos.value == -1){ 
		selectExtras.disabled=false;
	}
	else{
		selectExtras.disabled=true;
	}
}

function mostrar_ocultar_elemento_boolean(elementos,selectSig){ 
	var selectExtras = document.getElementById(selectSig);
	
	if(elementos.value.valueOf() == "true"){ 
		selectExtras.disabled = false;
	} 
	else{
		selectExtras.disabled = true;
	}
}

function mostrar_ocultar_elemento_boolean(elementos,selectSig,selectSig2){ 
	var selectExtras = document.getElementById(selectSig);
	var selectExtras2 = document.getElementById(selectSig2);
	
	if(elementos.value.valueOf() == "true"){ 
		selectExtras.disabled = false;
		selectExtras2.disabled = false;
	} 
	else{
		selectExtras.disabled = true;
		selectExtras2.disabled = true;
	}
}


function mostrar_elemento(sel){
	sel.disabled= false;
	sel.style.display="block";
}

function ocultar_elemento(sel){
	sel.disabled= true;
	sel.style.display="none";
}
