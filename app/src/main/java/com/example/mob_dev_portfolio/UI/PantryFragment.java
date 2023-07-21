package com.example.mob_dev_portfolio.UI;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mob_dev_portfolio.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Locale;

public class PantryFragment extends Fragment {

    private SpeechRecognizer ingredientRecognizer;
    private Intent ingredientRecognizerIntent;
    TextView tvListening, tvIngredients;
    EditText etIngredients;
    Button btnAddIngredients, btnGetRecipes, btnClearIngredients;
    ImageView imgMic;


    public PantryFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_pantry, container, false);
        tvIngredients = v.findViewById(R.id.tvIngredients);
        etIngredients = v.findViewById(R.id.etAddIngredients);
        imgMic = v.findViewById(R.id.imgMic);
        btnAddIngredients = v.findViewById(R.id.btnAddIngredient);
        btnGetRecipes = v.findViewById(R.id.btnGetRecipes);
        btnClearIngredients = v.findViewById(R.id.btnClearIngredients);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userInputIngredients", MODE_PRIVATE);
        String ingredientsList = sharedPreferences.getString("userInputIngredients","");
        if(!ingredientsList.isEmpty()){
            tvIngredients.setText(ingredientsList);
        }


        initSpeechRecognizer();

        imgMic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    ingredientRecognizer.stopListening();
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    imgMic.setImageResource(R.drawable.ic_mic_on);
                    ingredientRecognizer.startListening(ingredientRecognizerIntent);
                }
                return false;
            }
        });

        btnAddIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String addedIngredients = tvIngredients.getText().toString();
                String newIngredient = etIngredients.getText().toString();
                String finalIngredient = addedIngredients+newIngredient+",";

                if (newIngredient.isEmpty()) {
                    etIngredients.setError("Please enter ingredients");
                    etIngredients.requestFocus();
                }else {
                    tvIngredients.setText(finalIngredient);
                    etIngredients.setText("");
                }
            }
        });

        btnGetRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecipeActivity();
            }
        });

        btnClearIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().remove("userInputIngredients").apply();
                tvIngredients.setText("");
            }
        });

        return v;

    }
        private void openRecipeActivity() {
        String ingredientList = tvIngredients.getText().toString();

        if (ingredientList.isEmpty()){
            Toast.makeText(getContext(), "Please add some ingredients", Toast.LENGTH_SHORT).show();
        }else {
            SharedPreferences sharedPref = getActivity().getSharedPreferences("userInputIngredients", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("userInputIngredients", ingredientList);
            editor.apply();
            MenuFragment menuFragment = new MenuFragment();
            ((BottomNavigationView)getActivity().findViewById(R.id.bottomNavigationView)).setSelectedItemId(R.id.menu);
        }
    }

    // Code adapted from https://developer.android.com/reference/android/speech/SpeechRecognizer &
    // https://github.com/abhinav0612/SpeechToText [Accessed: 20th May 2022]
    private void initSpeechRecognizer() {
        ingredientRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());
        ingredientRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        ingredientRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        ingredientRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        ingredientRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                tvListening.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {
                tvListening.setVisibility(View.GONE);
            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                imgMic.setImageResource(R.drawable.ic_mic_off);
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String finalResult = data.get(0).replace(" ", ",");
                String addedIngredients = tvIngredients.getText().toString();
                String finalIngredient = addedIngredients+finalResult+",";
                tvIngredients.setText(finalIngredient);
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });
    }



}