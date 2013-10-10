package com.proyecto.util.properties;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class GlobalResourceUtil{
		private final String pathResource = "global/global";
		
		public GlobalResourceUtil(){
		}
		
		public ResourceBundle loadResourceBundle(Map<?,?> sesion){
 		
			if(sesion.containsKey("WW_TRANS_I18N_LOCALE")){
				System.out.println("************* SI HAY LOCALE **************");
				Locale locale = (Locale) sesion.get("WW_TRANS_I18N_LOCALE");
//				System.out.println("************* Locale.language -> "+locale.getLanguage()+" **************");
	
				return ResourceBundle.getBundle(pathResource,locale);
			}
			else{
				System.out.println("************* NO LOCALE **************");
				return ResourceBundle.getBundle(pathResource);
			}
		}		
}
