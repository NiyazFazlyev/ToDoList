package ru.job4j.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<Item> items = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new ItemAdapter(this.items);

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.items);
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    public void addItemBtn(View view) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Item item = new Item(data.getExtras().getString("name"), data.getExtras().getString("desc"), System.currentTimeMillis());
                this.items.add(item);
                System.out.println("New Item");
                adapter.notifyDataSetChanged();
//                adapter.notifyItemInserted(this.items.size() - 1);
            }
        }
    }

    private static final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<Item> items;

        public ItemAdapter(List<Item> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.info_item, parent, false)
            ) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
            final Item item = this.items.get(i);
            TextView info = holder.itemView.findViewById(R.id.info);
            info.setText(item.getName());
            TextView desc = holder.itemView.findViewById(R.id.desc);
            desc.setText(item.getDesc());
            TextView created = holder.itemView.findViewById(R.id.created);
            created.setText(String.valueOf(item.getCreated()));
            TextView closed = holder.itemView.findViewById(R.id.closed);
            closed.setText(String.valueOf(item.getClosed()));
        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }

    }


}
