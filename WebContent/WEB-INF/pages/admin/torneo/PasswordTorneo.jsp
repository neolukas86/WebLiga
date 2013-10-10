<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript" src="<s:url value="/javascript/mostrarOcultar.js" />" ></script>
 
 			<div class="form">
	    	<fieldset>	
			<legend><s:text name="seguridad" /></legend>
			<s:form name="formulario2" action="ConfirmacionRegistroTorneo" method="post" cssClass="form">
				<s:hidden name="id" value="%{id}" />
				<fieldset>
				<legend><s:text name="confirmacion.registro"/></legend>
				<s:select name="regRequest" key="confirmacion.registro" list="listaBoolean" value="%{regRequest}"/>	
				</fieldset>
				<sj:submit button="true" indicator="indicator" key="enviar" 
   				cssClass="centrado" cssStyle="font-size:15px;"/>
			</s:form>
			<s:form name="formulario" action="PasswordTorneo" method="post" cssClass="form">
	    		<s:hidden name="id" value="%{id}" />
				<fieldset>
				<legend><s:text name="password"/></legend>
				<table>
				<tr>
				<td><s:select key="password.protected" name="passwordProtected" list="listaBoolean" 
					onchange="mostrar_ocultar_elemento_boolean(this,'joinPassword','pw')" value="%{passwordProtected}"/></td>
				</tr><tr>
		    	<td><s:password id="joinPassword" name="joinPassword" key="password" size="20" maxlength="20" 
		    		disabled="%{!passwordProtected}" /></td>
		    	<td><s:password id="pw" name="pw" key="password.repetir" size="20" maxlength="20" 
	 				disabled="%{!passwordProtected}" /></td>
	 			</tr>
				</table>	 				
				</fieldset>
   				<sj:submit button="true" indicator="indicator" key="enviar" 
   					cssClass="centrado" cssStyle="font-size:15px;"/>
	    	</s:form>
	    	
	    	<s:if test="estado == 0 || estado == 1">
	    		<s:if test="estado == 0">
	    			<s:form name="formulario3" action="AbrirRegistrosTorneo" method="post" cssClass="form">
						<s:hidden name="id" value="%{id}" />
						<fieldset>
						<legend><s:text name="abrir.registro"/></legend>	
						
						<sj:submit button="true" indicator="indicator" key="abrir" 
		   				cssClass="centrado" cssStyle="font-size:15px;"/>
		   				</fieldset>
					</s:form>
	    		</s:if>
	    		<s:else>
	    			<s:form name="formulario4" action="CerrarRegistrosTorneo" method="post" cssClass="form">
						<s:hidden name="id" value="%{id}" />
						<fieldset>
						<legend><s:text name="cerrar.registro"/></legend>	
						
						<sj:submit button="true" indicator="indicator" key="cerrar" 
		   				cssClass="centrado" cssStyle="font-size:15px;"/>
		   				</fieldset>
					</s:form>
	    		</s:else>
	    	</s:if>
	    	</fieldset>
	    	</div>
