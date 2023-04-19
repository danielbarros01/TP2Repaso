package com.example.mimenu.ui.actividades;

import static android.content.Intent.getIntent;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mimenu.R;
import com.example.mimenu.databinding.FragmentActividadBinding;
import com.example.mimenu.databinding.FragmentDetallesBinding;
import com.example.mimenu.modelo.Actividad;

public class DetallesFragment extends Fragment {

    private FragmentDetallesBinding binding;
    private DetallesViewModel viewModel;

    public static DetallesFragment newInstance() {
        return new DetallesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetallesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetallesViewModel.class);

        Bundle bundle = getArguments();
        Actividad actividad = (Actividad) bundle.getSerializable("actividad");

        viewModel.setActividad(actividad);

        viewModel.getActividad().observe(this, new Observer<Actividad>() {
            @Override
            public void onChanged(Actividad actividad) {
                binding.tvNombreDetalles.setText(actividad.getNombre());
            }
        });
    }

}