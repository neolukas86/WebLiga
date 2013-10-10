<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<s:url value="/javascript/dialogConfirmar.js" />" ></script>
<script type="text/javascript">
$('#mytabsComunidadAdmin').tabs({
    load: function(event, ui) {
        $(ui.panel).delegate('span a, th a', 'click', function(event) {
            $(ui.panel).load(this.href);
            event.preventDefault();
        });
    }
});
</script>
			
	    	<h2><s:text name="palabra.equipos" /></h2>

			<s:url var="rutaImgKick" value="/images/template/kick-icon.png" />
			<s:url var="rutaImgBan" value="/images/template/banned-icon.png" />
			
			<display:table name="equipoList" id="row" class="table" pagesize="10" 
					requestURI="GoEquiposComunidad">
<%-- 			    <display:column property="id" titleKey="id" /> --%>
		     	<display:column titleKey="nombre" >
					<s:a action="VerEquipo"><s:param name="id" >${row.id}</s:param>${row.nombre}</s:a>			
		    	 </display:column>
		    	 
		    	<display:column >
			    <s:set var="equipoId">${row.id}</s:set>
			    
				<s:form action="ExpulsarEquipoComunidad">
					<s:hidden name="idequipo" value="%{equipoId}" />
					<s:hidden name="id" value="%{id}" />
					<sj:submit openDialog="mybuttondialogExpulsar" type="image" src="%{rutaImgKick}" 
								indicator="indicator" title="%{getText('expulsar')}" cssStyle="float:left"
								onClick="FuncionSubmit(this.form)"/>
				</s:form>
				
				<s:form action="BanearEquipoComunidad">
					<s:hidden name="idequipo" value="%{equipoId}" />
					<s:hidden name="id" value="%{id}" />
					<sj:submit openDialog="mybuttondialogBanear" type="image" src="%{rutaImgBan}" 
								indicator="indicator" title="%{getText('banear')}" cssStyle="float:left"
								onClick="FuncionSubmit(this.form)"/>
				</s:form>
			    </display:column>
			    
			    	
		</display:table>
		
	<sj:dialog
        id="mybuttondialogExpulsar"
        buttons="{
                'Aceptar':function() { okButtonExpulsar(); },
                'Cancelar':function() { cancelButtonExpulsar(); }
                }"
        autoOpen="false"
        modal="true"
        title="Expulsar Usuario"
    >
     Tenga en cuenta que si expulsa al equipo de la comunidad también lo hará de los torneos que pertenezcan a la misma.
    </sj:dialog>
    
    <sj:dialog
        id="mybuttondialogBanear"
        buttons="{
                'Aceptar':function() { okButtonBanear(); },
                'Cancelar':function() { cancelButtonBanear(); }
                }"
        autoOpen="false"
        modal="true"
        title="Banear Usuario"
    >
     Si banea a un equipo significa que lo expulsará de forma permanente, no tendrá opción a volver a registrarse en la comunidad.
     
     Además debe tener en cuenta que si expulsa al equipo de la comunidad también lo hará de los torneos que pertenezcan a la misma.
    </sj:dialog>
