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
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.text.DecimalFormat;
        import java.text.NumberFormat;

        import static br.com.gpistore.calculosautomotivos.R.id.linha_vlpistao;


public class TorqueFragment extends Fragment implements View.OnTouchListener {
    View view;
    EditText campo_potencia,campo_rotacao;
    TextView campo_resultado,lbl_potencia;
    Button btncalcular;
    Boolean fgpot = true,fgtorque = false;
    Spinner tipocalculo;
    ArrayAdapter<String> SpinertipocalculoAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_torque, container, false);


        btncalcular = (Button) view.findViewById(R.id.btncalcular);
        campo_resultado = (TextView) view.findViewById(R.id.txtvlresultado);
        campo_potencia = (EditText)    view.findViewById(R.id.txtpotencia);
        campo_rotacao = (EditText)    view.findViewById(R.id.txtrotmaxima);
        lbl_potencia = (TextView) view.findViewById(R.id.lblpotencia);
        String[] arrayTipoCalculo = {getString(R.string.potxtorque),getString(R.string.torquexpot)};
        tipocalculo = (Spinner) view.findViewById(R.id.spntipo);
        SpinertipocalculoAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, arrayTipoCalculo);
        tipocalculo.setAdapter(SpinertipocalculoAdapter);

        tipocalculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    lbl_potencia.setText(getString(R.string.potencia));
                    fgtorque = false;
                    fgpot = true;
                    campo_potencia.setText("");
                    campo_resultado.setText("");
                } else {
                    lbl_potencia.setText(getString(R.string.campo_torque));
                    fgtorque = true;
                    fgpot = false;
                    campo_potencia.setText("");
                    campo_resultado.setText("");
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
        // Inflate the layout for this fragment
        return view;
    }

    public void onStart() {
        super.onStart();

    }

    public void calcular(){

        NumberFormat mascara_result  = new DecimalFormat("#.##");
        if (campo_potencia.getText().toString().length() == 0) {
            Toast.makeText(getContext(), getString(R.string.erro_potencia), Toast.LENGTH_LONG).show();
        } else {
            if (campo_rotacao.getText().toString().length() == 0) {
                Toast.makeText(getContext(), getString(R.string.erro_rotacao), Toast.LENGTH_LONG).show();
            } else {
                double potencia = Double.valueOf(campo_potencia.getText().toString());
                double rotacao = Double.valueOf((campo_rotacao.getText().toString()));
                double result;
                double PI2 =  2*3.14159265359;

                if (fgtorque){
                    potencia = potencia*9.80665;
                    result = ((potencia * PI2 * rotacao)/60000)*1.3596216;
                    campo_resultado.setText(getString(R.string.potencia)+mascara_result.format(result));
                }else{
                    potencia = potencia*0.7354988;
                    result = (potencia * 60000)/(PI2 * rotacao)*0.101972;
                    // result = constante * potencia / rotacao;
                    campo_resultado.setText(getString(R.string.campo_torque)+mascara_result.format(result));
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