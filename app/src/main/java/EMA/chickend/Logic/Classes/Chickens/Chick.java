package EMA.chickend.Logic.Classes.Chickens;

import android.content.Context;

import EMA.chickend.R;

/**
 * This class is a class which represents a Chick.
 * This chicken is actually a baby chicken.
 */
public class Chick extends Chicken
{
    public Chick(Context context)
    {
        super(context, 150, 7000, 1, R.drawable.chick_picture,1);
    }

}

