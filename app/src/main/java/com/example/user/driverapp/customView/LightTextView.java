package com.example.user.driverapp.customView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;



/**
 * Created by khosroabadi on 11/5/2017.
 */

public class LightTextView extends AppCompatTextView {


    public LightTextView(Context context) {
        super(context);
        init(null);
    }

    public LightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LightTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs){
/*        if (attrs !=null){
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs , R.styleable.ContextTextView);
            String fontName = typedArray.getString(R.styleable.ContextTextView_fontName);

            try {
                if (fontName!=null){*/
                    Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"fonts/IRANSansMobile_UltraLight.ttf");
                    setTypeface(typeface);
                    //setTextColor(getResources().getColor(R.color.colorTextLight));
/*                }
            }catch (Exception e){
                e.printStackTrace();
            }
            typedArray.recycle();

        }*/
    }
}
