package tecno.academy.TecnoAcademy.Models;

import java.io.Serializable;

public class QuestionModel implements Serializable
{

    int score;
    String ex_title;
    String ques1, opt11, opt12, opt13, opt14, correctAnswer1,img1;
    String ques2, opt21, opt22, opt23, opt24, correctAnswer2,img2;
    String ques3, opt31, opt32, opt33, opt34, correctAnswer3,img3;
    String ques4, opt41, opt42, opt43, opt44, correctAnswer4,img4;
    String ques5, opt51, opt52, opt53, opt54, correctAnswer5,img5;
    String ques6, opt61, opt62, opt63, opt64, correctAnswer6,img6;
    String ques7, opt71, opt72, opt73, opt74, correctAnswer7,img7;
    String ques8, opt81, opt82, opt83, opt84, correctAnswer8,img8;
    String ques9, opt91, opt92, opt93, opt94, correctAnswer9,img9;
    String ques10, opt101, opt102, opt103, opt104, correctAnswer10,img10;

    public QuestionModel() {
    }

    public QuestionModel(int score, String ex_title) {
        this.score = score;
        this.ex_title = ex_title;
    }

    public QuestionModel(int score, String ex_title, String ques1, String opt11, String opt12, String opt13, String opt14, String correctAnswer1, String img1, String ques2, String opt21, String opt22, String opt23, String opt24, String correctAnswer2, String img2, String ques3, String opt31, String opt32, String opt33, String opt34, String correctAnswer3, String img3, String ques4, String opt41, String opt42, String opt43, String opt44, String correctAnswer4, String img4, String ques5, String opt51, String opt52, String opt53, String opt54, String correctAnswer5, String img5, String ques6, String opt61, String opt62, String opt63, String opt64, String correctAnswer6, String img6, String ques7, String opt71, String opt72, String opt73, String opt74, String correctAnswer7, String img7, String ques8, String opt81, String opt82, String opt83, String opt84, String correctAnswer8, String img8, String ques9, String opt91, String opt92, String opt93, String opt94, String correctAnswer9, String img9, String ques10, String opt101, String opt102, String opt103, String opt104, String correctAnswer10, String img10) {
        this.score = score;
        this.ex_title = ex_title;
        this.ques1 = ques1;
        this.opt11 = opt11;
        this.opt12 = opt12;
        this.opt13 = opt13;
        this.opt14 = opt14;
        this.correctAnswer1 = correctAnswer1;
        this.img1 = img1;
        this.ques2 = ques2;
        this.opt21 = opt21;
        this.opt22 = opt22;
        this.opt23 = opt23;
        this.opt24 = opt24;
        this.correctAnswer2 = correctAnswer2;
        this.img2 = img2;
        this.ques3 = ques3;
        this.opt31 = opt31;
        this.opt32 = opt32;
        this.opt33 = opt33;
        this.opt34 = opt34;
        this.correctAnswer3 = correctAnswer3;
        this.img3 = img3;
        this.ques4 = ques4;
        this.opt41 = opt41;
        this.opt42 = opt42;
        this.opt43 = opt43;
        this.opt44 = opt44;
        this.correctAnswer4 = correctAnswer4;
        this.img4 = img4;
        this.ques5 = ques5;
        this.opt51 = opt51;
        this.opt52 = opt52;
        this.opt53 = opt53;
        this.opt54 = opt54;
        this.correctAnswer5 = correctAnswer5;
        this.img5 = img5;
        this.ques6 = ques6;
        this.opt61 = opt61;
        this.opt62 = opt62;
        this.opt63 = opt63;
        this.opt64 = opt64;
        this.correctAnswer6 = correctAnswer6;
        this.img6 = img6;
        this.ques7 = ques7;
        this.opt71 = opt71;
        this.opt72 = opt72;
        this.opt73 = opt73;
        this.opt74 = opt74;
        this.correctAnswer7 = correctAnswer7;
        this.img7 = img7;
        this.ques8 = ques8;
        this.opt81 = opt81;
        this.opt82 = opt82;
        this.opt83 = opt83;
        this.opt84 = opt84;
        this.correctAnswer8 = correctAnswer8;
        this.img8 = img8;
        this.ques9 = ques9;
        this.opt91 = opt91;
        this.opt92 = opt92;
        this.opt93 = opt93;
        this.opt94 = opt94;
        this.correctAnswer9 = correctAnswer9;
        this.img9 = img9;
        this.ques10 = ques10;
        this.opt101 = opt101;
        this.opt102 = opt102;
        this.opt103 = opt103;
        this.opt104 = opt104;
        this.correctAnswer10 = correctAnswer10;
        this.img10 = img10;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEx_title() {
        return ex_title;
    }

    public void setEx_title(String ex_title) {
        this.ex_title = ex_title;
    }

    public String getQues1() {
        return ques1;
    }

    public void setQues1(String ques1) {
        this.ques1 = ques1;
    }

    public String getOpt11() {
        return opt11;
    }

    public void setOpt11(String opt11) {
        this.opt11 = opt11;
    }

    public String getOpt12() {
        return opt12;
    }

    public void setOpt12(String opt12) {
        this.opt12 = opt12;
    }

    public String getOpt13() {
        return opt13;
    }

    public void setOpt13(String opt13) {
        this.opt13 = opt13;
    }

    public String getOpt14() {
        return opt14;
    }

    public void setOpt14(String opt14) {
        this.opt14 = opt14;
    }

    public String getCorrectAnswer1() {
        return correctAnswer1;
    }

    public void setCorrectAnswer1(String correctAnswer1) {
        this.correctAnswer1 = correctAnswer1;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getQues2() {
        return ques2;
    }

    public void setQues2(String ques2) {
        this.ques2 = ques2;
    }

    public String getOpt21() {
        return opt21;
    }

    public void setOpt21(String opt21) {
        this.opt21 = opt21;
    }

    public String getOpt22() {
        return opt22;
    }

    public void setOpt22(String opt22) {
        this.opt22 = opt22;
    }

    public String getOpt23() {
        return opt23;
    }

    public void setOpt23(String opt23) {
        this.opt23 = opt23;
    }

    public String getOpt24() {
        return opt24;
    }

    public void setOpt24(String opt24) {
        this.opt24 = opt24;
    }

    public String getCorrectAnswer2() {
        return correctAnswer2;
    }

    public void setCorrectAnswer2(String correctAnswer2) {
        this.correctAnswer2 = correctAnswer2;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getQues3() {
        return ques3;
    }

    public void setQues3(String ques3) {
        this.ques3 = ques3;
    }

    public String getOpt31() {
        return opt31;
    }

    public void setOpt31(String opt31) {
        this.opt31 = opt31;
    }

    public String getOpt32() {
        return opt32;
    }

    public void setOpt32(String opt32) {
        this.opt32 = opt32;
    }

    public String getOpt33() {
        return opt33;
    }

    public void setOpt33(String opt33) {
        this.opt33 = opt33;
    }

    public String getOpt34() {
        return opt34;
    }

    public void setOpt34(String opt34) {
        this.opt34 = opt34;
    }

    public String getCorrectAnswer3() {
        return correctAnswer3;
    }

    public void setCorrectAnswer3(String correctAnswer3) {
        this.correctAnswer3 = correctAnswer3;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getQues4() {
        return ques4;
    }

    public void setQues4(String ques4) {
        this.ques4 = ques4;
    }

    public String getOpt41() {
        return opt41;
    }

    public void setOpt41(String opt41) {
        this.opt41 = opt41;
    }

    public String getOpt42() {
        return opt42;
    }

    public void setOpt42(String opt42) {
        this.opt42 = opt42;
    }

    public String getOpt43() {
        return opt43;
    }

    public void setOpt43(String opt43) {
        this.opt43 = opt43;
    }

    public String getOpt44() {
        return opt44;
    }

    public void setOpt44(String opt44) {
        this.opt44 = opt44;
    }

    public String getCorrectAnswer4() {
        return correctAnswer4;
    }

    public void setCorrectAnswer4(String correctAnswer4) {
        this.correctAnswer4 = correctAnswer4;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getQues5() {
        return ques5;
    }

    public void setQues5(String ques5) {
        this.ques5 = ques5;
    }

    public String getOpt51() {
        return opt51;
    }

    public void setOpt51(String opt51) {
        this.opt51 = opt51;
    }

    public String getOpt52() {
        return opt52;
    }

    public void setOpt52(String opt52) {
        this.opt52 = opt52;
    }

    public String getOpt53() {
        return opt53;
    }

    public void setOpt53(String opt53) {
        this.opt53 = opt53;
    }

    public String getOpt54() {
        return opt54;
    }

    public void setOpt54(String opt54) {
        this.opt54 = opt54;
    }

    public String getCorrectAnswer5() {
        return correctAnswer5;
    }

    public void setCorrectAnswer5(String correctAnswer5) {
        this.correctAnswer5 = correctAnswer5;
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    public String getQues6() {
        return ques6;
    }

    public void setQues6(String ques6) {
        this.ques6 = ques6;
    }

    public String getOpt61() {
        return opt61;
    }

    public void setOpt61(String opt61) {
        this.opt61 = opt61;
    }

    public String getOpt62() {
        return opt62;
    }

    public void setOpt62(String opt62) {
        this.opt62 = opt62;
    }

    public String getOpt63() {
        return opt63;
    }

    public void setOpt63(String opt63) {
        this.opt63 = opt63;
    }

    public String getOpt64() {
        return opt64;
    }

    public void setOpt64(String opt64) {
        this.opt64 = opt64;
    }

    public String getCorrectAnswer6() {
        return correctAnswer6;
    }

    public void setCorrectAnswer6(String correctAnswer6) {
        this.correctAnswer6 = correctAnswer6;
    }

    public String getImg6() {
        return img6;
    }

    public void setImg6(String img6) {
        this.img6 = img6;
    }

    public String getQues7() {
        return ques7;
    }

    public void setQues7(String ques7) {
        this.ques7 = ques7;
    }

    public String getOpt71() {
        return opt71;
    }

    public void setOpt71(String opt71) {
        this.opt71 = opt71;
    }

    public String getOpt72() {
        return opt72;
    }

    public void setOpt72(String opt72) {
        this.opt72 = opt72;
    }

    public String getOpt73() {
        return opt73;
    }

    public void setOpt73(String opt73) {
        this.opt73 = opt73;
    }

    public String getOpt74() {
        return opt74;
    }

    public void setOpt74(String opt74) {
        this.opt74 = opt74;
    }

    public String getCorrectAnswer7() {
        return correctAnswer7;
    }

    public void setCorrectAnswer7(String correctAnswer7) {
        this.correctAnswer7 = correctAnswer7;
    }

    public String getImg7() {
        return img7;
    }

    public void setImg7(String img7) {
        this.img7 = img7;
    }

    public String getQues8() {
        return ques8;
    }

    public void setQues8(String ques8) {
        this.ques8 = ques8;
    }

    public String getOpt81() {
        return opt81;
    }

    public void setOpt81(String opt81) {
        this.opt81 = opt81;
    }

    public String getOpt82() {
        return opt82;
    }

    public void setOpt82(String opt82) {
        this.opt82 = opt82;
    }

    public String getOpt83() {
        return opt83;
    }

    public void setOpt83(String opt83) {
        this.opt83 = opt83;
    }

    public String getOpt84() {
        return opt84;
    }

    public void setOpt84(String opt84) {
        this.opt84 = opt84;
    }

    public String getCorrectAnswer8() {
        return correctAnswer8;
    }

    public void setCorrectAnswer8(String correctAnswer8) {
        this.correctAnswer8 = correctAnswer8;
    }

    public String getImg8() {
        return img8;
    }

    public void setImg8(String img8) {
        this.img8 = img8;
    }

    public String getQues9() {
        return ques9;
    }

    public void setQues9(String ques9) {
        this.ques9 = ques9;
    }

    public String getOpt91() {
        return opt91;
    }

    public void setOpt91(String opt91) {
        this.opt91 = opt91;
    }

    public String getOpt92() {
        return opt92;
    }

    public void setOpt92(String opt92) {
        this.opt92 = opt92;
    }

    public String getOpt93() {
        return opt93;
    }

    public void setOpt93(String opt93) {
        this.opt93 = opt93;
    }

    public String getOpt94() {
        return opt94;
    }

    public void setOpt94(String opt94) {
        this.opt94 = opt94;
    }

    public String getCorrectAnswer9() {
        return correctAnswer9;
    }

    public void setCorrectAnswer9(String correctAnswer9) {
        this.correctAnswer9 = correctAnswer9;
    }

    public String getImg9() {
        return img9;
    }

    public void setImg9(String img9) {
        this.img9 = img9;
    }

    public String getQues10() {
        return ques10;
    }

    public void setQues10(String ques10) {
        this.ques10 = ques10;
    }

    public String getOpt101() {
        return opt101;
    }

    public void setOpt101(String opt101) {
        this.opt101 = opt101;
    }

    public String getOpt102() {
        return opt102;
    }

    public void setOpt102(String opt102) {
        this.opt102 = opt102;
    }

    public String getOpt103() {
        return opt103;
    }

    public void setOpt103(String opt103) {
        this.opt103 = opt103;
    }

    public String getOpt104() {
        return opt104;
    }

    public void setOpt104(String opt104) {
        this.opt104 = opt104;
    }

    public String getCorrectAnswer10() {
        return correctAnswer10;
    }

    public void setCorrectAnswer10(String correctAnswer10) {
        this.correctAnswer10 = correctAnswer10;
    }

    public String getImg10() {
        return img10;
    }

    public void setImg10(String img10) {
        this.img10 = img10;
    }
}
