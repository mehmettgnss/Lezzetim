package programci.lezzetim;



public class yemekbilgiler {

    private String yemekadi;
    private String yemekmalzeme;
    private String yemeksure;
    private String yemekisi;
    private String yemektarif;
    private String yemekack;
    private String yemekkull;
    private byte[] yemekfoto;



    public yemekbilgiler(String yad, String ymlz, String ysure, String ykisi, String ytrf, String yack, String ykul, byte[] foto) {
        this.setyemekadi(yad);
        this.setyemekmalzeme(ymlz);
        this.setyemeksure(ysure);
        this.setyemekisi(ykisi);
        this.setyemektarif(ytrf);
        this.setyemekack(yack);
        this.setyemekkull(ykul);
        this.setyemekfoto(foto);


    }
    public String getYemekadi() {
        return yemekadi;
    }

    public void setyemekadi(String yad) {
        this.yemekadi = yad;
    }



    public String getYemekmalzeme() {
        return yemekmalzeme;
    }

    public void setyemekmalzeme(String ymlz) {
        this.yemekmalzeme = ymlz;
    }


    public String getYemeksure() {
        return yemeksure;
    }

    public void setyemeksure(String ysure) {
        this.yemeksure = ysure;
    }


    public String getYemekisi() {
        return yemekisi;
    }

    public void setyemekisi(String ykisi) {
        this.yemekisi = ykisi;
    }


    public String getYemektarif() {
        return yemektarif;
    }

    public void setyemektarif(String ytarif) {
        this.yemektarif = ytarif;
    }


    public String getYemekack() {
        return yemekack;
    }

    public void setyemekack(String yack) {
        this.yemekack = yack;
    }



    public void setyemekkull(String ykul) {
        this.yemekkull = ykul;
    }

    public String getYemekkull() {
        return yemekkull;
    }

    public byte[] getYemekfoto() {
        return yemekfoto;
    }

    public void setyemekfoto(byte[] foto) {
        this.yemekfoto = foto;
    }


}
