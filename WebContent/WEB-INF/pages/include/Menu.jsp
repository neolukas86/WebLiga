<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div  class="menu_general sombra rounded-corners">

	<div class="menu_general_header">
		<s:text name="menu.title" />
	</div>
	
	<div class="menu_general_body">
		<b><s:text name="menu.subtitle.info" /></b><br>
		<ol>
		<li><s:a action="VolverIndex"><s:text name="palabra.noticias" /></s:a></li>
		</ol><br>
		<b><s:text name="crear"/></b>
		<ol>
		<li><s:a action="GoCrearEquipo"><s:text name="palabra.equipos" /></s:a></li>
        <li><s:a action="GoCrearComunidad"><s:text name="palabra.comunidades" /></s:a></li>
		</ol><br>
		<b><s:text name="menu.subtitle.mis.dominios"/></b>
		<ol>
		<li><s:a action="GoModifyUser"><s:text name="mi.perfil"/></s:a></li>
		<li><s:a action="ListarTorneosPropios"><s:text name="palabra.torneos" /></s:a></li>
        <li><s:a action="ListarComunidadesPropias"><s:text name="palabra.comunidades" /></s:a></li>
        <li><s:a action="ListarEquiposPropios"><s:text name="palabra.equipos"/></s:a></li>		
		</ol><br>	
		<b><s:text name="menu.subtitle.busqueda" /></b><br>
		<ol>
		<li><s:a action="GoSearchTorneo"><s:text name="palabra.torneos" /></s:a></li>
		<li><s:a action="GoSearchComunidad" ><s:text name="palabra.comunidades" /></s:a></li>
		<li><s:a action="GoSearchTeam"><s:text name="palabra.equipos" /></s:a></li>
		<li><s:a action="GoSearchUser"><s:text name="jugadores" /></s:a></li>
		</ol><br>
		<b><s:text name="menu.subtitle.soporte" /></b><br />
		<ol>
		<li><s:a action="GoFAQ"><s:text name="menu.faq" /></s:a></li>
		<li><a href='mailto:<s:property value="%{getText('contacto.destino')}" />'><s:text name="contacto" /></a></li>
		</ol><br>
		<div class="clr"></div>
	</div>
</div>	