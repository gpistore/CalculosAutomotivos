package br.com.gpistore.calculosautomotivos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class BicosFragment extends Fragment implements View.OnTouchListener {
    View view;
    EditText txtnrinjetores, txtpotencia;
    TextView txtvalorlbhr,txtvalorccmin;
    Spinner comb,tipo,Capacidade;
    Button btncalcular;
    ArrayAdapter<String> SpinerCombAdapter,SpinerCapacidadeAdapter,SpinerTipoAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bicos, container, false);

        String[] arrayCombustivel = {getString(R.string.comb1),getString(R.string.comb2),getString(R.string.comb3)};
        comb = (Spinner) view.findViewById(R.id.spinnercomb);
        SpinerCombAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, arrayCombustivel);
        comb.setAdapter(SpinerCombAdapter);
        String[] arrayCapacidade = {"80%","90%","100%"};
        Capacidade = (Spinner) view.findViewById(R.id.spinnercapacidade);
        SpinerCapacidadeAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, arrayCapacidade);
        Capacidade.setAdapter(SpinerCapacidadeAdapter);
        String[] arrayTipo = {getString(R.string.tipo1),getString(R.string.tipo2)};
        tipo = (Spinner) view.findViewById(R.id.spinnertipo);
        SpinerTipoAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, arrayTipo);
        tipo.setAdapter(SpinerTipoAdapter);

        txtnrinjetores = (EditText)    view.findViewById(R.id.txtnrinjetores);
        txtpotencia = (EditText)    view.findViewById(R.id.txtpotencia);

        btncalcular = (Button) view.findViewById(R.id.btncalcular);

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

        if (txtpotencia.getText().toString().length() == 0) {
            Toast.makeText(getContext(), getString(R.string.erro_potencia), Toast.LENGTH_LONG).show();
        }else{
            if (txtnrinjetores.getText().toString().length() == 0) {
                Toast.makeText(getContext(), getString(R.string.erro_injetores), Toast.LENGTH_LONG).show();
            }else{
                double vltipo =0;
                double vlcomb =0;
                double vlcapacidade = 0;
                double vlpotencia = Double.valueOf(txtpotencia.getText().toString());
                double vlnrbicos = Double.valueOf(txtnrinjetores.getText().toString());
                int seletorcomb = comb.getSelectedItemPosition();
                switch (seletorcomb){
                    case 0: {vlcomb = 1.4;break;}
                    case 1: {vlcomb = 1;break;}
                    case 2: {vlcomb = 2.1;break;}
                }
                int seletortipo = tipo.getSelectedItemPosition();
                switch (seletortipo){
                    case 0: { vltipo = 0.5;break;}
                    case 1: { vltipo = 0.6;break;}
                }
                int seletorcapacidade = Capacidade.getSelectedItemPosition();
                switch (seletorcapacidade){
                    case 0: { vlcapacidade = 0.8;break;}
                    case 1: { vlcapacidade = 0.9;break;}
                    case 2: { vlcapacidade = 1.0;break;}
                }

                double lbhr = Math.ceil((vlpotencia * vltipo * vlcomb)/(vlnrbicos * vlcapacidade));
                double ccmin = Math.ceil(lbhr*10.5);
                //vl_dimbicos_lbhr = ( (vl_pot_mot * vl_tip_motor * vl_tip_combust) / (vl_nbicos * vl_cap_utilizada) );

                txtvalorccmin= (TextView) view.findViewById(R.id.txtvalorccmin);
                txtvalorlbhr= (TextView) view.findViewById(R.id.txtvalorlbhr);
                txtvalorccmin.setText(getString(R.string.ccmin)+" "+String.valueOf(ccmin));
                txtvalorlbhr.setText(getString(R.string.lbhr)+" "+String.valueOf(lbhr));
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

