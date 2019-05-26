package common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import br.com.gpistore.calculosautomotivos.R;

public class Avaliacao {
    int nracessos;
    Context context;
    SharedPreferences prefs;

    public  Avaliacao(Context c){
        context = c;
        // Verifica quantidade de acessos para mostrar a opção de avaliar
        prefs = c.getSharedPreferences("pref", Context.MODE_PRIVATE);
        nracessos = prefs.getInt("qtdacessos",0);
        if (nracessos >=0) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("qtdacessos", nracessos + 1);
            editor.apply();
        }
    }

    public void mostraAvaliacao(){
        if(nracessos > 5){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle(context.getString(R.string.popup_classifique));
            alertDialog.setMessage(context.getString(R.string.texto_classifique));
            alertDialog.setPositiveButton(context.getString(R.string.btnpos), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id="+context.getPackageName()));
                    context.startActivity(intent);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("qtdacessos", -1);
                    editor.apply();
                }
            });
            alertDialog.setNegativeButton(context.getString(R.string.btnneg), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("qtdacessos", -1);
                    editor.apply();
                }
            });
            alertDialog.setNeutralButton(context.getString(R.string.btnneu), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("qtdacessos", 0);
                    editor.apply();
                }
            }).setIcon(R.mipmap.ic_launcher).create().show();
        }
    }

}
