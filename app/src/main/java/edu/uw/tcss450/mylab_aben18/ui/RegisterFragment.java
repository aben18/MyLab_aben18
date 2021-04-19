package edu.uw.tcss450.mylab_aben18.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import edu.uw.tcss450.mylab_aben18.R;
import edu.uw.tcss450.mylab_aben18.databinding.FragmentRegisterBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void handleRegister(View v) {
        if (binding.editFirstName.getText().toString().isEmpty())
            binding.editFirstName.setError("Empty first name");
        if (binding.editLastName.getText().toString().isEmpty())
            binding.editLastName.setError("Empty last name");
        if (binding.newEmail.getText().toString().isEmpty())
            binding.newEmail.setError("Empty email");
        if (binding.newEmail.getText().toString().isEmpty())
            binding.newEmail.setError("Empty password");
        if (binding.newPassword.getText().toString().isEmpty())
            binding.newPassword.setError("Empty password");
        else if (!binding.newEmail.getText().toString().contains("@"))
            binding.newEmail.setError("Incorrect email");
        else if (binding.newPassword.getText().toString().length() < 6)
            binding.newPassword.setError("Password less than 6 characters");
        else if (!binding.newPassword.getText().toString().equals(binding.newPassword.getText().toString()))
            binding.newPassword.setError("Passwords do not match");
        else {
            Navigation.findNavController(getView()).navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToMainActivity(
                                    generateJwt(binding.newEmail.getText().toString())
                            ));
            getActivity().finish();
        }

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.sendRegister.setOnClickListener(this::handleRegister);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private String generateJwt(final String email) {
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret key don't use a string literal in " +
                    "production code!!!");
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("email", email)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("JWT Failed to Create.");
        }
        return token;
    }
}