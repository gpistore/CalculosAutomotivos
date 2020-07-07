package br.com.gpistore.calculosautomotivos.fragments;

import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import br.com.gpistore.calculosautomotivos.R;
import common.utils;


public class RlFragment extends Fragment implements View.OnTouchListener {
    View view;
    TextInputLayout campo_cilindros, campo_diametro, campo_comprimento, campo_curso;
    TextView lbl_cilindrada, lbl_rl;
    LinearLayout layout_result;
    Button btncalcular;
    NumberFormat mascara_cil,mascara_rl;
    ArrayList<TextInputLayout> ListaCampos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rl, container, false);
        this.getActivity().setTitle(R.string.opt_rl);
        setup();
        setupActions();
        view.findViewById(R.id.mainlayout).setOnTouchListener(this);
        return view;
    }

    public void onStart() {
        super.onStart();

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

    private void setup(){

        btncalcular =       (Button)            view.findViewById(R.id.btncalcular);
        campo_cilindros =   (TextInputLayout)   view.findViewById(R.id.rl_txtcilindros);
        campo_diametro =    (TextInputLayout)   view.findViewById(R.id.rl_txtdiametro);
        campo_comprimento = (TextInputLayout)   view.findViewById(R.id.rl_txtbielas);
        campo_curso =       (TextInputLayout)   view.findViewById(R.id.rl_txtvirabrequim);
        lbl_cilindrada =    (TextView)          view.findViewById(R.id.rl_lblcilindrada);
        lbl_rl =            (TextView)          view.findViewById(R.id.rl_lblrl);
        layout_result =     (LinearLayout)      view.findViewById(R.id.layout_result);

        ListaCampos = new ArrayList<TextInputLayout>();
        ListaCampos.add(campo_cilindros);
        ListaCampos.add(campo_diametro);
        ListaCampos.add(campo_comprimento);
        ListaCampos.add(campo_curso);

        mascara_cil  = new DecimalFormat("#.##");
        mascara_rl  = new DecimalFormat("#.###");
    }

    public void calcular() {

        if (!utils.validar(ListaCampos,getActivity())) {
            //Valores

            double cilindros = Double.valueOf(campo_cilindros.getEditText().getText().toString());
            double comp = Double.valueOf((campo_comprimento.getEditText().getText().toString()));
            double diam = Double.valueOf(campo_diametro.getEditText().getText().toString());
            double curso = Double.valueOf((campo_curso.getEditText().getText().toString()));
            double PI = 3.14159265359;
            layout_result.setVisibility(View.VISIBLE);
            //CALCULO DO RL
            double RL = (curso / 2) / comp;
            lbl_rl.setText(getString(R.string.rl_rl) + " " + mascara_rl.format(RL));
            //CALCULO DA CILINDRADA
            double cilindrada = ((((PI * (diam * diam)) / 4) * curso) * cilindros) / 1000;
            lbl_cilindrada.setText(getString(R.string.rl_cilindrada) + " " + mascara_cil.format(cilindrada));
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        utils.escondeTeclado(getActivity());
        return false;
    }
}