package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Clientes;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-03-01T02:18:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Autos.class)
public class Autos_ { 

    public static volatile SingularAttribute<Autos, String> marca;
    public static volatile SingularAttribute<Autos, Double> km;
    public static volatile SingularAttribute<Autos, Boolean> estado;
    public static volatile SingularAttribute<Autos, String> color;
    public static volatile SingularAttribute<Autos, Clientes> idCliente;
    public static volatile SingularAttribute<Autos, String> vin;
    public static volatile SingularAttribute<Autos, Integer> id;
    public static volatile SingularAttribute<Autos, String> modelo;
    public static volatile SingularAttribute<Autos, Integer> año;
    public static volatile SingularAttribute<Autos, String> placas;

}