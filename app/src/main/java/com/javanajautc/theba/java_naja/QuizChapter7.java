package com.javanajautc.theba.java_naja;

public class QuizChapter7 {

    private String mQuizs [] = {
            "โปรแกรมเชิงวัตถุนั้น ออบเจ็กต์แต่ละตัวจะติดต่อกันได้ทางส่วนใด",
            "ในการสืบทอดคลาสนั้น คลาสต้นแบบเรียกว่าอะไร",
            "การประกาศคลาสว่ามีการสืบทอดคลาสคือ คําสั่งใด",
            "ในคลาสลูกไม่สามารถเข้าถึงสมาชิกประเภทใดในคลาสแม่ได้ ",
            "คําสั่งใดที่ใช้อ้างถึงคอนสตรัคเตอร์ในคลาสแม่",
            "ออบเจ็กต์ที่สร้างขึ้นจากคลาสมีชื่อเรียกว่าอะไร ",
            "การห่อหุ้ม (Encapsulation) สร้างขึ้นมาเพื่ออะไร "
    };

    private String mChoices [][] = {
            {"ก. ตัวแปร ","ข. คุณลักษณะ ","ค. เมธอด","ง. ถูกทุกข้อ "},
            {"ก. startclass","ข. Superclass","ค. subclass","ง. child class"},
            {"ก. derived","ข. specialized","ค. based","ง. extends"},
            {"ก. public","ข. private ","ค. protected","ง. ถูกทุกข้อ"},
            {"ก. Super","ข. superclass","ค. base","ง. this"},
            {"ก. อินสแตนซ์ ","ข. ตัวแปรคลาส","ค. คลาสลูก ","ง. ค่าคงที่ของคลาส"},
            {"ก. เพื่อไม่ให้ค่าตัวแปรมีการเปลี่ยนแปลง ","ข. ใช้ปกป้องข้อมูลไม่ให้ออบเจ็กต์ภายนอกมาแก้ไขโดยตรง ","ค. ใช้รวมตัวแปรคุณลักษณะไว้เป็นกลุ่มเดียวกัน","ง. ถูกทุกข้อ"}
    };

    private String mCorrestAnwsers [] = {"ค. เมธอด","ข. Superclass","ง. extends ","ข. private",
            "ก. Super", "ก. อินสแตนซ์","ข. ใช้ปกป้องข้อมูลไม่ให้ออบเจ็กต์ภายนอกมาแก้ไขโดยตรง"};

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

