<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="blog">
	<s:if test="#request.locale.language.equals('es')">
		<h2>Primeros Pasos</h2>
		<img src="<s:url value="/images/template/duda.jpg" />" alt="picture" width="200" height="85" />
		<p><strong>Da tus primeros pasos. </strong><br />
		Eres nuevo en WebLiga? Dejanos ayudarte a dar tus primeros pasos. </p>
	</s:if>
	
	<s:if test="#request.locale.language.equals('en')">	
		<h2>First Steps</h2>
		<img src="<s:url value="/images/template/duda.jpg" />" alt="picture" width="200" height="85" />
		<p><strong>Take your first steps. </strong><br />
		Are you new in WebLiga? Let us help you to take your first steps.</p>
	</s:if>
	
	<s:if test="#request.locale.language.equals('ca')">
		<h2>Primers Passos</h2>
		<img src="<s:url value="/images/template/duda.jpg" />" alt="picture" width="200" height="85" />
		<p><strong>Dóna els teus primers passos. </strong><br />
		Ets nou a WebLiga? Deixa'ns ajudar-te a donar les primeres passes. </p>
	</s:if>

	<p><s:a action="IrNoticia" ><s:param name="noticia">PrimerosPasos</s:param><strong><s:text name="seguir.leyendo"/></strong></s:a></p>
</div>