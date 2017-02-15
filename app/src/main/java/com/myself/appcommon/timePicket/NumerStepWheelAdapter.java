
package com.myself.appcommon.timePicket;

/**
 * wheel适配器
 */
public class NumerStepWheelAdapter implements WheelAdapter {

    private int minValue;
    private int maxValue;

    private String format;

    public NumerStepWheelAdapter(int minValue, int maxValue) {
        this(minValue, maxValue, null);
    }

    public NumerStepWheelAdapter(int minValue, int maxValue, String format) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.format = format;
    }

    @Override
    public String getItem(int index) {
        if (index >= 0 && index < getItemsCount()) {
            int value = minValue + index * 1000;
            String s = format != null ? String.format(format, value) : Integer.toString(value);
            return value == 6000 ? "6000(推荐)" : s;
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return maxValue - minValue + 1;
    }

    @Override
    public int getMaximumLength() {
        int max = Math.max(Math.abs(maxValue), Math.abs(minValue));
        int maxLen = Integer.toString(max).length();
        if (minValue < 0) {
            maxLen++;
        }
        return maxLen;
    }
}
