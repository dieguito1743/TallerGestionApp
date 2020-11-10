package com.buildingdreamcars.tallergestionapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.buildingdreamcars.tallergestionapp.DatosTemp.Preferencias;
import com.buildingdreamcars.tallergestionapp.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView tvWelcome;
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
        tvWelcome.setText("Bienvenido seas "+ Preferencias.getNickname(getApplicationContext())+". Parece que " +
                "todavía estamos en proceso de crear esta app. Por favor se paciente");

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.newFicha:
                Toast.makeText(this, "Todavia no se pueden crear fichas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.seeFicha:
                Toast.makeText(this, "No hay fichas disponibles", Toast.LENGTH_SHORT).show();

                break;
            case R.id.vehiculoAct:
                Toast.makeText(this, "No hay vehiculos disponnibles", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(this, "Cerrarás la sesión", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}