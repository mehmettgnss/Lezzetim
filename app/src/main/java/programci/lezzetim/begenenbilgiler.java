package programci.lezzetim;



public class begenenbilgiler {
    private String yemekadi;
    private String begenenadi;
    private  String yemekid;

    public begenenbilgiler(String yadi, String badi, String yid){
        this.setyemekadi(yadi);
        this.setbegenenadi(badi);
        this.setyemekid(yid);
    }

    public void setyemekadi(String yemekadi) {
        this.yemekadi = yemekadi;
    }
    public String getyemekadi() {
        return yemekadi;
    }

    public void setbegenenadi(String begenenadi) {
        this.begenenadi = begenenadi;
    }
    public String getbegenenadi() {
        return begenenadi;
    }

    public void setyemekid(String yemekid) {
        this.yemekid = yemekid;
    }
    public String getyemekid() {
        return yemekid;
    }
}
