<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<script type="text/javascript">
function okButton(){
    $('#mybuttondialog').dialog('close');
    document.formulario.submit();
  };
  function cancelButton(){
      $('#mybuttondialog').dialog('close');
    };  
</script>
 
	    	    <sj:dialog
        id="mybuttondialog"
        buttons="{
                'Aceptar':function() { okButton(); },
                'Cancelar':function() { cancelButton(); }
                }"
        autoOpen="false"
        modal="true"
        title="Eliminar Comunidad"
    >
     Estás seguro de eliminar el equipo?
    </sj:dialog>
    
	    	<s:form name="formulario" action="EliminarEquipo">
	    		<s:hidden name="id" value="%{equipo.id}" />
	    	</s:form> 
	    	<sj:submit openDialog="mybuttondialog" button="true" indicator="indicator"
	    	 	key="eliminar" cssStyle="margin-left:auto; margin-right:auto; display:block; font-size:15px;"/>