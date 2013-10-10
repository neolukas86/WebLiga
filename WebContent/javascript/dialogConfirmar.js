var nameFormulario;
     
     function okButtonExpulsar(){
       $('#mybuttondialogExpulsar').dialog('close');
       var formVar = document.getElementById(nameFormulario);
       formVar.submit();
     };
     
     function cancelButtonExpulsar(){
      $('#mybuttondialogExpulsar').dialog('close');
     };
     
     function okButtonBanear(){
         $('#mybuttondialogBanear').dialog('close');
         var formVar = document.getElementById(nameFormulario);
         formVar.submit();
       };
       
     function cancelButtonBanear(){
        $('#mybuttondialogBanear').dialog('close');
      };
      
      function okButton(){
          $('#mybuttondialog').dialog('close');
          document.formulario.submit();
        };
        
        function cancelButton(){
         $('#mybuttondialog').dialog('close');
        };
      
      function FuncionSubmit(formulario){
     	 $(formulario).submit(function(e){
              e.preventDefault();
          });
		nameFormulario = formulario.name;
      };