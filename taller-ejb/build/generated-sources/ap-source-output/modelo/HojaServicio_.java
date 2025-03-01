package modelo;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Clientes;
import modelo.DetalleServicio;
import modelo.Empleado;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-03-01T02:18:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(HojaServicio.class)
public class HojaServicio_ { 

    public static volatile SingularAttribute<HojaServicio, Date> fecha;
    public static volatile SingularAttribute<HojaServicio, Clientes> idCliente;
    public static volatile SingularAttribute<HojaServicio, Empleado> idEmpleado;
    public static volatile SingularAttribute<HojaServicio, Integer> folio;
    public static volatile SingularAttribute<HojaServicio, String> observaciones;
    public static volatile ListAttribute<HojaServicio, DetalleServicio> detalleServicioList;
    public static volatile SingularAttribute<HojaServicio, Integer> id;

}