package br.fmu.aula4an;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseActivity extends AppCompatActivity {

    private EditText editTextRe;
    private EditText editTextNome;
    private EditText editTextDataAdmissao;
    private EditText editTextsalario;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        editTextRe = findViewById(R.id.editTextRe);
        editTextNome = findViewById(R.id.editTextNome);
        editTextDataAdmissao = findViewById(R.id.editTextDataAdmissao);
        editTextsalario = findViewById(R.id.editTextSalario);
    }

    private void limpar() {
        editTextRe.getText().clear();
        editTextNome.getText().clear();
        editTextDataAdmissao.getText().clear();
        editTextsalario.getText().clear();
    }

    public void cadastrar(View view) {
        AppDatabase db = AppDatabase.getInstance(this);
        FuncionarioDao dao = db.funcionarioDao();
        int re = Integer.parseInt(editTextRe.getText().toString());
        String nome = editTextNome.getText().toString();
        Date dataAdmissao;
        try {
            dataAdmissao = dateFormat.parse(editTextDataAdmissao.getText().toString());
        } catch ( ParseException e ) {
            dataAdmissao = new Date();
        }
        double salario = Double.parseDouble(editTextsalario.getText().toString());
        Funcionario f = new Funcionario(re,nome,dataAdmissao,salario);
        if ( view.getId() == R.id.buttonCadastrar ) {
            dao.insert(f);
        } else if (view.getId() == R.id.buttonExcluir ) {
            dao.delete(f);
        } else if (view.getId() == R.id.buttonAtualizar ) {
            dao.update(f);
        }
        limpar();
    }

    public void buscar( View view ) {
        AppDatabase db = AppDatabase.getInstance(this);
        FuncionarioDao dao = db.funcionarioDao();
        int re = Integer.parseInt(editTextRe.getText().toString());
        Funcionario f = dao.buscarPeloRe(re);
        editTextNome.setText(f.getNome());
        editTextDataAdmissao.setText(dateFormat.format(f.getDataAdmissao()));
        editTextsalario.setText(Double.toString(f.getSalario()));
    }
}