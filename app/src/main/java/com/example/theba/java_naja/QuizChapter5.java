package com.example.theba.java_naja;

public class QuizChapter5 {

    private String mQuizs [] = {
            "หากประกาศตัวแปรอาร์เรย์เป็น int  x = new int[9] ข้อใดไม่ถูกต้อง",
            "ตัวแปรอาร์เรย์แบบ 2 มิติต่างจากอาร์เรย์แบบ 1 มิติอย่างไร",
            "การกําหนดค่าให้กับอาร์เรย์ข้อใดผิด",
            "ถ้าหากตัวแปร M เป็นอาร์เรย์ 2 มิติ ที่ประกาศเป็น int[ ] M = new int[2][2] การอ้างอิงในอาร์เรย์ข้อใดผิด",
            "ถ้าหากตัวแปรอาร์เรย์ประกาศเป็น int A = {8,10,12,4,93} ข้อใดถูกต้อง",
            "อินเด็กซ์ของอาร์เรย์ ข้อใดไม่ถูกต้อง",
            "จํานวนข้อมูลที่เก็บในอาร์เรย์เรียกว่า"

    };

    private String mChoices [][] = {
            {"ก. มีตัวแปรย่อยทั้งหมด 9 ตัว","ข. แต่ละเซลเก็บเลขจํานวนเต็ม","ค. ตัวแปรตัวสุดท้ายคือ x[9]","ง. ใช้หน่วยความจําแบบอ้างอิง"},
            {"ก. การอ้างถึงข้อมูล","ข. การเก็บข้อมูล","ค. ขนาดของข้อมูล","ง. ถูกทุกข้อ"},
            {"ก. int[ ] x = {1,2,3,4};","ข. char [4] x = {'A','1','B','2'};","ค. float [2] x = {1.8,2.4};","ง. string UX = “TWAT\":"},
            {"ก. M[1][0]","ข. M[0][0]","ค. M[2][2]","ง. M[1][1]"},
            {"ก. A[4] มีค่าเท่ากับ 93","ข. A[3] มีค่าเท่ากับ 9","ค. A[2] มีค่าเป็น 0","ง. A[1] มีค่าเท่ากับ 8"},
            {"ก. 100","ข. 0","ค. -1","ง. เลขจํานวนเต็มน้อยกว่าจํานวนเซลหนึ่งค่า"},
            {"ก. elements","ข. size","ค. delete","ง. width"},
    };
    private String mCorrestAnwsers [] = {"ค. ตัวแปรตัวสุดท้ายคือ x[9] ","ง. ถูกทุกข้อ ","ข. char [4] x = {'A','1','B','2'}; ","ค. M[2][2] ",
            "ก. A[4] มีค่าเท่ากับ 93", "ค. -1 ","ก. elements "};

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

