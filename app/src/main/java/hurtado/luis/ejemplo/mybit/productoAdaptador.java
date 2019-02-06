package hurtado.luis.ejemplo.mybit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Trujas on 05/08/2015.
 */
public class productoAdaptador extends RecyclerView.Adapter<productoAdaptador.productoViewHolder>{
    private ArrayList<clsProducto> item;

    public productoAdaptador(ArrayList<clsProducto> item) {
        this.item = item;
    }

    @Override
    public productoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_productos,viewGroup,false);
        productoViewHolder producto= new productoViewHolder(v);
        return producto;
    }

    @Override
    public void onBindViewHolder(productoViewHolder productoViewHolder, int i) {
        productoViewHolder.nombre.setText(item.get(i).getNombre_p());
        productoViewHolder.precio.setText(item.get(i).getPrecio_p());
        productoViewHolder.imagen.setImageResource(item.get(i).getImagen_p());

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class productoViewHolder extends RecyclerView.ViewHolder{
        TextView nombre,precio;
        ImageView imagen;

        public productoViewHolder(View itemView) {
            super(itemView);

            nombre=(TextView)itemView.findViewById(R.id.lblNombre);
            precio=(TextView)itemView.findViewById(R.id.lblPrecio);
            imagen=(ImageView)itemView.findViewById(R.id.imgProducto);

        }
    }

}
