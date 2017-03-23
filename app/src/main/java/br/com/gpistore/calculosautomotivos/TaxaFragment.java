
package br.com.gpistore.calculosautomotivos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class TaxaFragment extends Fragment implements View.OnTouchListener {
    View view;
    EditText campo_curso,campo_diam,campo_vlpistao, campo_vlcamara, campo_junta;
    TextView campo_valor_taxa,lblvolumepistao;
    LinearLayout linha_vlpistao;
    Button btncalcular;
    Spinner tipopistao;
    boolean fgcava,fgdomo;
    ArrayAdapter<String> SpinerPistaoAdapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_taxa, container, false);
        lblvolumepistao = (TextView) view.findViewById(R.id.lblvolumepistao);
        linha_vlpistao = (LinearLayout) view.findViewById(R.id.linha_vlpistao);
        btncalcular = (Button) view.findViewById(R.id.btncalcular);
        campo_curso = (EditText)    view.findViewById(R.id.txtcurso);
        campo_diam= (EditText)    view.findViewById(R.id.txtdiam);
        campo_vlpistao= (EditText)    view.findViewById(R.id.txtvlpistao);
        campo_vlcamara= (EditText)    view.findViewById(R.id.txtvlcamara);
        campo_junta = (EditText)    view.findViewById(R.id.txtjunta);

        String[] arrayTipoPistao = {getString(R.string.plano),getString(R.string.concavo),getString(R.string.domo)};
        tipopistao = (Spinner) view.findViewById(R.id.spnpistao);
        SpinerPistaoAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, arrayTipoPistao);
        tipopistao.setAdapter(SpinerPistaoAdapter);

            tipopistao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i == 0){
                        linha_vlpistao.setVisibility(View.GONE);
                        fgdomo = false;
                        fgcava = false;
                    }else{
                        linha_vlpistao.setVisibility(View.VISIBLE);
                        if(i == 1){
                            lblvolumepistao.setText(getString(R.string.volume_cava));
                            fgdomo = false;
                            fgcava = true;
                        }else{
                            lblvolumepistao.setText(getString(R.string.volume_domo));
                            fgdomo = true;
                            fgcava = false;
                        }
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

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
        NumberFormat mascara_taxa  = new DecimalFormat("#.##");
        campo_valor_taxa = (TextView) view.findViewById(R.id.txtvalortaxa);

        if (campo_curso.getText().toString().length() == 0) {
            Toast.makeText(getContext(), getString(R.string.erro_vira), Toast.LENGTH_LONG).show();
        } else {
            if (campo_diam.getText().toString().length() == 0) {
                Toast.makeText(getContext(), getString(R.string.erro_diam), Toast.LENGTH_LONG).show();
            } else {
                if (campo_vlcamara.getText().toString().length() == 0) {
                  Toast.makeText(getContext(), getString(R.string.erro_volume_camara), Toast.LENGTH_LONG).show();
                } else {
                    if (campo_junta.getText().toString().length() == 0) {
                        Toast.makeText(getContext(), getString(R.string.erro_espessura_junta), Toast.LENGTH_LONG).show();
                    } else {
                        if ((fgdomo == true || fgcava == true) && campo_vlpistao.getText().toString().length() == 0) {
                            if (fgdomo == true){ Toast.makeText(getContext(), getString(R.string.erro_volume_domo), Toast.LENGTH_LONG).show();}
                            if(fgcava == true){Toast.makeText(getContext(), getString(R.string.erro_volume_cava), Toast.LENGTH_LONG).show();}
                        } else {
                        //PI
                            double PI = 3.14159265359;
                            //Calculo do volume da junta
                            double volumepistao = 0;
                            double diam = Double.valueOf(campo_diam.getText().toString());
                            double curso = Double.valueOf((campo_curso.getText().toString()));
                            double espessura = Double.valueOf((campo_junta.getText().toString()));
                            double volumecamara = Double.valueOf((campo_vlcamara.getText().toString()));
                            double cilindrada = ((PI * (diam * diam))* curso) / 4000;
                            double volumejunta = ((PI * (diam * diam))* espessura) / 4000;
                            if (fgdomo){
                                volumepistao = Double.valueOf((campo_vlpistao.getText().toString()));
                                volumepistao = volumepistao * -1;
                            }
                            if (fgcava) {
                                volumepistao = Double.valueOf((campo_vlpistao.getText().toString()));
                            }
                            volumecamara += volumejunta + volumepistao;
                            double taxa = (cilindrada + volumecamara)/volumecamara;

                            campo_valor_taxa.setText(getString(R.string.taxa)+": "+mascara_taxa.format(taxa)+":1");
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





