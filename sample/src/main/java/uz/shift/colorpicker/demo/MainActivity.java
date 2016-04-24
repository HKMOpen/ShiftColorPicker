package uz.shift.colorpicker.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import uz.shift.colorpicker.LetterPicker;
import uz.shift.colorpicker.LineColorPicker;
import uz.shift.colorpicker.util.OnChangePos;
import uz.shift.colorpicker.util.OnColorChangedListener;
import uz.shift.colorpicker.util.helper;

public class MainActivity extends AppCompatActivity {

    protected static final String TAG = "ShiftPicker";

    private LineColorPicker horizontalPicker, verticalPicker, mletterpicker;

    private TextView tvColor;

    String[] pallete = new String[]{"#b8c847", "#67bb43", "#41b691",
            "#4182b6", "#4149b6", "#7641b6", "#b741a7", "#c54657", "#d1694a"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        horizontalPicker = (LineColorPicker) findViewById(R.id.picker);
        verticalPicker = (LineColorPicker) findViewById(R.id.picker2);
        mletterpicker = (LetterPicker) findViewById(R.id.pickercal);
        tvColor = (TextView) findViewById(R.id.textViewColor);
        verticalPicker.setColors(helper.generateColors(Color.BLUE, Color.RED, 10));

        // Create palette from HEX values
        int[] colors = new int[pallete.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.parseColor(pallete[i]);
        }
        // Set palette
        horizontalPicker.setColors(colors);
        // Set selected color [optional]
        horizontalPicker.setSelectedColor(colors[0]);
        // Get selected color
        int color = horizontalPicker.getColor();
        updateColor(color);
        OnColorChangedListener onChangeListener = new OnColorChangedListener() {
            @Override
            public void onColorChanged(int c) {
                Log.d(TAG, "Selected color " + Integer.toHexString(c));

                updateColor(c);
            }
        };
        horizontalPicker.setOnColorChangedListener(onChangeListener);
        verticalPicker.setOnColorChangedListener(onChangeListener);
        final LineColorPicker xmlPicker = (LineColorPicker) findViewById(R.id.picker3);
        xmlPicker.setOnColorChangedListener(onChangeListener);
        mletterpicker.setColors(helper.generateColors(Color.BLUE, Color.RED, 12));
        mletterpicker.setOnChangePositionSelected(new OnChangePos() {
            @Override
            public void onSelectedChanged(int c, String label) {
                updateLabel(label);
            }
        });
    }


    /**
     * Display selected color
     */
    private void updateColor(int color) {
        String hex = Integer.toHexString(color);
        hex = hex.toUpperCase();
        tvColor.setText(String.format("Selected color: #%s", hex));
    }

    private void updateLabel(String label) {
        tvColor.setText(String.format("Selected label: #%s", label));
    }


}
