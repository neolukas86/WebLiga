<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="#request.locale.language.equals('es')">
	<div class="noticia">
		<h1>Da tus primeros pasos en WebLiga</h1>
		<img src="<s:url value="/images/template/bebeRascandose.jpg" />" alt="picture" width="675" height="190" 
			style="border: 5px solid #BAD340; margin-bottom:15px; margin-top:5px;"/>
		<p><strong>Da tus primeros pasos.</strong><br /></p>
		Si eres nuevo en WebLiga y no sabes por donde empezar dejanos ayudarte a encontrar tu lugar.<br><br>
		Lo primero que debes saber es que los torneos pueden ser para dos tipos de participantes: el primero es el individual,
		 es decir, el de usuario, y el segundo es el de equipos, los equipos estarán formados por varios usuarios.<br>
		Si quieres unirte a un equipo puedes buscar uno en el <s:a action="GoSearchTeam">Buscador de Equipos</s:a>, o si 
		lo prefieres puedes <s:a action="GoCrearEquipo">crear</s:a> uno tú mismo.<br><br>
		Todos los torneos forman parte de una comunidad, por lo que ya sea un equipo o un usuario para participar en un torneo
		 previamente debe pertenecer a su comunidad.<br>
		Para unirte a una comunidad puedes buscar una en el <s:a action="GoSearchComunidad">Buscador de Comunidades</s:a>, o si
		 lo prefieres <s:a action="GoCrearComunidad">crear</s:a> una por ti mismo.<br><br>
		Los torneos también se pueden encontrar en el <s:a action="GoSearchTorneo">Buscador de Torneos</s:a>, pero para unirte
		 a ellos, como ya hemos dicho, debes estar registrado en la comunidad a la que pertenece, también puedes encontrar los que pertenecen a la comunidad
		 en la pestaña 'Torneos' dentro del menú de la comunidad, o si lo prefieres, siempre que tengas permiso, crear uno tu mismo en 
		 la pestaña 'Registrar Torneo'.<br><br>
		 Tanto en la parte superior de la página como en el menú lateral izquierdo puedes acceder a los equipos, los torneos y las comunidades
		  de las que formas parte.<br><br>
		 Tanto en 'Mis Equipos' como en la página principal del equipo, se mostrará, si posee el suficiente rango en el equipo, una imagen
		 como esta: <img src="<s:url value="/images/template/cambiarPerfiAEquipo.png" />"/> que nos sirve para cambiar al perfil del equipo.<br><br>
		 En caso de estar usando el perfil de equipo tendremos la opción de pasar a usar el perfil de usuario, nos aparecerá esta otra imagen: 
		 <img src="<s:url value="/images/template/cambiarPerfiAUsuario.png" />"/> 
		 tanto en la página principal del equipo como en la parte superior de la página web. <br><br>
		 <s:a action="VolverIndex" cssStyle="font:bold 14px Arial, Helvetica, sans-serif; color:black">« <s:text name="volver"/></s:a>
	</div>
</s:if>
	
