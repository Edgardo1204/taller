/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;

/**
 *
 * @author Jesus
 */
@Named(value = "aDFecha")
@SessionScoped
public class aDFecha implements Serializable {

    private static final long serialVersionUID = 1L;
    private String currentDate;

    @PostConstruct
    public void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = sdf.format(new Date());
    }

    public String getCurrentDate() {
        return currentDate;
    }
}
