package tecno.academy.TecnoAcademy.Models;

public class ChatModel
{
    String msg,uid;

    public ChatModel() {
    }

    public ChatModel(String msg, String uid) {
        this.msg = msg;
        this.uid = uid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
