package br.com.gpistore.calculosautomotivos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class VmpFragment extends Fragment implements View.OnTouchListener {
        View view;
        EditText campo_curso, campo_rotacao;
        TextView campo_valor_vmp,campo_nome_vmp;
        Button btncalcular;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vmp, container, false);


        btncalcular = (Button) view.findViewById(R.id.btncalcular);
        campo_curso = (EditText)    view.findViewById(R.id.txtcursopistao);
        campo_rotacao = (EditText)     view.findViewById(R.id.txtrotacao);

        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        });

        view.findViewById(R.id.mainlayout).setOnTouchListener(this);
        // Inflate the layout for this fragment
        return view;
    }

    public void onStart() {
        super.onStart();

    }

    public void calcular(){
        NumberFormat mascara_vmp = new DecimalFormat("#.##");

        campo_valor_vmp = (TextView) view.findViewById(R.id.txtvalorvmp);
        campo_nome_vmp = (TextView) view.findViewById(R.id.txtnmvmp);

        if (campo_curso.getText().toString().length() == 0) {
            Toast.makeText(getContext(),getString(R.string.erro_curso_pistao), Toast.LENGTH_LONG).show();
        } else {

            if (campo_rotacao.getText().toString().length() == 0) {
                Toast.makeText(getContext(), getString(R.string.erro_rotacao), Toast.LENGTH_LONG).show();
            } else {
                double rotacao = Double.valueOf(campo_rotacao.getText().toString());
                double curso = Double.valueOf((campo_curso.getText().toString()));
                //CALCULO DO VMP
                double vmp = (curso * rotacao) / 30000;


                campo_valor_vmp.setText(mascara_vmp.format(vmp) + " m/s");
                campo_nome_vmp.setText(getString(R.string.VMP));

            }

        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        return false;
    }

}
