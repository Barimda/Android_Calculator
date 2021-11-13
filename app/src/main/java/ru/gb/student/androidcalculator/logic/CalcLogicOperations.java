package ru.gb.student.androidcalculator.logic;

public class CalcLogicOperations {

    private double result;
    private double lastValue;

    private int lastCalcID;

    public double plus(double value) {
        lastValue = value;
        lastCalcID = 0;
        result += value;
        return result;
    }

    public double minus(double value) {
        lastValue = value;
        lastCalcID = 1;
        result -= value;
        return result;
    }

    public double multiple(double value) {
        lastValue = value;
        lastCalcID = 2;
        result *= value;
        return result;
    }

    public double diemen(double value) {
        try {
            result /= value;
            lastValue = value;
            lastCalcID = 3;
        } catch (Exception e) {

        }
        return value;
    }

    public double reverse() {
        result = -result;
        lastCalcID = 4;
        return result;
    }

    public double clearResult() {
        result = 0;
        return result;
    }

    public void clearLastValue() {
        lastValue = 0;
    }

    public void clearAll() {
        clearResult();
        clearLastValue();
    }

    public void Result(){
        switch (lastCalcID){
            case 0: plus(lastValue); break;
            case 1: minus(lastValue); break;
            case 2: multiple(lastValue); break;
            case 3: diemen(lastValue); break;
        }
    }

    public double getResult() {
        return result;
    }

    public int getLastCalcID() {
        return lastCalcID;
    }
}

