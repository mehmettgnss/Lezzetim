package programci.lezzetim;



public class listduzen {
    private String userName;
    private boolean userGender;
    private String currentTime;
    private String like;

    public listduzen(String userName, boolean userGender, String currentTime, String like) {
        this.userName = userName;
        this.userGender = userGender;
        this.currentTime = currentTime;
        this.like = like;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isUserGender() {
        return userGender;
    }

    public void setUserGender(boolean userGender) {
        this.userGender = userGender;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
    public  String getLike(){
        return like;
    }
    public void setLike(String like){
        this.like = like;
    }

}
