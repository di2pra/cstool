package com.appiancorp.ps.cstools;

import com.appiancorp.suiteapi.expression.Writer;

public class ExampleWriter implements Writer {
	 private String systemName;
	 private String value;

	 public ExampleWriter(String systemName, String value) {
	   this.systemName = systemName;
	   this.value = value;
	 }

	 @Override
	 public void execute() {
	   // use systemName and value to make update
		 
		 this.systemName = this.systemName + " updated";
		 
		 Boolean failure = false;
		 
	   if(failure) {
	     throw new RuntimeException("Failed to process write");
	   }
	 }
}