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

			<s:set var="rangoCREADOR" value="@com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares@CREADOR" />
			<s:set var="rangoADMIN" value="@com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares@ADMIN" />
			<s:set var="rangoMOD" value="@com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares@MOD" />
			<s:set var="rangoPOWERUSER" value="@com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares@POWERUSER" />
			<s:set var="rangoNORMALUSER" value="@com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares@NORMALUSER" />
			
	    	<h2><s:text name="palabra.usuarios" /></h2>

			<c:set var="coleccion" value="${comunidad.userComunidades}" />			
			<s:url var="rutaImgEdit" value="/images/template/edit-icon.png" />
			<s:url var="rutaImgKick" value="/images/template/kick-icon.png" />
			<s:url var="rutaImgBan" value="/images/template/banned-icon.png" />
			
			<display:table name="userList" id="row" class="table" pagesize="10" 
					requestURI="GoUsuariosComunidad">
<%-- 			    <display:column property="id" titleKey="id" /> --%>
		     	<display:column titleKey="user.alias" >
				<s:url var="perfilAction" action="GoPerfil"><s:param name="id" >${row.id}</s:param></s:url>
				
				<sj:a  
					href="%{perfilAction}"
					openDialog="myremotelinkdialog"
					button="false" >
						${row.alias}
				</sj:a>
		     </display:column>
<%-- 			    <display:column property="nombre" titleKey="firstname"/> --%>
<%-- 			    <display:column property="apellido" titleKey="lastname"/> --%>
<%-- 			    <display:column property="email" titleKey="email" autolink="true"/> --%>
<%-- 			    <display:column property="nacimiento" titleKey="fecha.nacimiento"/> --%>
			    <display:column titleKey="palabra.pais">
			    	<c:out value="${row.parentPais.nombre}" />
			    </display:column>
			    <display:column titleKey="sexo">
			    	<c:if test="${row.sexo == false}">
			    		<s:text name="sexo.femenino"/>
			    	</c:if>
			    	<c:if test="${row.sexo == true}">
			    		<s:text name="sexo.masculino"/>
			    	</c:if>
			    </display:column>    
			    <display:column property="fechaRegistro" titleKey="fecha.registro"/>
			    
			    <c:forEach  var="var" items="${coleccion}">
			    	<c:if test="${var.id.parentUser.id == row.id }">
			    		<s:set var="userId">${row.id}</s:set>
			    		<c:choose>
				    		<c:when test ="${var.rango == rangoCREADOR}" >
				    			<display:column titleKey="rango">
				    				<s:text name="rango.creador"/>
				    			</display:column >
				    		</c:when>
				    		<c:when test ="${var.rango == rangoADMIN}" >
				    			<display:column titleKey="rango">
				    				<s:if test="rolcomunidad == 'creador'">

										<s:form action="EditarRangoUserComunidad">
											<s:hidden name="iduser" value="%{userId}" />	
											<s:hidden name="id" value="%{comunidad.id}" />	
											<s:select name="rangoCom" list="listaRangos" value="%{rangoADMIN}" cssStyle="float:left"/>
											<s:submit type="image" 	src="%{rutaImgEdit}" title="%{getText('editar')}" cssStyle="float:left"/>
										</s:form>	
										
										<s:set var="idExpulsar">Expulsar${row.id}</s:set>
										<s:set var="idBanear">Banear${row.id}</s:set>
										
										<s:form id="%{idExpulsar}" name="%{idExpulsar}" action="ExpulsarUsuarioComunidad">
												<s:hidden name="iduser" value="%{userId}" />
												<s:hidden name="id" value="%{comunidad.id}" />
												<sj:submit openDialog="mybuttondialogExpulsar" type="image" src="%{rutaImgKick}" 
													indicator="indicator" title="%{getText('expulsar')}" cssStyle="float:left"
													onClick="FuncionSubmit(this.form)"/>
										</s:form>
										
										
										<s:form id="%{idBanear}" name="%{idBanear}" action="BanearUsuarioComunidad">
											<s:hidden name="iduser" value="%{userId}" />
											<s:hidden name="id" value="%{comunidad.id}" />											
											<sj:submit openDialog="mybuttondialogBanear" type="image" src="%{rutaImgBan}" 
												indicator="indicator" title="%{getText('banear')}" cssStyle="float:left"
												onClick="FuncionSubmit(this.form)"/>
										</s:form>						
									
				    				</s:if>
									<s:else>
				    					<s:text name="rango.admin" />
				    				</s:else>
				    			</display:column>				    			
				    		</c:when>
				    		<c:when test ="${var.rango == rangoMOD}" >
				    			<display:column titleKey="rango">
				    				<s:if test="rolcomunidad == 'creador' || rolcomunidad == 'admin'">

										<s:form action="EditarRangoUserComunidad">
											<s:hidden name="iduser" value="%{userId}" />
											<s:hidden name="id" value="%{comunidad.id}" />	
											<s:select name="rangoCom" list="listaRangos" value="%{rangoMOD}" cssStyle="float:left"/>
											<s:submit type="image" 	src="%{rutaImgEdit}" title="%{getText('editar')}" cssStyle="float:left"/>									
										</s:form>
										
										
										<s:set var="idExpulsar">Expulsar${row.id}</s:set>
										<s:set var="idBanear">Banear${row.id}</s:set>
										
										<s:form id="%{idExpulsar}" name="%{idExpulsar}" action="ExpulsarUsuarioComunidad">
												<s:hidden name="iduser" value="%{userId}" />
												<s:hidden name="id" value="%{comunidad.id}" />
												<sj:submit openDialog="mybuttondialogExpulsar" type="image" src="%{rutaImgKick}" 
													indicator="indicator" title="%{getText('expulsar')}" cssStyle="float:left"
													onClick="FuncionSubmit(this.form)"/>
										</s:form>
										
										
										<s:form id="%{idBanear}" name="%{idBanear}" action="BanearUsuarioComunidad">
											<s:hidden name="iduser" value="%{userId}" />
											<s:hidden name="id" value="%{comunidad.id}" />											
											<sj:submit openDialog="mybuttondialogBanear" type="image" src="%{rutaImgBan}" 
												indicator="indicator" title="%{getText('banear')}" cssStyle="float:left"
												onClick="FuncionSubmit(this.form)"/>
										</s:form>										

									</s:if>
									<s:else>
				    					<s:text name="rango.mod" />
				    				</s:else>
				    			</display:column>				    				

				    		</c:when>			    
				    		<c:when test ="${var.rango == rangoPOWERUSER}" >
				    			<display:column titleKey="rango">
				    				<s:if test="rolcomunidad == 'creador' || rolcomunidad == 'admin' || rolcomunidad == 'mod'">

										<s:form action="EditarRangoUserComunidad">
											<s:hidden name="iduser" value="%{userId}" />
											<s:hidden name="id" value="%{comunidad.id}" />
											<s:select name="rangoCom" list="listaRangos" value="%{rangoPOWERUSER}" cssStyle="float:left"/>
											<s:submit type="image" 	src="%{rutaImgEdit}" title="%{getText('editar')}" cssStyle="float:left"/>					
										</s:form>
										
										<s:set var="idExpulsar">Expulsar${row.id}</s:set>
										<s:set var="idBanear">Banear${row.id}</s:set>
										
										<s:form id="%{idExpulsar}" name="%{idExpulsar}" action="ExpulsarUsuarioComunidad">
												<s:hidden name="iduser" value="%{userId}" />
												<s:hidden name="id" value="%{comunidad.id}" />
												<sj:submit openDialog="mybuttondialogExpulsar" type="image" src="%{rutaImgKick}" 
													indicator="indicator" title="%{getText('expulsar')}" cssStyle="float:left"
													onClick="FuncionSubmit(this.form)"/>
										</s:form>
										
										
										<s:form id="%{idBanear}" name="%{idBanear}" action="BanearUsuarioComunidad">
											<s:hidden name="iduser" value="%{userId}" />
											<s:hidden name="id" value="%{comunidad.id}" />											
											<sj:submit openDialog="mybuttondialogBanear" type="image" src="%{rutaImgBan}" 
												indicator="indicator" title="%{getText('banear')}" cssStyle="float:left"
												onClick="FuncionSubmit(this.form)"/>
										</s:form>										

									</s:if>
									<s:else>
				    					<s:text name="rango.poweruser" />
				    				</s:else>
				    			</display:column>	

				    		</c:when>
				    		<c:when test ="${var.rango == rangoNORMALUSER}" >
				    			<display:column titleKey="rango" style="width:150px">
				    				<s:if test="rolcomunidad == 'creador' || rolcomunidad == 'admin' || rolcomunidad == 'mod'">

