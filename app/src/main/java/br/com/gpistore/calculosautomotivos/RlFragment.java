package br.com.gpistore.calculosautomotivos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
    TextView campo_cilindrada, campo_rl;
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
            }
        });

        view.findViewById(R.id.mainlayout).setOnTouchListener(this);


        return view;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void onStart() {
        super.onStart();

    }

    public void calcular(){
        //Formatação dos números de cilindrada e R/L
        NumberFormat mascara_cil = new DecimalFormat("#.##");
        NumberFormat mascara_rl = new DecimalFormat("#.###");

        campo_cilindrada = (TextView) view.findViewById(R.id.txtcilindrada);
        campo_rl = (TextView) view.findViewById(R.id.txtrl);

        if (campo_curso.getText().toString().length() == 0) {
            campo_curso.setError("Preencha o Curso do Virabrequim!");
        } else {

            if (campo_comprimento.getText().toString().length() == 0) {
                campo_comprimento.setError("Preencha o Comprimento das Bielas!");
            } else {

                if (campo_diametro.getText().toString().length() == 0) {
                    campo_diametro.setError("Preencha o Diâmetro do Pistão!");
                } else {

                    if (campo_cilindros.getText().toString().length() == 0) {
                        campo_cilindros.setError("Preencha a Quantidade de Cilindros!");
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

                        campo_cilindrada.setText(mascara_cil.format(cilindrada));
                        campo_rl.setText(mascara_rl.format(RL));

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







