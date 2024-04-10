package br.fmu.aula4an;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Funcionario f = new Funcionario();

        System.out.println(f);
    }
}