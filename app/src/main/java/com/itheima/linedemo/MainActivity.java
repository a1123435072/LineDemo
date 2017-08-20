package com.itheima.linedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
	private static final int MAX_OPEN_DAYS=7;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LinearLayout ll_container= (LinearLayout) findViewById(R.id.ll_container);

		//ImageView的宽高和当前屏幕的宽高有关系
		//获取屏幕宽度
		DisplayMetrics outMetrics=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		int winWidth = outMetrics.widthPixels;

		//获取图片
		Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.movie_timeline);

		//计算图片的宽度
		int currentWidth= (int) (winWidth*1.0f/7+0.5f);
		int currentHeight= (int) (src.getHeight()*1.0f*currentWidth/src.getWidth());

		for (int i = 0; i < MAX_OPEN_DAYS; i++) {
			ImageView iv=new ImageView(this);
			iv.setLayoutParams(new LinearLayout.LayoutParams(currentWidth,currentHeight));
			iv.setImageBitmap(src);
			ll_container.addView(iv);
		}


	}
}
