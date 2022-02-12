package EMA.chickend.Logic.Classes;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import java.util.Locale;

public class AppUtils {
    public static int getIdValueByIdStringName(String idStringName, Context context) {
        Resources res = context.getResources();
        String packageName = context.getPackageName();

        int identifierResourceId = res.getIdentifier(idStringName, "id", packageName);

        return identifierResourceId;
    }

    public static int getResourceDrawableByDrawableName(String drawableName, Context context) {
        Resources res = context.getResources();
        String packageName = context.getPackageName();

        int drawableResourceId = res.getIdentifier(drawableName, "drawable", packageName);

        return drawableResourceId;
    }

    /*
        Source: https://stackoverflow.com/a/39818246/2196301
    */
    public static int GetResourceColorByColorName(String i_ColorName, Context i_Context)
    {
        Resources res = i_Context.getResources();
        String packageName = i_Context.getPackageName();

        int colorId = res.getIdentifier(i_ColorName, "color", packageName);
        int chosenColor = res.getColor(colorId);

        return chosenColor;
    }

    public static String getResourceStringByStringName(String i_StringName, Context i_Context)
    {
        Resources res = i_Context.getResources();
        String packageName = i_Context.getPackageName();

        int stringId = res.getIdentifier(i_StringName, "string", packageName);

        return i_Context.getString(stringId);
    }

    public static int GetResourceRawByRawName(String i_RawName, Context i_Context)
    {
        Resources res = i_Context.getResources();
        String packageName = i_Context.getPackageName();

        int chosenRaw = res.getIdentifier(i_RawName, "raw", packageName);

        return chosenRaw;
    }

    /*
        Convert a given pixels value to dp value
        (Source: https://stackoverflow.com/a/37208440/2196301)
    */
    public static int ConvertPixelsToDPs(Resources i_Resource, float pixelsValue)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixelsValue, i_Resource.getDisplayMetrics());
    }

    public static int ConvertDPsToPixels(Resources resource, int DPsValue)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DPsValue,resource.getDisplayMetrics());
    }

    /*
        Retrieve the current user's language
        (Source: https://stackoverflow.com/a/4212417/2196301)
    */
    public static String GetUserLanguage()
    {
        return Locale.getDefault().getLanguage();
    }
}