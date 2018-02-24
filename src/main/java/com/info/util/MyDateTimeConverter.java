package com.info.util;

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
        if (value != null && value.length() != getPattern().length()) {
            throw new ConverterException();
        }

        return super.getAsObject(context, component, value);
    }

}
