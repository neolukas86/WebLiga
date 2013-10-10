<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="blog">
	<s:if test="#request.locale.language.equals('es')">	
		<h2>Descubre WebLiga</h2>
		<img src="<s:url value="/images/template/logos/logo_WebLiga_blog.gif" />" alt="picture" width="200" height="85" />
		<p><strong>Descubre Webliga.</strong><br />
		Ya puedes disfrutar de la primera versión de WebLiga y colaborar con nosotros. </p>
	</s:if>
	
	<s:if test="#request.locale.language.equals('en')">
		<h2>Discover WebLiga</h2>
		<img src="<s:url value="/images/template/logos/logo_WebLiga_blog.gif" />" alt="picture" width="200" height="85" />
		<p><strong>Discover Webliga.</strong><br />
		Now you can enjoy the first version of WebLiga and cooperate with us. </p>
	</s:if>

	<s:if test="#request.locale.language.equals('ca')">
		<h2>Descobreix WebLiga</h2>
		<img src="<s:url value="/images/template/logos/logo_WebLiga_blog.gif" />" alt="picture" width="200" height="85" />
		<p><strong>Descobreix WebLiga.</strong><br />
		Ja pots gaudir de la primera versió de WebLiga i col · laborar amb nosaltres. </p>
	</s:if>

	<p><s:a action="IrNoticia" ><s:param name="noticia">WebLiga</s:param><strong><s:text name="seguir.leyendo"/></strong></s:a></p>
	
</div>