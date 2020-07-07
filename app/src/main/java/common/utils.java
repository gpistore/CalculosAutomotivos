package common;

import android.app.Activity;
import android.content.Context;
import com.google.android.material.textfield.TextInputLayout;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.gpistore.calculosautomotivos.R;

/**
 * Created by thetu on 01/07/2017.
 */

public class utils {

    public static boolean validar (ArrayList<TextInputLayout> campos,Context c){
        ArrayList<String> lista = new ArrayList<>();
        TextView label;
        boolean retorno = false;
        for( TextInputLayout campo : campos ) {
            if (campo.getEditText().getText().toString().length() == 0) {
            campo.setError(c.getString(R.string.obrigatorio));
            retorno = true;
            }else{
                campo.setError(null);
                campo.setErrorEnabled(false);
            }
        }
        return retorno;
    }

    public static boolean validarRotacaoMinima(TextInputLayout rotacao,Context c){

        if(Double.valueOf(rotacao.getEditText().getText().toString())>1000.0){
            return false;
        }else{
            rotacao.setError(c.getString(R.string.erro_rotacao_minima));
            return true;
        }


    }


    public static void escondeTeclado(Activity A){
        InputMethodManager imm = (InputMethodManager) A.getSystemService(A.getApplicationContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(A.getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }


}
