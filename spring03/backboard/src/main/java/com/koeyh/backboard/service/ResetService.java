package com.koeyh.backboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.koeyh.backboard.common.NotFoundException;
import com.koeyh.backboard.entity.Reset;
import com.koeyh.backboard.repository.ResetRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
public class ResetService {

    private final ResetRepository resetRepository;

    public void setReset(String uuid, String email) {
        Reset reset = Reset.builder().uuid(uuid).email(email).regDate(LocalDateTime.now()).build();

        this.resetRepository.save(reset);
        log.info("▷▷▷▷▷ setReset() Complited !! ◁◁◁◁◁");
    }

    public Reset getReset(String uuid) {
        Optional<Reset> _reset = this.resetRepository.findByUuid(uuid);

        if(_reset.isPresent()) {
            log.info("▷▷▷▷▷ Data isPresent !! ◁◁◁◁◁");
            return _reset.get();
        } else {
            throw new NotFoundException("Reset Not Found !");
        }
    }
}
