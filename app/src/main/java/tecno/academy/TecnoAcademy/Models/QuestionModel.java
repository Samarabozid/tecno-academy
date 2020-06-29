package tecno.academy.TecnoAcademy.Models;

import java.io.Serializable;

public class QuestionModel implements Serializable {

    int score;
    String id, ex_title;

    String question1, optionA1, optionB1, optionC1, optionD1, correct_answer1, imageurl1;
    String question2, optionA2, optionB2, optionC2, optionD2, correct_answer2, imageurl2;
    String question3, optionA3, optionB3, optionC3, optionD3, correct_answer3, imageurl3;
    String question4, optionA4, optionB4, optionC4, optionD4, correct_answer4, imageurl4;
    String question5, optionA5, optionB5, optionC5, optionD5, correct_answer5, imageurl5;
    String question6, optionA6, optionB6, optionC6, optionD6, correct_answer6, imageurl6;
    String question7, optionA7, optionB7, optionC7, optionD7, correct_answer7, imageurl7;
    String question8, optionA8, optionB8, optionC8, optionD8, correct_answer8, imageurl8;
    String question9, optionA9, optionB9, optionC9, optionD9, correct_answer9, imageurl9;
    String question10, optionA10, optionB10, optionC10, optionD10, correct_answer10, imageurl10;

    public QuestionModel() {
    }

    public QuestionModel(int score, String ex_title) {
        this.score = score;
        this.ex_title = ex_title;
    }

    public QuestionModel(String id, String ex_title, String question1, String optionA1, String optionB1, String optionC1, String optionD1, String correct_answer1, String imageurl1, String question2, String optionA2, String optionB2, String optionC2, String optionD2, String correct_answer2, String imageurl2, String question3, String optionA3, String optionB3, String optionC3, String optionD3, String correct_answer3, String imageurl3, String question4, String optionA4, String optionB4, String optionC4, String optionD4, String correct_answer4, String imageurl4, String question5, String optionA5, String optionB5, String optionC5, String optionD5, String correct_answer5, String imageurl5, String question6, String optionA6, String optionB6, String optionC6, String optionD6, String correct_answer6, String imageurl6, String question7, String optionA7, String optionB7, String optionC7, String optionD7, String correct_answer7, String imageurl7, String question8, String optionA8, String optionB8, String optionC8, String optionD8, String correct_answer8, String imageurl8, String question9, String optionA9, String optionB9, String optionC9, String optionD9, String correct_answer9, String imageurl9, String question10, String optionA10, String optionB10, String optionC10, String optionD10, String correct_answer10, String imageurl10) {
        this.id = id;
        this.ex_title = ex_title;
        this.question1 = question1;
        this.optionA1 = optionA1;
        this.optionB1 = optionB1;
        this.optionC1 = optionC1;
        this.optionD1 = optionD1;
        this.correct_answer1 = correct_answer1;
        this.imageurl1 = imageurl1;
        this.question2 = question2;
        this.optionA2 = optionA2;
        this.optionB2 = optionB2;
        this.optionC2 = optionC2;
        this.optionD2 = optionD2;
        this.correct_answer2 = correct_answer2;
        this.imageurl2 = imageurl2;
        this.question3 = question3;
        this.optionA3 = optionA3;
        this.optionB3 = optionB3;
        this.optionC3 = optionC3;
        this.optionD3 = optionD3;
        this.correct_answer3 = correct_answer3;
        this.imageurl3 = imageurl3;
        this.question4 = question4;
        this.optionA4 = optionA4;
        this.optionB4 = optionB4;
        this.optionC4 = optionC4;
        this.optionD4 = optionD4;
        this.correct_answer4 = correct_answer4;
        this.imageurl4 = imageurl4;
        this.question5 = question5;
        this.optionA5 = optionA5;
        this.optionB5 = optionB5;
        this.optionC5 = optionC5;
        this.optionD5 = optionD5;
        this.correct_answer5 = correct_answer5;
        this.imageurl5 = imageurl5;
        this.question6 = question6;
        this.optionA6 = optionA6;
        this.optionB6 = optionB6;
        this.optionC6 = optionC6;
        this.optionD6 = optionD6;
        this.correct_answer6 = correct_answer6;
        this.imageurl6 = imageurl6;
        this.question7 = question7;
        this.optionA7 = optionA7;
        this.optionB7 = optionB7;
        this.optionC7 = optionC7;
        this.optionD7 = optionD7;
        this.correct_answer7 = correct_answer7;
        this.imageurl7 = imageurl7;
        this.question8 = question8;
        this.optionA8 = optionA8;
        this.optionB8 = optionB8;
        this.optionC8 = optionC8;
        this.optionD8 = optionD8;
        this.correct_answer8 = correct_answer8;
        this.imageurl8 = imageurl8;
        this.question9 = question9;
        this.optionA9 = optionA9;
        this.optionB9 = optionB9;
        this.optionC9 = optionC9;
        this.optionD9 = optionD9;
        this.correct_answer9 = correct_answer9;
        this.imageurl9 = imageurl9;
        this.question10 = question10;
        this.optionA10 = optionA10;
        this.optionB10 = optionB10;
        this.optionC10 = optionC10;
        this.optionD10 = optionD10;
        this.correct_answer10 = correct_answer10;
        this.imageurl10 = imageurl10;
    }

