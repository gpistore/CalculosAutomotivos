package br.com.gpistore.calculosautomotivos.fragments;

import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import java.util.ArrayList;

import br.com.gpistore.calculosautomotivos.R;
import common.utils;


public class CambioFragment extends Fragment implements View.OnTouchListener {
    View view;
    TextInputLayout campo_largura,campo_altura,campo_aro,campo_pri,campo_seg,campo_ter,campo_qua,campo_qui,campo_sex,campo_dif,campo_rotacao;
    Button btncalcular;
    ArrayList<TextInputLayout> ListaCampos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cambio, container, false);
        this.getActivity().setTitle(R.string.opt_cambio);
        btncalcular = (Button) view.findViewById(R.id.btncalcular);

        campo_largura = (TextInputLayout)    view.findViewById(R.id.cambio_txtlargura);
        campo_altura = (TextInputLayout)    view.findViewById(R.id.cambio_txtaltura);
        campo_aro = (TextInputLayout)    view.findViewById(R.id.cambio_txtaro);
        campo_pri = (TextInputLayout)    view.findViewById(R.id.cambio_txtprimeira);
        campo_seg = (TextInputLayout)    view.findViewById(R.id.cambio_txtsegunda);
        campo_ter = (TextInputLayout)    view.findViewById(R.id.cambio_txtterceira);
        campo_qua = (TextInputLayout)    view.findViewById(R.id.cambio_txtquarta);
        campo_qui = (TextInputLayout)    view.findViewById(R.id.cambio_txtquinta);
        campo_sex = (TextInputLayout)    view.findViewById(R.id.cambio_txtsexta);
        campo_dif = (TextInputLayout)    view.findViewById(R.id.cambio_txtdiferencial);
        campo_rotacao = (TextInputLayout) view.findViewById(R.id.cambio_txtrotacao);



        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
                utils.escondeTeclado(getActivity());

            }
        });



        view.findViewById(R.id.mainlayout).setOnTouchListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null){
            campo_largura.getEditText().setText(savedInstanceState.getString("lar"));
            campo_altura.getEditText().setText(savedInstanceState.getString("alt"));
            campo_aro.getEditText().setText(savedInstanceState.getString("aro"));
            campo_pri.getEditText().setText(savedInstanceState.getString("pri"));
            campo_seg.getEditText().setText(savedInstanceState.getString("seg"));
            campo_ter.getEditText().setText(savedInstanceState.getString("ter"));
            campo_qua.getEditText().setText(savedInstanceState.getString("qua"));
            campo_qui.getEditText().setText(savedInstanceState.getString("qui"));
            campo_sex.getEditText().setText(savedInstanceState.getString("sex"));
            campo_dif.getEditText().setText(savedInstanceState.getString("dif"));
            campo_rotacao.getEditText().setText(savedInstanceState.getString("rot"));
        }
    }


    public void onStart() {
        super.onStart();

    }

    public void calcular() {
        ListaCampos = new ArrayList<TextInputLayout>();
        ListaCampos.add(campo_largura);
        ListaCampos.add(campo_altura);
        ListaCampos.add(campo_aro);
        ListaCampos.add(campo_pri);
        ListaCampos.add(campo_dif);
        ListaCampos.add(campo_rotacao);
        if (!utils.validar(ListaCampos, getActivity()) && !utils.validarRotacaoMinima(campo_rotacao,getActivity())) {

                Bundle bundle = new Bundle();
                bundle.putString("pri",campo_pri.getEditText().getText().toString());
                bundle.putString("seg",campo_seg.getEditText().getText().toString());
                bundle.putString("ter",campo_ter.getEditText().getText().toString());
                bundle.putString("qua",campo_qua.getEditText().getText().toString());
                bundle.putString("qui",campo_qui.getEditText().getText().toString());
                bundle.putString("sex",campo_sex.getEditText().getText().toString());
                bundle.putString("dif",campo_dif.getEditText().getText().toString());
                bundle.putString("rot",campo_rotacao.getEditText().getText().toString());
                bundle.putString("lar",campo_largura.getEditText().getText().toString());
                bundle.putString("alt",campo_altura.getEditText().getText().toString());
                bundle.putString("aro",campo_aro.getEditText().getText().toString());

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            ResultcambioFragment resultcambioFragment = new ResultcambioFragment();
            resultcambioFragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.mainframe,resultcambioFragment);
            fragmentTransaction.addToBackStack("pilha");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putString("pri",campo_pri.getEditText().getText().toString());
        outstate.putString("seg",campo_seg.getEditText().getText().toString());
        outstate.putString("ter",campo_ter.getEditText().getText().toString());
        outstate.putString("qua",campo_qua.getEditText().getText().toString());
        outstate.putString("qui",campo_qui.getEditText().getText().toString());
        outstate.putString("sex",campo_sex.getEditText().getText().toString());
        outstate.putString("dif",campo_dif.getEditText().getText().toString());
        outstate.putString("rot",campo_rotacao.getEditText().getText().toString());
        outstate.putString("lar",campo_largura.getEditText().getText().toString());
        outstate.putString("alt",campo_altura.getEditText().getText().toString());
        outstate.putString("aro",campo_aro.getEditText().getText().toString());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        utils.escondeTeclado(getActivity());
        return false;
    }
}