package Server.User;

import java.util.HashMap;
import java.util.HashSet;

public class MemberManager
{
    private static HashMap<String, Member> members = new HashMap<>();

    public void addMember(Member member)
    {
        if (members.isEmpty())
            member.setManageer();
        if (!MemberManager.members.containsKey(member.getNickName()))
            MemberManager.members.put(member.getNickName(), member);
    }

    public static Member getMember(String nickName)
    {

        for( String key : members.keySet() ){
            Member value = members.get(key);
            System.out.println( String.format("키 : "+key+", 값 : "+value));
        }

        Member member = null;
        if (MemberManager.members.containsKey(nickName))
            member = MemberManager.members.get(nickName);
        return member;
    }
}
