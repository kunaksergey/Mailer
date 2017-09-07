package ua.shield.jsf.converter;

import ua.shield.entity.IOwnedId;
import ua.shield.entity.Task;
import ua.shield.helper.FrontMessage;
import ua.shield.service.IService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by sa on 05.09.17.
 */

abstract public class FaceConverter<T extends IOwnedId> implements Converter {


    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                IService s=getService();
                   return s.findById(Integer.parseInt(value));
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
