package example.com.bingocard;

import android.graphics.drawable.GradientDrawable;
import android.widget.Button;
import android.content.Context;
import android.graphics.Color;


public class BingoButton extends Button {

    public BingoButton ( Context c ){
        super( c );
        //--
        // shapeSetting
        GradientDrawable drawable = new GradientDrawable();
        drawable.setStroke(2, Color.parseColor("#000000"));
        drawable.setCornerRadius(20);
        this.setPadding(7, 7, 7, 7);
        //--
    }
}
