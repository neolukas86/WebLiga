<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<script type="text/javascript" src="<s:url value="/javascript/dialogConfirmar.js" />" ></script>
    
	<s:form id="formulario" action="EliminarTorneo">
		<s:hidden name="id" value="%{torneo.id}" />
	</s:form> 
	    	
	<sj:submit openDialog="mybuttondialog" button="true" indicator="indicator" key="eliminar" 
	cssStyle="margin-left:auto; margin-right:auto; display:block; font-size:15px;"/>
	
	<sj:dialog
        id="mybuttondialog"
        buttons="{
                '%{getText('aceptar')}':function() { okButton(); },
                '%{getText('cancelar')}':function() { cancelButton(); }
                }"
        autoOpen="false"
        modal="true"
        title="%{getText('torneo.eliminar.registro')}"
    >
     Seguro que quieres eliminar el torneo?
    </sj:dialog>