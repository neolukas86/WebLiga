
function cambiarActividad(){
	var deporte = document.getElementById('deporte');
	var carta = document.getElementById('carta');
	var juego = document.getElementById('juego');
	var juegodemesa = document.getElementById('juegodemesa');
	
//	alert('cambiarActividad() ha sido llamada!');
	
	if(deporte != null && deporte[0].selected == false){
		bloquearActividad('carta');
		bloquearActividad('juego');
		bloquearActividad('juegodemesa');
		bloquear('plataforma');
	}
	else if(carta != null && carta[0].selected == false){
			bloquearActividad('deporte');
			bloquearActividad('juego');
			bloquearActividad('juegodemesa');
			bloquear('plataforma');
		}

		else if(juego != null && juego[0].selected == false){
				bloquearActividad('carta');
				bloquearActividad('deporte');
				bloquearActividad('juegodemesa');
				
				desbloquear('plataforma');
			}

			else if(juegodemesa != null && juegodemesa[0].selected == false){
					bloquearActividad('carta');
					bloquearActividad('juego');
					bloquearActividad('deporte');
					bloquear('plataforma');
				}
				else {
					desbloquear('juegodemesa');
					desbloquear('carta');
					desbloquear('juego');
					desbloquear('deporte');
					
					desbloquear('plataforma');
				}
}

function bloquearActividad(str){
	var var1 = document.getElementById(str);

	if(var1 != null){
		var1.options[0].selected = true;
		var1.disabled=true;
	}
}

function desbloquear(str){
	var var1 = document.getElementById(str);
	
	if(var1 != null){
		var1.disabled=false;	
	}
}

function bloquear(str){
	var var1 = document.getElementById(str);
	
	if(var1 != null){
		var1.disabled=true;	
	}
}

