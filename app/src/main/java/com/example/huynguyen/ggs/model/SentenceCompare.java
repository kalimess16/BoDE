package com.example.huynguyen.ggs.model;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ha Truong on 5/8/2018.
 * This is a BoDE
 * into the com.example.huynguyen.ggs.model
 */

public class SentenceCompare {
    private static final String BLANK_SPACE = " ";
    private ArrayList<String> originSentence, recordingSentence;
    private ArrayList<Boolean> corrects;

    public SentenceCompare(String origin, String recording) {
        originSentence = toArrayList(origin);
        recordingSentence = toArrayList(recording);
        corrects = correctWords(originSentence, recordingSentence);
    }

    private ArrayList<String> toArrayList(String str) {
        //
        Scanner scanner = new Scanner(str.trim());
        ArrayList<String> sentence = new ArrayList<>();
        // solving
        while (scanner.hasNext()) {
            sentence.add(scanner.next());
        }
        return sentence;
    }

    // Phuong thuc dung de so xac dinh vi tri cac tu dung
    private ArrayList<Boolean> correctWords(ArrayList<String> origin, ArrayList<String> list) {
        int n = origin.size(), m = list.size();
        boolean[][] matrix = new boolean[n][m];
        ArrayList<Boolean> correct = new ArrayList<>();
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
                ssSubject.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 5, 0);
            }else{
                ssSubject.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, 0);
            }
            //str += "<font color = '" + (corrects.get(i) ? GREEN : RED) + "'>" + originSentence.get(i) + "</font>" + BLANK_SPACE;
        }
        return ssSubject;
    }

}
