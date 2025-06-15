package ru.kpfu.itis.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.ColorUtils
import ru.kpfu.itis.springpractice.customview.R
import kotlin.math.min
import kotlin.math.sqrt

class RingDiagramView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : View(ctx, attrs, defStyleAttr) {

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 48f
        textAlign = Paint.Align.LEFT
    }

    private var selectedText: String = ""
    private var selectedTextColor: Int = Color.BLACK

    private var sectorValues: IntArray = intArrayOf(50, 50)
    private var sectorColors: IntArray = intArrayOf(Color.RED, Color.BLUE)
    private var selectedSector: Int = -1
    private val drawingRect = RectF()
    private val sectorPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }

    private var centerX = 0f
    private var centerY = 0f
    private var maxRadius = 0f
    private var ringThickness = 0f

    private val paddingDp = 8f
    private val textSizeSp = 16f

    init {
        textPaint.textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, textSizeSp, resources.displayMetrics
        )

        attrs.let {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.RingDiagramView)
            val valuesId = ta.getResourceId(R.styleable.RingDiagramView_sectorValues, 0)
            val colorsId = ta.getResourceId(R.styleable.RingDiagramView_sectorColors, 0)

            if (valuesId != 0 && colorsId != 0) {
                sectorValues = resources.getIntArray(valuesId)
                validateData(sectorValues)
                sectorColors = resources.getIntArray(colorsId)
            }
            ta.recycle()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handleTouch(event.x, event.y)
                performClick()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        maxRadius = min(w - paddingLeft - paddingRight, h - paddingTop - paddingBottom) / 2.5f
        ringThickness = maxRadius / sectorValues.size
    }

    override fun onDraw(canvas: Canvas) {

        for (i in sectorValues.indices) {
            val innerRadius = i * ringThickness
            val outerRadius = (i + 1) * ringThickness
            val percent = sectorValues[i] / 100f
            val sweepAngle = 360f * percent

            sectorPaint.color =
                if (i == selectedSector) getSelectedColor(sectorColors[i]) else sectorColors[i]
            sectorPaint.strokeWidth = ringThickness

            drawingRect.set(
                centerX - outerRadius,
                centerY - outerRadius,
                centerX + outerRadius,
                centerY + outerRadius
            )
            canvas.drawArc(drawingRect, 90f, sweepAngle, false, sectorPaint)
        }

        if (selectedSector != -1) {
            val paddingPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, paddingDp, resources.displayMetrics
            )
            textPaint.color = selectedTextColor
            val textX = width - paddingPx - textPaint.measureText(selectedText)
            val textY = paddingPx + textPaint.textSize
            canvas.drawText(selectedText, textX, textY, textPaint)
        }
    }

    fun updateData(values: IntArray, colors: IntArray) {
        sectorValues = values
        sectorColors = colors
        invalidate()
    }

    private fun validateData(rawValues: IntArray) {
        if (rawValues.size !in 2..7) {
            throw IllegalArgumentException("The dataset must be of size from 2 to 7")
        }
        if (rawValues.any { it !in 0..100 }) {
            throw IllegalArgumentException("All sector values must be between 0 and 100")
        }

    }

    private fun getSelectedColor(color: Int): Int {
        return ColorUtils.blendARGB(color, Color.WHITE, 0.3f)
    }

    private fun handleTouch(x: Float, y: Float) {
        val sector = calculateTouchedSector(x, y)
        if (sector != -1) {
            selectedSector = sector
            selectedText = "${sectorValues[sector]}%"
            selectedTextColor = sectorColors[sector]
            invalidate()
        }
    }

    private fun calculateTouchedSector(touchX: Float, touchY: Float): Int {
        val centerX = width / 2f
        val centerY = height / 2f
        val relativeX = touchX - centerX
        val relativeY = touchY - centerY
        val distanceToCenter = sqrt(relativeX * relativeX + relativeY * relativeY)

        for (i in sectorValues.indices) {
            val innerRadius = i * ringThickness + ringThickness / 2f
            val outerRadius = (i + 1) * ringThickness + ringThickness / 2f
            if (distanceToCenter in innerRadius..outerRadius) {
                return i
            }
        }
        return -1
    }

}