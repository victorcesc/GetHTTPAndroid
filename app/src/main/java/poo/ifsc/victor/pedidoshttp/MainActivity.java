package poo.ifsc.victor.pedidoshttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private TextView mtextView;
    private EditText meditTextMatricula;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mtextView = (TextView) findViewById(R.id.textViewResultado);
        meditTextMatricula = (EditText) findViewById(R.id.editTextMaticula);
        mButton = (Button) findViewById(R.id.button);
    }


    //volley é como se fosse uma fila de gets http
    public void pedidoGET(View view){
        RequestQueue fila = Volley.newRequestQueue(this);


        String url= "http://191.36.9.85:5000/matricula/"+meditTextMatricula.getText().toString();

        //4 parametros 2 classes implementadas no proprio parametro : response.listener response.errorListener.

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mtextView.setText(response);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mButton.setEnabled(true);

                Toast toast = Toast.makeText(getApplicationContext(),"Belezz",Toast.LENGTH_LONG);
                toast.show();


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mtextView.setText("Erro:" + error.toString());
                mButton.setEnabled(true);
                //pode tratar o erro, usando o switch case
            }
        }
        );

        fila.add(stringRequest);
        //desabilitando botao ate a resposta
        mButton.setEnabled(false);



    }






}
