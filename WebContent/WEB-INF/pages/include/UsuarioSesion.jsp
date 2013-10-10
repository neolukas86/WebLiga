<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="usuarioSesion">

<s:if test="#session.equipo != 0">
	<s:url var="rutaCambiarUsuario" value="/images/template/cambiarPerfiAUsuario.png" />
	
	<s:a action="TomarPerfilUsuario">
		<img src="<s:url value="/images/template/cambiarPerfiAUsuario.png" />" alt="picture" 
			title="<s:property value="%{getText('utilizar.perfil.usuario')}"/>" />
	</s:a>
	
	<s:text name="palabra.equipo" />:
	<s:a action="VerEquipo"><s:param name="id" value="#session.equipo"/><s:property value="#session.equipoNombre"/></s:a> 
</s:if>

<s:text name="palabra.usuario" />:
	<s:a action="GoModifyUser"><s:property value="#session.alias" /></s:a>


<s:a action="Logout"><s:text name="logout" /></s:a>


</div>