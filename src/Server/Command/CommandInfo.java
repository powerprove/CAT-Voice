package Server.Command;

public class CommandInfo
{
    public static String splitCommand = ":";

    // COMMANDSTART:CREATEROOM:아이디(nickname):방제목:END
    public static String CreateRoom = "CREATEROOM";
    public static int CreateRoomArgs = 5;

    // COMMANDSTART:DELETEROOM:아이디(nickname):방제목:END
    public static String DeleteRoom = "DeleteRoom";
    public static int DeleteRoomArgs = 5;
}
