package com.javanajautc.theba.java_naja;

public class QuizChapter1 {

    private String mQuizs [] = {
            "ข้อใดเป็นลักษณะของภาษาระดับสูง",
            "โปรแกรมแอสเซมเบลอร์คืออะไร",
            "การแปลภาษาคอมพิวเตอร์เป็นรหัสภาษาเครื่องที่มีการแปลทีละบรรทัดเรียกว่าอะไร"
    };

    private String mChoices [][] = {
            {"ก.ทำงานได้โดยไม่ต้องมีโปรแกรมระบบ","ข.มีภาษาใกล้เคียงกับภาษามนุษย์","ค.เป็นภาษาคอมพิวเตอร์ยุคใหม่","ง.เป็นภาษาที่ใช้กับงานขั้นสูง"},
            {"ก.ตัวแปลภาษา Basic","ข.ตัวแปลภาษาซี","ค.ตัวแปลภาษาแอสเซมบลี","ง.ถูกทุกข้อ"},
            {"ก.คอมไพเลอร์","ข.อินเทอร์พรีเตอร์","ค.แอสเซมเบลอร์","ง.รันไทม์"}
    };

    private String mCorrestAnwsers [] = {"ข.มีภาษาใกล้เคียงกับภาษามนุษย์","ค.ตัวแปลภาษาแอสเซมบลี","ข.อินเทอร์พรีเตอร์"};

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
