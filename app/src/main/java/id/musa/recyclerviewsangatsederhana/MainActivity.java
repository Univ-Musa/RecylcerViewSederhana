package id.musa.recyclerviewsangatsederhana;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.musa.recyclerviewsangatsederhana.adapters.SuperHeroAdapter;
import id.musa.recyclerviewsangatsederhana.models.SuperHero;

public class MainActivity extends AppCompatActivity implements SuperHeroAdapter.OnHeroActionListener {

    RecyclerView rvSuperHero;
    EditText etHeroName;
    Button btnTambah;
    List<SuperHero> listSuperHero = new ArrayList<>();
    SuperHeroAdapter superHeroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSuperHero = findViewById(R.id.rvSuperHero);
        etHeroName = findViewById(R.id.etHeroName);
        btnTambah = findViewById(R.id.btnTambah);

        // Data awal
        listSuperHero.add(new SuperHero("Petruk"));
        listSuperHero.add(new SuperHero("Gareng"));

        superHeroAdapter = new SuperHeroAdapter(listSuperHero, this);
        rvSuperHero.setAdapter(superHeroAdapter);
        rvSuperHero.setLayoutManager(new LinearLayoutManager(this));

        // Tombol Tambah
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaBaru = etHeroName.getText().toString().trim();
                if (namaBaru.isEmpty()) {
                    namaBaru = "Bagong";
                }
                superHeroAdapter.addHero(new SuperHero(namaBaru));
                etHeroName.setText("");
                rvSuperHero.scrollToPosition(listSuperHero.size() - 1);
            }
        });
    }

    // Aksi ketika tombol Edit pada item diklik
    @Override
    public void onEditClick(int position, SuperHero hero) {
        EditText inputEdit = new EditText(this);
        inputEdit.setText(hero.getHeroName());

        new AlertDialog.Builder(this)
                .setTitle("Edit SuperHero")
                .setView(inputEdit)
                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String namaUpdate = inputEdit.getText().toString().trim();
                        if (!namaUpdate.isEmpty()) {
                            superHeroAdapter.updateHero(position, new SuperHero(namaUpdate));
                            Toast.makeText(MainActivity.this, "Data diperbarui", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    // Aksi ketika tombol Hapus pada item diklik
    @Override
    public void onDeleteClick(int position) {
        superHeroAdapter.removeHero(position);
        Toast.makeText(this, "Data dihapus", Toast.LENGTH_SHORT).show();
    }
}