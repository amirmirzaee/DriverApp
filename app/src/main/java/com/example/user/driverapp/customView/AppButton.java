package com.example.user.driverapp.customView;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class AppButton extends AppCompatButton {
    public AppButton(Context context) {
        super(context);
        setFont();
    }

    public AppButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public AppButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }

    private void setFont() {
        Typeface normal = Typeface.createFromAsset(getContext().getAssets(),"fonts/IRANSansMobile.ttf");
        setTypeface( normal, Typeface.NORMAL );

        Typeface bold = Typeface.createFromAsset( getContext().getAssets(), "fonts/IRANSansMobile_Bold.ttf" );
        setTypeface( bold, Typeface.BOLD );
    }
}
