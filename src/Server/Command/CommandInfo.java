package Server.Command;

public class CommandInfo
{
    public static String CommandStart = "COMMANDSTART:";
    public static String CommandEnd = ":END";
    public static String Server = "SERVER";
    public static String splitCommand = ":";

    // COMMANDSTART:CREATEROOM:아이디(nickname):방제목:END
    public static String CreateRoom = "CREATEROOM";
    public static int CreateRoomArgs = 5;

    // COMMANDSTART:DELETEROOM:아이디(nickname):방제목:END
    public static String DeleteRoom = "DeleteRoom";
    public static int DeleteRoomArgs = 5;

    // COMMANDSTART:INROOM:아이디(nickname):닉네임:상태메시지:ROOM이름:END
    public static String InRoom = "INROOM";
    public static int InRoomArgs = 6;
    // COMMANDSTART:INROOMUSER:닉네임:상태메시지:END
    public static String InRoomSendCommand = "INROOMUSER";

    // COMMANDSTART:SETMYSTATUS:아이디(nickname):룸ID:상메:END
    public static String SetMyStatus = "SETMYSTATUS";
    public static int SetMyStatusArgs = 6;
    // COMMANDSTART:SETMYSTATUS:바뀔유저이름:바뀐상메:END

    // COMMANDSTART:GETROOMLIST:아이디(nickname):END
    public static String GetRoomList = "GETROOMLIST";
    public static int GetRoomListArgs = 4;
    // COMMANDSTART:GETROOMLIST:SERVER:방개수:방이름:방인원수:방이름:방인원수:END


}
