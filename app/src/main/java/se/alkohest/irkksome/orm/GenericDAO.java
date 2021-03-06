package se.alkohest.irkksome.orm;

import java.util.List;

import se.alkohest.irkksome.db.SprinklesAdapter;
import se.emilsjolander.sprinkles.CursorList;

public abstract class GenericDAO<E extends AbstractBean> {
    public void persist(E entityBean) {
        entityBean.save();
    }

    public void delete(E entityBean) {
        entityBean.delete();
    }

    public void delete(long id) {
        E item = findById(id);
        if (item != null) {
            item.delete();
        }
    }

    public E findById(long id) {
        return SprinklesAdapter.findById(getEntityBean(), id);
    }

    public List<E> getAll() {
        final CursorList<E> databaseEntries = SprinklesAdapter.getAll(getEntityBean());
        final List<E> entityBeans = databaseEntries.asList();
        databaseEntries.close();
        return entityBeans;
    }

    protected abstract Class<E> getEntityBean();
}
