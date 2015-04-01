package se.alkohest.irkksome.model.api.dao;

import se.alkohest.irkksome.model.entity.IrcUser;
import se.alkohest.irkksome.model.impl.IrcUserEB;
import se.alkohest.irkksome.orm.GenericDAO;

public class IrcUserDAO extends GenericDAO<IrcUserEB> {
    public IrcUser create(String name) {
        IrcUser ircUser = new IrcUserEB();
        ircUser.setName(name);
        return ircUser;
    }

    public boolean compare(IrcUser user, String nick) {
        return user.getName().toLowerCase().equals(nick.toLowerCase());
    }

    protected Class<IrcUserEB> getEntityBean() {
        return IrcUserEB.class;
    }
}
