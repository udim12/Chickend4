package EMA.chickend.Logic.Classes.Chickens;

import android.content.Context;

import EMA.chickend.Logic.Classes.Chickens.Chicken;
import EMA.chickend.R;

public class RegularChicken extends Chicken {
    public RegularChicken(Context context) {
        super(context, 150, 6000, 2, R.drawable.regular_chicken_picture,2);
    }
}
