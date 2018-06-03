package com.example.user.driverapp.customView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.user.driverapp.R;


/**
 * Created by khosroabadi on 3/11/2018.
 */

public class AppEditText extends android.support.v7.widget.AppCompatEditText {

    private Context context;
    private AttributeSet attrs;
    private int defStyle;

    public AppEditText(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public AppEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        this.attrs=attrs;
        init();
    }

    public AppEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        this.attrs=attrs;
        this.defStyle=defStyle;
        init();
    }

    private void init() {
        Typeface font=Typeface.createFromAsset(getContext().getAssets(), "fonts/IRANSansMobile.ttf");
        setTextColor(getResources().getColor(R.color.colorTextPrimary));
        this.setTypeface(font);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        tf=Typeface.createFromAsset(getContext().getAssets(), "fonts/IRANSansMobile.ttf");
        super.setTypeface(tf, style);
    }

    @Override
    public void setTypeface(Typeface tf) {
        tf=Typeface.createFromAsset(getContext().getAssets(), "fonts/IRANSansMobile.ttf");
        super.setTypeface(tf);
    }

}
