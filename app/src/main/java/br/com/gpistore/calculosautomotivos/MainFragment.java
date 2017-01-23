package br.com.gpistore.calculosautomotivos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MainFragment extends Fragment {
    View view;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);

        ImageView imgrl = (ImageView) view.findViewById(R.id.imgrl);
        ImageView imgvmp = (ImageView) view.findViewById(R.id.imgvmp);
        ImageView imgcambio = (ImageView) view.findViewById(R.id.imgcambio);
        ImageView imgrodas = (ImageView) view.findViewById(R.id.imgrodas);

        imgrl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RlFragment RL = new RlFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, RL).commit();
            }
        });
        imgvmp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VmpFragment VMP = new VmpFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, VMP).commit();
            }
        });
        imgcambio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CambioFragment cambio = new CambioFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, cambio).commit();
            }
        });
        imgrodas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RodaFragment Roda = new RodaFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, Roda).commit();
            }
        });

        return view;
    }

}
