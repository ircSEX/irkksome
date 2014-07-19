package se.alkohest.irkksome.model.api.dao;

import android.content.ContentValues;

import java.util.Date;

import se.alkohest.irkksome.model.api.local.IrcMessageDAOLocal;
import se.alkohest.irkksome.model.entity.IrcMessage;
import se.alkohest.irkksome.model.entity.IrcUser;
import se.alkohest.irkksome.model.impl.IrcMessageEB;
import se.alkohest.irkksome.orm.GenericDAO;

public class IrcMessageDAO extends GenericDAO<IrcMessageEB, IrcMessage> implements IrcMessageDAOLocal {
    @Override
    public IrcMessage create(IrcUser author, String message, Date timestamp) {
        IrcMessage ircMessage = new IrcMessageEB();
        ircMessage.setAuthor(author);
        ircMessage.setMessage(message);
        ircMessage.setTimestamp(timestamp);
        return ircMessage;
    }



    @Override
    protected ContentValues createContentValues(IrcMessage beanEntity) {
        return null;
    }
}