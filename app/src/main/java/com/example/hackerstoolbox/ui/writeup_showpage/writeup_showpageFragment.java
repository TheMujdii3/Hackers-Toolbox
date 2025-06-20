package com.example.hackerstoolbox.ui.writeup_showpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hackerstoolbox.R;
import com.example.hackerstoolbox.Writeup;
import com.example.hackerstoolbox.WriteupAdapter;
import com.example.hackerstoolbox.WriteupService;
import com.example.hackerstoolbox.databinding.WriteupsLayoutBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class writeup_showpageFragment extends Fragment {

    private WriteupsLayoutBinding binding;
    private Retrofit retrofit; // Fixed variable name (was 'aretrofit')
    private WriteupService service;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = WriteupsLayoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Use requireContext() for non-null context
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));

        retrofit = new Retrofit.Builder()
                .baseUrl("https://th3mujd11.synology.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(WriteupService.class);

        loadWriteups();

        return view;
    }

    private void loadWriteups() {
        service.getAllWriteups().enqueue(new Callback<List<Writeup>>() {
            @Override
            public void onResponse(@NonNull Call<List<Writeup>> call, @NonNull Response<List<Writeup>> response) {
                if (!isAdded()) return; // Check fragment attachment

                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(requireContext(), "Empty or bad response", Toast.LENGTH_SHORT).show();
                    return;
                }

                WriteupAdapter adapter = new WriteupAdapter(requireContext(), response.body(), (view, writeup) -> {
                    Bundle bundle = new Bundle();

                    //bundle.putString("writeupId", String.valueOf(writeup.getId()));
                    //bundle.putSerializable("writeup", writeup);
                    bundle.putString("content",writeup.getContent());
                    Navigation.findNavController(view).navigate(R.id.writeupDetailFragment, bundle);
                });


                binding.recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Writeup>> call, @NonNull Throwable t) {
                if (isAdded()) { // Check fragment attachment
                    Toast.makeText(requireContext(), "Failed to load writeups", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}