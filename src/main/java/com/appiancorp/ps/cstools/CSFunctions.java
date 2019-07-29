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
	    if (object instanceof JSONArray) {
	    	
	        JSONArray array = (JSONArray) object;
	        for (int i = 0; i < array.length(); ++i) removeNullFields(array.get(i));
	        
	    } else if (object instanceof JSONObject) {
	    	
	        JSONObject json = (JSONObject) object;
	        JSONArray names = json.names();
	        
	        
	        if (names == null) return;
	        for (int i = 0; i < names.length(); ++i) {
	            String key = names.getString(i);
	            
	            Object value = json.get(key);
	            
	            // if value is an array
	            if(value instanceof JSONArray) {
	            	if(((JSONArray) value).isEmpty()) {
	            		json.remove(key);
	            		return;
	            	}
	            }
	            
	            if (json.isNull(key) || json.get(key).equals("")) {
	                json.remove(key);
	            } else {
	                removeNullFields(json.get(key));
	            }
	        }
	        
	    }
	}
}
