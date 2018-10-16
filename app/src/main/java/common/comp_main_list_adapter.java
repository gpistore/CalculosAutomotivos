package common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;
import br.com.gpistore.calculosautomotivos.R;

public class comp_main_list_adapter extends RecyclerView.Adapter<comp_main_list_adapter.main_list_view_holder> {
    private List<comp_main_list_item> List;
    Context context;

    public comp_main_list_adapter(List<comp_main_list_item> list, Context context) {
        this.List = list;
        this.context = context;
    }

    public static class main_list_view_holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtview;

        public main_list_view_holder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.main_item_img);
            this.txtview = (TextView) view.findViewById(R.id.main_item_text);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public main_list_view_holder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view
        View  view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.comp_main_item, parent, false);
        main_list_view_holder vh = new main_list_view_holder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final main_list_view_holder holder, int position) {
        holder.imageView.setImageResource(List.get(position).ImageID);
        holder.txtview.setText(List.get(position).NameId);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return List.size();
    }
}