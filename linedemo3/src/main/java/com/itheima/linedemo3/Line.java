package com.itheima.linedemo3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by sszz on 2016/12/17.
 */

public class Line extends View {

	private int winWidth;
	private Bitmap src;
	private Bitmap scaledBitmap;

	public Line(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		winWidth = displayMetrics.widthPixels;

		src = BitmapFactory.decodeResource(getResources(), R.drawable.movie_timeline);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = measureWidth(widthMeasureSpec);
		int height = (int) (src.getHeight() * width * 1.0f / MAX_OPEN_DAYS / src.getWidth());
		setMeasuredDimension(width, height);
	}

	private int measureWidth(int widthMeasureSpec) {
		int result = 0;
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		int size = MeasureSpec.getSize(widthMeasureSpec);
		switch (mode) {
			case MeasureSpec.UNSPECIFIED:

			case MeasureSpec.EXACTLY:
				result = size;
				break;
			case MeasureSpec.AT_MOST:
				//如果调用者传入WRAP_CONTENT
				//我们返回图片的原始宽度*7和屏幕宽度的较小值
				//如果传入WRAP_CONTENT,size默认就是屏幕的宽度
				result = Math.min(src.getWidth() * MAX_OPEN_DAYS, size);
				break;
		}
		return result;
	}

	private static final int MAX_OPEN_DAYS = 7;

	//在onMeasure方法之后调用
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		//创建缩放后的图片
		//参数1:原图片
		//参数2:目标的宽度
		//参数3:目标的高度
		//参数4:缩放后的图片是否处理,传为true,则防止图片缩放后失真
		scaledBitmap = Bitmap.createScaledBitmap(src, (int) (w * 1.0f / MAX_OPEN_DAYS+0.5f), getMeasuredHeight(), true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < MAX_OPEN_DAYS; i++) {
			//利用canvas绘制图片
			//参数1:Bitmap
			//参数2:绘制位置的x
			//参数3:绘制位置的
			canvas.drawBitmap(scaledBitmap,i*scaledBitmap.getWidth(),0,null);

		}
	}
}
