package ch.nelson.appdev;

public class Femme {
    private String imgURL;
    private String title;
    private int idEscort;

    public Femme(String imgURL, String title,int idEscort) {
        this.imgURL = imgURL;
        this.title = title;
        this.idEscort = idEscort;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getidEscort() {
        return idEscort;
    }

    public void setidEscort(int id) {
        this.idEscort = id;
    }
}
