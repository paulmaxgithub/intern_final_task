package config.AWS;

public class AWSConfig {

    private String session_id;
    private String session_token;
    private String session_id_time;
    private String ubid_main;
    private String x_main;

    /// GETTERs
    public String getSession_id() { return session_id; }
    public String getSession_token() { return session_token; }
    public String getSession_id_time() { return session_id_time; }
    public String getUbid_main() { return ubid_main; }
    public String getX_main() { return x_main; }

    /// SETTERs
    public void setSession_id(String session_id) { this.session_id = session_id; }
    public void setSession_token(String session_token) { this.session_token = session_token; }
    public void setSession_id_time(String session_id_time) { this.session_id_time = session_id_time; }
    public void setUbid_main(String ubid_main) { this.ubid_main = ubid_main; }
    public void setX_main(String x_main) { this.x_main = x_main; }
}