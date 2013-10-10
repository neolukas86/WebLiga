<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$('#mytabsTorneo').tabs({
    load: function(event, ui) {
        $(ui.panel).delegate('span a, th a', 'click', function(event) {
            $(ui.panel).load(this.href);
            event.preventDefault();
        });
    }
});
</script>

<div class="solo_pagelinks">
			<s:if test="partidoPaginatedList!=null">
			
				<h2><s:text name="palabra.jornada"/> <s:property value="partidoPaginatedList.pageNumber"/></h2>
				<display:table name="partidoPaginatedList" id="row" class="table" sort="external" partialList="true" 
							size="${partidoPaginatedList.fullListSize}" pagesize="${partidoPaginatedList.objectsPerPage}" 
							requestURI="GoCalendario">
							
				<display:setProperty name="paging.banner.some_items_found" value=""/>
				<display:setProperty name="paging.banner.group_size" value="10"/>						
<%-- 				    <display:column titleKey="jornada" property="jornada" /> --%>
				    <display:column titleKey="local" style="width:42%">
				    	<c:if test="${row.parentLocal.invitado == false}">
			     			<s:if test="porEquipos == false">
			     				<s:url var="perfilAction" action="GoPerfil">
			     					<s:param name="id" >${row.parentLocal.id}</s:param></s:url>
			     			
						     	<sj:a  
									href="%{perfilAction}"
									openDialog="myremotelinkdialog"
									button="false" >
										${row.parentLocal.alias}
								</sj:a>
							</s:if>
							<s:elseif test="porEquipos == true">
								<a href="<s:url action="VerEquipo"><s:param name="id" >${row.parentLocal.id}</s:param>
									</s:url>">${row.parentLocal.nombre}</a>
							</s:elseif>
						</c:if>
						<c:if test="${row.parentLocal.invitado == true}">
							<div style="color:grey; float:left">
								<s:if test="porEquipos == false">
									<c:out value="${row.parentLocal.alias}"/>
								</s:if>
								<s:elseif test="porEquipos == true">
									<c:out value="${row.parentLocal.nombre}"/>
								</s:elseif>	
							</div>
						</c:if>
						
						<div style="float:right">
						<c:if test="${row.confirmado == true}">
							<c:if test="${row.parentGanador == row.parentLocal}">
								<img src="<s:url value="/images/template/Stock-Index-Up-icon.png"/>" 
											title="<s:text name='ganador'/>" width="16" height="16"/>
							</c:if>
							<c:if test="${row.parentGanador == row.parentVisitante}">
								<img src="<s:url value="/images/template/Stock-Index-Down-icon.png"/>" 
											title="<s:text name='perdedor'/>" width="16" height="16"/>
							</c:if>
							<c:if test="${row.parentGanador != row.parentLocal && row.parentGanador != row.parentVisitante}">
								<img src="<s:url value="/images/template/equals.png"/>" 
											title="<s:text name='empate'/>" width="16" height="16"/>
							</c:if>
						</c:if>
						</div>
							
																
		    		</display:column>
		    		<display:column titleKey="resultado" style="width:72px" class="guion_imagen">
		    			<c:if test="${row.confirmado == true}">
				    		<div style="float:left;padding-left:5px; width:20px" >
								<b><c:out value="${row.puntosLocal}"/></b>
							</div>
							
							<div style="float:right;padding-left:5px; width:20px">
								<b><c:out value="${row.puntosVisitante}" /></b>
							</div>
						</c:if>
		    		</display:column>
				    <display:column titleKey="visitante">
				    	<div style="float:left; margin-right:10px">
							<c:if test="${row.confirmado == true}">
								<c:if test="${row.parentGanador == row.parentVisitante}">
									<img src="<s:url value="/images/template/Stock-Index-Up-icon.png"/>" 
												title="<s:text name='ganador'/>" width="16" height="16"/>
								</c:if>
								<c:if test="${row.parentGanador == row.parentLocal}">
									<img src="<s:url value="/images/template/Stock-Index-Down-icon.png"/>" 
												title="<s:text name='perdedor'/>" width="16" height="16"/>
								</c:if>
								<c:if test="${row.parentGanador != row.parentLocal && row.parentGanador != row.parentVisitante}">
									<img src="<s:url value="/images/template/equals.png"/>" 
												title="<s:text name='empate'/>" width="16" height="16"/>
								</c:if>
							</c:if>
						</div>
				    	<c:if test="${row.parentVisitante.invitado == false}">
			     			<s:if test="porEquipos == false">
			     				<s:url var="perfilAction" action="GoPerfil">
			     					<s:param name="id" >${row.parentVisitante.id}</s:param></s:url>
			     			
						     	<sj:a  
									href="%{perfilAction}"
									openDialog="myremotelinkdialog"
									button="false" >
										${row.parentVisitante.alias}
								</sj:a>
							</s:if>
							<s:elseif test="porEquipos == true">
								<a href="<s:url action="VerEquipo"><s:param name="id" >${row.parentVisitante.id}</s:param>
									</s:url>">${row.parentVisitante.nombre}</a>
							</s:elseif>
						</c:if>
						<c:if test="${row.parentVisitante.invitado == true}">
							<div style="color:grey">
								<s:if test="porEquipos == false">
									<c:out value="${row.parentVisitante.alias}"/>
								</s:if>
								<s:elseif test="porEquipos == true">
									<c:out value="${row.parentVisitante.nombre}"/>
								</s:elseif>
							</div>
						</c:if>				    
		    		</display:column>				
				</display:table>				
			</s:if>
</div>


<s:else>
	<s:form id="formularioJornada" action="IrAJornada" method="post" cssClass="form">
		<fieldset>
		<legend><s:text name="palabra.jornada" /></legend>
		<s:hidden name="id" value="%{torneo.id}"/>
		<s:select name="entero" list="listaJornadas" value="%{jornadaActual}"/>
		</fieldset>
	</s:form>
	
	<sj:a
		id="ajaxformlinkCalendario"
		formIds="formularioJornada"
		targets="resultCalendario"
		indicator="indicator"
		button="true"
		buttonIcon="ui-icon-gear"
	>
	<s:text name="mostrar"/>
	</sj:a>
	
	
	<div id="resultCalendario" class="result ui-widget-content ui-corner-all" style="margin:0px;">
		<jsp:include page="/WEB-INF/pages/display/torneo/CalendarioDisplay.jsp"/>			
	</div>
</s:else>
	    	