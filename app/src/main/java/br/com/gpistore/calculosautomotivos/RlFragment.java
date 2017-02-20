package br.com.gpistore.calculosautomotivos;

import android.os.Build;
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


public class RlFragment extends Fragment implements View.OnTouchListener {
    View view;
    EditText campo_cilindros, campo_diametro, campo_comprimento, campo_curso;
    TextView campo_valor_cilindrada, campo_valor_rl,campo_nome_rl,campo_nome_cilindrada;
    Button btncalcular;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
           view = inflater.inflate(R.layout.fragment_rl, container, false);

            btncalcular = (Button) view.findViewById(R.id.btncalcular);
            campo_cilindros = (EditText)    view.findViewById(R.id.txtcilindros);
            campo_diametro = (EditText)     view.findViewById(R.id.txtdiametro);
            campo_comprimento = (EditText)  view.findViewById(R.id.txtbielas);
            campo_curso = (EditText)        view.findViewById(R.id.txtvirabrequim);

            btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        });

        view.findViewById(R.id.mainlayout).setOnTouchListener(this);


        return view;
    }

    public void onStart() {
        super.onStart();

    }

    public void calcular(){
        //Formatação dos números de cilindrada e R/L
        NumberFormat mascara_cil = new DecimalFormat("#.##");
        NumberFormat mascara_rl = new DecimalFormat("#.###");

        campo_valor_cilindrada = (TextView) view.findViewById(R.id.txtvalorcilindrada);
        campo_valor_rl = (TextView) view.findViewById(R.id.txtvalorrl);
        campo_nome_rl = (TextView) view.findViewById(R.id.txtnomerl);
        campo_nome_cilindrada = (TextView) view.findViewById(R.id.txtnomecilindrada);

            if (campo_curso.getText().toString().length() == 0) {
                Toast.makeText(getContext(), getString(R.string.erro_vira), Toast.LENGTH_LONG).show();
            } else {

                if (campo_comprimento.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), getString(R.string.erro_bielas), Toast.LENGTH_LONG).show();
                } else {

                if (campo_diametro.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), getString(R.string.erro_bielas), Toast.LENGTH_LONG).show();
                } else {

                    if (campo_cilindros.getText().toString().length() == 0) {
                        Toast.makeText(getContext(), getString(R.string.erro_cilindros), Toast.LENGTH_LONG).show();
                    } else {
                        double cilindros = Double.valueOf(campo_cilindros.getText().toString());
                        double comp = Double.valueOf((campo_comprimento.getText().toString()));
                        double diam = Double.valueOf(campo_diametro.getText().toString());
                        double curso = Double.valueOf((campo_curso.getText().toString()));
                        //CALCULO DO RL
                        double RL = (curso / 2) / comp;

                        //CALCULO DA CILINDRADA

                        double PI = 3.14159265359;
                        double cilindrada = ((((PI * (diam * diam)) / 4) * curso) * cilindros) / 1000;
                        campo_valor_cilindrada.setText(mascara_cil.format(cilindrada));
                        campo_valor_rl.setText(mascara_rl.format(RL));
                        campo_nome_cilindrada.setText(getString(R.string.cilindrada));
                        campo_nome_rl.setText(getString(R.string.RL));


                    }
                }
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







