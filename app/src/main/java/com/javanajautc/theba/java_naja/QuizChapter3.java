package com.example.theba.java_naja;

public class QuizChapter3 {

    private String mQuizs [] = {
            "นิพจน์ประเภทใดให้ผลลัพธ์เป็น จริง หรือ เท็จ",
            "ตัวดําเนินการ &&, || และ ! เป็นตัวดําเนินการประเภทใด",
            "สเตตเมนต์การเลือกทําแบบ switch ถ้าหากค่าคงที่ไม่เท่ากับค่าคงที่ใดเลย โปรแกรมจะไปทําสเตตเมนต์ที่ตําแหน่งใด",
            "หากเปรียบเทียบค่าภายในตัวแปรสองตัว ว่าเท่ากันหรือไม่ ใช้ตัวดำเนินการใด",
            "ลูปประเภทใดที่ทราบจำนวนครั้งในการทำซ้ำแน่นอน",
            "คำสั่งใดที่สามารถใช้หยุดการทำซ้ำได้",
            "ลูปประเภทใดที่ตรวจสอบเงื่อนไขก่อนการทำในลูป",
            "for ข้อใดไม่ถูกต้องเกี่ยวกับลูปแบบ for",
    };

    private String mChoices [][] = {
            {"ก. นิพจน์แบบไบนารี","ข. นิพจน์การตัดสินใจ","ค. นิพจน์แบบไม่มีเงื่อนไข","ง. นิพจน์แบบบูลีน"},
            {"ก. ตัวดําเนินการความสัมพันธ์","ข. ตัวดําเนินการระดับบิต","ค. ตัวดําเนินการเงื่อนไข","ง. ตัวดําเนินการแบบตรรกศาสตร์"},
            {"ก. หลัง else","ข. หลัง default","ค. หลัง case","ง. หลัง if"},
            {"ก. ==","ข. =","ค. equals","ง. compare"},
            {"ก. repeat","ข. for","ค. do..while","ง. while"},
            {"ก. stop","ข. break","ค. delay","ง. ถูกทุกข้อ"},
            {"ก. do..while","ข. do..while และ for","ค. for","ง. for และ while"},
            {"ก. สามารถเพิ่มค่าหรือลดค่าได้","ข. สามารถเพิ่มค่าตัวแปร 2 ตัวพร้อมกันได้","ค. ตรวจสอบเงื่อนไขหลังการทําในลูป","ง. ไม่มีข้อถูก"}
    };
    private String mCorrestAnwsers [] = {"ง. นิพจน์แบบบูลีน","ง. ตัวดําเนินการแบบตรรกศาสตร์","ข. หลัง default","ก. ==","ข. for",
            "ข. break","ข. do..while และ for","ง. ไม่มีข้อถูก"};

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

