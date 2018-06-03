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

public class NormalTextView extends AppCompatTextView {


    public NormalTextView(Context context) {
        super(context);
        init(null);
    }

    public NormalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NormalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs){
/*        if (attrs !=null){
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs , R.styleable.ContextTextView);
            String fontName = typedArray.getString(R.styleable.ContextTextView_fontName);

            try {
                if (fontName!=null){*/
                    Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"fonts/IRANSansMobile.ttf");
                    setTypeface(typeface);
                   // setTextColor(getResources().getColor(R.color.colorTextPrimary));
/*                }
            }catch (Exception e){
                e.printStackTrace();
            }
            typedArray.recycle();

        }*/
    }
}
