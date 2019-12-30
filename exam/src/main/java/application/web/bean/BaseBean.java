package application.web.bean;

import javax.faces.context.FacesContext;
import java.io.IOException;

public abstract class BaseBean {
    protected void redirect(String url){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/view" + url + ".jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
