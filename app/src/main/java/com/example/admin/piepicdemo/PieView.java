package com.example.admin.piepicdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by admin on 2016/9/11.
 */
public class PieView extends View {
    //饼状图的画笔
    private Paint piePaint;
    //文字的画笔
    private Paint textPaint;
    //饼状图的半径
    private float radius;
    private RectF rectF;
    //饼状图第一个扇形占圆的比例
    private float percent;
    //深浅两种颜色
    private int mBigBallColor, mSmallBallColor;
    //大小球的数量
    private int mBigNumber, mSmallNumber;
    //大小球所占的百分比
    private String mBigballPercent;
    private String mSmallBallpercent;
    //动态获取属性
    private TypedValue typedValue;
    //中间的文字信息
    private String strBigName;
    private String strSmallName;

    public PieView(Context context) {
        super(context);
        init(context);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        //初始化饼状图画笔
        piePaint = new Paint();
        piePaint.setAntiAlias(true);
        piePaint.setStyle(Paint.Style.FILL);
        //初始化文字画笔
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        mBigBallColor = 0xFF9CCA5D;
        mSmallBallColor = 0xFF5F7048;
        typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.color.mainTextColor, typedValue, true);
        mBigNumber = 2;
        mSmallNumber = 3;
        mSmallBallpercent = "40%";
        mBigballPercent = "60%";
        strBigName = getResources().getString(R.string.big);
        strSmallName = getResources().getString(R.string.small);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //饼状图的X坐标
        float centerX = getWidth() / 5;
        //饼状图的Y坐标
        float centerY = getHeight() / 2;
        float textSize = getHeight()/30;
        float width = (float) getWidth();
        float height = (float) getHeight();
         /*中间小正方形边长的一半*/
        float halfSmallRec = ((float) getHeight())/80;

        percent = ((float) mBigNumber) / (mBigNumber + mSmallNumber);
        /*求饼状图的半径*/
        radius = Math.min(getWidth() * 1 / 8, getHeight() * 10 / 35);
        /*构建一个正方形，饼状图是这个正方形的内切圆*/
        rectF = new RectF((int) (centerX - radius), (int) (centerY - radius), (int) (centerX + radius), (int) (centerY + radius));
         /*设置饼状图画笔的颜色，先绘制大球占的比例*/
        piePaint.setColor(mBigBallColor);
        canvas.drawArc(rectF, 270, 360 * percent, true, piePaint);
        piePaint.setColor(mSmallBallColor);
        canvas.drawArc(rectF, 270 + 360 * percent, 360 - 360 * percent, true, piePaint);
        piePaint.setColor(mBigBallColor);
        /*绘制上边的小方块，也就是大球的方块*/
        canvas.drawRect(width * 2 / 5 - halfSmallRec, height * 28 / 60 - halfSmallRec, width * 2 / 5 + halfSmallRec, height * 28 / 60 + halfSmallRec, piePaint);
        /*更改画笔颜色为小球颜色*/
        piePaint.setColor(mSmallBallColor);
        /*绘制下边的小方块即小球的小方块*/
        canvas.drawRect(width * 2 / 5 - halfSmallRec, height * 32 / 60 - halfSmallRec, width * 2 / 5 + halfSmallRec, height * 32 / 60 + halfSmallRec, piePaint);
        //绘制文字
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(textSize);
        //大球数量
        String strBig = strBigName + mBigNumber;
        /*测量文字宽度*/
        float textBigWidth = textPaint.measureText(strBig);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        /*绘制上边大球数量*/
        canvas.drawText(strBig, width * 9 / 20 + textBigWidth / 2, height * 28 / 60 - fontMetrics.top / 3, textPaint);
        /*小球数量*/
        String strSmall = strSmallName + mSmallNumber;
        /*测量文字宽度*/
        float textUnderWidth = textPaint.measureText(strSmall);
        /*绘制下边的小球数量*/
        canvas.drawText(strSmall, width * 9 / 20 + textUnderWidth / 2, height * 32 / 60 - fontMetrics.top / 3, textPaint);
        /*更改画笔颜色，开始绘制百分比*/
        textPaint.setColor(Color.BLUE);
        String strBigPercent = " (" + mBigballPercent + ")";
        /*测量大球百分比文字宽度*/
        float bigPercent = textPaint.measureText(strBigPercent);
        /*drawText(String text, float x, float y, Paint paint)
        * 绘制文字的API，四个参数分别是文字内容，起始绘制x坐标，起始绘制y坐标，画笔
        * 以为设置了居中绘制，因此穿进去的xy坐标为文字的中心点*/
        canvas.drawText(strBigPercent, width * 9 / 20 + textBigWidth + bigPercent / 2, height * 28 / 60 - fontMetrics.top * 1 / 3, textPaint);
        /*同样的道理绘制小球的百分比*/
        String strSmallPercent = " (" + mSmallBallpercent + ")";
        float smallPercent = textPaint.measureText(strSmallPercent);
        canvas.drawText(strSmallPercent, width * 9 / 20 + textUnderWidth + smallPercent / 2, height * 32 / 60 - fontMetrics.top / 3, textPaint);
    }

    public void setColor(int mBigBallColor,int mSmallBallColor) {
        this.mBigBallColor = mBigBallColor;
        this.mSmallBallColor = mSmallBallColor;
    }


    public void setPercent(float percent) {
        this.percent = percent;
        invalidate();
    }
    public void setOverRunner(String bigPecent, String smallPercent, int big, int small, int bigColor, int smallColor){
        this.mBigballPercent = bigPecent;
        this.mSmallBallpercent = smallPercent;
        this.mBigNumber = big;
        this.mSmallNumber = small;
        this.mBigBallColor = bigColor;
        this.mSmallBallColor = smallColor;
        invalidate();
    }
}
