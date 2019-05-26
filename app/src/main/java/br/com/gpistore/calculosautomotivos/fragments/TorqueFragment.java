package br.com.gpistore.calculosautomotivos.fragments;

        import android.os.Bundle;
        import android.support.design.widget.TextInputLayout;
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

        import java.text.DecimalFormat;
        import java.text.NumberFormat;
        import java.util.ArrayList;

        import br.com.gpistore.calculosautomotivos.R;
        import common.utils;


public class TorqueFragment extends Fragment implements View.OnTouchListener {
    View view;
    TextInputLayout campo_potencia;
    TextInputLayout campo_rotacao;
    TextView lbl_valor;
    LinearLayout layout_result;
    Button btncalcular;
    Spinner spn_tipo;
    ArrayAdapter<String> spiner_tipo_adapter;
    ArrayList<TextInputLayout> ListaCampos;
    int tipocalc = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_torque, container, false);
        this.getActivity().setTitle(R.string.opt_torque);
        setup();
        setupaction();
        return view;
    }

    private void setup() {
        btncalcular = (Button) view.findViewById(R.id.btncalcular);
        campo_potencia = (TextInputLayout) view.findViewById(R.id.torque_txtpotencia);
        campo_rotacao = (TextInputLayout) view.findViewById(R.id.torque_txtrotacao);
        lbl_valor = (TextView) view.findViewById(R.id.torque_lbl_result);
        layout_result = (LinearLayout) view.findViewById(R.id.layout_result);
        spn_tipo = (Spinner) view.findViewById(R.id.torque_spntipo);
        spiner_tipo_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_torque_tipo));
        spn_tipo.setAdapter(spiner_tipo_adapter);
        spn_tipo.setPrompt(getString(R.string.torque_tipo));

        spn_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    tipocalc = 0;
                    campo_potencia.setHint(getString(R.string.torque_potencia));
                } else {
                    tipocalc = 1;
                    campo_potencia.setHint(getString(R.string.torque_torque));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        view.findViewById(R.id.mainlayout).setOnTouchListener(this);
        // Inflate the layout for this fragment
    }

        private void setupaction(){

            btncalcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calcular();
                    utils.escondeTeclado(getActivity());
                }
            });

    }

    public void onStart() {
        super.onStart();

    }

    public void calcular() {
        NumberFormat mascara_result  = new DecimalFormat("#.##");

        ListaCampos = new ArrayList<TextInputLayout>();
        ListaCampos.add(campo_potencia);
        ListaCampos.add(campo_rotacao);

        if (!utils.validar(ListaCampos, getActivity())) {
            double potencia = Double.valueOf(campo_potencia.getEditText().getText().toString());
            double rotacao = Double.valueOf((campo_rotacao.getEditText().getText().toString()));
            double result;
            double PI2 =  2*3.14159265359;

            if (tipocalc == 0){
                potencia = potencia*0.7354988;
                result = (potencia * 60000)/(PI2 * rotacao)*0.101972;
                // result = constante * potencia / rotacao;
                lbl_valor.setText(getString(R.string.torque_torque)+" "+mascara_result.format(result));
            }else{
                potencia = potencia*9.80665;
                result = ((potencia * PI2 * rotacao)/60000)*1.3596216;
                lbl_valor.setText(getString(R.string.torque_potencia)+" "+mascara_result.format(result));
            }
            layout_result.setVisibility(View.VISIBLE);
        }

    }

    /*


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
                    campo_resultado.setText(getString(R.string.bicos_potencia)+mascara_result.format(result));
                }else{
                    potencia = potencia*0.7354988;
                    result = (potencia * 60000)/(PI2 * rotacao)*0.101972;
                    // result = constante * potencia / rotacao;
                    campo_resultado.setText(getString(R.string.campo_torque)+mascara_result.format(result));
                }

            }
        }
    }
*/
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        return false;
    }

}