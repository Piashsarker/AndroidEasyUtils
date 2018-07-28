package com.piashsarker.www.easy_utils_lib;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public class FontOverride {
    /** Usages     in  any class which extends Application
     *
     FontOverride.setDefaultFont(this, "DEFAULT", "fonts/dinnextltpro-light.ttf");
     FontOverride.setDefaultFont(this, "MONOSPACE", "fonts/dinnextltpro-light.ttf");
     FontOverride.setDefaultFont(this, "SERIF", "fonts/dinnextltpro-light.ttf");
     FontOverride.setDefaultFont(this, "SANS_SERIF", "fonts/dinnextltpro-light.ttf");
     *
     *************/

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    private static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
