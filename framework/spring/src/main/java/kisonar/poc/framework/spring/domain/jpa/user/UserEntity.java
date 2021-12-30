package kisonar.poc.framework.spring.domain.jpa.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

import static kisonar.poc.framework.spring.domain.jpa.EntitytGenerationType.NATIVE;

@Entity
@Table(name = "users")
public class UserEntity {

    private static final String USER_ID = "userId";
    private static final String USER_NAME = "userName";
    private static final String USER_PASSWORD = "userPassword";
    private static final String USER_EMAIL = "userEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = NATIVE)
    @GenericGenerator(name = NATIVE, strategy = NATIVE)
    @Column(name = USER_ID)
    private Long userId;

    @Column(name = USER_NAME)
    private String userName;

    @Column(name = USER_PASSWORD)
    private String userPassword;

    @Column(name = USER_EMAIL)
    private String userEmail;

    public UserEntity() {
    }

    public UserEntity(Long userId, String userName,
                      String userPassword, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId.equals(that.userId) && userName.equals(that.userName) && userPassword.equals(that.userPassword) && userEmail.equals(that.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPassword, userEmail);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}