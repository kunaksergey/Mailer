package ua.shield.helper;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by sa on 04.09.17.
 */
public class Url {
    public static final String UNREGISTERED_URL="/unregisteredUser";
    public static final String INDEX_URL ="/index.xhtml";
    public static final String MESSAGE_LIST_URL ="/message/list.xhtml";
    public static final String MESSAGE_EDIT_URL ="/message/edit.xhtml";
    public static final String TASK_LIST_URL ="/task/list.xhtml";
    public static final String TASK_EDIT_URL ="/task/edit.xhtml";
    public static final String MAIL_SERVER_LIST_URL ="/server/list.xhtml";
    public static final String MAIL_SERVER_EDIT_URL ="/server/edit.xhtml";
    public static final String MAIL_ADDRESS_LIST_URL ="/email/list.xhtml";
    public static final String MAIL_ADDRESS_EDIT_URL ="/email/edit.xhtml";
    public static final String GROUP_MAIL_ADDRESS_LIST_URL ="/email/group/list.xhtml";
    public static final String GROUP_MAIL_ADDRESS_EDIT_URL ="/email/group/edit.xhtml";
    public static final String GROUP_MAIL_SERVER_LIST_URL ="/server/group/list.xhtml";
    public static final String GROUP_MAIL_SERVER_EDIT_URL ="/server/group/edit.xhtml";

    public static void  redirect(String url){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
