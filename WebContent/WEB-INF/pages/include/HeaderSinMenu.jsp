<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


  <div class="header_resize">
    <div class="header">
      <div class="logo"><s:a action="VolverIndex">
      		<img src="<s:url value="%{getText('ruta.logo.header')}" />" width="321" height="100" border="0" alt="logo" /></s:a></div>
      <div class="clr"></div>
      <jsp:include page="/WEB-INF/pages/include/Internacionalizacion.jsp" />
    </div>
  </div>
