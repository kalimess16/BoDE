package com.example.huynguyen.ggs.model;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ha Truong on 5/8/2018.
 * This is a BoDE
 * into the com.example.huynguyen.ggs.model
 */

public class SentenceCompare {
    private static final String BLANK_SPACE = " ";
    private List<String> originSentence, recordingSentence;
    private List<Boolean> corrects;

    public SentenceCompare(String origin, String recording) {
        originSentence = toArrayList(origin);
        recordingSentence = toArrayList(recording);
        corrects = correctWords(originSentence, recordingSentence);
    }

    private List<String> toArrayList(String text) {
        List<String> words = new ArrayList<>();
        BreakIterator breakIterator = BreakIterator.getWordInstance();
        breakIterator.setText(text);
        int lastIndex = breakIterator.first();
        while (BreakIterator.DONE != lastIndex) {
            int firstIndex = lastIndex;
            lastIndex = breakIterator.next();
            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                words.add(text.substring(firstIndex, lastIndex));
            }
        }
        return words;
    }

    // Phuong thuc dung de so xac dinh vi tri cac tu dung
    private List<Boolean> correctWords(List<String> origin, List<String> list) {
        int n = origin.size(), m = list.size();
        boolean[][] matrix = new boolean[n][m];
        List<Boolean> correct = new ArrayList<>();
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            correct.add(false);
            for (int j = tmp; j < m; j++) {
                matrix[i][j] = origin.get(i).equalsIgnoreCase(list.get(j));
                if (matrix[i][j]) {
                    correct.set(i, true);
                    tmp = j + 1;
                    break;
                }
            }
        }
        return correct;
    }

    public SpannableString spanString() {
        //String str = "";
        SpannableString ssSubject = null;
        for (int i = 0; i < originSentence.size(); i++) {
            ssSubject = new SpannableString(originSentence.get(i) + BLANK_SPACE);
            if(corrects.get(i)){
                ssSubject.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 4, 0);
            }else{
                ssSubject.setSpan(new ForegroundColorSpan(Color.RED), 0, 4, 0);
            }
            //str += "<font color = '" + (corrects.get(i) ? GREEN : RED) + "'>" + originSentence.get(i) + "</font>" + BLANK_SPACE;
        }
        return ssSubject;
    }

}
