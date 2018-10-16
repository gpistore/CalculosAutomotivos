package br.com.gpistore.calculosautomotivos.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.com.gpistore.calculosautomotivos.R;
import common.utils;


public class BicosFragment extends Fragment implements View.OnTouchListener {
    View view;
    TextInputLayout campo_nrinjetores;
    TextInputLayout campo_potencia;
    TextView lbl_valor;
    Spinner spn_comb;
    ArrayAdapter<String> spn_combAdapter;
    Spinner spn_tipo;
    ArrayAdapter<String> spn_tipoAdapter;
    Spinner spn_motor;
    ArrayAdapter<String> spn_motorAdapter;
    Spinner spn_capacidade;
    ArrayAdapter<String> spn_capacidadeAdapter;

    Button btncalcular;
    int tipocalc = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bicos, container, false);
        this.getActivity().setTitle(R.string.opt_bicos);
        setup();
        setupAction();
        return view;
    }


    private void setup(){
        campo_nrinjetores = (TextInputLayout)   view.findViewById(R.id.bicos_txtinjetores);
        campo_potencia =    (TextInputLayout)   view.findViewById(R.id.bicos_txtpotencia);
        lbl_valor =         (TextView) view.findViewById(R.id.bicos_lblvalor);;
        btncalcular =       (Button) view.findViewById(R.id.btncalcular);

        spn_tipo =          (Spinner) view.findViewById(R.id.bicos_spntipo);
        spn_tipoAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_bicos_calculo_tipo));
        spn_tipo.setAdapter(spn_tipoAdapter);
        spn_tipo.setPrompt(getString(R.string.bicos_calculo_tipo));

        spn_comb =          (Spinner) view.findViewById(R.id.bicos_spncomb);
        spn_combAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_bicos_combustivel));
        spn_comb.setAdapter(spn_combAdapter);
        spn_comb.setPrompt(getString(R.string.bicos_combustivel));

        spn_motor =         (Spinner) view.findViewById(R.id.bicos_spnmotor);
        spn_motorAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_bicos_motor));
        spn_motor.setAdapter(spn_motorAdapter);
        spn_motor.setPrompt(getString(R.string.bicos_tipo_motor));

        spn_capacidade =    (Spinner) view.findViewById(R.id.bicos_spncapacidade);
        spn_capacidadeAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_bicos_capac));
        spn_capacidade.setAdapter(spn_capacidadeAdapter);
        spn_capacidade.setPrompt(getString(R.string.bicos_capacidade));

        //        tipocalculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i == 0) {
//                    tipocalc = 0;
//                    lblpotencia.setText(getString(R.string.potencia));
//                }else{
//                    tipocalc = 1;
//                    lblpotencia.setText(getString(R.string.img_bico));
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//               return;
//            }
//        });
        view.findViewById(R.id.mainlayout).setOnTouchListener(this);
        // Inflate the layout for this fragment


    }
    private void setupAction(){

        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
                utils.escondeTeclado(getActivity());
            }
        });
    }

    public void onStart() {
        super.onStart();

    }

    public void calcular(){


//        if (txtpotencia.getText().toString().length() == 0) {
            Toast.makeText(getContext(), getString(R.string.erro_potencia), Toast.LENGTH_LONG).show();
//        }else{
//            if (txtnrinjetores.getText().toString().length() == 0) {
//                Toast.makeText(getContext(), getString(R.string.erro_injetores), Toast.LENGTH_LONG).show();
//            }else{
//                double vltipo =0;
//                double vlcomb =0;
//                double vlcapacidade = 0;
//                double vlpotencia = Double.valueOf(txtpotencia.getText().toString());
//                double vlnrbicos = Double.valueOf(txtnrinjetores.getText().toString());
//                int seletorcomb = comb.getSelectedItemPosition();
//                switch (seletorcomb){
//                    case 0: {vlcomb = 1.4;break;}
//                    case 1: {vlcomb = 1;break;}
//                    case 2: {vlcomb = 2.1;break;}
//                }
//                int seletortipo = tipo.getSelectedItemPosition();
//                switch (seletortipo){
//                    case 0: { vltipo = 0.5;break;}
//                    case 1: { vltipo = 0.6;break;}
//                }
//                int seletorcapacidade = Capacidade.getSelectedItemPosition();
//                switch (seletorcapacidade){
//                    case 0: { vlcapacidade = 0.8;break;}
//                    case 1: { vlcapacidade = 0.9;break;}
//                    case 2: { vlcapacidade = 1.0;break;}
//                }
//
//                double lbhr = Math.ceil((vlpotencia * vltipo * vlcomb)/(vlnrbicos * vlcapacidade));
//                double ccmin = Math.ceil(lbhr*10.5);
//                //vl_dimbicos_lbhr = ( (vl_pot_mot * vl_tip_motor * vl_tip_combust) / (vl_nbicos * vl_cap_utilizada) );
//
//                txtvalorccmin.setText(getString(R.string.ccmin)+" "+String.valueOf(ccmin));
//                txtvalorlbhr.setText(getString(R.string.lbhr)+" "+String.valueOf(lbhr));
//            }
//        }
        }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        utils.escondeTeclado(getActivity());
        return false;
    }

}

