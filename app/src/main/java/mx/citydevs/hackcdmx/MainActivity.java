package mx.citydevs.hackcdmx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by zace3d on 3/7/15.
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private AlertDialog customDialog= null;	//Creamos el dialogo generico

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }


    private void initUI() {
        findViewById(R.id.main_btn_officer).setOnClickListener(this);
        findViewById(R.id.main_btn_infraction).setOnClickListener(this);
        findViewById(R.id.iv_acerca_de).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_acerca_de:
                mostrarAercaDe().show();
                break;
            case R.id.main_btn_officer:
                startOfficersIntent();

                break;
            case R.id.main_btn_infraction:
                startInfractionsIntent();
                break;
        }
    }

    private void startOfficersIntent() {
        Intent intent = new Intent(getBaseContext(), OfficersActivity.class);
        startActivity(intent);
    }

    private void startInfractionsIntent() {
        Intent intent = new Intent(getBaseContext(), InfractionsActivity.class);
        startActivity(intent);
    }

    /**
     * Dialogo que muestra el acerca de
     *
     * @return Dialog (regresa el dialogo creado)
     **/
    public Dialog mostrarAercaDe()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialogo_acercade, null);
        builder.setView(view);
        builder.setCancelable(true);
        //escucha del boton aceptar
        ((Button) view.findViewById(R.id.dialogo_acercade_btnAceptar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                customDialog.dismiss();
            }
        });
        return (customDialog=builder.create());// return customDialog;//regresamos el di�logo
    }
}
