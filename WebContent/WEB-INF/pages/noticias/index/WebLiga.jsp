<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="#request.locale.language.equals('es')">
	<div class="noticia">
		<h1>Descubre WebLiga</h1>
		<img src="<s:url value="%{getText('ruta.imagen.noticia.webliga')}" />" alt="picture" width="675" height="190" 
			style="border: 5px solid #BAD340; margin-bottom:15px; margin-top:5px;"/>
		<p><strong>Descubre Webliga.</strong><br /></p>
		Ya está disponible la primera versión de WebLiga, disfruta de todas sus posibilidades.<br><br>
		Para colaborar con la web o para ponerse en contacto con el equipo de WebLiga podeis dirigiros a la siguiente direccion:
		<a href="mailto:correo.liga86@gmail.com">correo.liga86@gmail.com</a> <br><br>
		 <s:a action="VolverIndex" cssStyle="font:bold 14px Arial, Helvetica, sans-serif; color:black">« <s:text name="volver"/></s:a>
	</div>
</s:if>
	
<s:if test="#request.locale.language.equals('en')">
	<div class="noticia">
		<h1>Discover WebLiga</h1>
		<img src="<s:url value="%{getText('ruta.imagen.noticia.webliga')}" />" alt="picture" width="675" height="190" 
			style="border: 5px solid #BAD340; margin-bottom:15px; margin-top:5px;"/>
		<p><strong>Discover WebLiga.</strong><br /></p>
		Now available the first version of WebLiga, enjoys all its possibilities. <br> <br>
		For cooperate with the web or contact WebLiga staff you can go to the following address:
		<a href="mailto:correo.liga86@gmail.com">correo.liga86@gmail.com</a> <br><br>
		 <s:a action="VolverIndex" cssStyle="font:bold 14px Arial, Helvetica, sans-serif; color:black">« <s:text name="volver"/></s:a>
	</div>
</s:if>

<s:if test="#request.locale.language.equals('ca')">
	<div class="noticia">
		<h1>Descobreix WebLiga</h1>
		<img src="<s:url value="%{getText('ruta.imagen.noticia.webliga')}" />" alt="picture" width="675" height="190" 
			style="border: 5px solid #BAD340; margin-bottom:15px; margin-top:5px;"/>
		<p><strong>Descobreix WebLiga.</strong><br /></p>
		Ja està disponible la primera versió de WebLiga, gaudeix de totes les seves possibilitats. <br>
		Per col · laborar amb la web o per posar-se en contacte amb l'equip de WebLiga podeu adreçar-vos a la següent adreça:
		<a href="mailto:correo.liga86@gmail.com">correo.liga86@gmail.com</a> <br><br>
		 <s:a action="VolverIndex" cssStyle="font:bold 14px Arial, Helvetica, sans-serif; color:black">« <s:text name="volver"/></s:a>
	</div>
</s:if>


