package com.appiancorp.ps.cstools;

import com.appiancorp.suiteapi.expression.Writer;
import com.appiancorp.suiteapi.expression.annotations.Category;
import com.appiancorp.suiteapi.expression.annotations.Function;
import com.appiancorp.suiteapi.expression.annotations.Parameter;



@Category("CSToolsCategory")
public class ExampleWriterFunction {
	
	 @Function
	 public Writer examplewrite(@Parameter String systemName, @Parameter String value) {
	   return new ExampleWriter(systemName, value);
	 }
}