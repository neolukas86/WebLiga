<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

			<s:actionerror theme="jquery"/>
			<s:form action="ChangeUserPassword" method="post" cssClass="form">
			<fieldset>
			<legend><s:text name="password"/></legend>
			<table>
			<tr>
			<td><s:password  name="pw" required="true" key="antiguo.password" size="20" maxlength="20" /></td>
			</tr><tr>
			<td><s:password  name="password" required="true" key="nuevo.password" size="20" maxlength="20" /></td>
			<td><s:password  name="pwRep" required="true" key="password.repetir" size="20" maxlength="20" /></td>
			</tr>
			</table>		
			<br><br>	    
			    
			    
			<sj:submit button="true" key="change.password" cssClass="centrado" cssStyle="font-size:15px;"/>
			
			</fieldset>
			</s:form>
