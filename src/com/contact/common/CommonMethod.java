package com.contact.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;

public class CommonMethod {

	
	
	
	public static String titleStringId= "";
	public static String titleString= "";
	
	public static int getImageId(Context context, String imageName) {	   
		return context.getResources().getIdentifier(imageName, "drawable",context.getPackageName());
	}
		
	public static void openErrorDialog(String err_msg, Context context)
	{
		err_msg = Html.fromHtml(err_msg).toString();
		final AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setMessage(err_msg);
		alert.setCancelable(true);

		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

			}
		});
		alert.show();
	}
}
