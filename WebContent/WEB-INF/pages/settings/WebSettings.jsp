<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
				 
<s:actionerror theme="jquery"/>
<s:form id="chUsPr"  action="ChangeUserPropertiesWeb" method="post" cssClass="form">
	<fieldset>
		<legend><s:text name="change.web.properties"/></legend>
		<table>
			<tr>    			    	
			<td><s:select name="lenguaje" list="listaLenguajes" value="%{lenguaje}" required="true" key="idioma.defecto"/></td>
			</tr>
		</table>
			    
		<sj:submit button="true" key="enviar"  cssClass="centrado" cssStyle="font-size:15px;"/>
	</fieldset>
</s:form>