<%-- 										<s:url id="editarAction" action="EditarRangoUserComunidad" escapeAmp="false"> --%>
										<s:form action="EditarRangoUserComunidad">
											<s:hidden name="iduser" value="%{userId}" />
											<s:hidden name="id" value="%{comunidad.id}" />
											<s:select name="rangoCom" list="listaRangos" value="%{rangoNORMALUSER}" cssStyle="float:left"/>
											<s:submit type="image" src="%{rutaImgEdit}" title="%{getText('editar')}" cssStyle="float:left"/>										
										</s:form>
										
										<s:set var="idExpulsar">Expulsar${row.id}</s:set>
										<s:set var="idBanear">Banear${row.id}</s:set>
										
										<s:form id="%{idExpulsar}" name="%{idExpulsar}" action="ExpulsarUsuarioComunidad">
												<s:hidden name="iduser" value="%{userId}" />
												<s:hidden name="id" value="%{comunidad.id}" />
												<sj:submit openDialog="mybuttondialogExpulsar" type="image" src="%{rutaImgKick}" 
													indicator="indicator" title="%{getText('expulsar')}" cssStyle="float:left; padding-left:5px"
													onClick="FuncionSubmit(this.form)"/>
										</s:form>
										
										
										<s:form id="%{idBanear}" name="%{idBanear}" action="BanearUsuarioComunidad">
											<s:hidden name="iduser" value="%{userId}" />
											<s:hidden name="id" value="%{comunidad.id}" />											
											<sj:submit openDialog="mybuttondialogBanear" type="image" src="%{rutaImgBan}" 
												indicator="indicator" title="%{getText('banear')}" cssStyle="float:left; padding-left:5px"
												onClick="FuncionSubmit(this.form)"/>
										</s:form>																		

				    				</s:if>
				    				<s:else>				    			
				    					<s:text name="rango.normaluser" />
				    				</s:else>
				    			</display:column>

				    		</c:when>
				    	</c:choose>			    			
				    </c:if>
			    </c:forEach>	
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
     Tenga en cuenta que si expulsa al usuario de la comunidad también lo hará de los torneos que pertenezcan a la misma.
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
     Si banea a un usuario significa que lo expulsará de forma permanente, no tendrá opción a volver a registrarse en la comunidad.
     
     Además debe tener en cuenta que si expulsa al usuario de la comunidad también lo hará de los torneos que pertenezcan a la misma.
    </sj:dialog>
    
    <sj:dialog 
    	id="myremotelinkdialog" 
    	autoOpen="false"  
    	title="Perfil de Usuario"
    />
