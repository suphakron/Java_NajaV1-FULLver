package com.javanajautc.theba.java_naja;

public class QuizChapter4 {

    private String mQuizs [] = {
            "เมธอดมีประโยชน์ต่อการเขียนโปรแกรมอย่างไร" ,
            "ถ้าหากต้องการเรียกใช้เมธอดที่มีอยู่แล้วในภาษาจาวา จะต้องทำอย่างไร" ,
            "การคืนค่าให้กับชื่อเมธอด ใช้คําสั่งใด" ,
            "ข้อใดถูกต้องถ้าหากใช้ void นําหน้าชื่อเมธอด" ,
            "ตัวแปรประเภทใดที่เรียกใช้ได้ทุกส่วนของโปรแกรม" ,
            "ตัว body ของเมธอดจะอยู่ภายในเครื่องหมายใด" ,
            "ข้อมูลที่ส่งผ่านเข้าไปในเมธอดมีชื่อเรียกว่าอะไร" ,
            "ในส่วนหัวของเมธอดประกอบไปด้วยอะไร" ,
            "ตัวแปรที่ใช้รับข้อมูลเข้ามาในเมธอดเรียกว่าอะไร" ,
            "ข้อใดไม่ถูกต้องเกี่ยวกับเมธอด" ,
    };

    private String mChoices [][] = {
            {"ก. นำโค้ดที่เขียนไปแล้วกลับมาใช้ใหม่ได้","ข. ลดความซับซ้อนของโปรแกรมได้","ค. สามารถเรียกใช้ได้หลายครั้ง","ง. ถูกทุกข้อ"},
            {"ก. ต้องเรียกผ่านไลบรารี","ข. ต้องเขียนเมธอดขึ้นมาก่อนแล้วจึงเรียก","ค. เรียกชื่อเมธอดได้เลย","ง. ถูกทุกข้อ"},
            {"ก. goto","ข. result","ค. return","ง. get"},
            {"ก. ตัวแปรแบบโกลบอล","ข. เมธอดนี้ไม่มีการคืนค่ากลับ","ค. เมธอดนี้ไม่มีการส่งค่าอาร์กิวเมนต์เข้า","ง. ถูกทั้ง ข. และ ค."},
            {"ก. ตัวแปรแบบโกลบอล","ข. ตัวแปรที่ประกาศภายในเมธอด","ค. ตัวแปรแบบโลคอล","ง. ถูกทั้งข้อ ข. และ ค."},
            {"ก. Curly braces { }","ข. square brackets [ ]","ค. parentheses ( )","ง. quotation marks “ ”"},
            {"ก. argument","ข. signal","ค. parameter","ง. return value"},
            {"ก. ชื่อเมธอด","ข. ประเภทของข้อมูลที่ส่งกลับ","ค. ขอบเขตการเข้าถึงเมธอด","ง. ถูกทุกข้อ"},
            {"ก. parameter","ข. signal","ค. argument","ง. return value"},
            {"ก. สามารถสร้างก่อนหรือหลังเมธอด main ก็ได้","ข. การตั้งชื่อเมธอดเหมือนกับการตั้งชื่อตัวแปร","ค. ต้องมีค่าว่า public เสมอ","ง. ต้องมีการส่งอาร์กิวเมนต์เข้าเมธอดเสมอ"}
    };
    private String mCorrestAnwsers [] = {"ง. ถูกทุกข้อ","ก. ต้องเรียกผ่านไลบรารี","ค. return","ข. เมธอดนี้ไม่มีการคืนค่ากลับ","ก. ตัวแปรแบบโกลบอล",
            "ก. Curly braces { }","ก. argument","ง. ถูกทุกข้อ","ก. parameter ","ง. ต้องมีการส่งอาร์กิวเมนต์เข้าเมธอดเสมอ"};

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

