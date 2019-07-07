package com.zww149.androidtraning1.activity;

import android.graphics.Color;
import android.graphics.Matrix;
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
        final String[] years = new String[]{"应届生","1-2年","2-3年",
                "3-5年","5-8年","8-10年","10年"};
        int[] salaries = new int[]{6000,13000,20000,26000,35000,50000,100000};
        TextView titleView = findViewById(R.id.title);
        titleView.setText("Java统计");
        LineChart chart = findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < salaries.length; i++) {
            entries.add(new Entry(i,salaries[i]));
        }
        LineDataSet dataSet = new LineDataSet(entries,"工资");
        //线条颜色
        dataSet.setColor(Color.parseColor("#7d7d7d"));
        //圆点颜色
        dataSet.setCircleColor(Color.parseColor("#7d7d7d"));
        //线条宽度
        dataSet.setLineWidth(3f);
        // 模式为贝塞尔曲线
        dataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSet.setValueTextColor(Color.RED);
        dataSet.setValueTextSize(12f);
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
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
        yAxis.setSpaceTop(20f);

        LimitLine limitLine = new LimitLine(15000,"平均工资");
        limitLine.setLineColor(Color.MAGENTA);
        limitLine.setLineWidth(2f);
        yAxis.addLimitLine(limitLine);
        Description desc = new Description();
        desc.setText("Java工程师工作经验对应的薪资情况");
        desc.setPosition(300,80);
        desc.setTextAlign(Paint.Align.CENTER);
        desc.setTextSize(16f);
        chart.setDescription(desc);
        Legend legend = chart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);

        // 设置是否可以缩放图表
        chart.setScaleEnabled(true);
        // 设置是否可以用手指移动图表
        chart.setDragEnabled(true);
        Matrix matrix = new Matrix();
        // x轴放大1.2倍，y不变
        matrix.postScale(1.2f, 1.0f);
        // 在图表动画显示之前进行缩放
        chart.getViewPortHandler().refresh(matrix, chart, false);
        //动画
        chart.animateX(2000);
        chart.invalidate();

    }
}
