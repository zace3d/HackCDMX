package mx.citydevs.hackcdmx.typeface;

import android.content.Context;
import android.graphics.Typeface;

public class TypefaceFactory {
    public static final int Roboto_Black = 0;
    public static final int Roboto_BlackItalic = 1;
    public static final int Roboto_Bold = 2;
    public static final int Roboto_BoldItalic = 3;
    public static final int Roboto_Italic = 4;
    public static final int Roboto_Light = 5;
    public static final int Roboto_LightItalic = 6;
    public static final int Roboto_Medium = 7;
    public static final int Roboto_MediumItalic = 8;
    public static final int Roboto_Regular = 9;
    public static final int Roboto_Thin = 10;
    public static final int Roboto_ThinItalic = 11;

    public static final int RobotoCondensed_Bold = 12;
    public static final int RobotoCondensed_BoldItalic = 13;
    public static final int RobotoCondensed_Italic = 14;
    public static final int RobotoCondensed_Light = 15;
    public static final int RobotoCondensed_LightItalic = 16;
    public static final int RobotoCondensed_Regular = 17;

    public static final int OpenSans_Bold = 18;
    public static final int OpenSans_BoldItalic = 19;
    public static final int OpenSans_ExtraBold = 20;
    public static final int OpenSans_ExtraBoldItalic = 21;
    public static final int OpenSans_Italic = 22;
    public static final int OpenSans_Light = 23;
    public static final int OpenSans_LightItalic = 24;
    public static final int OpenSans_Regular = 25;
    public static final int OpenSans_SemiBold = 26;
    public static final int OpenSans_SemiBoldItalic = 27;

    public static final int Vollkorn_Bold = 28;
    public static final int Vollkorn_BoldItalic = 29;
    public static final int Vollkorn_Italic = 30;
    public static final int Vollkorn_Regular = 31;

    public static final int Tw_Cen_MT = 32;
    public static final int Tw_Cen_MT_Bold = 33;
    public static final int Tw_Cen_MT_Bold_Italic = 34;
    public static final int Tw_Cen_MT_Italic = 35;

	private static String typefaceDirRoboto = "fonts/Roboto/";
    private static String typefaceDirRobotoCondensed = "fonts/RobotoCondensed/";
    private static String typefaceDirOpenSans = "fonts/OpenSans/";
    private static String typefaceDirVollkorn = "fonts/Vollkorn/";
    private static String typefaceDirTW = "fonts/TW/";
	
	public static Typeface createTypeface(Context context, int type) {
		if(type == Roboto_Black) {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRoboto + "Roboto-Black.ttf");
			return typeface;
		} else if(type == Roboto_BlackItalic) {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRoboto + "Roboto-BlackItalic.ttf");
			return typeface;
		} else if(type == Roboto_Bold) {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRoboto + "Roboto-Bold.ttf");
			return typeface;
		} else if(type == Roboto_BoldItalic) {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRoboto + "Roboto-BoldItalic.ttf");
			return typeface;
		} else if(type == Roboto_Italic) {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRoboto + "Roboto-Italic.ttf");
			return typeface;
		} else if(type == Roboto_Light) {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRoboto + "Roboto-Light.ttf");
			return typeface;
		} else if(type == Roboto_LightItalic) {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRoboto + "Roboto-LightItalic.ttf");
			return typeface;
		} else if(type == Roboto_Medium) {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRoboto + "Roboto-Medium.ttf");
			return typeface;
		} else if(type == Roboto_MediumItalic) {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRoboto + "Roboto-MediumItalic.ttf");
			return typeface;
		} else if(type == Roboto_Regular) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRoboto + "Roboto-Regular.ttf");
            return typeface;
        } else if(type == Roboto_Thin) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRoboto + "Roboto-Thin.ttf");
            return typeface;
        } else if(type == Roboto_ThinItalic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRoboto + "Roboto-ThinItalic.ttf");
            return typeface;
        } else if(type == RobotoCondensed_Bold) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRobotoCondensed + "RobotoCondensed-Bold.ttf");
            return typeface;
        } else if(type == RobotoCondensed_BoldItalic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRobotoCondensed + "RobotoCondensed-BoldItalic.ttf");
            return typeface;
        } else if(type == RobotoCondensed_Italic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRobotoCondensed + "RobotoCondensed-Italic.ttf");
            return typeface;
        } else if(type == RobotoCondensed_Light) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRobotoCondensed + "RobotoCondensed-Light.ttf");
            return typeface;
        } else if(type == RobotoCondensed_LightItalic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRobotoCondensed + "RobotoCondensed-LightItalic.ttf");
            return typeface;
        } else if(type == RobotoCondensed_Regular) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirRobotoCondensed + "RobotoCondensed-Regular.ttf");
            return typeface;
        } else if(type == OpenSans_Bold) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirOpenSans + "OpenSans-Bold.ttf");
            return typeface;
        } else if(type == OpenSans_BoldItalic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirOpenSans + "OpenSans-BoldItalic.ttf");
            return typeface;
        } else if(type == OpenSans_ExtraBold) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirOpenSans + "OpenSans-ExtraBold.ttf");
            return typeface;
        } else if(type == OpenSans_ExtraBoldItalic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirOpenSans + "OpenSans-ExtraBoldItalic.ttf");
            return typeface;
        } else if(type == OpenSans_Italic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirOpenSans + "OpenSans-Italic.ttf");
            return typeface;
        } else if(type == OpenSans_Light) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirOpenSans + "OpenSans-Light.ttf");
            return typeface;
        } else if(type == OpenSans_LightItalic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirOpenSans + "OpenSans-LightItalic.ttf");
            return typeface;
        } else if(type == OpenSans_Regular) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirOpenSans + "OpenSans-Regular.ttf");
            return typeface;
        } else if(type == OpenSans_SemiBold) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirOpenSans + "OpenSans-Semibold.ttf");
            return typeface;
        } else if(type == OpenSans_SemiBoldItalic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirOpenSans + "OpenSans-SemiboldItalic.ttf");
            return typeface;
        } else if(type == Vollkorn_Bold) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirVollkorn + "Vollkorn-Bold.ttf");
            return typeface;
        } else if(type == Vollkorn_BoldItalic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirVollkorn + "Vollkorn-BoldItalic.ttf");
            return typeface;
        } else if(type == Vollkorn_Italic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirVollkorn + "Vollkorn-Italic.ttf");
            return typeface;
        } else if(type == Vollkorn_Regular) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirVollkorn + "Vollkorn-Regular.ttf");
            return typeface;
        } else if(type == Tw_Cen_MT) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirTW + "Tw_Cen_MT.ttf");
            return typeface;
        } else if(type == Tw_Cen_MT_Bold) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirTW + "Tw_Cen_MT_Bold.ttf");
            return typeface;
        } else if(type == Tw_Cen_MT_Bold_Italic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirTW + "Tw_Cen_MT_Bold_Italic.ttf");
            return typeface;
        } else if(type == Tw_Cen_MT_Italic) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirTW + "Tw_Cen_MT_Italic.ttf");
            return typeface;
        }


        else {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceDirOpenSans + "OpenSans-Regular.ttf");
			return typeface;
		}
	}
}
