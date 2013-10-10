<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



	<fmt:setLocale value="${request.locale}"/>
	<s:set name="idioma" value="%{#request.locale.language}"/>
	
	<div class="noticias">
	
	<s:iterator value="noticiaPaginatedList.list" status="rowstatus">
<%-- 	<s:if test="#rowstatus.odd == true"> --%>
<!-- 		<div class="clr"></div> -->
<%-- 	</s:if> --%>
<!-- 		<div class="sombra rounded-corners"  -->
<!-- 			style="width:300px; margin-top:15px; margin-right:50px; float:left;border:1px solid black; background:#fafafa;"> -->

			<s:if test="tipo.equals('comunidad')">
				<jsp:include page="/WEB-INF/pages/noticias/NoticiasDisplayComunidad.jsp"/>
			</s:if>
			<s:elseif test="tipo.equals('torneo')">
				<jsp:include page="/WEB-INF/pages/noticias/NoticiasDisplayTorneo.jsp"/>
			</s:elseif>
			<s:elseif test="tipo.equals('equipo')">
				<jsp:include page="/WEB-INF/pages/noticias/NoticiasDisplayEquipo.jsp"/>
			</s:elseif>


		<div class="sombra rounded-corners" style="width:730px; margin-top:15px; float:left; background:#fafafa;">
		<!-- 	background:#182227 -->
			<div class="degradado" style="padding:3px; text-align:center;font:bold 12px Arial, Helvetica, sans-serif; color:black;" >
				<s:if test="titulo != null">
					<s:property value="titulo"/>
				</s:if>
				<s:else>
					<s:if test="%{#request.locale.language.equals('es')}">
						<s:property value="parentCategoriaNoticia.titulo_ES"/>
					</s:if>
					<s:elseif test="%{#request.locale.language.equals('ca')}">
						<s:property value="parentCategoriaNoticia.titulo_CA"/>
					</s:elseif>
					<s:elseif test="%{#request.locale.language.equals('en')}">
						<s:property value="parentCategoriaNoticia.titulo_EN"/>
					</s:elseif>
				</s:else>
			</div>
<!-- 			<hr> -->
		<!-- 	</h2> -->
			<br>
		
		<div style="padding-left:5px; padding-right:5px">
	
		<s:if test="mensaje != null">
				<s:property value="mensaje"/>
		</s:if>
		
		<s:else>
			<s:if test="parentCategoriaNoticia.ppioMensaje == true">
				<s:if test="parentUsuario != null">
					<s:url id="perfilAction" action="GoPerfil"><s:param name="id" value="parentUsuario.id"/></s:url>
					<sj:a  
								href="%{perfilAction}"
								openDialog="myremotelinkdialog"
								button="false" 
								>
								<b>${parentUsuario.alias} </b>
							</sj:a>			
				</s:if>
				<s:elseif test="parentEquipo != null">
					<a href="<s:url action="VerEquipo"><s:param name="id" >${parentEquipo.id}</s:param>
						</s:url>"><b>${parentEquipo.nombre}</b></a>
				</s:elseif>			
			</s:if>
			
			<s:if test="%{#request.locale.language.equals('es')}">
				<s:property value="parentCategoriaNoticia.mensaje_ES"/>
			</s:if>
			<s:elseif test="%{#request.locale.language.equals('ca')}">
				<s:property value="parentCategoriaNoticia.mensaje_CA"/>
			</s:elseif>
			<s:elseif test="%{#request.locale.language.equals('en')}">
				<s:property value="parentCategoriaNoticia.mensaje_EN"/>
			</s:elseif>
			
			<s:if test="parentCategoriaNoticia.finalMensaje == true || (parentEquipo != null && tipo.equals('equipo'))">
				<s:if test="parentCategoriaNoticia.nombre.startsWith('Rango')">
					<s:property value="nuevoRango" />
				</s:if>
				<s:elseif test="parentTorneo != null">
					<a href="<s:url action="VerTorneo"><s:param name="id" >${parentTorneo.id}</s:param>
						</s:url>"><b>${parentTorneo.nombre}</b></a>
				</s:elseif>
					<s:elseif test="parentComunidad != null">
						<a href="<s:url action="VerComunidad"><s:param name="id" >${parentComunidad.id}</s:param>
							</s:url>"><b>${parentComunidad.nombre}</b></a>					
					</s:elseif>
			</s:if>
			
			 			
		</s:else>
		

		
			<br>
			<div style="color:grey;font:11px Arial, Helvetica, sans-serif; padding-top:5px; padding-bottom:5px">
			<s:property value="%{getText('fecha.publicacion')}" />: <fmt:formatDate value="${fechaPublicacion}"
										  dateStyle="full" type="both" /> GMT+1
			</div>										  
										  
			
		</div>
		</div>
		

	</s:iterator>
	
	</div>
	
	<div class="clr"></div>
	
	<div  style="margin-top:15px;text-align:center">
		<jsp:include page="/WEB-INF/pages/noticias/NoticiasPaginacion.jsp"/>
	</div>

    <sj:dialog 
    	id="myremotelinkdialog" 
    	autoOpen="false"  
    	title="%{getText('perfil.usuario')}"
    	buttons="{'%{getText('cerrar')}':function() { $('#myremotelinkdialog').dialog('close'); }}"
    	width="500"
    />
			
