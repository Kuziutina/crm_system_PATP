package app.service;

import app.model.Car;
import app.model.Position;
import app.service.interfaces.PositionServiceInt;
import org.springframework.stereotype.Service;
import app.repository.PositionRepository;

import java.util.List;

@Service
public class PositionService implements PositionServiceInt{
    private PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    @Override
    public void addPosition(Position position) {
        positionRepository.save(position);
    }

    @Override
    public void addPosition(String name) {
        Position position = Position.builder().name(name).build();
        positionRepository.save(position);
    }

    @Override
    public void updatePosition(Position position) {
        //TODO update position
    }

    @Override
    public Position find(long id) {
        return positionRepository.getOne(id);
    }

    @Override
    public Position find(String name) {
        return positionRepository.getOneByName(name);
    }

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }
}
