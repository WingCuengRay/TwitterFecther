package lyc;

public class Result {
    public Result(){}
    public Result(String user_name, long user_id){
        this.user_name = user_name;
        this.user_id = user_id;
    }


    public String getHyperlink() {
        return hyperlink;
    }

    public String getText() {
        return text;
    }

    public String getRef_user() {
        return ref_user;
    }

    public String getUser_name() {

        return user_name;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public void setHyperlink(String hyperlink){
        this.hyperlink = hyperlink;
    }
    public void setText(String text){
        this.text = text;
    }
    public void setRef_user(String ref_user){
        this.ref_user = ref_user;
    }

    private String user_name;
    private long user_id;
    private String hyperlink;
    private String text;
    private String ref_user;
    private String screen_name;
}
