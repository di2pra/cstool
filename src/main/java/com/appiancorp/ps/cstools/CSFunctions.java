package com.appiancorp.ps.cstools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.appiancorp.services.ServiceContext;
import com.appiancorp.suiteapi.expression.annotations.Category;
import com.appiancorp.suiteapi.expression.annotations.Function;
import com.appiancorp.suiteapi.expression.annotations.Parameter;

@Category("CSToolsCategory")
public class CSFunctions {

	@Function
	public String cleanJson(ServiceContext sc, @Parameter String json) {
		
		JSONObject jsonObject = new JSONObject(json);
		
		removeNullFields(jsonObject);
		
		return jsonObject.toString();
		
	}
	
	
	private static void removeNullFields(Object object) throws JSONException {
		
		// if the object is an array
	    if (object instanceof JSONArray) {
	    	
	    	// cast the array
	        JSONArray array = (JSONArray) object;
	        
	        // iterate through the array
	        for (int i = 0; i < array.length(); ++i) removeNullFields(array.get(i));
	      
	    // if the object is an json object
	    } else if (object instanceof JSONObject) {
	    	
	    	// cast the object
	        JSONObject json = (JSONObject) object;
	        
	        // get the list of keys
	        JSONArray names = json.names();
	        
	        // if no key return
	        if (names == null) return;
	        
	        // iterate through each key
	        for (int i = 0; i < names.length(); ++i) {
	        	
	        	
	            String key = names.getString(i);
	            
	            Object value = json.get(key);
	            
	            // if value is an array
	            if(value instanceof JSONArray) {
	            	// if the array is empty
	            	if(((JSONArray) value).isEmpty()) {
	            		
	            		// then remove the key
	            		json.remove(key);
	            	}
	            	
	            }
	            
	            if (json.isNull(key) || json.get(key).equals("")) {
	            	
	            	// then remove the key
	                json.remove(key);
	                
	            } else {
	            	
	                removeNullFields(json.get(key));
	                
	            }
	        }
	        
	    }
	}
}
