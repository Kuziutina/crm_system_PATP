package app.service;

import app.model.Position;
import org.springframework.stereotype.Service;
import app.repository.PositionRepository;

import java.util.List;

@Service
public class PositionService {
    private PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public void addNewPosition(String name) {
        Position position = new Position(name);
        positionRepository.save(position);
    }

    public List<Position> getAll() {
        return positionRepository.findAll();
    }

}
