package br.com.gpistore.calculosautomotivos;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;


public class MainFragment extends Fragment {
    View view;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        final NavigationView navigationView = ((MainActivity) getActivity()).navigationView;
        final Toolbar toolbar = ((MainActivity) getActivity()).toolbar;
        ImageView imgrl = (ImageView) view.findViewById(R.id.imgrl);
        ImageView imgvmp = (ImageView) view.findViewById(R.id.imgvmp);
        ImageView imgcambio = (ImageView) view.findViewById(R.id.imgcambio);
        ImageView imgrodas = (ImageView) view.findViewById(R.id.imgrodas);
        ImageView imgbicos = (ImageView) view.findViewById(R.id.imgbicos);
        ImageView imgtaxa = (ImageView) view.findViewById(R.id.imgtaxa);

        imgrl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RlFragment RL = new RlFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, RL).commit();
                navigationView.setCheckedItem(R.id.rl);
                toolbar.setTitle(R.string.calculo_rl);
            }
        });
        imgvmp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VmpFragment VMP = new VmpFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, VMP).commit();
                navigationView.setCheckedItem(R.id.vmp);
                toolbar.setTitle(R.string.calculo_vmp);
            }
        });
        imgcambio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CambioFragment cambio = new CambioFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, cambio).commit();
                navigationView.setCheckedItem(R.id.cambio);
                toolbar.setTitle(R.string.calculo_cambio);
            }
        });
        imgrodas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    RodaFragment Roda = new RodaFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, Roda).commit();
                navigationView.setCheckedItem(R.id.rodas);
                toolbar.setTitle(R.string.tamanho_rodas);
            }
        });

        imgbicos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BicosFragment Bicos = new BicosFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, Bicos).commit();
                navigationView.setCheckedItem(R.id.bicos);
                toolbar.setTitle(R.string.bicos);
            }
        });

        imgtaxa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TaxaFragment Taxa = new TaxaFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, Taxa).commit();
                navigationView.setCheckedItem(R.id.taxa);
                toolbar.setTitle(R.string.taxa);
            }
        });

        return view;
    }

}
