package JDKAOP;

public class UserManagerImpl implements IUserManager {
    @Override
    public void addUser(String id, String password) {
        System.out.println("用户名" + id + "\t" + "密码" + password);
    }
}
