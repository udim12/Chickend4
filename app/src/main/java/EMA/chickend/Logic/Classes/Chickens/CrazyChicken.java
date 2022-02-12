package EMA.chickend.Logic.Classes.Chickens;

import android.content.Context;

import EMA.chickend.Logic.Classes.Chickens.Chicken;
import EMA.chickend.R;

/**
 * This class is a class which represents a CrazyChicken.
 * This chicken is crazier than regular chicken, which holds an eggs and can lay them.
 */
public class CrazyChicken extends Chicken
{
    public CrazyChicken(Context context)
    {
        super(context, 150, 2000, 1, R.drawable.crazy_chicken_picture,5);
    }
}
