<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

			<s:if test="%{sexo}"><s:set var="valorSexo" value="1" /></s:if>
			<s:elseif test="!%{sexo}"><s:set var="valorSexo" value="2" /></s:elseif>

			<s:fielderror theme="jquery"/>
			<s:actionerror theme="jquery"/>
			<s:form id="chUsPr" name="chUsPr" action="ChangeUserProperties" method="post" cssClass="form">
			<fieldset>
			<legend><s:text name="change.user.properties"/></legend>
			<table>
			<tr>
			<td><s:textfield name="nombre" key="firstname" size="20" maxlength="35" value="%{nombre}" /></td>
			<td><s:textfield name="apellido" key="lastname" size="20" maxlength="50" value="%{apellido}" /></td>
			</tr><tr>
			<td><s:select name="genero" key="sexo" list="listaSexo" value="%{valorSexo}"/></td>
			</tr>
			</table>
			<table>
			<tr>
			<td><sj:datepicker name="nacimiento" key="fecha.nacimiento" firstDay="1" maxDate="0y0m0d" changeYear="true" changeMonth="true"
					yearRange="-100:+100" displayFormat="dd-MM-yy" value="%{nacimiento}"/></td>
			</tr>
			</table>
			<table>
			<tr>
			<td><s:url var="paislist" action="listarPaises" />
			    <sj:select name="pais" href="%{paislist}" key="palabra.pais" 
			    	onChangeTopics="reloadprovincialist,reloadmunicipiolist" onCompleteTopics="reloadprovincialist,reloadmunicipiolist" 
			    	list="listaPaises" listKey="id" listValue="nombre" value="%{parentPais.id}" /></td>
			    	
			<td><s:url var="provincialist" action="listarProvinciasByPais" />	
			    <sj:select name="provincia" href="%{provincialist}" key="palabra.provincia" formIds="chUsPr" 
			    	reloadTopics="reloadprovincialist" deferredLoading="true" onChangeTopics="reloadmunicipiolist" onCompleteTopics="reloadmunicipiolist" 
			    	list="listaProvincias" listKey="id" listValue="nombre" value="%{parentMunicipio.parentProvincia.id}"/></td>
			    	
			<td><s:url var="municipiolist" action="listarMunicipiosByProvincia" />
			    <sj:select name="municipio" href="%{municipiolist}" key="palabra.municipio" formIds="chUsPr" 
			    	reloadTopics="reloadmunicipiolist" deferredLoading="true" 
			    	list="listaMunicipios" listKey="id" listValue="nombre" value="%{parentMunicipio.id}"/></td>		    			    	
			</tr>
			</table>
			<table><tr>
			<td><s:textfield name="email" key="email" size="30" maxlength="50" value="%{email}" required="true"/></td>
			</tr>
			<tr>
			<td><s:textfield name="homepage" key="homepage" size="30" maxlength="255" value="%{homepage}"/></td>
			</tr>
			</table>
			<br><br>			    	

				<sj:submit button="true" indicator="indicator" key="change.user.properties" cssClass="centrado" cssStyle="font-size:15px;"/>
				</fieldset>
			</s:form>