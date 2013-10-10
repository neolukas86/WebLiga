<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<s:url value="/javascript/dialogConfirmar.js" />" ></script>
<script type="text/javascript">
$('#mytabsTorneoAdmin').tabs({
    load: function(event, ui) {
        $(ui.panel).delegate('span a, th a', 'click', function(event) {
            $(ui.panel).load(this.href);
            event.preventDefault();
        });
    }
});
</script>

			<s:set var="rangoCREADOR" value="@com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares@CREADOR" />
			<s:set var="rangoADMIN" value="@com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares@ADMIN" />
			<s:set var="rangoMOD" value="@com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares@MOD" />
			<s:set var="rangoNORMALUSER" value="@com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares@NORMALUSER" />
			
			<s:if test="porEquipos && (estado < 2) &&
			roltorneo == 'creador' || roltorneo == 'admin' || roltorneo == 'mod'">
				<s:form name="formularioCrearEquipoInvitado" action="CrearInvitadoTeam" method="post" cssClass="form">
					<fieldset>
					<legend><s:text name="crear.equipo.invitado.title"/></legend>
					<s:hidden name="id" value="%{torneo.id}"/>
					<s:textfield name="cadena" key="nombre" cssStyle="float:left"/>
					<sj:submit  button="true" key="crear.invitado" cssStyle="float:left"/>
					</fieldset>
				</s:form>
			</s:if>
			
			<h2><s:text name="palabra.equipos" /></h2>
			<s:set name="idequipo" value="#session.id" />
			<c:set var="coleccion" value="${torneo.equipoTorneos}" />
			<s:url var="rutaImgEdit" value="/images/template/edit-icon.png" />
			<s:url var="rutaImgKick" value="/images/template/kick-icon.png" />
			<s:url var="rutaImgBan" value="/images/template/banned-icon.png" />
			
			<display:table name="equipoList" id="rowEq" class="table" pagesize="10" 
					requestURI="GoEquiposTorneo">
<%-- 			    <display:column property="id" titleKey="id" /> --%>
			    <display:column titleKey="nombre" >
			    	<c:if test="${rowEq.invitado == false}">
			     		<s:a action="VerEquipo"><s:param name="id" >${rowEq.id}</s:param>${rowEq.nombre}</s:a>
					</c:if>
					<c:if test="${rowEq.invitado == true}">
						<div style="color:grey"><c:out value="${rowEq.nombre}"/></div>
					</c:if>
		     	</display:column>
			    		    
			    <c:set var="coleccionAct" value="${rowEq.actividades}" />
		    
		    	<display:column titleKey="palabra.actividades" style="width:160px">
		    		<c:forEach  var="var" items="${coleccionAct}" end="4">
		    			<s:set name="rutaIMG">/images/iconos/actividades/25${var.rutaimagen}</s:set>
		    		
				    	<s:if test="#request.locale.language.equals('es')">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
						</s:if>
						<s:elseif test="#request.locale.language.equals('en')">
							<s:if test="nombre_EN!=null">
								<img src="<s:url value="%{rutaIMG}"/>" 
									title="<s:property value="nombre_EN"/>" width="25" height="25"/>
							</s:if>
							<s:else>
								<img src="<s:url value="%{rutaIMG}"/>" 
									title="<s:property value="nombre"/>" width="25" height="25"/>
							</s:else>
						</s:elseif>
						<s:elseif test="#request.locale.language.equals('ca')">
							<s:if test="nombre_CA!=null">
								<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
							</s:if>
							<s:else>
								<img src="<s:url value="%{rutaIMG}"/>" 
									title="<s:property value="nombre"/>" width="25" height="25"/>
							</s:else>
						</s:elseif>
			    	</c:forEach>
			    	<c:if test="${coleccionAct.size() > 5}">
			    		(<c:out value="${coleccionAct.size()}"/>)
			    	</c:if>
			    </display:column>
		    
			    <c:set var="coleccionPl" value="${rowEq.plataformas}" />
			    
			    <display:column titleKey="palabra.plataformas" style="width:160px">
			    	<c:forEach  var="var" items="${coleccionPl}" end="4">
			    		<s:set name="rutaIMG">/images/iconos/plataformas/25${var.rutaimagen}</s:set>
			    		
				    	<s:if test="#request.locale.language.equals('es')">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
						</s:if>
						<s:elseif test="#request.locale.language.equals('en')">
							<s:if test="nombre_EN!=null">
								<img src="<s:url value="%{rutaIMG}"/>" 
									title="<s:property value="nombre_EN"/>" width="25" height="25"/>
							</s:if>
							<s:else>
								<img src="<s:url value="%{rutaIMG}"/>" 
									title="<s:property value="nombre"/>" width="25" height="25"/>
							</s:else>
						</s:elseif>
						<s:elseif test="#request.locale.language.equals('ca')">
							<s:if test="nombre_CA!=null">
								<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
							</s:if>
							<s:else>
								<img src="<s:url value="%{rutaIMG}"/>" 
									title="<s:property value="nombre"/>" width="25" height="25"/>
							</s:else>
						</s:elseif>
			    	</c:forEach>
			    	<c:if test="${coleccionPl.size() > 5}">
			    		(<c:out value="${coleccionPl.size()}"/>)
			    	</c:if>		    
			    </display:column>
			    <display:column>    			    
				    <c:forEach  var="var" items="${coleccion}" >
				    	<c:if test="${var.id.parentEquipo.id == rowEq.id }">
				    		<s:set var="equipoId">${rowEq.id}</s:set>
	
					    	<s:if test="roltorneo == 'creador' || roltorneo == 'admin' ||
					    			roltorneo == 'mod'">
											
								<s:set var="idExpulsar">Expulsar${rowEq.id}</s:set>
								<s:set var="idBanear">Banear${rowEq.id}</s:set>
											
								<s:form id="%{idExpulsar}" name="%{idExpulsar}" action="ExpulsarEquipoTorneo">
									<s:hidden name="idequipo" value="%{equipoId}" />
									<s:hidden name="id" value="%{torneo.id}" />
									<sj:submit openDialog="mybuttondialogExpulsar" type="image" src="%{rutaImgKick}" 
										indicator="indicator" title="%{getText('expulsar')}" 
										cssStyle="float:left; margin-top:-8px; margin-left:5px"
										onClick="FuncionSubmit(this.form)"/>
								</s:form>
																				
								<s:form id="%{idBanear}" name="%{idBanear}" action="BanearEquipoTorneo">
									<s:hidden name="idequipo" value="%{equipoId}" />
									<s:hidden name="id" value="%{torneo.id}" />
									<sj:submit openDialog="mybuttondialogBanear" type="image" src="%{rutaImgBan}" 
										indicator="indicator" title="%{getText('banear')}" 
										cssStyle="float:left; margin-top:-8px; margin-left:5px"
										onClick="FuncionSubmit(this.form)"/>
								</s:form>										
					    	</s:if>		    			
					    </c:if>
				    </c:forEach>
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
        title="Expulsar Equipo"
    >
     Seguro de expulsar al equipo del torneo?
    </sj:dialog>
    
    <sj:dialog
        id="mybuttondialogBanear"
        buttons="{
                'Aceptar':function() { okButtonBanear(); },
                'Cancelar':function() { cancelButtonBanear(); }
                }"
        autoOpen="false"
        modal="true"
        title="Banear Equipo"
    >
     Seguro de banear al equipo del torneo?
    </sj:dialog>		