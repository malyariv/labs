package businesslogic;

import Interfaces.IFReactionInfo;
import dataclasses.*;
import dbmysql.ReactionAdapterMySQL;

import java.util.*;

public class ReactionLib {

    private static Map<String, Reaction> lib=new HashMap<>();
    private static Map<String, String> cacheNames =new HashMap<>();

    public static Reaction getReaction(String s) {
        if (lib.isEmpty()) {
            IFReactionInfo ri=new ReactionAdapterMySQL();
            lib=ri.getReactions();
        }
        if (cacheNames.isEmpty()){
            IFReactionInfo reactionInfoInfo= new ReactionAdapterMySQL();
            cacheNames=reactionInfoInfo.getNames();
        }
        String name=cacheNames.get(s);
        return lib.get(name);
    }

}
