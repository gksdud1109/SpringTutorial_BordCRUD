package mycrud.bcinside.domain;

public class Member {

    private Long id;
    private String userId;
    private String password;

    public Member(){
    }

    public Member(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
