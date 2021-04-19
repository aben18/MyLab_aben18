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
import edu.uw.tcss450.mylab_aben18.databinding.FragmentSignInBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    private FragmentSignInBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void handleSignIn(View v) {
        if (binding.authEmail.getText().toString().isEmpty())
            binding.authEmail.setError("Empty email");
        if (binding.authPassword.getText().toString().isEmpty())
            binding.authPassword.setError("Empty password");
        else if (!binding.authEmail.getText().toString().contains("@"))
            binding.authEmail.setError("Incorrect email");
        else {
            SignInFragmentDirections.ActionSignInFragmentToMainActivity directions =
                    SignInFragmentDirections.actionSignInFragmentToMainActivity(
                            generateJwt(binding.authEmail.getText().toString()));
            Navigation.findNavController(getView()).navigate(directions);
            getActivity().finish();
        }
    }

    private void handleRegister(View v) {
        Navigation.findNavController(getView()).navigate(SignInFragmentDirections
                .actionSignInFragmentToRegisterFragment());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.signIn.setOnClickListener(this::handleSignIn);
        binding.register.setOnClickListener(this::handleRegister);
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
