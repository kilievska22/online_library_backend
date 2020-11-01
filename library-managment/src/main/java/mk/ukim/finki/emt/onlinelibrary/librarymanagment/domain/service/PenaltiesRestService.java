package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PenaltiesRestService {
    public Page<Penalty> getAllPenalties(int page, int size);

    public void delete_Penalty(String id);

    public Optional<Penalty>findPenaltyById(String id);
    public Penalty createPenalty(Penalty penalty);
    Penalty updatePenalty(String penaltyId);
    Page<Penalty> getUnpaidTodayPenalties(int page, int size, LocalDate today);
    List<Penalty> getAllPenalties();


    List<Penalty> searchPenalty(String parseInt);
}
