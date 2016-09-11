package com.example.admin.piepicdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2016/9/11.
 */
public class DrawText extends View {
    private Paint mPaint;

    public DrawText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(80);
        // FontMetrics对象
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        String text = "abcdefg";
        // 计算每一个坐标
        float textWidth = mPaint.measureText(text);
        float baseX = 30;
        float baseY = 700;
        float topY = baseY + fontMetrics.top;
        float ascentY = baseY + fontMetrics.ascent;
        float descentY = baseY + fontMetrics.descent;
        float bottomY = baseY + fontMetrics.bottom;
        canvas.drawText(text,baseX,baseY,mPaint);
        //baseLine描画
        mPaint.setColor(Color.RED);
        canvas.drawLine(baseX,baseY,baseX+textWidth,baseY,mPaint);
        mPaint.setTextSize(20);
        canvas.drawText("base",baseX+textWidth,baseY,mPaint);
        //base描画
        canvas.drawCircle(baseX,baseY,5,mPaint);
        //TopLine描画
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(baseX,topY,baseX+textWidth,topY,mPaint);
        mPaint.setTextSize(30);
        canvas.drawText("Top",baseX+textWidth,topY,mPaint);
        //Ascent描画
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(baseX,ascentY,baseX+textWidth,ascentY,mPaint);
        mPaint.setTextSize(30);
        canvas.drawText("Ascent",baseX+textWidth,ascentY,mPaint);
        //Descent描画
        mPaint.setColor(Color.MAGENTA);
        canvas.drawLine(baseX,descentY,baseX+textWidth,descentY,mPaint);
        mPaint.setTextSize(30);
        canvas.drawText("Descent",baseX+textWidth,descentY,mPaint);
        //Bottom描画
        mPaint.setColor(Color.GRAY);
        canvas.drawLine(baseX,bottomY,baseX+textWidth,bottomY,mPaint);
        mPaint.setTextSize(30);
        canvas.drawText("Bottom",baseX+textWidth,bottomY,mPaint);

    }
}
