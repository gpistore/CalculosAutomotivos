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


public class CambioFragment extends Fragment implements View.OnTouchListener {
    View view;
    EditText campo_largura,campo_altura,campo_aro,campo_pri,campo_seg,campo_ter,campo_qua,campo_qui,campo_sex,campo_dif,campo_rotacao;
    TextView campo_valor_cambio;
    Button btncalcular;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cambio, container, false);

        btncalcular = (Button) view.findViewById(R.id.btncalcular);

        campo_largura = (EditText)    view.findViewById(R.id.txtnmlargura);
        campo_altura = (EditText)    view.findViewById(R.id.txtnmaltura);
        campo_aro = (EditText)    view.findViewById(R.id.txtnmaro);
        campo_pri = (EditText)    view.findViewById(R.id.txtnmpri);
        campo_seg = (EditText)    view.findViewById(R.id.txtnmseg);
        campo_ter = (EditText)    view.findViewById(R.id.txtnmter);
        campo_qua = (EditText)    view.findViewById(R.id.txtnmqua);
        campo_qui = (EditText)    view.findViewById(R.id.txtnmqui);
        campo_sex = (EditText)    view.findViewById(R.id.txtnmsex);
        campo_dif = (EditText)    view.findViewById(R.id.txtdiferencial);
        campo_rotacao = (EditText)    view.findViewById(R.id.txtrotacaomaxima);

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

        NumberFormat mascara_vel = new DecimalFormat("###.##");

        campo_valor_cambio = (TextView) view.findViewById(R.id.txtvalorcambio);

        if (campo_largura.getText().toString().length() == 0) {
            Toast.makeText(getContext(), getString(R.string.erro_largura), Toast.LENGTH_LONG).show();
        }else{
            if (campo_altura.getText().toString().length() == 0) {
                Toast.makeText(getContext(), getString(R.string.erro_altura), Toast.LENGTH_LONG).show();
            }else{
                if (campo_aro.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), getString(R.string.erro_aro), Toast.LENGTH_LONG).show();
                }else{
                    if (campo_pri.getText().toString().length() == 0) {
                        Toast.makeText(getContext(), getString(R.string.erro_pri), Toast.LENGTH_LONG).show();
                    }else{
                        if (campo_seg.getText().toString().length() == 0) {
                            Toast.makeText(getContext(), getString(R.string.erro_seg), Toast.LENGTH_LONG).show();
                        }else{
                            if (campo_ter.getText().toString().length() == 0) {
                                Toast.makeText(getContext(), getString(R.string.erro_ter), Toast.LENGTH_LONG).show();
                            }else{
                                if (campo_qua.getText().toString().length() == 0) {
                                    Toast.makeText(getContext(), getString(R.string.erro_qua), Toast.LENGTH_LONG).show();
                                }else{
                                    if (campo_qui.getText().toString().length() == 0) {
                                        Toast.makeText(getContext(), getString(R.string.erro_qui), Toast.LENGTH_LONG).show();
                                    }else{
                                        if (campo_sex.getText().toString().length() == 0) {
                                            Toast.makeText(getContext(), getString(R.string.erro_sex), Toast.LENGTH_LONG).show();
                                        }else{
                                            if (campo_dif.getText().toString().length() == 0) {
                                                Toast.makeText(getContext(), getString(R.string.erro_dif), Toast.LENGTH_LONG).show();
                                            }else{
                                                if (campo_rotacao.getText().toString().length() == 0) {
                                                    Toast.makeText(getContext(), getString(R.string.erro_rotacao), Toast.LENGTH_LONG).show();
                                                }else{

                                                    double larg = Double.valueOf(campo_largura.getText().toString());
                                                    double alt = Double.valueOf(campo_altura.getText().toString());
                                                    double aro = Double.valueOf(campo_aro.getText().toString());
                                                    double pri = Double.valueOf(campo_pri.getText().toString());
                                                    double seg = Double.valueOf(campo_seg.getText().toString());
                                                    double ter = Double.valueOf(campo_ter.getText().toString());
                                                    double qua = Double.valueOf(campo_qua.getText().toString());
                                                    double qui = Double.valueOf(campo_qui.getText().toString());
                                                    double sex = Double.valueOf(campo_sex.getText().toString());
                                                    double dif = Double.valueOf(campo_dif.getText().toString());
                                                    double rotacao = Double.valueOf(campo_rotacao.getText().toString());
                                                    double circ = ((((larg/10)*(alt/100)*2) + (aro * 2.54))/100)* 3.14159265359;
                                                    double cons = circ * rotacao * 0.06;

                                                    double vel_pri = cons/ (dif*pri);
                                                    double vel_seg = cons/ (dif*seg);
                                                    double vel_ter = cons/ (dif*ter);
                                                    double vel_qua = cons/ (dif*qua);
                                                    double vel_qui = cons/ (dif*qui);
                                                    double vel_sex = cons/ (dif*sex);

                                                    campo_valor_cambio.setText( getString(R.string.resultado_velocidade)+'\n'
                                                                                +getString(R.string.primeira)+" "+mascara_vel.format(vel_pri)+'\n'
                                                                                +getString(R.string.segunda)+" "+mascara_vel.format(vel_seg)+'\n'
                                                                                +getString(R.string.terceira)+" "+mascara_vel.format(vel_ter)+'\n'
                                                                                +getString(R.string.quarta)+" "+mascara_vel.format(vel_qua)+'\n'
                                                                                +getString(R.string.quinta)+" "+mascara_vel.format(vel_qui)+'\n'
                                                                                +getString(R.string.sexta)+" "+mascara_vel.format(vel_sex)+'\n');
                                                }
                                            }
                                        }
                                    }
                                }
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