package br.com.gpistore.calculosautomotivos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class ConfigFragment extends Fragment implements View.OnTouchListener {

    View view;
    Button btncompartilhar;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_config, container, false);
        btncompartilhar = (Button) view.findViewById(R.id.btncompartilhar);

        btncompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent shareIntent = new Intent(Intent.ACTION_SEND);
             shareIntent.setType("text/plain");
             shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Compartilhar");
             shareIntent.putExtra(Intent.EXTRA_TEXT, "Conheça o aplicativo Calculadora automotiva.\n https://play.google.com/store/apps/details?id=br.com.gpistore.calculosautomotivos");
             startActivity(shareIntent);
            }
        });


        view.findViewById(R.id.mainlayout).setOnTouchListener(this);

        return view;
    }

    public void onStart() {
        super.onStart();

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        return false;
    }
}

