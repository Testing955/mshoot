package Utilities;

public class UserEmailStorage 
{
private static String userEmail;
public static void setUserEmail(String email)
{
userEmail=email;
}
public static String getUserEmail()
{
return userEmail;
}
}
