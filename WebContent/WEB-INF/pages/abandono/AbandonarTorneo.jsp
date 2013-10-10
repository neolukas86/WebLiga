<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<script type="text/javascript" src="<s:url value="/javascript/dialogConfirmar.js" />" ></script>
    
    <s:form id="formulario" action="AbandonarTorneo">
    	<s:hidden name="id" value="%{id}" />
    </s:form>
    
    <sj:submit
    	openDialog="mybuttondialog"  
    	button="true"
    	key="abandonar"
    />
    
    <sj:dialog
        id="mybuttondialog"
        buttons="{
                'Aceptar':function() { okButton(); },
                'Cancelar':function() { cancelButton(); }
                }"
        autoOpen="false"
        modal="true"
        title="Abandonar Torneo"
    >
     Abandonar Torneo? Tenga en cuenta que si tiene partidos pendientes se le darán por perdidos.
    </sj:dialog>