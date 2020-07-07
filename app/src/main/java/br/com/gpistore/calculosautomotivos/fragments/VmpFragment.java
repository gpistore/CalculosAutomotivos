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

public class VmpFragment extends Fragment implements View.OnTouchListener {
        View view;
        TextInputLayout campo_curso, campo_rotacao;
        TextView lbl_vmp;
        LinearLayout layout_result;
        Button btncalcular;
        NumberFormat mascara_vmp;
        ArrayList<TextInputLayout> ListaCampos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vmp, container, false);
        this.getActivity().setTitle(R.string.opt_vmp);
        setup();
        setupActions();
        view.findViewById(R.id.mainlayout).setOnTouchListener(this);
        return view;
    }

    public void onStart() {
        super.onStart();
    }

    private void setup(){
        btncalcular =   (Button)    view.findViewById(R.id.btncalcular);
        campo_curso =   (TextInputLayout)  view.findViewById(R.id.vmp_txtcurso);
        campo_rotacao = (TextInputLayout)  view.findViewById(R.id.vmp_txtrotacao);
        lbl_vmp =       (TextView)  view.findViewById(R.id.vmp_lblvmp);
        layout_result =     (LinearLayout)      view.findViewById(R.id.layout_result);
        mascara_vmp = new DecimalFormat("#.##");
        ListaCampos = new ArrayList<TextInputLayout>();
        ListaCampos.add(campo_curso);
        ListaCampos.add(campo_rotacao);
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

    public void calcular(){
        if (!utils.validar(ListaCampos,getActivity())) {
            //valores
            double rotacao = Double.valueOf(campo_rotacao.getEditText().getText().toString());
            double curso = Double.valueOf((campo_curso.getEditText().getText().toString()));
            //CALCULO DO VMP
            double vmp = (curso * rotacao) / 30000;
            lbl_vmp.setText(getString(R.string.vmp_vmp)+" "+mascara_vmp.format(vmp) + " m/s");
            layout_result.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        utils.escondeTeclado(getActivity());
        return false;
    }

}