package br.com.gpistore.calculosautomotivos;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import br.com.gpistore.calculosautomotivos.fragments.BicosFragment;
import br.com.gpistore.calculosautomotivos.fragments.CambioFragment;
import br.com.gpistore.calculosautomotivos.fragments.ConfigFragment;
import br.com.gpistore.calculosautomotivos.fragments.RlFragment;
import br.com.gpistore.calculosautomotivos.fragments.RodaFragment;
import br.com.gpistore.calculosautomotivos.fragments.TaxaFragment;
import br.com.gpistore.calculosautomotivos.fragments.TorqueFragment;
import br.com.gpistore.calculosautomotivos.fragments.VmpFragment;

public class MainFragment extends Fragment {
    View view;
    ImageView imgrl;
    ImageView imgvmp;
    ImageView imgcambio;
    ImageView imgrodas;
    ImageView imgbicos;
    ImageView imgtaxa;
    ImageView imgtorque;
    ImageView imgsobre;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        navigationView = ((MainActivity) getActivity()).navigationView;
        toolbar = ((MainActivity) getActivity()).toolbar;
        this.getActivity().setTitle(R.string.app_name);
        setup();
        setupactions();

        return view;
    }

    private void setup(){
        imgrl = (ImageView) view.findViewById(R.id.imgrl);
        imgvmp = (ImageView) view.findViewById(R.id.imgvmp);
        imgcambio = (ImageView) view.findViewById(R.id.imgcambio);
        imgrodas = (ImageView) view.findViewById(R.id.imgrodas);
        imgbicos = (ImageView) view.findViewById(R.id.imgbicos);
        imgtaxa = (ImageView) view.findViewById(R.id.imgtaxa);
        imgtorque = (ImageView) view.findViewById(R.id.imgtorque);
        imgsobre = (ImageView) view.findViewById(R.id.imgsobre);
    }

    private void setupactions(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        imgrl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RlFragment RL = new RlFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainframe, RL);
                ft.addToBackStack("pilha");
                ft.commit();
                navigationView.setCheckedItem(R.id.rl);
            }
        });
        imgvmp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VmpFragment VMP = new VmpFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainframe, VMP);
                ft.addToBackStack("pilha");
                ft.commit();
                navigationView.setCheckedItem(R.id.vmp);
            }
        });
        imgcambio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CambioFragment cambio = new CambioFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainframe, cambio);
                ft.addToBackStack("pilha");
                ft.commit();
                navigationView.setCheckedItem(R.id.cambio);
            }
        });
        imgrodas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RodaFragment Roda = new RodaFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainframe, Roda);
                ft.addToBackStack("pilha");
                ft.commit();
                navigationView.setCheckedItem(R.id.rodas);
            }
        });

        imgbicos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BicosFragment Bicos = new BicosFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainframe, Bicos);
                ft.addToBackStack("pilha");
                ft.commit();
                navigationView.setCheckedItem(R.id.bicos);
            }
        });

        imgtaxa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TaxaFragment Taxa = new TaxaFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainframe, Taxa);
                ft.addToBackStack("pilha");
                ft.commit();
                navigationView.setCheckedItem(R.id.taxa);
            }
        });

        imgtorque.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TorqueFragment Torque = new TorqueFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainframe, Torque);
                ft.addToBackStack("pilha");
                ft.commit();
                navigationView.setCheckedItem(R.id.torque);
            }
        });

        imgsobre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ConfigFragment Config= new ConfigFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mainframe, Config);
                ft.addToBackStack("pilha");
                ft.commit();
                navigationView.setCheckedItem(R.id.configuracoes);
            }
        });
    }

}