package ua.shield.helper;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by sa on 05.09.17.
 */
public class FrontMessage {
    public static void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void addMessage(FacesMessage.Severity severity, String summary,String detail) {
        FacesMessage message = new FacesMessage(severity,summary,detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
