package com.itheima.linedemo2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by sszz on 2016/12/17.
 */

public class MyLinearLayout extends LinearLayout {
	private static final int MAX_OPEN_DAYS=7;
	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		//获取屏幕宽度
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		int winWidth = displayMetrics.widthPixels;
		Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.movie_timeline);
		int currentWidth= (int) (winWidth*1.0f/7+0.5f);
		int currentHeight=src.getHeight()*currentWidth/src.getWidth();
		for (int i = 0; i < MAX_OPEN_DAYS; i++) {
			ImageView iv=new ImageView(getContext());
			iv.setLayoutParams(new LinearLayout.LayoutParams(currentWidth,currentHeight));
			iv.setImageBitmap(src);
			//让ImageView的内容填充整个ImageView
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			this.addView(iv);
		}
	}
}
