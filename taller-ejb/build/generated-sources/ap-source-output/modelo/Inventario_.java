package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.DetalleServicio;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-03-01T02:18:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Inventario.class)
public class Inventario_ { 

    public static volatile SingularAttribute<Inventario, String> descripcion;
    public static volatile SingularAttribute<Inventario, String> marca;
    public static volatile SingularAttribute<Inventario, String> tipo;
    public static volatile SingularAttribute<Inventario, Integer> precioCompra;
    public static volatile SingularAttribute<Inventario, String> categoria;
    public static volatile ListAttribute<Inventario, DetalleServicio> detalleServicioList;
    public static volatile SingularAttribute<Inventario, Integer> id;
    public static volatile SingularAttribute<Inventario, Integer> cantidad;
    public static volatile SingularAttribute<Inventario, Integer> precioVenta;
    public static volatile SingularAttribute<Inventario, String> nombre;

}