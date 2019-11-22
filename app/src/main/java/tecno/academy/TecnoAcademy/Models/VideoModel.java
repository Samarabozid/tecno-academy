package tecno.academy.TecnoAcademy.Models;

import android.net.Uri;

import java.io.Serializable;

public class VideoModel implements Serializable {

    String url;

    public VideoModel(Uri url) {
    }

    public VideoModel(String url)
    {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
