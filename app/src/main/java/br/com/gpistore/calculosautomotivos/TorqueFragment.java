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


public class TorqueFragment extends Fragment implements View.OnTouchListener {
    View view;
    EditText campo_potencia,campo_rotacao;
    TextView campo_resultado;
    Button btncalcular;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_torque, container, false);


        btncalcular = (Button) view.findViewById(R.id.btncalcular);
        campo_potencia = (EditText)    view.findViewById(R.id.txtpotencia);
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
        // Inflate the layout for this fragment
        return view;
    }

    public void onStart() {
        super.onStart();

    }

    public void calcular(){
        campo_resultado = (TextView) view.findViewById(R.id.txtvlresultado);

        



    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        return false;
    }

}