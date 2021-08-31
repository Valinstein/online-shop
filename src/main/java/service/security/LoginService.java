package service.security;

import entity.Role;
import entity.Session;
import entity.User;
import jdbc.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
//@NoArgsConstructor
//@AllArgsConstructor
public class LoginService {

    private Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<>());
    private PasswordEncryptor encryptor;
    private UserDAO userDAO;


    @Value("${session.lifeTime}")
    private int lifeTimeOfSession;

    public Cookie createNewCookie(String token) {
        Cookie cookie = new Cookie("user-token", token);
        cookie.setMaxAge(lifeTimeOfSession);
        return cookie;
    }

    public boolean isAuthorised(String token) {
        Session session = sessions.get(token);
        if (session.getExpireDate().isBefore(LocalDateTime.now())) {
            sessions.remove(token);
            return false;
        }
        session.setExpireDate(LocalDateTime.now().plusMinutes(lifeTimeOfSession));
        return true;
    }

    public boolean isAccessAllowForRole(Role role, String token) {
        Session session = sessions.get(token);
        return session.getUser().getRole().equals(role);
    }

    public Cookie login(String login, String password) {
        if (checkPassword(login, password)) {
            return createNewCookie(createNewSession(userDAO.getUserByLogin(login)));
        }
        return null;
    }

    public void logout(String token) {
        sessions.remove(token);
    }


    public boolean checkPassword(String login, String password) {
        User user = userDAO.getUserByLogin(login);
        return encryptor.encryptPassword(password, user.getSalt()).equals(user.getPassword());
    }

    public String generateRandomSalt() {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        int len = 64;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    private String createNewSession(User user) {
        String token = UUID.randomUUID().toString();
        Session session = new Session(LocalDateTime.now().plusHours(lifeTimeOfSession), new ArrayList<>(), user);
        sessions.put(token, session);
        return token;
    }

    public Map<String, Session> getSessions() {
        return sessions;
    }
}

