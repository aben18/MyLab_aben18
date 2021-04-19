package edu.uw.tcss450.mylab_aben18.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uw.tcss450.mylab_aben18.R;
import edu.uw.tcss450.mylab_aben18.databinding.FragmentSuccessBinding;
import edu.uw.tcss450.mylab_aben18.model.UserInfoViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuccessFragment extends Fragment {

    private FragmentSuccessBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //binding = FragmentSuccessBinding.inflate(inflater, container,false);
        //return binding.getRoot();
        return inflater.inflate(R.layout.fragment_success, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // SuccessFragmentArgs args = SuccessFragmentArgs.fromBundle(getArguments());

        // binding.textView.setText(args.getEmail());
        binding = FragmentSuccessBinding.bind(getView());
        UserInfoViewModel model = new ViewModelProvider(getActivity()).get(UserInfoViewModel.class);
        binding.textView.setText("Welcome Home " + model.getEmail() + "!");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}