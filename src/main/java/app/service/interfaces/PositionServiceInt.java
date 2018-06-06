package app.service.interfaces;

import app.model.Position;

import java.util.List;

public interface PositionServiceInt {
    void addPosition(Position position);
    void addPosition(String name);
    void updatePosition(Position position);
    Position find(long id);
    Position find(String name);
    List<Position> findAll();
}
