<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="internacional">

<s:a action="Internacionalizar"><s:param name="request_locale" >es</s:param><s:param name="internacionalizarAttempt" value="%{'1'}" />
	<img src="<s:url value="/images/flags/16/Spain.png" />" width="16" height="16" border="0" alt="flag" />
	<s:text name="language.español" /></s:a>
<s:a action="Internacionalizar"><s:param name="request_locale" >en</s:param><s:param name="internacionalizarAttempt" value="%{'1'}" />
	<img src="<s:url value="/images/flags/16/United Kingdom(Great Britain).png" />" width="16" height="16" border="0" alt="flag" />
	<s:text name="language.ingles" /></s:a>
<%-- <s:a action="Internacionalizar"><s:param name="request_locale" >it</s:param><s:param name="internacionalizarAttempt" value="%{'1'}" /> --%>
<%-- 	<img src="<s:url value="/images/flags/16/Italy.png" />" width="16" height="16" border="0" alt="flag" /> --%>
<%-- 	<s:text name="language.italiano" /></s:a> --%>
<%-- <s:a action="Internacionalizar"><s:param name="request_locale" >fr</s:param><s:param name="internacionalizarAttempt" value="%{'1'}" /> --%>
<%-- 	<img src="<s:url value="/images/flags/16/France.png" />" width="16" height="16" border="0" alt="flag" /> --%>
<%-- 	<s:text name="language.frances" /></s:a> --%>
<%-- <s:a action="Internacionalizar"><s:param name="request_locale" >de</s:param><s:param name="internacionalizarAttempt" value="%{'1'}" /> --%>
<%-- 	<img src="<s:url value="/images/flags/16/Germany.png" />" width="16" height="16" border="0" alt="flag" /> --%>
<%-- 	<s:text name="language.aleman" /></s:a> --%>
<%-- <s:a action="Internacionalizar"><s:param name="request_locale" >pt</s:param><s:param name="internacionalizarAttempt" value="%{'1'}" /> --%>
<%-- 	<img src="<s:url value="/images/flags/16/Portugal.png" />" width="16" height="16" border="0" alt="flag" /> --%>
<%-- 	<s:text name="language.portugues" /></s:a> --%>
<%-- <s:a action="Internacionalizar"><s:param name="request_locale" >nl</s:param><s:param name="internacionalizarAttempt" value="%{'1'}" /> --%>
<%-- 	<img src="<s:url value="/images/flags/16/Netherlands.png" />" width="16" height="16" border="0" alt="flag" /> --%>
<%-- 	<s:text name="language.holandes" /></s:a> --%>
<s:a action="Internacionalizar"><s:param name="request_locale" >ca</s:param><s:param name="internacionalizarAttempt" value="%{'1'}" />
	<img src="<s:url value="/images/flags/16/Catalonia.png" />" width="16" height="16" border="0" alt="flag" />
	<s:text name="language.catalan" /></s:a>
<%-- <s:a action="Internacionalizar"><s:param name="request_locale" >eu</s:param><s:param name="internacionalizarAttempt" value="%{'1'}" /> --%>
<%-- 	<img src="<s:url value="/images/flags/16/Basque Country.png" />" width="16" height="16" border="0" alt="flag" /> --%>
<%-- 	<s:text name="language.euskera" /></s:a>	 --%>
<%-- <s:a action="Internacionalizar"><s:param name="request_locale" >gl</s:param><s:param name="internacionalizarAttempt" value="%{'1'}" /> --%>
<%-- 	<img src="<s:url value="/images/flags/16/Galicia.png" />" width="16" height="16" border="0" alt="flag" /> --%>
<%-- 	<s:text name="language.gallego" /></s:a> --%>

</div>
