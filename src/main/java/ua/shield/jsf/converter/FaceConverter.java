package ua.shield.jsf.converter;

import ua.shield.entity.IOwned;
import ua.shield.entity.Task;
import ua.shield.helper.FrontMessage;
import ua.shield.service.IService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by sa on 05.09.17.
 */

abstract public class FaceConverter<T extends IOwned> implements Converter {


    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
//                IService<T> s=getService();
//                T task=s.findById(Integer.parseInt(value));
                   return getService().findById(Integer.parseInt(value));
            } catch(NumberFormatException e) {
               FrontMessage.addMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid entity.");
            }
        }

        return null;

    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            String str=String.valueOf(((T) object).getId());
            return str;
        }
        else {
            return null;
        }
    }


   abstract public IService<T> getService();
   abstract public void setService(IService<T> service);

}
