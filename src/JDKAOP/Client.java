package JDKAOP;

public class Client {
    public static void main(String[] args) {
        System.out.println("**********************JDKProxy**********************");
        JDKProxy jdkPrpxy = new JDKProxy();
        IUserManager userManagerJDK = (IUserManager) jdkPrpxy.newProxy(new UserManagerImpl());
        userManagerJDK.addUser("lanhuigu", "123456");
    }
}