<s:if test="#request.locale.language.equals('en')">
	<div class="noticia">
		<h1>Take your first steps in WebLiga</h1>
		<img src="<s:url value="/images/template/bebeRascandose.jpg" />" alt="picture" width="675" height="190" 
			style="border: 5px solid #BAD340; margin-bottom:15px; margin-top:5px;"/>
		<p><strong>Take your first steps.</strong><br /></p>
		If you are new in WebLiga and you don't know how begin, let us help you.<br><br>
		First of all you must know is tournaments can be for two kinds of participants: Individual, ie user, or teams. The teams are be formed by
		 multiple users.<br>
		If you want to join a team you can search one at the <s:a action="GoSearchTeam">Team Search</s:a>, or you can 
		<s:a action="GoCrearEquipo">create</s:a> one yourself.<br><br>
		All the tournaments are part of a community, so either a team or a user to participate in a tournament previously must be a member of his community.<br>
		For join a community you can search one at the <s:a action="GoSearchComunidad">Community Search</s:a>, or you can
		<s:a action="GoCrearComunidad">create</s:a> one yourself.<br><br>
		The tournaments can be found at the <s:a action="GoSearchTorneo">Tournament Search</s:a>, but to join
		to them, as already said, you must be registered in the community to which tournament belongs, you can also find those belonging to the community in
		 the tab 'Tournaments' inside the community menu, or if you prefer, if you have permission, create one yourself in the tab 'Register Tournament'.<br><br> 
		Both at the top of the page and in the left menu you can access the teams, tournaments and communities of which you are a part.<br><br>
		Both in 'My Teams' as in the home page team, will show, if you have enough range on the team, an image like this: 
		<img src="<s:url value="/images/template/cambiarPerfiAEquipo.png" />"/> for change to the profile of the team.<br><br>
		If you are using the team profile you can change to the user profile, this image will appear: 
		<img src="<s:url value="/images/template/cambiarPerfiAUsuario.png" />"/> both in the home page team as on top of the web page.<br><br> 
		 <s:a action="VolverIndex" cssStyle="font:bold 14px Arial, Helvetica, sans-serif; color:black">« <s:text name="volver"/></s:a>
	</div>
</s:if>

<s:if test="#request.locale.language.equals('ca')">
	<div class="noticia">
		<h1>Connecta't a WebLiga</h1>
		<img src="<s:url value="/images/template/bebeRascandose.jpg" />" alt="picture" width="675" height="190" 
			style="border: 5px solid #BAD340; margin-bottom:15px; margin-top:5px;"/>
		<p><strong>Connecta't ja a WebLiga.</strong><br /></p>
		Si ets nou en WebLiga i no saps per on començar deixa'ns ajudar-te a trobar el teu lloc.<br><br>
		El primer que has de saber és que els tornejos poden ser per dos tipus de participants: el primer és l'individual,
		és a dir, el de usuari, i el segon és el d'equips, els equips estaran formats per diversos usuaris.<br>
		Si vols unir-te a un equip pots buscar un al <s:a action="GoSearchTeam">Cercador d'Equips</s:a>, o si
		ho prefereixes pots <s:a action="GoCrearEquipo">crear</s:a> un tu mateix.<br><br>
		Tots els tornejos formen part d'una comunitat, de manera que ja sigui un equip o un usuari per participar en un torneig
		prèviament ha de pertànyer a la seva comunitat.
		Per unir-te a una comunitat pots buscar una al <s:a action="GoSearchComunidad">Cercador de Comunitats</s:a>, o si
		ho prefereixes <s:a action="GoCrearComunidad">crear</s:a> una per tu mateix.<br><br>
		Els tornejos també es poden trobar en el <s:a action="GoSearchTorneo">Cercador de torneigs</s:a>, però per unir-te
		a ells, com ja hem dit, has d'estar registrat a la comunitat a la qual pertany, també pots trobar els que pertanyen a la comunitat
		a la pestanya 'Tornejos' dins el menú de la comunitat, o si ho prefereixes, sempre que tinguis els permisos, crear-ne un tu mateix en
		la pestanya 'Registrar Torneig'.<br><br>
		Tant en la part superior de la pàgina com al menú lateral esquerre pots accedir als equips, els tornejos i les comunitats
		de les que formes part.<br><br>
		Tant a 'Meus Equips' com a la pàgina principal de l'equip, es mostrarà, si té prou rang en l'equip, una imatge
		com aquesta: <img src="<s:url value="/images/template/cambiarPerfiAEquipo.png" />"/> que ens serveix per canviar el perfil de l'equip.<br><br>
		En cas d'estar usant el perfil d'equip tindrem l'opció de passar a fer servir el perfil d'usuari, ens apareixerà aquesta altra imatge: 
		<img src="<s:url value="/images/template/cambiarPerfiAUsuario.png" />"/> tant a la pàgina principal de l'equip com en la part superior de la pàgina web.
		<br><br>
		 <s:a action="VolverIndex" cssStyle="font:bold 14px Arial, Helvetica, sans-serif; color:black">« <s:text name="volver"/></s:a>
	</div>
</s:if>