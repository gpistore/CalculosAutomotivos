package br.com.gpistore.calculosautomotivos.fragments;

import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.gpistore.calculosautomotivos.R;
import common.utils;


public class BicosFragment extends Fragment implements View.OnTouchListener {
    View view;
    TextInputLayout campo_nrinjetores;
    TextInputLayout campo_potencia;
    TextView lbl_valor;
    LinearLayout layout_result;
    Spinner spn_comb;
    ArrayAdapter<String> spn_combAdapter;
    Spinner spn_tipo;
    ArrayAdapter<String> spn_tipoAdapter;
    Spinner spn_motor;
    ArrayAdapter<String> spn_motorAdapter;
    Spinner spn_capacidade;
    ArrayAdapter<String> spn_capacidadeAdapter;
    ArrayList<TextInputLayout> ListaCampos;

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
        lbl_valor =         (TextView) view.findViewById(R.id.bicos_lblvalor);
        btncalcular =       (Button) view.findViewById(R.id.btncalcular);
        layout_result =     (LinearLayout)      view.findViewById(R.id.layout_result);

        spn_tipo = view.findViewById(R.id.torque_spntipo);
        spn_tipoAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_bicos_calculo_tipo));
        spn_tipo.setAdapter(spn_tipoAdapter);
        spn_tipo.setPrompt(getString(R.string.bicos_calculo_tipo));

        spn_comb = view.findViewById(R.id.bicos_spncomb);
        spn_combAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_bicos_combustivel));
        spn_comb.setAdapter(spn_combAdapter);
        spn_comb.setPrompt(getString(R.string.bicos_combustivel));

        spn_motor = view.findViewById(R.id.bicos_spnmotor);
        spn_motorAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_bicos_motor));
        spn_motor.setAdapter(spn_motorAdapter);
        spn_motor.setPrompt(getString(R.string.bicos_tipo_motor));

        spn_capacidade = view.findViewById(R.id.bicos_spncapacidade);
        spn_capacidadeAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_bicos_capac));
        spn_capacidade.setAdapter(spn_capacidadeAdapter);
        spn_capacidade.setPrompt(getString(R.string.bicos_capacidade));

        spn_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    tipocalc = 0;
                    campo_potencia.setHint(getString(R.string.bicos_potencia));
                }else{
                    tipocalc = 1;
                    campo_potencia.setHint(getString(R.string.bicos_vazao));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               return;
            }
        });
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

    private void calcular() {
        ListaCampos = new ArrayList<TextInputLayout>();
        ListaCampos.add(campo_potencia);
        ListaCampos.add(campo_nrinjetores);

        if (!utils.validar(ListaCampos, getActivity())) {
            //Valores

                double vltipo =0;
                double vlcomb =0;
                double vlcapacidade = 0;
                double vlpotencia = Double.valueOf(campo_potencia.getEditText().getText().toString());
                double vlnrbicos= Double.valueOf(campo_nrinjetores.getEditText().getText().toString());
                int seletorcomb = spn_comb.getSelectedItemPosition();
                switch (seletorcomb){
                    case 0: {vlcomb = 1.4;break;}
                    case 1: {vlcomb = 1;break;}
                    case 2: {vlcomb = 2.1;break;}
                }

                int seletorcapacidade = spn_capacidade.getSelectedItemPosition();
                switch (seletorcapacidade){
                    case 0: { vlcapacidade = 0.8;break;}
                    case 1: { vlcapacidade = 0.9;break;}
                    case 2: { vlcapacidade = 1.0;break;}
                }

                int seletortipomotor = spn_motor.getSelectedItemPosition();
                switch (seletortipomotor){
                    case 0: { vltipo = 0.5;break;}
                    case 1: { vltipo = 0.6;break;}
                }

                switch (tipocalc){
                    case 0: {
                        double lbhr = Math.ceil((vlpotencia * vltipo * vlcomb)/(vlnrbicos * vlcapacidade));
                        double ccmin = Math.ceil(lbhr*10.5);
                        lbl_valor.setText(getString(R.string.bicos_ccmin)+" "+String.valueOf(ccmin)+"\n"+getString(R.string.bicos_lbhr)+" "+String.valueOf(lbhr));
                        break;
                    }

                    case 1: {
                        //alteração do nome da varíavel para facilitar o entendimento da memória de cálculo
                        double vltamanho = vlpotencia;
                        double potencia = Math.floor((vlnrbicos * vlcapacidade *vltamanho)/ (vltipo * vlcomb));
                        lbl_valor.setText(getString(R.string.bicos_potencia)+" "+String.valueOf(potencia));
                        break;
                    }
                }

                layout_result.setVisibility(View.VISIBLE);




        }
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        utils.escondeTeclado(getActivity());
        return false;
    }

}

