package tecno.academy.TecnoAcademy.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TriviaQuizHelper extends SQLiteOpenHelper
{
    private Context context;
    private static final String DB_NAME = "TQuiz.db";

    //If you want to add more questions or wanna update table values
    //or any kind of modification in db just increment version no.
    private static final int DB_VERSION = 3;
    //Table name
    private static final String TABLE_NAME = "TQ";
    //Id of question
    private static final String UID = "_UID";
    //Question
    private static final String QUESTION = "QUESTION";
    //Option A
    private static final String OPTA = "OPTA";
    //Option B
    private static final String OPTB = "OPTB";
    //Option C
    private static final String OPTC = "OPTC";
    //Option D
    private static final String OPTD = "OPTD";
    //Answer
    private static final String ANSWER = "ANSWER";
    //So basically we are now creating table with first column-id , sec column-question , third column -option A, fourth column -option B , Fifth column -option C , sixth column -option D , seventh column - answer(i.e ans of  question)
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public TriviaQuizHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void allQuestion() {
        ArrayList<TriviaQuestion> arraylist = new ArrayList<>();

        arraylist.add(new TriviaQuestion("طور العالم غاليليو عالم فلك إيطالى؟", "تلسكوب", "طائره", "الكهرباء", "القطار", "تلسكوب"));

        arraylist.add(new TriviaQuestion("من هو العالم الملقب بوالد الهندسة؟", "أرسطو", "إقليدس", "فيثاغورس", "كبلر", "إقليدس"));

        arraylist.add(new TriviaQuestion("Who was known as Iron man of India ?", "Govind Ballabh Pant", "Jawaharlal Nehru", "Subhash Chandra Bose", "Sardar Vallabhbhai Patel", "Sardar Vallabhbhai Patel"));

        arraylist.add(new TriviaQuestion("من هى أول امرأة في الفضاء؟", "فالنتينا تيريشكوفا" , "سالي رايد" , "نادية كومانيكي" , "تمارا برس" ,"فالنتينا تيريشكوفا"));

        arraylist.add(new TriviaQuestion("Who is the Flying Sikh of India ?", "Mohinder Singh", "Joginder Singh", "Ajit Pal Singh", "Milkha singh", "Milkha singh"));

        arraylist.add(new TriviaQuestion("The Indian to beat the computers in mathematical wizardry is", "Ramanujam", "Rina Panigrahi", "Raja Ramanna", "Shakunthala Devi", "Shakunthala Devi"));

        arraylist.add(new TriviaQuestion("من هو لاري بريسلر؟ ","سياسى","رسام","ممثل","لاعب تنس","سياسي"));

        arraylist.add(new TriviaQuestion("مايكل جاكسون شخص مميز في مجال؟ "," موسيقى البوب "," الصحافة "," الرياضة "," التمثيل "," موسيقى البوب "));

        arraylist.add(new TriviaQuestion("The first Indian to swim across English channel was ?", "V. Merchant", "P. K. Banerji", "Mihir Sen", "Arati Saha", "Mihir Sen"));

        arraylist.add(new TriviaQuestion("Who was the first Indian to make a movie?", "Dhundiraj Govind Phalke", " Asha Bhonsle", " Ardeshir Irani", "V. Shantaram", "Dhundiraj Govind Phalke"));

        arraylist.add(new TriviaQuestion("Who is known as the ' Saint of the gutters ?", "B.R.Ambedkar", "Mother Teresa", "Mahatma Gandhi", "Baba Amte", "Mother Teresa"));

        arraylist.add(new TriviaQuestion("من اخترع القانون الشهير E=mc^2 ؟", "Albert Einstein", "Galilio", "Sarvesh", "Bill Gates", "Albert Einstein"));

        arraylist.add(new TriviaQuestion("من ينتخب رئيسًا للولايات المتحدة 2016 ؟" , "دونالد ترامب" , "هيلاري كلينتون" , "جون بول" , "باراك أوباما", "دونالد ترامب"));

        arraylist.add(new TriviaQuestion("من كان مؤسس شركة Microsoft ؟" , "بيل جيتس" , "بيل كلينتون" , "جون ريو" , "ستيف جوبز" , "بيل جيتس"));

        arraylist.add(new TriviaQuestion(" من هو مؤسس شركة Apple ؟", "Steve Jobs", "Steve Washinton", "Bill Gates", "Jobs Wills", "Steve Jobs"));

        arraylist.add(new TriviaQuestion("من هو مؤسس شركة Google ؟", "Steve Jobs", "Bill Gates", "Larry Page", "Sundar Pichai", "Larry Page"));

        arraylist.add(new TriviaQuestion("Who is know as god of cricket ?", "Sachin Tendulkar", "Kapil Dev", "Virat Koli", "Dhoni", "Sachin Tendulkar"));

        arraylist.add(new TriviaQuestion("من فاز بالون دور 2015؟" , "ليونيل ميسي" , "كريستيانو رونالدو" , "نيمار" , "كاكا" , "ليونيل ميسي"));

        arraylist.add(new TriviaQuestion("من فاز بالون دور 2014؟" , "نيمار" , "ليونيل ميسي" , "كريستيانو رونالدو" , "كاكا" , "كريستيانو رونالدو"));

        arraylist.add(new TriviaQuestion("مؤسس الأكثر شهرة بخار منصة الألعاب هو؟" , "بيل كلينتون" , "بيل ويليامز" , "غابي نيويل" , "بيل جيتس" , "غابي نيويل"));

        this.addAllQuestions(arraylist);
    }

    private void addAllQuestions(ArrayList<TriviaQuestion> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (TriviaQuestion question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    public List<TriviaQuestion> getAllOfTheQuestions() {

        List<TriviaQuestion> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            TriviaQuestion question = new TriviaQuestion();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}