package com.zww149.androidtraning1.activity;

import android.graphics.Color;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;


import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.formatter.ValueFormatter;
import com.zww149.androidtraning1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/5 9:49
 * java线性的类
 */
public class PHPBarChartActivity extends HomeAsUpBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_bar);
        final String[] years = new String[]{"应届生","1-2年","2-3年",
                "3-5年","5-8年","8-10年","10年"};
        int[] salaries = new int[]{6000,13000,20000,26000,35000,50000,100000};
        TextView titleView = findViewById(R.id.title);
        titleView.setText("PHP统计");

        BarChart chart = findViewById(R.id.chart);

        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < salaries.length; i++) {
            entries.add(new BarEntry(i,salaries[i]));
        }

        BarDataSet dataSet = new BarDataSet(entries,"工资");
        BarData barData = new BarData(dataSet);

        int[] colors={Color.RED,Color.BLUE,Color.GREEN,Color.LTGRAY,
                Color.DKGRAY,Color.CYAN,Color.RED};

        dataSet.setColors(colors);
        List<Integer> color_list = new ArrayList<>();

        for (int color:colors){
            color_list.add(color);
        }
        dataSet.setValueTextColors(color_list);
        dataSet.setValueTextSize(12f);
        chart.setData(barData);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineWidth(2f);
        xAxis.enableGridDashedLine(10f,10f,0f);

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return years[(int)value];
            }
        });
        //设置图表右边的y轴禁用
        chart.getAxisRight().setEnabled(false);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisLineWidth(2f);
        yAxis.enableGridDashedLine(10f,10f,0f);
        yAxis.setSpaceTop(38.2f);
        yAxis.setAxisMinimum(0);

        LimitLine limitLine = new LimitLine(15000f,"平均工资");
        limitLine.setLineColor(Color.MAGENTA);
        limitLine.setLineWidth(2f);
        yAxis.addLimitLine(limitLine);
        Description desc = new Description();
        desc.setText("PHP工程师工作经验对应的薪资情况");
        desc.setPosition(300,80);
        desc.setTextAlign(Paint.Align.CENTER);
        desc.setTextSize(16f);
        chart.setDescription(desc);

        Legend legend = chart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
/*
        // 设置是否可以缩放图表
        chart.setScaleEnabled(true);
        // 设置是否可以用手指移动图表
        chart.setDragEnabled(true);
        Matrix matrix = new Matrix();
        // x轴放大1.2倍，y不变
        matrix.postScale(1.2f, 1.0f);
        // 在图表动画显示之前进行缩放
        chart.getViewPortHandler().refresh(matrix, chart, false);
*/
        //动画
        chart.animateX(2000);
        chart.invalidate();

    }
}
