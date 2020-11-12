package com.buildingdreamcars.tallergestionapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.buildingdreamcars.tallergestionapp.Activities.EstadoActivity;
import com.buildingdreamcars.tallergestionapp.Activities.MainActivity;
import com.buildingdreamcars.tallergestionapp.R;


public class NewFicha extends Fragment {

     public NewFicha() {
        // Required empty public constructor
    }
    ImageView flecha_client,flecha_vehicle,flecha_service,esquemacoche;
     TextView txtCliente,txtNom,txtNIF,txtDom,txtCP,txtPob,txtProv,txtTlfno,txtMail,txtComment,txtVehicle,txtMarca,txtModelo,txtAno,txtMotor,txtMatricula,txtKM,txtDeposito,txtEstado;
     EditText etNom,etNIF,etDom,etCP,etPob,etProv,etTlfno,etMail,etComment,etMarca,etModelo,etAno,etMotor,etMatricula,etKM,etEstado;
     boolean client, vehicle, service;
     Spinner spinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_new_ficha, container, false);
        client=false;
        vehicle=false;
        service=false;
        flecha_client=v.findViewById(R.id.flecha_spand_client);
        flecha_vehicle=v.findViewById(R.id.flecha_spand_vehicle);
        //Declaración del apartado cliente
        txtCliente=v.findViewById(R.id.txt_client);
        txtNom=v.findViewById(R.id.txt_client_nombre);
        txtNIF=v.findViewById(R.id.txt_client_NIF);
        txtDom=v.findViewById(R.id.txt_client_Domi);
        txtCP=v.findViewById(R.id.txt_client_CP);
        txtPob=v.findViewById(R.id.txt_client_Pobla);
        txtProv=v.findViewById(R.id.txt_client_Prov);
        txtTlfno=v.findViewById(R.id.txt_client_Tlfno);
        txtMail=v.findViewById(R.id.txt_client_Mail);
        txtComment=v.findViewById(R.id.txt_client_comment);
        etNom=v.findViewById(R.id.et_nombre_client);
        etNIF=v.findViewById(R.id.et_client_NIF);
        etDom=v.findViewById(R.id.et_client_Domi);
        etCP=v.findViewById(R.id.et_client_CP);
        etPob=v.findViewById(R.id.et_client_Pobla);
        etProv=v.findViewById(R.id.et_client_Prov);
        etTlfno=v.findViewById(R.id.et_client_Tlfno);
        etMail=v.findViewById(R.id.et_client_Mail);
        etComment=v.findViewById(R.id.et_client_comment);
        //Declaración del apartado vehiculo
        txtVehicle=v.findViewById(R.id.txt_vehicle);
        txtMarca=v.findViewById(R.id.txt_vehicle_Marca);
        txtModelo=v.findViewById(R.id.txt_vehicle_Modelo);
        txtAno=v.findViewById(R.id.txt_vehicle_ano);
        txtMotor=v.findViewById(R.id.txt_vehicle_Motor);
        txtMatricula=v.findViewById(R.id.txt_vehicle_matricula);
        txtKM=v.findViewById(R.id.txt_vehicle_KM);
        txtDeposito=v.findViewById(R.id.txt_vehicle_Depos);
        txtEstado=v.findViewById(R.id.txt_vehicle_estado);
        etMarca=v.findViewById(R.id.et_vehicle_Marca);
        etModelo=v.findViewById(R.id.et_vehicle_Modelo);
        etAno=v.findViewById(R.id.et_vehicle_ano);
        etMotor=v.findViewById(R.id.et_vehicle_Motor);
        etMatricula=v.findViewById(R.id.et_vehicle_matricula);
        etKM=v.findViewById(R.id.et_vehicle_KM);
        etEstado=v.findViewById(R.id.et_vehicle_estado);
        spinner=v.findViewById(R.id.spinner_vehicle_depos);
        esquemacoche=v.findViewById(R.id.img_esquema_coche);


        flecha_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!client){
                    client=true;
                    if (vehicle){
                        desaparecerVehiculo();
                    }
                    aparecerCliente();
                }else{
                    client=false;
                    desaparecerCliente();
                }
            }
        });
        txtCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!client){
                    client=true;
                    if (vehicle){
                        desaparecerVehiculo();
                    }
                    aparecerCliente();
                }else{
                    client=false;
                    desaparecerCliente();
                }
            }
        });

        flecha_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vehicle){
                    vehicle=true;
                    if (client){
                        desaparecerCliente();
                    }
                    aparecerVehiculo();
                }
                else {
                    vehicle=false;
                    desaparecerVehiculo();
                }
            }
        });
        txtVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vehicle){
                    vehicle=true;
                    if (client){
                        desaparecerCliente();
                    }
                    aparecerVehiculo();
                }
                else {
                    vehicle=false;
                    desaparecerVehiculo();
                }
            }
        });
        esquemacoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EstadoActivity.class));
            }
        });



        return v;
    }
    public void aparecerCliente(){
        flecha_client.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);

        txtNom.setVisibility(View.VISIBLE);
        txtNIF.setVisibility(View.VISIBLE);
        txtDom.setVisibility(View.VISIBLE);
        txtCP.setVisibility(View.VISIBLE);
        txtPob.setVisibility(View.VISIBLE);
        txtProv.setVisibility(View.VISIBLE);
        txtTlfno.setVisibility(View.VISIBLE);
        txtMail.setVisibility(View.VISIBLE);
        txtComment.setVisibility(View.VISIBLE);

        etNom.setVisibility(View.VISIBLE);
        etNIF.setVisibility(View.VISIBLE);
        etDom.setVisibility(View.VISIBLE);
        etCP.setVisibility(View.VISIBLE);
        etPob.setVisibility(View.VISIBLE);
        etProv.setVisibility(View.VISIBLE);
        etTlfno.setVisibility(View.VISIBLE);
        etMail.setVisibility(View.VISIBLE);
        etComment.setVisibility(View.VISIBLE);
    }
    public void desaparecerCliente(){
        flecha_client.setImageResource(R.drawable.ic_baseline_arrow_left_24);

        txtNom.setVisibility(View.GONE);
        txtNIF.setVisibility(View.GONE);
        txtDom.setVisibility(View.GONE);
        txtCP.setVisibility(View.GONE);
        txtPob.setVisibility(View.GONE);
        txtProv.setVisibility(View.GONE);
        txtTlfno.setVisibility(View.GONE);
        txtMail.setVisibility(View.GONE);
        txtComment.setVisibility(View.GONE);

        etNom.setVisibility(View.GONE);
        etNIF.setVisibility(View.GONE);
        etDom.setVisibility(View.GONE);
        etCP.setVisibility(View.GONE);
        etPob.setVisibility(View.GONE);
        etProv.setVisibility(View.GONE);
        etTlfno.setVisibility(View.GONE);
        etMail.setVisibility(View.GONE);
        etComment.setVisibility(View.GONE);
    }
    public void aparecerVehiculo(){
        flecha_vehicle.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
        txtMarca.setVisibility(View.VISIBLE);
        txtModelo.setVisibility(View.VISIBLE);
        txtAno.setVisibility(View.VISIBLE);
        txtMotor.setVisibility(View.VISIBLE);
        txtMatricula.setVisibility(View.VISIBLE);
        txtKM.setVisibility(View.VISIBLE);
        txtDeposito.setVisibility(View.VISIBLE);
        txtEstado.setVisibility(View.VISIBLE);
        etMarca.setVisibility(View.VISIBLE);
        etModelo.setVisibility(View.VISIBLE);
        etAno.setVisibility(View.VISIBLE);
        etMotor.setVisibility(View.VISIBLE);
        etMatricula.setVisibility(View.VISIBLE);
        etKM.setVisibility(View.VISIBLE);
        etEstado.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.VISIBLE);
        esquemacoche.setVisibility(View.VISIBLE);
    }
    public void desaparecerVehiculo(){
        flecha_vehicle.setImageResource(R.drawable.ic_baseline_arrow_left_24);
        txtMarca.setVisibility(View.GONE);
        txtModelo.setVisibility(View.GONE);
        txtAno.setVisibility(View.GONE);
        txtMotor.setVisibility(View.GONE);
        txtMatricula.setVisibility(View.GONE);
        txtKM.setVisibility(View.GONE);
        txtDeposito.setVisibility(View.GONE);
        txtEstado.setVisibility(View.GONE);
        etMarca.setVisibility(View.GONE);
        etModelo.setVisibility(View.GONE);
        etAno.setVisibility(View.GONE);
        etMotor.setVisibility(View.GONE);
        etMatricula.setVisibility(View.GONE);
        etKM.setVisibility(View.GONE);
        etEstado.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
        esquemacoche.setVisibility(View.GONE);
    }
}