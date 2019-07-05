package com.zww149.androidtraning1.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;


import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
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
public class JavaLineChartActivity extends HomeAsUpBaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_line);
        final TextView titleView = findViewById(R.id.title);
        titleView.setText("线性图");

        LineChart chart = findViewById(R.id.chart);

        final String[] years = new String[]{"应届生","1-2年","2-3年","3-5年",
                "5-6年","5-8年","8-10年","10年"};
        int[] salaries = {6000,13000,20000,26000,35000,50000,100000};

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < salaries.length; i++) {
            // turn your data into Entry objects
            entries.add(new Entry(i, salaries[i]));
        }
        LineDataSet dataSet = new LineDataSet(entries, "工资"); // add entries to dataset
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.RED);
        dataSet.setValueTextSize(14f);
        dataSet.setLineWidth(6f);

        LineData lineData = new LineData(dataSet);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineWidth(2f);
        xAxis.enableGridDashedLine(10f,10f,0f);
        //x轴的值
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return years[(int) value];
            }
        });
        chart.getAxisRight().setEnabled(false);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisLineWidth(2f);
        yAxis.setSpaceTop(40f);//设置百分之二十的空白
        yAxis.enableGridDashedLine(10f,10f,0f);
        //加入一个参考线
        LimitLine limitLine =new LimitLine(13000,"平均工资");

        limitLine.setLineColor(Color.MAGENTA);
        limitLine.setLineWidth(4f);

        yAxis.addLimitLine(limitLine);
        chart.setData(lineData);

        Legend legend =chart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        //设置描述
        Description description = new Description();
        description.setText("Java工程师工作经验对应薪资");
        description.setTextSize(16f);
        description.setPosition(540,100);
        description.setTextAlign(Paint.Align.CENTER);
        chart.setDescription(description);

        //动态上升的效果
        chart.animateX(5000);

        chart.invalidate();//refresh


    }
}
