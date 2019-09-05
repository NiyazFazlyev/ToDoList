package ru.job4j.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);

    }

    public void addBtn(View view) {
        EditText name = findViewById(R.id.editName);
        EditText desc = findViewById(R.id.editDesc);
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("desc", desc.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
