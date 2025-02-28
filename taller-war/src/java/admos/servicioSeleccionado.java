package admos;

public class servicioSeleccionado {

    private int id;
    private int cantidad;

    public servicioSeleccionado(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
