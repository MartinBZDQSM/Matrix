package com.martin.matrix;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class ColorHueActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    ImageView imageView;
    SeekBar seekBarHue, seekBarSaturation, seekBarLightness;
    ColorMatrix colorMatrix = new ColorMatrix();
    ColorMatrix mHueMatrix = new ColorMatrix();
    ColorMatrix mSaturationMatrix = new ColorMatrix();
    ColorMatrix mLightnessMatrix = new ColorMatrix();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_hue);
        imageView = (ImageView) findViewById(R.id.imageView);

        seekBarHue = (SeekBar) findViewById(R.id.bar_hue);
        seekBarSaturation = (SeekBar) findViewById(R.id.bar_saturation);
        seekBarLightness = (SeekBar) findViewById(R.id.bar_lightness);

        seekBarHue.setOnSeekBarChangeListener(this);
        seekBarSaturation.setOnSeekBarChangeListener(this);
        seekBarLightness.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        float mHueValue = (seekBarHue.getProgress() - 128f) * 1.0f / 128f * 180;
        float mSaturationValue = seekBarSaturation.getProgress() / 128f;
        float mLightnessValue = seekBarLightness.getProgress() / 128f;

        mHueMatrix.reset();
        mHueMatrix.setRotate(0, mHueValue);
        mHueMatrix.setRotate(1, mHueValue);
        mHueMatrix.setRotate(2, mHueValue);

        mSaturationMatrix.reset();
        mSaturationMatrix.setSaturation(mSaturationValue);

        mLightnessMatrix.reset();
        mLightnessMatrix.reset();
        mLightnessMatrix.setScale(mLightnessValue, mLightnessValue, mLightnessValue, 1);

        colorMatrix.reset();// 效果叠加
        colorMatrix.postConcat(mLightnessMatrix);
        colorMatrix.postConcat(mSaturationMatrix);
        colorMatrix.postConcat(mHueMatrix);

        imageView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
