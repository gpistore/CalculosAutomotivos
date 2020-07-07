package br.com.gpistore.calculosautomotivos.fragments;

import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
        navigationView = ((MainActivity) getActivity()).navigationView;
        ads = ((MainActivity) getActivity()).ads;
        this.getActivity().setTitle(R.string.app_name);
        crialista();
        setup();
        setupactions();

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

    public void setupactions(){
        ((comp_main_list_adapter) listaAdapter).setOnItemClickListener(new comp_main_list_adapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                switch(position){
                    case 0: {
                        BicosFragment Bicos = new BicosFragment();
                        ft.replace(R.id.mainframe, Bicos);
                        ft.addToBackStack("pilha");
                        ads.mostra();
                        navigationView.setCheckedItem(R.id.bicos);
                        ft.commit();
                        break;
                    }
                    case 1: {
                        CambioFragment cambio = new CambioFragment();
                        ft.replace(R.id.mainframe, cambio);
                        ft.addToBackStack("pilha");
                        ads.mostra();
                        navigationView.setCheckedItem(R.id.cambio);
                        ft.commit();
                        break;
                    }
                    case 2: {
                        RlFragment RL = new RlFragment();
                        ft.replace(R.id.mainframe, RL);
                        ft.addToBackStack("pilha");
                        ads.mostra();
                        navigationView.setCheckedItem(R.id.rl);
                        ft.commit();
                        break;
                    }
                    case 3: {
                        RodaFragment Roda = new RodaFragment();
                        ft.replace(R.id.mainframe, Roda);
                        ft.addToBackStack("pilha");
                        ads.mostra();
                        navigationView.setCheckedItem(R.id.rodas);
                        ft.commit();
                        break;
                    }
                    case 4: {
                        TaxaFragment Taxa = new TaxaFragment();
                        ft.replace(R.id.mainframe, Taxa);
                        ft.addToBackStack("pilha");
                        ads.mostra();
                        navigationView.setCheckedItem(R.id.taxa);
                        ft.commit();
                        break;
                    }
                    case 5: {
                        TorqueFragment Torque = new TorqueFragment();
                        ft.replace(R.id.mainframe, Torque);
                        ft.addToBackStack("pilha");
                        ads.mostra();
                        navigationView.setCheckedItem(R.id.torque);
                        ft.commit();
                        break;
                    }
                    case 6: {
                        VmpFragment VMP = new VmpFragment();
                        ft.replace(R.id.mainframe, VMP);
                        ft.addToBackStack("pilha");
                        ads.mostra();
                        navigationView.setCheckedItem(R.id.vmp);
                        ft.commit();
                        break;
                    }
                    case 7: {
                        ConfigFragment Config= new ConfigFragment();
                        ft.replace(R.id.mainframe, Config);
                        ft.addToBackStack("pilha");
                        ads.mostra();
                        navigationView.setCheckedItem(R.id.configuracoes);
                        ft.commit();
                        break;
                    }
                }
            }
        });
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