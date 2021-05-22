package Server.User;

import java.util.HashMap;
import java.util.HashSet;

public class MemberManager
{
    private static HashMap<String, Member> members = new HashMap<>();

    public void addMember(Member member)
    {
        if (!MemberManager.members.containsKey(member.getNickName()))
            MemberManager.members.put(member.getNickName(), member);
    }
}