    public QuestionModel(int score, String id, String ex_title, String question1, String optionA1, String optionB1, String optionC1, String optionD1, String correct_answer1, String imageurl1, String question2, String optionA2, String optionB2, String optionC2, String optionD2, String correct_answer2, String imageurl2, String question3, String optionA3, String optionB3, String optionC3, String optionD3, String correct_answer3, String imageurl3, String question4, String optionA4, String optionB4, String optionC4, String optionD4, String correct_answer4, String imageurl4, String question5, String optionA5, String optionB5, String optionC5, String optionD5, String correct_answer5, String imageurl5, String question6, String optionA6, String optionB6, String optionC6, String optionD6, String correct_answer6, String imageurl6, String question7, String optionA7, String optionB7, String optionC7, String optionD7, String correct_answer7, String imageurl7, String question8, String optionA8, String optionB8, String optionC8, String optionD8, String correct_answer8, String imageurl8, String question9, String optionA9, String optionB9, String optionC9, String optionD9, String correct_answer9, String imageurl9, String question10, String optionA10, String optionB10, String optionC10, String optionD10, String correct_answer10, String imageurl10) {
        this.score = score;
        this.id = id;
        this.ex_title = ex_title;
        this.question1 = question1;
        this.optionA1 = optionA1;
        this.optionB1 = optionB1;
        this.optionC1 = optionC1;
        this.optionD1 = optionD1;
        this.correct_answer1 = correct_answer1;
        this.imageurl1 = imageurl1;
        this.question2 = question2;
        this.optionA2 = optionA2;
        this.optionB2 = optionB2;
        this.optionC2 = optionC2;
        this.optionD2 = optionD2;
        this.correct_answer2 = correct_answer2;
        this.imageurl2 = imageurl2;
        this.question3 = question3;
        this.optionA3 = optionA3;
        this.optionB3 = optionB3;
        this.optionC3 = optionC3;
        this.optionD3 = optionD3;
        this.correct_answer3 = correct_answer3;
        this.imageurl3 = imageurl3;
        this.question4 = question4;
        this.optionA4 = optionA4;
        this.optionB4 = optionB4;
        this.optionC4 = optionC4;
        this.optionD4 = optionD4;
        this.correct_answer4 = correct_answer4;
        this.imageurl4 = imageurl4;
        this.question5 = question5;
        this.optionA5 = optionA5;
        this.optionB5 = optionB5;
        this.optionC5 = optionC5;
        this.optionD5 = optionD5;
        this.correct_answer5 = correct_answer5;
        this.imageurl5 = imageurl5;
        this.question6 = question6;
        this.optionA6 = optionA6;
        this.optionB6 = optionB6;
        this.optionC6 = optionC6;
        this.optionD6 = optionD6;
        this.correct_answer6 = correct_answer6;
        this.imageurl6 = imageurl6;
        this.question7 = question7;
        this.optionA7 = optionA7;
        this.optionB7 = optionB7;
        this.optionC7 = optionC7;
        this.optionD7 = optionD7;
        this.correct_answer7 = correct_answer7;
        this.imageurl7 = imageurl7;
        this.question8 = question8;
        this.optionA8 = optionA8;
        this.optionB8 = optionB8;
        this.optionC8 = optionC8;
        this.optionD8 = optionD8;
        this.correct_answer8 = correct_answer8;
        this.imageurl8 = imageurl8;
        this.question9 = question9;
        this.optionA9 = optionA9;
        this.optionB9 = optionB9;
        this.optionC9 = optionC9;
        this.optionD9 = optionD9;
        this.correct_answer9 = correct_answer9;
        this.imageurl9 = imageurl9;
        this.question10 = question10;
        this.optionA10 = optionA10;
        this.optionB10 = optionB10;
        this.optionC10 = optionC10;
        this.optionD10 = optionD10;
        this.correct_answer10 = correct_answer10;
        this.imageurl10 = imageurl10;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEx_title() {
        return ex_title;
    }

    public void setEx_title(String ex_title) {
        this.ex_title = ex_title;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getOptionA1() {
        return optionA1;
    }

    public void setOptionA1(String optionA1) {
        this.optionA1 = optionA1;
    }

    public String getOptionB1() {
        return optionB1;
    }

    public void setOptionB1(String optionB1) {
        this.optionB1 = optionB1;
    }

    public String getOptionC1() {
        return optionC1;
    }

    public void setOptionC1(String optionC1) {
        this.optionC1 = optionC1;
    }

    public String getOptionD1() {
        return optionD1;
    }

    public void setOptionD1(String optionD1) {
        this.optionD1 = optionD1;
    }

    public String getCorrect_answer1() {
        return correct_answer1;
    }

    public void setCorrect_answer1(String correct_answer1) {
        this.correct_answer1 = correct_answer1;
    }

    public String getImageurl1() {
        return imageurl1;
    }

    public void setImageurl1(String imageurl1) {
        this.imageurl1 = imageurl1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getOptionA2() {
        return optionA2;
    }

    public void setOptionA2(String optionA2) {
        this.optionA2 = optionA2;
    }

    public String getOptionB2() {
        return optionB2;
    }

    public void setOptionB2(String optionB2) {
        this.optionB2 = optionB2;
    }

    public String getOptionC2() {
        return optionC2;
    }

    public void setOptionC2(String optionC2) {
        this.optionC2 = optionC2;
    }

    public String getOptionD2() {
        return optionD2;
    }

    public void setOptionD2(String optionD2) {
        this.optionD2 = optionD2;
    }

    public String getCorrect_answer2() {
        return correct_answer2;
    }

    public void setCorrect_answer2(String correct_answer2) {
        this.correct_answer2 = correct_answer2;
    }

    public String getImageurl2() {
        return imageurl2;
    }

    public void setImageurl2(String imageurl2) {
        this.imageurl2 = imageurl2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getOptionA3() {
        return optionA3;
    }

    public void setOptionA3(String optionA3) {
        this.optionA3 = optionA3;
    }

    public String getOptionB3() {
        return optionB3;
    }

    public void setOptionB3(String optionB3) {
        this.optionB3 = optionB3;
    }

    public String getOptionC3() {
        return optionC3;
    }

    public void setOptionC3(String optionC3) {
        this.optionC3 = optionC3;
    }

    public String getOptionD3() {
        return optionD3;
    }

    public void setOptionD3(String optionD3) {
        this.optionD3 = optionD3;
    }

    public String getCorrect_answer3() {
        return correct_answer3;
    }

    public void setCorrect_answer3(String correct_answer3) {
        this.correct_answer3 = correct_answer3;
    }

    public String getImageurl3() {
        return imageurl3;
    }

    public void setImageurl3(String imageurl3) {
        this.imageurl3 = imageurl3;
    }

    public String getQuestion4() {
        return question4;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    public String getOptionA4() {
        return optionA4;
    }

    public void setOptionA4(String optionA4) {
        this.optionA4 = optionA4;
    }

    public String getOptionB4() {
        return optionB4;
    }

    public void setOptionB4(String optionB4) {
        this.optionB4 = optionB4;
    }

    public String getOptionC4() {
        return optionC4;
    }

    public void setOptionC4(String optionC4) {
        this.optionC4 = optionC4;
    }

    public String getOptionD4() {
        return optionD4;
    }

    public void setOptionD4(String optionD4) {
        this.optionD4 = optionD4;
    }

    public String getCorrect_answer4() {
        return correct_answer4;
    }

    public void setCorrect_answer4(String correct_answer4) {
        this.correct_answer4 = correct_answer4;
    }

    public String getImageurl4() {
        return imageurl4;
    }

    public void setImageurl4(String imageurl4) {
        this.imageurl4 = imageurl4;
    }

    public String getQuestion5() {
        return question5;
    }

    public void setQuestion5(String question5) {
        this.question5 = question5;
    }

    public String getOptionA5() {
        return optionA5;
    }

    public void setOptionA5(String optionA5) {
        this.optionA5 = optionA5;
    }

    public String getOptionB5() {
        return optionB5;
    }

    public void setOptionB5(String optionB5) {
        this.optionB5 = optionB5;
    }

    public String getOptionC5() {
        return optionC5;
    }

    public void setOptionC5(String optionC5) {
        this.optionC5 = optionC5;
    }

    public String getOptionD5() {
        return optionD5;
    }

    public void setOptionD5(String optionD5) {
        this.optionD5 = optionD5;
    }

    public String getCorrect_answer5() {
        return correct_answer5;
    }

    public void setCorrect_answer5(String correct_answer5) {
        this.correct_answer5 = correct_answer5;
    }

    public String getImageurl5() {
        return imageurl5;
    }

    public void setImageurl5(String imageurl5) {
        this.imageurl5 = imageurl5;
    }

    public String getQuestion6() {
        return question6;
    }

    public void setQuestion6(String question6) {
        this.question6 = question6;
    }

    public String getOptionA6() {
        return optionA6;
    }

    public void setOptionA6(String optionA6) {
        this.optionA6 = optionA6;
    }

    public String getOptionB6() {
        return optionB6;
    }

    public void setOptionB6(String optionB6) {
        this.optionB6 = optionB6;
    }

    public String getOptionC6() {
        return optionC6;
    }

    public void setOptionC6(String optionC6) {
        this.optionC6 = optionC6;
    }

    public String getOptionD6() {
        return optionD6;
    }

    public void setOptionD6(String optionD6) {
        this.optionD6 = optionD6;
    }

    public String getCorrect_answer6() {
        return correct_answer6;
    }

    public void setCorrect_answer6(String correct_answer6) {
        this.correct_answer6 = correct_answer6;
    }

    public String getImageurl6() {
        return imageurl6;
    }

    public void setImageurl6(String imageurl6) {
        this.imageurl6 = imageurl6;
    }

    public String getQuestion7() {
        return question7;
    }

    public void setQuestion7(String question7) {
        this.question7 = question7;
    }

    public String getOptionA7() {
        return optionA7;
    }

    public void setOptionA7(String optionA7) {
        this.optionA7 = optionA7;
    }

    public String getOptionB7() {
        return optionB7;
    }

    public void setOptionB7(String optionB7) {
        this.optionB7 = optionB7;
    }

    public String getOptionC7() {
        return optionC7;
    }

    public void setOptionC7(String optionC7) {
        this.optionC7 = optionC7;
    }

    public String getOptionD7() {
        return optionD7;
    }

    public void setOptionD7(String optionD7) {
        this.optionD7 = optionD7;
    }

    public String getCorrect_answer7() {
        return correct_answer7;
    }

    public void setCorrect_answer7(String correct_answer7) {
        this.correct_answer7 = correct_answer7;
    }

    public String getImageurl7() {
        return imageurl7;
    }

    public void setImageurl7(String imageurl7) {
        this.imageurl7 = imageurl7;
    }

    public String getQuestion8() {
        return question8;
    }

    public void setQuestion8(String question8) {
        this.question8 = question8;
    }

    public String getOptionA8() {
        return optionA8;
    }

    public void setOptionA8(String optionA8) {
        this.optionA8 = optionA8;
    }

    public String getOptionB8() {
        return optionB8;
    }

    public void setOptionB8(String optionB8) {
        this.optionB8 = optionB8;
    }

    public String getOptionC8() {
        return optionC8;
    }

    public void setOptionC8(String optionC8) {
        this.optionC8 = optionC8;
    }

    public String getOptionD8() {
        return optionD8;
    }

    public void setOptionD8(String optionD8) {
        this.optionD8 = optionD8;
    }

    public String getCorrect_answer8() {
        return correct_answer8;
    }

    public void setCorrect_answer8(String correct_answer8) {
        this.correct_answer8 = correct_answer8;
    }

    public String getImageurl8() {
        return imageurl8;
    }

    public void setImageurl8(String imageurl8) {
        this.imageurl8 = imageurl8;
    }

    public String getQuestion9() {
        return question9;
    }

    public void setQuestion9(String question9) {
        this.question9 = question9;
    }

    public String getOptionA9() {
        return optionA9;
    }

    public void setOptionA9(String optionA9) {
        this.optionA9 = optionA9;
    }

    public String getOptionB9() {
        return optionB9;
    }

    public void setOptionB9(String optionB9) {
        this.optionB9 = optionB9;
    }

    public String getOptionC9() {
        return optionC9;
    }

    public void setOptionC9(String optionC9) {
        this.optionC9 = optionC9;
    }

    public String getOptionD9() {
        return optionD9;
    }

    public void setOptionD9(String optionD9) {
        this.optionD9 = optionD9;
    }

    public String getCorrect_answer9() {
        return correct_answer9;
    }

    public void setCorrect_answer9(String correct_answer9) {
        this.correct_answer9 = correct_answer9;
    }

    public String getImageurl9() {
        return imageurl9;
    }

    public void setImageurl9(String imageurl9) {
        this.imageurl9 = imageurl9;
    }

    public String getQuestion10() {
        return question10;
    }

    public void setQuestion10(String question10) {
        this.question10 = question10;
    }

    public String getOptionA10() {
        return optionA10;
    }

    public void setOptionA10(String optionA10) {
        this.optionA10 = optionA10;
    }

    public String getOptionB10() {
        return optionB10;
    }

    public void setOptionB10(String optionB10) {
        this.optionB10 = optionB10;
    }

    public String getOptionC10() {
        return optionC10;
    }

    public void setOptionC10(String optionC10) {
        this.optionC10 = optionC10;
    }

    public String getOptionD10() {
        return optionD10;
    }

    public void setOptionD10(String optionD10) {
        this.optionD10 = optionD10;
    }

    public String getCorrect_answer10() {
        return correct_answer10;
    }

    public void setCorrect_answer10(String correct_answer10) {
        this.correct_answer10 = correct_answer10;
    }

    public String getImageurl10() {
        return imageurl10;
    }

    public void setImageurl10(String imageurl10) {
        this.imageurl10 = imageurl10;
    }
}
