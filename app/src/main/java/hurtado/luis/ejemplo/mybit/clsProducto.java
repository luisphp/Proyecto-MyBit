package hurtado.luis.ejemplo.mybit;

/**
 * Created by Trujas on 05/08/2015.
 */
public class clsProducto {
    private int imagen_p;
    private String nombre_p;
    private String precio_p;

    public clsProducto(int imagen_p, String nombre_p, String precio_p) {
        this.imagen_p = imagen_p;
        this.nombre_p = nombre_p;
        this.precio_p = precio_p;
    }

    public int getImagen_p() {
        return imagen_p;
    }

    public String getNombre_p() {
        return nombre_p;
    }

    public String getPrecio_p() {
        return precio_p;
    }
}
