package br.com.gpistore.calculosautomotivos.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

import br.com.gpistore.calculosautomotivos.R;

public class ConfigFragment extends Fragment implements View.OnTouchListener {
    View view;
    LinearLayout compartilhar,avaliar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_config, container, false);
        this.getActivity().setTitle(R.string.opt_config);
        setup();
        setupAction();
        view.findViewById(R.id.mainlayout).setOnTouchListener(this);

        return view;
    }

    private void setup(){
        compartilhar = (LinearLayout) view.findViewById(R.id.layout_compartilhe);
        avaliar = (LinearLayout) view.findViewById(R.id.layout_clasifique);
    }

    private void setupAction(){
        compartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Compartilhar");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Conheça o aplicativo Calculadora automotiva.\n https://play.google.com/store/apps/details?id=br.com.gpistore.calculosautomotivos");
                startActivity(shareIntent);
            }
        });

        avaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=br.com.gpistore.calculosautomotivos"));
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        return false;
    }
}

