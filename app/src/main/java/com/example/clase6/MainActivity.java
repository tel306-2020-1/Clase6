package com.example.clase6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView progressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressTextView = findViewById(R.id.progressValue);
    }

    public void calcularBtn(View view){

        EditText editTextX = findViewById(R.id.editTextX);
        EditText editTextY = findViewById(R.id.editTextY);
        EditText editTextN = findViewById(R.id.editTextN);

        Long x = Long.valueOf(editTextX.getText().toString());
        Long y = Long.valueOf(editTextY.getText().toString());
        Long n = Long.valueOf(editTextN.getText().toString());

        ContadorWorker contadorWorker = new ContadorWorker();
        contadorWorker.execute(x,y,n);
    }

    class ContadorWorker extends AsyncTask<Long, Integer, Long> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "Iniciando contador", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Long aLong) {
            Toast.makeText(MainActivity.this, "Resultado final: " + aLong, Toast.LENGTH_SHORT).show();
            super.onPostExecute(aLong);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            progressTextView.setText(String.valueOf(values[0]));
        }

        @Override
        protected Long doInBackground(Long... longs) {

            Long x = longs[0];
            Long y = longs[1];
            Long n = longs[2];

            Long resultado = 1L;

            for (int i = 1; i <= n; i++) {
                resultado = resultado * (x * y);
                int progreso = (int) ((i / (float) n) * 100);
                publishProgress(progreso);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }

            return resultado;

        }
    }

}
