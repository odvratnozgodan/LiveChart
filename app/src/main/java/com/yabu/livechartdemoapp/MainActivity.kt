package com.yabu.livechartdemoapp

import android.graphics.Color
import android.graphics.Path
import android.graphics.PathMeasure
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.yabu.livechart.model.DataPoint
import com.yabu.livechart.model.Dataset
import com.yabu.livechart.view.LiveChart
import com.yabu.livechart.view.LiveChartStyle
import com.yabu.livechart.view.LiveChartTouchOverlay
import com.yabu.livechart.view.LiveChartView

class MainActivity : AppCompatActivity() {
    private lateinit var livechart: LiveChart
    private lateinit var livechartSimple: LiveChart
    private lateinit var livechartNegative: LiveChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        livechart = findViewById(R.id.main_live_chart)
        livechartSimple = findViewById(R.id.main_simple_live_chart)
        livechartNegative = findViewById(R.id.main_negative_live_chart)

        val dataset = SampleData.createSampleData()

        val negativeDataset = SampleData.createNegativeSampleData()

        val chartStyle = LiveChartStyle().apply {
            mainColor = Color.GRAY
            pathStrokeWidth = 8f
            secondPathStrokeWidth = 4f
            textHeight = 40f
            overlayCircleDiameter = 32f
            overlayCircleColor = Color.GREEN
        }

        val secondDataset = Dataset(mutableListOf(DataPoint(0f, 0f),
            DataPoint(1f, 1f),
            DataPoint(2f, 2f),
            DataPoint(3f, 3f),
            DataPoint(4f, 4f),
            DataPoint(5f, 5f),
            DataPoint(6f, 10f),
            DataPoint(7f, 18f)
        ))

        livechartSimple.setDataset(dataset)
            .setSecondDataset(secondDataset)
            .setLiveChartStyle(chartStyle)
            .drawDataset()

        val firstDataset = Dataset(mutableListOf(DataPoint(0f, 1f),
            DataPoint(1f, 2f),
            DataPoint(2f, 3f),
            DataPoint(3f, 4f),
            DataPoint(4f, 5f),
            DataPoint(5f, 8f),
            DataPoint(6f, 13f),
            DataPoint(7f, 21f)
        ))

        val style = LiveChartStyle().apply {
            mainColor = Color.GRAY
            secondColor = Color.MAGENTA
            pathStrokeWidth = 8f
            secondPathStrokeWidth = 4f
            textHeight = 40f
        }

        livechart.setDataset(firstDataset)
            .setSecondDataset(secondDataset)
            .setLiveChartStyle(style)
            .drawYBounds()
            .drawDataset()

        livechartNegative.setDataset(negativeDataset)
            .drawYBounds()
            .drawBaseline()
            .drawLastPointLabel()
            .drawFill(false)
            .drawDataset()
    }
}