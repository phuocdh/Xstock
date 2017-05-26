package com.xstock.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xstock.R;

/**
 * Created by PhuocDH on 9/18/2016.
 */

public class ItemsAdapterMain extends ArrayAdapter<Integer> {

    Context context;
    LayoutInflater inflater;
    int layoutResourceId;
    float imageWidth;

    public ItemsAdapterMain(Context context, int layoutResourceId, Integer[] items) {
        super(context, layoutResourceId, items);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        
        float width = ((Activity)context).getWindowManager().getDefaultDisplay().getWidth();
        float margin = (int)convertDpToPixel(10f, (Activity)context);
        // two images, three margins of 10dips
		imageWidth = ((width - (3 * margin)) / 2);
    }

	@Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FrameLayout row = (FrameLayout) convertView;
        ItemHolder holder;
        Integer item = getItem(position);
        
		if (row == null) {
			holder = new ItemHolder();
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = (FrameLayout) inflater.inflate(layoutResourceId, parent, false);
			holder.itemImage = (ImageView)row.findViewById(R.id.item_image);
			holder.txt = (TextView) row.findViewById(R.id.txt_name);
		} else {
			holder = (ItemHolder) row.getTag();
		}
		
		row.setTag(holder);
		setImageBitmap(item, holder.itemImage);
		String[] str = new String[]{"aa","bbb","ccc", "ddd", "eee"};
		holder.txt.setText(str[position].toString());
		holder.itemImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(context,"aaaaaa  " + position, Toast.LENGTH_LONG).show();
			}
		});
        return row;
    }

    public static class ItemHolder
    {
    	ImageView itemImage;
		TextView txt;
    }
	
    // resize the image proportionately so it fits the entire space
	private void setImageBitmap(Integer item, ImageView imageView){

		Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), item);
		float i = ((float) imageWidth) / ((float) bitmap.getWidth());
		float imageHeight = i * (bitmap.getHeight());
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageView.getLayoutParams();
		params.height = (int) imageHeight;
		params.width = (int) imageWidth;
		imageView.setLayoutParams(params);
		imageView.setImageResource(item);

	}
	
	public static float convertDpToPixel(float dp, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi/160f);
	    return px;
	}

}