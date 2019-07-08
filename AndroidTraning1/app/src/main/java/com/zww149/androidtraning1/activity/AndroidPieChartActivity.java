package com.zww149.androidtraning1.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.zww149.androidtraning1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/7 18:37
 */
public class AndroidPieChartActivity extends HomeAsUpBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart);
        final String[] years = new String[]{"应届生","1-3年",
                "3-5年","5-10年"};
        final float[] salaries = new float[]{4500f,8000f,
                12000f,24000f};
        final float[] bil = new float[]{0.18f,0.27f,0.33f,0.22f};
        TextView titleView = findViewById(R.id.title);
        titleView.setText("Android统计");
        PieChart mPieChart = findViewById(R.id.chart);

        List<PieEntry> pieList = new ArrayList<>();

        for (int i = 0; i < salaries.length; i++) {
            PieEntry pieEntry = new PieEntry(bil[i], years[i]);
            pieList.add(pieEntry);
        }


        PieDataSet dataSet = new PieDataSet(pieList, "工作经验");

        // 设置颜色list，让不同的块显示不同颜色，下面是我觉得不错的颜色集合，比较亮
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(0xFFFC9E7E);
        colors.add(0xFF12CD10);
        colors.add(0xFFFFA7A4);
        colors.add(0xFFFFB867);
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);

        // 设置描述
        Description desc = new Description();
        desc.setText("2019年Android工程师工作经验就业情况");
        desc.setPosition(320,40);
        desc.setTextAlign(Paint.Align.CENTER);
        desc.setTextSize(16f);
        mPieChart.setDescription(desc);

        //设置半透明圆环的半径, 0为透明
        mPieChart.setTransparentCircleRadius(0f);

        //设置初始旋转角度
        mPieChart.setRotationAngle(-15);

        // 显示饼图上面字体颜色
//        mPieChart.setEntryLabelColor(0xFFFF0000);

        //数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(80f);

        //设置连接线的颜色
        dataSet.setValueLineColor(Color.LTGRAY);
        // 连接线在饼状图外面
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        // 设置饼块之间的间隔
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);
        dataSet.setHighlightEnabled(true);
        // 不显示图例
        Legend legend = mPieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);



        // 和四周相隔一段距离,显示数据
        mPieChart.setExtraOffsets(26, 5, 26, 5);
        //是否显示PieChart内部圆环(true:下面属性才有意义)
        mPieChart.setDrawHoleEnabled(true);
        //设置PieChart内部圆的颜色
        mPieChart.setHoleColor(Color.WHITE);

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);
        //设置值是否用显示百分数，默认为假
        mPieChart.setUsePercentValues(true);

        // 设置pieChart图表是否可以手动旋转
        mPieChart.setRotationEnabled(false);

        // 设置piecahrt图表点击Item高亮是否可用
        mPieChart.setHighlightPerTapEnabled(true);

        // 设置pieChart图表展示动画效果，动画运行1.4秒结束
        mPieChart.animateY(1400);

        //设置pieChart是否只显示饼图上百分比不显示文字
        mPieChart.setDrawEntryLabels(true);

        //是否绘制PieChart内部中心文本
        mPieChart.setDrawCenterText(false);
        //设置绘制Label的字体大小
        mPieChart.setEntryLabelTextSize(12f);


        // 绘制内容value，设置字体颜色大小
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter(){
            @Override
            public String getFormattedValue(float value) {
                return salaries[(int) value]+" %";
            }
        });
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.DKGRAY);


        mPieChart.setData(pieData);
        // 更新 mPieChart 视图
        mPieChart.postInvalidate();


    }

}
