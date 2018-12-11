package com.javanajautc.theba.java_naja;

public class QuizChapter8 {

    private String mQuizs [] = {
            "ตัวดําเนินการ instanceof ใช้ในงานลักษณะใด",
            "การเขียนโปรแกรมลักษณะใดควรนํา interface มาใช้ ",
            "ข้อใดเกิดได้จากการทําโอเวอร์โหลด ",
            "ออบเจ็กต์ที่สร้างจากคลาส Calendar สามารถใช้งานลักษณะใดได้บ้าง "
    };

    private String mChoices [][] = {
            {"ก. ใช้ตรวจสอบว่ามีข้อมูลอยู่ในตัวแปรหรือไม่ ","ข. ใช้ตรวจสอบว่ามี class ที่สนใจมีการสร้าง object ต์ขึ้นมาหรือยัง ","ค. ใช้ตรวจสอบว่า object ที่สนใจ เป็นประเภท class ที่สนใจอยู่หรือไม่","ง. ใช้ตรวจสอบว่า object ถูกสร้างมาจาก class หรือไม่"},
            {"ก. กําหนดโครงสร้างเมธอดขึ้นมาก่อนโดยใช้ไม่กําหนดรายละเอียด","ข. ใช้ในการสืบทอดคลาส ","ค. ใช้ในกรณีที่มีโอเวอร์โหลดเมธอด ","ง. ใช้ในการสร้างคลาสแบบไดนามิก"},
            {"ก. สร้างคอนสตรัคเตอร์ได้หลายตัว ","ข. เกิดการพ้องรูป หรือ Polymorphism","ค. เกิดเมธอดนามธรรม ","ง. เกิด abstract เมธอด"},
            {"ก. อ่านค่าวันเดือนปี แล้วนํามาแสดงผล ","ข. ใช้กําหนดค่าวัน เดือน ปี และเวลาให้กับโปรแกรม ","ค. ใช้อ่านเวลา ลําดับวันในแต่ละเดือนออกจากโปรแกรม ","ง. ถูกทุกข้อ"}

    };

    private String mCorrestAnwsers [] = {"ง. ใช้ตรวจสอบว่า object ถูกสร้างมาจาก class หรือไม่","ก. กําหนดโครงสร้างเมธอดขึ้นมาก่อนโดยใช้ไม่กําหนดรายละเอียด",
            "ก. สร้างคอนสตรัคเตอร์ได้หลายตัว ", "ง. ถูกทุกข้อ" };

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

