package edu.cnm.deepdive.temperatureconverter;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity implements TextWatcher{

  private TextInputEditText fahrenheitEdit;
  private TextInputEditText celsiusEdit;
  private TextInputEditText kelvinEdit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    fahrenheitEdit = findViewById(R.id.fahrenheit_edit);
    celsiusEdit = findViewById(R.id.celsius_edit);
    kelvinEdit = findViewById(R.id.kelvin_edit);

    fahrenheitEdit.addTextChangedListener(this);
    celsiusEdit.addTextChangedListener(this);
    kelvinEdit.addTextChangedListener(this);
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
      if (fahrenheitEdit.getEditableText() == s) {
        try {
          celsiusEdit.removeTextChangedListener(this);
          kelvinEdit.removeTextChangedListener(this);
          celsiusEdit.setText(String
              .valueOf(
                  fahrenheitToCelsius(Double.parseDouble(fahrenheitEdit.getText().toString()))));
          kelvinEdit.setText(String
              .valueOf(
                  fahrenheitToKelvin(Double.parseDouble(fahrenheitEdit.getText().toString()))));
          celsiusEdit.addTextChangedListener(this);
          kelvinEdit.addTextChangedListener(this);
        } catch (NullPointerException | NumberFormatException ex) {
          celsiusEdit.addTextChangedListener(this);
          kelvinEdit.addTextChangedListener(this);
        }
      } else if (celsiusEdit.getEditableText() == s) {
        try {
          fahrenheitEdit.removeTextChangedListener(this);
          kelvinEdit.removeTextChangedListener(this);
          fahrenheitEdit.setText(String
              .valueOf(celsiusToFahrenheit(Double.parseDouble(celsiusEdit.getText().toString()))));
          kelvinEdit.setText(
              String
                  .valueOf(celsiusToKelvin(Double.parseDouble(celsiusEdit.getText().toString()))));
          fahrenheitEdit.addTextChangedListener(this);
          kelvinEdit.addTextChangedListener(this);
        } catch (NullPointerException | NumberFormatException ex) {
          fahrenheitEdit.addTextChangedListener(this);
          kelvinEdit.addTextChangedListener(this);
        }
      } else if (kelvinEdit.getEditableText() == s) {
        try {
          fahrenheitEdit.removeTextChangedListener(this);
          celsiusEdit.removeTextChangedListener(this);
          fahrenheitEdit.setText(String
              .valueOf(kelvinToFahrenheit(Double.parseDouble(kelvinEdit.getText().toString()))));
          celsiusEdit.setText(
              String.valueOf(kelvinToCelsius(Double.parseDouble(kelvinEdit.getText().toString()))));
          fahrenheitEdit.addTextChangedListener(this);
          celsiusEdit.addTextChangedListener(this);
        } catch (NullPointerException | NumberFormatException ex) {
          fahrenheitEdit.addTextChangedListener(this);
          celsiusEdit.addTextChangedListener(this);
        }
      }
  }

  @Override
  public void afterTextChanged(Editable s) {

  }

  private static double fahrenheitToCelsius(double input) {
    return (input - 32.0) * (5.0 / 9.0);
  }

  private static double fahrenheitToKelvin(double input) {
    return (input + 459.67) * (5.0 / 9.0);
  }

  private static double celsiusToFahrenheit(double input) {
    return input * (9.0 / 5.0) + 32.0;
  }

  private static double celsiusToKelvin(double input) {
    return input + 273.15;
  }

  private static double kelvinToFahrenheit(double input) {
    return input * (9.0 / 5.0) - 459.67;
  }

  private static double kelvinToCelsius(double input) {
    return input + 273.15;
  }
}
