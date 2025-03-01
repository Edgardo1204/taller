/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@ManagedBean
@ViewScoped
public class UserWizard {

    // ... otros atributos y métodos ...
    public void save() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String htmlContent = (String) facesContext.getExternalContext().getRequestParameterMap().get("pdfContent");

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            ConverterProperties converterProperties = new ConverterProperties();

            HtmlConverter.convertToPdf(htmlContent, writer, converterProperties);

            facesContext.getExternalContext().responseReset();
            facesContext.getExternalContext().setResponseContentType("application/pdf");
            facesContext.getExternalContext().setResponseHeader("Content-Disposition", "attachment;filename=documento.pdf");

            OutputStream responseOutputStream = facesContext.getExternalContext().getResponseOutputStream();
            responseOutputStream.write(baos.toByteArray());
            responseOutputStream.flush();
            responseOutputStream.close();

            facesContext.responseComplete();
        } catch (Throwable t) { // Captura cualquier excepción
            System.out.println(t);
            t.printStackTrace(); // Imprime el stack trace completo
        }
    }
}
