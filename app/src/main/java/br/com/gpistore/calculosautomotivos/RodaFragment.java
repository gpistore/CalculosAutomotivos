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


public class RodaFragment extends Fragment implements View.OnTouchListener {
    View view;
    EditText campo_largura1,campo_largura2, campo_altura1,campo_altura2, campo_aro1, campo_aro2;
    TextView campo_valor_roda1, campo_valor_roda2,campo_valor_diferenca;
    Button btncalcular;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_roda, container, false);

        btncalcular = (Button) view.findViewById(R.id.btncalcularroda);
        campo_largura1 = (EditText)    view.findViewById(R.id.txtlargura1);
        campo_largura2 = (EditText)    view.findViewById(R.id.txtlargura2);
        campo_altura1 = (EditText)    view.findViewById(R.id.txtaltura1);
        campo_altura2 = (EditText)    view.findViewById(R.id.txtaltura2);
        campo_aro1 = (EditText)    view.findViewById(R.id.txtaro1);
        campo_aro2 = (EditText)    view.findViewById(R.id.txtaro2);

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

    public void calcular() {
        NumberFormat mascara_diametro = new DecimalFormat("###.##");
        NumberFormat mascara_diferenca = new DecimalFormat("##.##");

        campo_valor_roda1 = (TextView) view.findViewById(R.id.txtvalorroda1);
        campo_valor_roda2 = (TextView) view.findViewById(R.id.txtvalorroda2);
        campo_valor_diferenca = (TextView) view.findViewById(R.id.txtdiferenca);

        if (campo_largura1.getText().toString().length() == 0) {
            Toast.makeText(getContext(),getString(R.string.erro_largura1) , Toast.LENGTH_LONG).show();
        } else {
            if (campo_altura1.getText().toString().length() == 0) {
                Toast.makeText(getContext(),getString(R.string.erro_altura1), Toast.LENGTH_LONG).show();
            } else {
                if (campo_aro1.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), getString(R.string.erro_aro1), Toast.LENGTH_LONG).show();
                } else {
                    if (campo_largura2.getText().toString().length() == 0) {
                        Toast.makeText(getContext(), getString(R.string.erro_largura2), Toast.LENGTH_LONG).show();
                    } else {
                        if (campo_altura2.getText().toString().length() == 0) {
                            Toast.makeText(getContext(), getString(R.string.erro_altura2), Toast.LENGTH_LONG).show();
                        } else {
                            if (campo_aro2.getText().toString().length() == 0) {
                                Toast.makeText(getContext(), getString(R.string.erro_aro2), Toast.LENGTH_LONG).show();
                            } else {
                                double larg1 = Double.valueOf((campo_largura1.getText().toString()));
                                double alt1 = Double.valueOf(campo_altura1.getText().toString());
                                double aro1 = Double.valueOf(campo_aro1.getText().toString());
                                double larg2 = Double.valueOf((campo_largura2.getText().toString()));
                                double alt2 = Double.valueOf(campo_altura2.getText().toString());
                                double aro2 = Double.valueOf(campo_aro2.getText().toString());

                                double diam1 = ((larg1/10)*(alt1/100)*2) + (aro1 * 2.54);
                                double diam2 = ((larg2/10)*(alt2/100)*2) + (aro2 * 2.54);

                                double dif = (diam2 - diam1)*100/diam1;


                                campo_valor_roda1.setText("Diâmetro 1: "+mascara_diametro.format(diam1)+" cm");
                                campo_valor_roda2.setText("Diâmetro 2: "+mascara_diametro.format(diam2)+" cm");
                                campo_valor_diferenca.setText("Diferença: "+mascara_diferenca.format(dif)+"%");

                            }
                        }
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

