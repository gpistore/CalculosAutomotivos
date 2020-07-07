package br.com.gpistore.calculosautomotivos.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import br.com.gpistore.calculosautomotivos.R;
import common.utils;


public class ResultcambioFragment extends Fragment implements View.OnTouchListener {
    View view;
    Double cons;
    TextView lbl_pri,lbl_seg,lbl_ter,lbl_qua,lbl_qui,lbl_sex;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_resultcambio, container, false);
        this.getActivity().setTitle(R.string.opt_cambio);
        Bundle bundle = getArguments();
        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Km/h");
        graph.getGridLabelRenderer().setVerticalAxisTitle("RPM (x1000)");
        Double velmax = 0.0;

        NumberFormat mascara_vel = new DecimalFormat("###.##");

        String pri = bundle.getString("pri");
        String seg = bundle.getString("seg");
        String ter = bundle.getString("ter");
        String qua = bundle.getString("qua");
        String qui = bundle.getString("qui");
        String sex = bundle.getString("sex");
        String dif = bundle.getString("dif");
        String rot = bundle.getString("rot");
        String lar = bundle.getString("lar");
        String alt = bundle.getString("alt");
        String aro = bundle.getString("aro");
        Double rotmax = Double.valueOf(rot)/1000.0;
        lbl_pri = (TextView) view.findViewById(R.id.cambio_resultpri);
        lbl_seg = (TextView) view.findViewById(R.id.cambio_resultseg);
        lbl_ter = (TextView) view.findViewById(R.id.cambio_resultter);
        lbl_qua = (TextView) view.findViewById(R.id.cambio_resultqua);
        lbl_qui = (TextView) view.findViewById(R.id.cambio_resultqui);
        lbl_sex = (TextView) view.findViewById(R.id.cambio_resultsex);

        double circ = ((((Double.valueOf(lar)/10)*(Double.valueOf(alt)/100)*2) + (Double.valueOf(aro) * 2.54))/100)* 3.14159265359;
        cons = (circ * 0.06)/Double.valueOf(dif);
        if(!pri.isEmpty() && Double.valueOf(pri)> 0.0){
            Double vel = getVelocidade(pri,rot);
            velmax = vel;
            LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(getVelocidade(pri,"1000"), 1),
                    new DataPoint(vel,rotmax),
            });
            graph.addSeries(series1);
            lbl_pri.setText("1ª: "+mascara_vel.format(vel));
        }else{
            lbl_pri.setText("1ª: -");
        }



        if(!seg.isEmpty() && Double.valueOf(seg)> 0.0){
            Double vel = getVelocidade(seg,rot);
            if(vel > velmax){
                velmax = vel;
            }
            LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(getVelocidade(seg,"1000"), 1),
                    new DataPoint(vel, rotmax),
            });

            graph.addSeries(series2);
            lbl_seg.setText("2ª: "+mascara_vel.format(vel));
        }else{
            lbl_seg.setText("2ª: -");
        }
        if(!ter.isEmpty()&& Double.valueOf(ter)> 0.0){
            Double vel = getVelocidade(ter,rot);
            if(vel > velmax){
                velmax = vel;
            }

            LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(getVelocidade(ter,"1000"), 1),
                    new DataPoint(vel, rotmax),
            });
            graph.addSeries(series3);
            lbl_ter.setText("3ª: "+mascara_vel.format(vel));
        }else{
            lbl_ter.setText("3ª: -");
        }
        if(!qua.isEmpty() && Double.valueOf(qua)> 0.0){
            Double vel = getVelocidade(qua,rot);
            if(vel > velmax){
                velmax = vel;
            }
            LineGraphSeries<DataPoint> series4 = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(getVelocidade(qua,"1000"), 1),
                    new DataPoint(vel, rotmax),
            });
            graph.addSeries(series4);
            lbl_qua.setText("4ª: "+mascara_vel.format(vel));
        }else{
            lbl_qua.setText("4ª: -");
        }
        if(!qui.isEmpty() && Double.valueOf(qui)> 0.0){
            Double vel = getVelocidade(qui,rot);
            if(vel > velmax){
                velmax = vel;
            }
            LineGraphSeries<DataPoint> series5 = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(getVelocidade(qui,"1000"), 1),
                    new DataPoint(vel, rotmax),
            });
            graph.addSeries(series5);
            lbl_qui.setText("5ª: "+mascara_vel.format(vel));
        }else{
            lbl_qui.setText("5ª: -");
        }
        if(!sex.isEmpty() && Double.valueOf(sex)> 0.0){
            Double vel = getVelocidade(sex,rot);
            if(vel > velmax){
                velmax = vel;
            }
            LineGraphSeries<DataPoint> series6 = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(getVelocidade(sex,"1000"), 1),
                    new DataPoint(vel, rotmax),
            });
            graph.addSeries(series6);
            lbl_sex.setText("6ª: "+mascara_vel.format(vel));
        }else{
            lbl_sex.setText("6ª: -");
        }

        graph.getViewport().setMaxX(velmax+20);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxY(rotmax+5);
        graph.getViewport().setScalable(true);
        view.findViewById(R.id.mainlayout).setOnTouchListener(this);
        return view;
    }

    public void onStart() {
        super.onStart();

    }
    private Double getVelocidade(String rel, String rot){
        Double relacao = Double.valueOf(rel);
        Double rotacao = Double.valueOf(rot);
        Double vel = cons * rotacao/ relacao;
        return vel;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        utils.escondeTeclado(getActivity());
        return false;
    }
}