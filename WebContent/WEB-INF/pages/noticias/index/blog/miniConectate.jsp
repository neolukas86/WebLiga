<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="blog">
	<s:if test="#request.locale.language.equals('es')">
		<h2>Conéctate a WebLiga</h2>
		<img src="<s:url value="/images/template/conectate.png" />" alt="picture" width="200" height="85" />
		<p><strong>Conéctate ya a Webliga.</strong><br />
		Ahora ya puedes seguir por las diferentes redes sociales a WebLiga y no perderte nada. </p>
	</s:if>
	
	<s:if test="#request.locale.language.equals('en')">
		<h2>Connect to WebLiga</h2>
		<img src="<s:url value="/images/template/conectate.png" />" alt="picture" width="200" height="85" />
		<p><strong>Connect already Webliga.</strong><br />
		Now you can follow different social networks WebLiga and not miss anything. </p>
	</s:if>

	<s:if test="#request.locale.language.equals('ca')">	
		<h2>Connecta't a WebLiga</h2>
		<img src="<s:url value="/images/template/conectate.png" />" alt="picture" width="200" height="85" />
		<p><strong>Connecta't ja a WebLiga.</strong><br />
		Ara ja pots seguir per les diferents xarxes socials a WebLiga i no perdre't res. </p>
	</s:if>

	<p><s:a action="IrNoticia" ><s:param name="noticia">Conectate</s:param><strong><s:text name="seguir.leyendo"/></strong></s:a></p>
</div>