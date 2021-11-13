package ru.gb.student.androidcalculator.logic;

import android.view.View;
import android.widget.*;

import ru.gb.student.androidcalculator.R;

public class CalcLogicVisual {

    private final String DOT_SYMBOL = ".";
    private final String EMPTY_RESULT_TEXT = "0";
    private boolean lastTapOnLogicButton = false;

    private final int[] numberButtonIds;
    private final int[] logicButtonsIds;

    private final TextView results;
    private final TextView subresults;

    private final Button clear;
    private final Button backspace;
    private final Button dot;

    private final View screen;

    private final CalcLogicOperations logic = new CalcLogicOperations();

    public CalcLogicVisual(View screen) {
        numberButtonIds = new int[]{R.id.num0, R.id.num1, R.id.num2, R.id.num3,
                R.id.num4, R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9};
        logicButtonsIds = new int[]{R.id.numDimen, R.id.numMultiply, R.id.numPlus, R.id.numMinus, R.id.numResult};
        this.screen = screen;

        clear = screen.findViewById(R.id.numClear);
        clear.setOnClickListener(v -> clearVisualResultAll());

        backspace = screen.findViewById(R.id.numBackspace);
        backspace.setOnClickListener(v -> deleteLastSymbol());

        dot = screen.findViewById(R.id.numDot);
        dot.setOnClickListener(v -> printDot(DOT_SYMBOL));

        subresults = screen.findViewById(R.id.calculationsTextResult);
        results = screen.findViewById(R.id.calculationsText);

        setNumButtonsListeners();
        setLogicButtonsListeners();
    }


    private void setNumButtonsListeners() {
        for (int i = 0; i < numberButtonIds.length; i++) {
            screen.findViewById(numberButtonIds[i]).setOnClickListener(v -> {
                Button btn = (Button) v;
                printNum(btn.getText().toString());
            });
        }
    }

    private void setLogicButtonsListeners() {
        for (int i = 0; i < logicButtonsIds.length; i++) {
            int operationID = i;
            screen.findViewById(logicButtonsIds[operationID]).setOnClickListener(v -> {
                lastTapOnLogicButton = true;
                switch (operationID){
                    case 0: logic.diemen(getValueFromScreen()); break;
                    case 1: logic.multiple(getValueFromScreen()); break;
                    case 2: logic.plus(getValueFromScreen()); break;
                    case 3: logic.minus(getValueFromScreen()); break;
                    case 4: logic.Result(); break;
                }
                printHistory(operationID);
                results.setText(String.valueOf(logic.getResult()));
            });

        }
    }

    private double getValueFromScreen() {
        return Double.valueOf(results.getText().toString());
    }

    private void printNum(String addText) {
        String text = "";

        if (lastTapOnLogicButton) {
            lastTapOnLogicButton = false;
        } else
            text = results.getText().toString();

        text += addText;
        if (text.charAt(0) == '0' && text.length() > 1)
            text = text.substring(1);
        if (text.charAt(0) == DOT_SYMBOL.toCharArray()[0])
            text = "0" + text;

        results.setText(text);
    }

    private void printDot(String dotSymbol) {
        lastTapOnLogicButton = false;

        String text = results.getText().toString();
        if (!text.contains(dotSymbol)) {
            if (text.length() == 0)
                text += "0" + dotSymbol;
            else
                text += dotSymbol;
        }
        results.setText(text);
    }

    private void printHistory(int action){
        String historyString = "";

        if (subresults.getText()=="")
            historyString = "0";

        switch (action){
            case 0: historyString += " / " + getValueFromScreen(); break;
            case 1: historyString += " * " + getValueFromScreen(); break;
            case 2: historyString += " + " + getValueFromScreen(); break;
            case 3: historyString += " -" + getValueFromScreen(); break;
            case 4: historyString += " = " + getValueFromScreen(); break;
        }
        subresults.setText(subresults.getText() + historyString);
    }

    private void deleteLastSymbol() {
        lastTapOnLogicButton = false;

        String text = results.getText().toString();
        if (text.length() > 0)
            results.setText(text.substring(0, text.length() - 1));
        if (text.length() == 1)
            results.setText(EMPTY_RESULT_TEXT);
    }

    public void clearVisualResult() {
        lastTapOnLogicButton = false;

        results.setText(EMPTY_RESULT_TEXT);
        subresults.setText("");
    }

    public void clearVisualResultAll() {
        logic.clearResult();
        clearVisualResult();
    }
}