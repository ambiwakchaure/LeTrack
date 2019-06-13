package com.example.letrack.support;

import android.content.Context;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.letrack.R;

public class F {

    public static void getMonths(Context context, final ReturnDetails returnDetails)
    {
        new MaterialDialog.Builder(context)
                .title("Select Month")
                .items(R.array.monthName)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice()
                {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence monthName) {

                        returnDetails.returnDetails(monthName.toString());
                        return true;
                    }
                })
                .show();
    }
    public static void getYears(Context context, final ReturnDetails returnDetails)
    {
        new MaterialDialog.Builder(context)
                .title("Select Years")
                .items(T.Companion.getYears())
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice()
                {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence monthName) {

                        returnDetails.returnDetails(monthName.toString());
                        return true;
                    }
                })
                .show();
    }
}
