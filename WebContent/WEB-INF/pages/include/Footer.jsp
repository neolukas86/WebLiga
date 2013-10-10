<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="footer">
	
    <div class="footer_resize"> 
    	<a href="VolverIndex.action">
    		<img src="<s:url value="%{getText('ruta.logo.footer')}" />"  
    			alt="picture" width="321" height="60" border="0" class="loggo" /></a>
    <p class="right"> 
<!--       <a href="#"> -->
<%--       	<img src="<s:url value="/images/template/rss_1.gif" />"  --%>
<!--       		alt="picture" width="16" height="16" border="0" class="rss" /></a>  -->
<!--       <a href="#"> -->
<%--       	<img src="<s:url value="/images/template/rss_2.gif" />"  --%>
<!--       		alt="picture" width="16" height="16" border="0" class="rss" /></a> -->
      <a href='<s:property value="%{getText('contacto.twitter')}" />'>
      	<img src="<s:url value="/images/template/rss_3.gif" />" 
      		alt="picture" title="Twitter" width="16" height="16" border="0" class="rss" /></a> 
      <a href='<s:property value="%{getText('contacto.facebook')}" />'>
      	<img src="<s:url value="/images/template/rss_4.gif" />"
      		alt="picture" title="Facebook" width="16" height="16" border="0" class="rss" /></a>
	  <a href='<s:property value="%{getText('contacto.google')}" />'>
      	<img src="<s:url value="/images/template/Google-plus-icon.png" />"
      		alt="picture" title="Google+" width="16" height="16" border="0" class="rss" /></a>       		 
      <s:text name="proyecto.fin.carrera" /><br />
      <s:a action="VolverIndex"><s:text name="home" /></s:a>
       | <a href='mailto:<s:property value="%{getText('contacto.destino')}" />'><s:text name="contacto" /></a>
       | <a href='<s:property value="%{getText('contacto.usal')}" />'><s:text name="universidad.salamanca"/></a>
    </p>
      <div class="clr"></div>
    </div>
    <div class="clr"></div>
</div>
