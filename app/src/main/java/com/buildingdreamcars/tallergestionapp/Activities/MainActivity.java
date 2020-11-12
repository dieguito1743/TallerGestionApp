package com.buildingdreamcars.tallergestionapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.buildingdreamcars.tallergestionapp.DatosTemp.Preferencias;
import com.buildingdreamcars.tallergestionapp.Fragments.NewFicha;
import com.buildingdreamcars.tallergestionapp.Fragments.SeeAllFicha;
import com.buildingdreamcars.tallergestionapp.Fragments.VehiculoActual;
import com.buildingdreamcars.tallergestionapp.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView tvWelcome;
    Button btniracrear,btniraver,btnvergarage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_main);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        tvWelcome = findViewById(R.id.txtwelcome);
        tvWelcome.setText("Bienvenido seas "+ Preferencias.getNickname(getApplicationContext())+". ¿Que quieres hacer?");

        btniracrear = findViewById(R.id.btn_iracrearfic);
        btniraver = findViewById(R.id.btn_vertodaslasfi);
        btnvergarage = findViewById(R.id.btn_vercochesgarage);
        btniracrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new NewFicha();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
                tvWelcome.setVisibility(View.GONE);
                btniracrear.setVisibility(View.GONE);
                btniraver.setVisibility(View.GONE);
                btnvergarage.setVisibility(View.GONE);
            }
        });
        btniraver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SeeAllFicha();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
                tvWelcome.setVisibility(View.GONE);
                btniracrear.setVisibility(View.GONE);
                btniraver.setVisibility(View.GONE);
                btnvergarage.setVisibility(View.GONE);
            }
        });
        btnvergarage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new VehiculoActual();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
                tvWelcome.setVisibility(View.GONE);
                btniracrear.setVisibility(View.GONE);
                btniraver.setVisibility(View.GONE);
                btnvergarage.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.newFicha:
                Fragment fragment = new NewFicha();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
                tvWelcome.setVisibility(View.GONE);
                btniracrear.setVisibility(View.GONE);
                btniraver.setVisibility(View.GONE);
                btnvergarage.setVisibility(View.GONE);
                break;
            case R.id.seeFicha:
                Fragment fragment1 = new SeeAllFicha();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment1).commit();
                tvWelcome.setVisibility(View.GONE);
                btniracrear.setVisibility(View.GONE);
                btniraver.setVisibility(View.GONE);
                btnvergarage.setVisibility(View.GONE);
                Toast.makeText(this, "No hay fichas disponibles", Toast.LENGTH_SHORT).show();

                break;
            case R.id.vehiculoAct:
                Fragment fragment2 = new VehiculoActual();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment2).commit();
                tvWelcome.setVisibility(View.GONE);
                btniracrear.setVisibility(View.GONE);
                btniraver.setVisibility(View.GONE);
                btnvergarage.setVisibility(View.GONE);
                Toast.makeText(this, "No hay vehiculos disponnibles", Toast.LENGTH_SHORT).show();

                break;
            case R.id.logout:

                Toast.makeText(this, "Cerrarás la sesión", Toast.LENGTH_SHORT).show();

                break;
        }
        return false;
    }
}