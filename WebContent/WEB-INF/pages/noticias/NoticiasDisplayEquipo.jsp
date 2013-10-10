<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript">
$('#mytabsEquipo').tabs({
    load: function(event, ui) {
        $(ui.panel).delegate('span a, th a', 'click', function(event) {
            $(ui.panel).load(this.href);
            event.preventDefault();
        });
    }
});
</script>
	
<s:set name="accion">GoNoticiasEquipo</s:set>

			
