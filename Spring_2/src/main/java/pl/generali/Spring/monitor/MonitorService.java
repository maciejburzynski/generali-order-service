package pl.generali.Spring.monitor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitorService {

    private final MonitorRepository monitorRepository;

    public List<Monitor> findAllMonitors() {
        return monitorRepository.findAllMonitors();
    }

    public void save(Monitor monitor) {
        monitorRepository.save(monitor);
    }
}
