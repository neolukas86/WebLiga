<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	
	
	<s:form name="formulario" action="ModificarSettingsEquipo" cssClass="form">
		<s:hidden name="id" value="%{id}" />
		<fieldset>
		<legend><s:text name="modify" /></legend>
		<fieldset>
		<legend><s:text name="palabra.actividades" /></legend>
		<fieldset>
	    <legend><s:text name="palabra.deportes" /></legend>
	     
		<sj:checkboxlist id="checkbuttonset" list="listaDeportes" name="deportes" value="defaultActividades"/>
	    
	    </fieldset>	
	    
	    <fieldset>
	    <legend><s:text name="palabra.videojuegos" /></legend>
	     
		<sj:checkboxlist id="checkbuttonset1" list="listaJuegos" name="juegos" value="defaultActividades"/>
		</fieldset>
		
		<fieldset>
	    <legend><s:text name="palabra.cartas" /></legend>
	     
		<sj:checkboxlist id="checkbuttonset2" list="listaCartas" name="cartas" value="defaultActividades"/>
		</fieldset>
		
		<fieldset>
	    <legend><s:text name="palabra.juegos.mesa" /></legend>
	     
		<sj:checkboxlist  id="checkbuttonset3" list="listaJuegosdemesa" name="juegosdemesa" value="defaultActividades"/>
		</fieldset>
		</fieldset>
		
		<fieldset>
		<legend><s:text name="palabra.plataformas" /></legend>
	    	
	    <sj:checkboxlist  id="checkbuttonset4" list="listaPlataformas" name="plataformasdejuegos" value="defaultPlataformas"/> 
	    
	    </fieldset>
		
		<sj:submit button="true" indicator="indicator" key="modify" 
	    	cssClass="centrado" cssStyle="font-size:15px;"/>
	    	
		</fieldset>	    	
	</s:form>
