package br.com.gpistore.calculosautomotivos.fragments;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.gpistore.calculosautomotivos.MainActivity;
import br.com.gpistore.calculosautomotivos.R;
import common.ItemDecoration;
import common.Propaganda;
import common.comp_main_list_adapter;
import common.comp_main_list_item;

import static android.widget.LinearLayout.VERTICAL;

public class MainFragment extends Fragment {
    View view;
    java.util.List<comp_main_list_item> List;
    RecyclerView lista;
    RecyclerView.Adapter listaAdapter;
    RecyclerView.LayoutManager listamanager;
    NavigationView navigationView;
    Propaganda ads;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        ads = ((MainActivity) getActivity()).ads;
        this.getActivity().setTitle(R.string.app_name);
        crialista();
        setup();
        //setupactions();

        return view;
    }

    private void setup(){
        lista = (RecyclerView) view.findViewById(R.id.main_lista);
        lista.setHasFixedSize(true);

        listamanager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        lista.setLayoutManager(listamanager);
        listaAdapter = new comp_main_list_adapter(List,view.getContext());
        ItemDecoration Decorador = new ItemDecoration(view.getContext(),R.color.colorDivider,7);
        lista.addItemDecoration(Decorador);
        lista.setAdapter(listaAdapter);


    }

    private void setupactions(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

    }

    private void crialista(){
        this.List = new ArrayList<comp_main_list_item>();
        this.List.add(new comp_main_list_item(getContext(),R.string.opt_bicos,R.drawable.img_bico));
        this.List.add(new comp_main_list_item(getContext(),R.string.opt_cambio,R.drawable.img_driving_gear_controls));
        this.List.add(new comp_main_list_item(getContext(),R.string.opt_rl,R.drawable.img_bicycle_sprockets));
        this.List.add(new comp_main_list_item(getContext(),R.string.opt_rodas,R.drawable.img_cart_wheel));
        this.List.add(new comp_main_list_item(getContext(),R.string.opt_taxa,R.drawable.img_taxa));
        this.List.add(new comp_main_list_item(getContext(),R.string.opt_torque,R.drawable.img_car_speedometer));
        this.List.add(new comp_main_list_item(getContext(),R.string.opt_vmp,R.drawable.img_pistons_cross ));
        this.List.add(new comp_main_list_item(getContext(),R.string.opt_config,R.drawable.img_config));
    }

}