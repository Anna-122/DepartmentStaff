package edu.goncharova.dao;

public interface DAO<Entity, Key> {
    Entity save(Entity model);
    Entity getByName(Key key);
    boolean delete(Entity model);
    Entity getById(Integer id);
}
