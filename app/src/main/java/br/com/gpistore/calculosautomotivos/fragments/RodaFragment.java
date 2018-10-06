package br.com.gpistore.calculosautomotivos.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import br.com.gpistore.calculosautomotivos.R;
import common.utils;


public class RodaFragment extends Fragment implements View.OnTouchListener {
    View view;
    TextInputLayout campo_largura1,campo_largura2, campo_altura1,campo_altura2, campo_aro1, campo_aro2;
    TextView lbl_roda1, lbl_roda2,lbl_diferenca;
    NumberFormat mascara_diametro;
    NumberFormat mascara_diferenca;
    ArrayList<TextInputLayout> ListaCampos;
    Button btncalcular;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_roda, container, false);
        this.getActivity().setTitle(R.string.opt_rodas);
        setup();
        setupActions();
        view.findViewById(R.id.mainlayout).setOnTouchListener(this);
        return view;
    }

    private void setupActions(){
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

    public void calcular() {
        if (!utils.validar(ListaCampos,getActivity())) {
            double larg1 = Double.valueOf((campo_largura1.getEditText().getText().toString()));
            double larg2 = Double.valueOf((campo_largura2.getEditText().getText().toString()));
            double aro1 =  Double.valueOf(campo_aro1.getEditText().getText().toString());
            double aro2 =  Double.valueOf(campo_aro2.getEditText().getText().toString());
            double alt1 =  Double.valueOf(campo_altura1.getEditText().getText().toString());
            double alt2 =  Double.valueOf(campo_altura2.getEditText().getText().toString());

            double diam1 = ((larg1/10)*(alt1/100)*2) + (aro1 * 2.54);
            double diam2 = ((larg2/10)*(alt2/100)*2) + (aro2 * 2.54);
            double dif = (diam2 - diam1)*100/diam1;

            lbl_roda1.setText(getString(R.string.roda_diamori)+" "+mascara_diametro.format(diam1)+" cm");
            lbl_roda2.setText(getString(R.string.roda_diamalt)+" "+mascara_diametro.format(diam2)+" cm");
            lbl_diferenca.setText(getString(R.string.roda_diferenca)+" "+mascara_diferenca.format(dif)+"%");
            if (dif > 5.0 || dif < -5.0 ){
                lbl_diferenca.setTextColor(Color.RED);
            }else {
                lbl_diferenca.setTextColor(Color.BLACK);
            }
        }
    }

    private void setup(){
        btncalcular =       (Button)            view.findViewById(R.id.btncalcular);
        campo_largura1 =    (TextInputLayout)   view.findViewById(R.id.roda_txtlargura1);
        campo_largura2 =    (TextInputLayout)   view.findViewById(R.id.roda_txtlargura2);
        campo_altura1 =     (TextInputLayout)   view.findViewById(R.id.roda_txtaltura1);
        campo_altura2 =     (TextInputLayout)   view.findViewById(R.id.roda_txtaltura2);
        campo_aro1 =        (TextInputLayout)   view.findViewById(R.id.roda_txtaro1);
        campo_aro2 =        (TextInputLayout)   view.findViewById(R.id.roda_txtaro2);

        ListaCampos = new ArrayList<TextInputLayout>();
        ListaCampos.add(campo_largura1);
        ListaCampos.add(campo_altura1);
        ListaCampos.add(campo_aro1);
        ListaCampos.add(campo_largura2);
        ListaCampos.add(campo_altura2);
        ListaCampos.add(campo_aro2);

        mascara_diametro = new DecimalFormat("###.##");
        mascara_diferenca = new DecimalFormat("##.##");

        lbl_roda1 = (TextView) view.findViewById(R.id.roda_lbloriginal);
        lbl_roda2 = (TextView) view.findViewById(R.id.roda_lblalterado);
        lbl_diferenca = (TextView) view.findViewById(R.id.roda_lbldiferenca);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        utils.escondeTeclado(getActivity());
        return false;
    }
}

