package com.example.theba.java_naja;

public class QuizChapter2 {

    private String mQuizs [] = {
            "การรวมกลุ่มสเตทเมนต์ในคลาสหรือในเมธอดจะต้องเขียนอยู่ภายในเครื่องหมายใด",
            "ข้อใดต่อไปนี้ไม่ใช่สเตตเมนต์การกําหนดค่าข้อมูลในภาษาจาวา",
            "ข้อใดไม่ถูกต้องในการใช้ println",
            "ข้อใดคือคําสงวนในภาษาจาวา",
            "ในคลาส Scanner จะใช้เมธอดใดในการรับข้อมูลที่เป็น String.",
            "ในคลาส Scanner จะใช้เมธอดใดในการรับข้อมูลตัวเลขแบบ double",
    };

    private String mChoices [][] = {
            {"ก. Angle brackets <>","ข. parentheses ()","ค. brackets {}","ง. ถูกทุกข้อ"},
            {"ก. total = 9;","ข. 85 = X;","ค. profit = 120;","ง. ch = ‘w’;"},
            {"ก, System.outprintln(A + “Hello Java)","ข. System.out.println(&quot; &quot;+value);","ค. System.println.out(value)","ง. มีมากกว่าหนึ่งข้อ"},
            {"ก. constatnt","ข. nameConstant","ค. concrete","ง. final"},
            {"ก. nextString ()","ข. getLine()","ค. readString ()","ง. nextLine()"},
            {"ก. nextDouble()","ข. getDouble()","ค. readDouble()","ง. ไม่มีเมธอดนี้ในคลาส Scanner()"}
    };

    private String mCorrestAnwsers [] = {"ค. brackets {}","ข. 85 = X;","ง. มีมากกว่าหนึ่งข้อ","ง. final","ง. nextLine()","ก. nextDouble()"};

    public String getQuestion(int a){
            String quiz = mQuizs[a];
            return quiz;
    }

    public String getChoice1(int a){
        String choice0 = mChoices[a][0];
        return choice0;
    }

    public String getChoice2(int a){
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a){
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getChoice4(int a){
        String choice3 = mChoices[a][3];
        return choice3;
    }

    public String getCorrectAnswer(int a){
        String answer = mCorrestAnwsers[a];
        return answer;
    }

}
