package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.HojaServicio;
import modelo.Inventario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-02-27T22:05:21", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(DetalleServicio.class)
public class DetalleServicio_ { 

    public static volatile SingularAttribute<DetalleServicio, Integer> precio;
    public static volatile SingularAttribute<DetalleServicio, HojaServicio> idHoja;
    public static volatile SingularAttribute<DetalleServicio, Inventario> idProdServ;
    public static volatile SingularAttribute<DetalleServicio, Integer> id;
    public static volatile SingularAttribute<DetalleServicio, Integer> cantidad;

}