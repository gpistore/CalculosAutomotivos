
package br.com.gpistore.calculosautomotivos.fragments;

import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import br.com.gpistore.calculosautomotivos.R;
import common.utils;


public class TaxaFragment extends Fragment implements View.OnTouchListener {
    View view;
    TextInputLayout campo_curso,campo_diam,campo_vlpistao, campo_vlcamara, campo_junta;
    TextView lbl_valor_taxa;
    LinearLayout layout_result;
    int cdtipo =0;

    Button btncalcular;
    Spinner tipopistao;
    ArrayAdapter<String> SpinerPistaoAdapter;
    ArrayList<TextInputLayout> ListaCampos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_taxa, container, false);
        this.getActivity().setTitle(R.string.opt_taxa);

        btncalcular = (Button) view.findViewById(R.id.btncalcular);
        campo_curso = (TextInputLayout) view.findViewById(R.id.taxa_txtcurso);
        campo_diam= (TextInputLayout)    view.findViewById(R.id.taxa_txtdiametro);
        campo_vlpistao= (TextInputLayout)    view.findViewById(R.id.taxa_volumedomo);
        campo_vlcamara= (TextInputLayout)    view.findViewById(R.id.taxa_txtvolumecamara);
        campo_junta = (TextInputLayout)    view.findViewById(R.id.taxa_txtespessura);
        lbl_valor_taxa = (TextView) view.findViewById(R.id.lbl_result);
        layout_result = (LinearLayout) view.findViewById(R.id.layout_result);
        tipopistao = (Spinner) view.findViewById(R.id.taxa_spnpistao);
        SpinerPistaoAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_taxa_pistao));
        tipopistao.setAdapter(SpinerPistaoAdapter);

            tipopistao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i == 0){
                        cdtipo = 0;
                        campo_vlpistao.setVisibility(View.GONE);
                    }else{
                        campo_vlpistao.setVisibility(View.VISIBLE);
                        if(i == 1){
                            cdtipo = 1;
                            campo_vlpistao.setHint(getString(R.string.taxa_volumecava));
                        }else{
                            cdtipo = 2;
                            campo_vlpistao.setHint(getString(R.string.taxa_volumedomo));
                        }
                    }
                }
//
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

        btncalcular.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                calcular();
            }
        });

        view.findViewById(R.id.mainlayout).setOnTouchListener(this);
        return view;
    }

    public void onStart() {
        super.onStart();

    }

    public void calcular() {
        NumberFormat mascara_taxa  = new DecimalFormat("#.##");
        ListaCampos = new ArrayList<TextInputLayout>();
        ListaCampos.add(campo_curso);
        ListaCampos.add(campo_diam);
        ListaCampos.add(campo_junta);
        ListaCampos.add(campo_vlcamara);

        if(cdtipo > 0){
            ListaCampos.add(campo_vlpistao);
        }

        if (!utils.validar(ListaCampos, getActivity())) {

            //PI
            double PI = 3.14159265359;
            //Calculo do volume da junta
            double volumepistao = 0;
            double diam = Double.valueOf(campo_diam.getEditText().getText().toString());
            double curso = Double.valueOf((campo_curso.getEditText().getText().toString()));
            double espessura = Double.valueOf((campo_junta.getEditText().getText().toString()));
            double volumecamara = Double.valueOf((campo_vlcamara.getEditText().getText().toString()));
            double cilindrada = ((PI * (diam * diam)) * curso) / 4000;
            double volumejunta = ((PI * (diam * diam)) * espessura) / 4000;

            switch (cdtipo) {
                case 0: {
                    break;
                }
                case 1: {
                    ListaCampos.add(campo_vlpistao);
                    //cava
                    volumepistao = Double.valueOf((campo_vlpistao.getEditText().getText().toString()));
                    break;
                }
                case 2: {
                    ListaCampos.add(campo_vlpistao);
                    //domo
                    volumepistao = Double.valueOf((campo_vlpistao.getEditText().getText().toString()));
                    volumepistao = volumepistao * -1;
                    break;
                }
            }

            volumecamara += volumejunta + volumepistao;
            double taxa = (cilindrada + volumecamara) / volumecamara;
            lbl_valor_taxa.setText(getString(R.string.taxa_result) + ": " + mascara_taxa.format(taxa) + ":1");
            layout_result.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        utils.escondeTeclado(getActivity());
        return false;
    }
}





