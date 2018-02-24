package com.info.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("myDateTimeConverter")
public class MyDateTimeConverter extends DateTimeConverter {

    public MyDateTimeConverter() {
        setPattern("ddMMyyyy");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat(getPattern());
    	
    	Date d;
    	try{
    		d = sdf.parse(value);
    	} catch(ParseException pe){
    		d = new Date();
    	}
    	
        if (value != null && (value.length() != getPattern().length() || d.compareTo(new Date())>0)) {
            throw new ConverterException();
        }

        return super.getAsObject(context, component, value);
    }

}
