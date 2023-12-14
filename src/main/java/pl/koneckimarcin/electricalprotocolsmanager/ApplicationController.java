package pl.koneckimarcin.electricalprotocolsmanager;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    private int missedHeartbeats = 0;

    @PostMapping("/checkHeartbeats")
    public void checkHeartbeats() {
        missedHeartbeats = 0;
    }

    @Scheduled(fixedRate = 3000)
    public void schedule() {
        missedHeartbeats++;
        if(missedHeartbeats > 10) {
            //System.exit(0);
        }
    }
}
