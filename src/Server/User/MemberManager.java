package Server.User;

import java.util.HashMap;
import java.util.HashSet;

public class MemberManager
{
    private static HashMap<String, Member> members = new HashMap<>();
    private static Member manager;

    public void addMember(Member member)
    {
        if (members.isEmpty()) {
            member.setManageer();
            this.manager = member;
        }
        if (!MemberManager.members.containsKey(member.getNickName()))
            MemberManager.members.put(member.getNickName(), member);
    }

    public static Member getMember(String nickName)
    {

        Member member = null;
        if (MemberManager.members.containsKey(nickName))
            member = MemberManager.members.get(nickName);
        return member;
    }

    public static Member getManager()
    {
        return manager;
    }
}
