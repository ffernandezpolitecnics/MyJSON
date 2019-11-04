package com.example.myjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myjson.Clases.Pelicula;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Pelicula> peliculas;

    private RecyclerView recyclerViewPeliculas;
    private RecyclerView.Adapter adapterPeliculas;
    private RecyclerView.LayoutManager layoutManager;

    private int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, getFilesDir().toString(), Toast.LENGTH_LONG).show();

        try {
            peliculas = getPeliculas();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        recyclerViewPeliculas = findViewById(R.id.recyclerViewPelicula);
        layoutManager = new LinearLayoutManager(this);
        adapterPeliculas = new PeliculaAdapter(peliculas, R.layout.recycler_view_item, new PeliculaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pelicula pelicula, int position) {
                //Toast.makeText(MainActivity.this, pelicula.getTitulo() + " - " + pelicula.getDirector(), Toast.LENGTH_LONG).show();
                deletePelicula(position);
            }
        });

        recyclerViewPeliculas.setHasFixedSize(true);
        recyclerViewPeliculas.setItemAnimator(new DefaultItemAnimator());

        recyclerViewPeliculas.setLayoutManager(layoutManager);
        recyclerViewPeliculas.setAdapter(adapterPeliculas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean retorno;

        switch (item.getItemId()) {
            case R.id.add_pelicula:
                this.addPelicula(0);
                retorno = true;

            default:
                retorno  = super.onOptionsItemSelected(item);
        }

        return retorno;
    }

    private void addPelicula(int position) {

//        Pelicula pelicula = new Pelicula("Titulo" + contador, "Director" + contador, 2000,
//                new ArrayList<String>() {{
//                    add("Tema 1");
//                    add("Tema 2");
//                }
//
//                });




        adapterPeliculas.notifyItemInserted(position);
        layoutManager.scrollToPosition(position);

    }

    private void deletePelicula (int position) {
        peliculas.remove(position);
        adapterPeliculas.notifyItemRemoved(position);

    }

    private List<Pelicula> getPeliculas() throws FileNotFoundException {


        File fichero = new File(getExternalFilesDir(null), "peliculas.json");

        Gson gson = new Gson();
        //Toast.makeText(this, getExternalFilesDir(null).toString(), Toast.LENGTH_LONG).show();
        BufferedReader br = new BufferedReader(new FileReader(fichero));
        Type type = new TypeToken<List<Pelicula>>(){}.getType();
        List<Pelicula> peliculas = gson.fromJson(br, type);
        return peliculas;
    }
}
