package Yipykso.service;

import Yipykso.entity.Revenue;
import Yipykso.model.RevenueDeleted;
import Yipykso.model.RevenueRequest;
import Yipykso.model.RevenueResponse;
import Yipykso.model.RevenueUpdate;
import Yipykso.repository.RevenueRepository;
import Yipykso.util.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RevenueService {

    @Autowired
    private RevenueRepository revenueRepository;

    @Autowired
    private ValidationService validationService;

   String TimeNow = Time.getTimeNow();

    // Get By ID
    @Transactional(readOnly = true)
    public RevenueResponse getById(Integer id) {
        return revenueRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }


    // Get all list
    @Transactional(readOnly = true)
    public List<RevenueResponse> getAll() {
        // Where clause, added spesification in JPA REPOSITORY
        Specification<Revenue> active = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("deleted"), false);

        List<Revenue> allRevenue = revenueRepository.findAll(active);
        return allRevenue.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    // Insert data untuk response
    private RevenueResponse mapToResponse(Revenue revenue) {
        return RevenueResponse.builder()
                .id(revenue.getId())
                .buildingId(revenue.getBuildingId())
                .transactionTypeId(revenue.getTransactionTypeId())
                .effectiveDate(revenue.getEffectiveDate())
                .createdAt(revenue.getCreatedAt())
                .updatedAt(revenue.getUpdatedAt())
                .deleted(revenue.getDeleted())
                .build();
    }

    // Create
    @Transactional
    public void create (RevenueRequest bReq) {
        // validate diambil dari class validate
        validationService.validate(bReq);

        Revenue revenue = new Revenue();
        // Set datanya yang diambil dari entity yang valuenya di set dari model body request
        // Karena ID nya auto increment maka tidak harus di get dan di set
        revenue.setBuildingId(bReq.getBuildingId());
        revenue.setTransactionTypeId(bReq.getTransactionTypeId());
        revenue.setEffectiveDate(bReq.getEffectiveDate());
        revenue.setCreatedAt(TimeNow);
        revenue.setDeleted(false);

        revenueRepository.save(revenue);
    }

    // Update
    @Transactional
    public String update(Integer id, RevenueUpdate bReq) {
        validationService.validate(bReq);

        Revenue revenue = revenueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Set nilai baru
        revenue.setBuildingId(bReq.getBuildingId());
        revenue.setTransactionTypeId(bReq.getTransactionTypeId());
        revenue.setEffectiveDate(bReq.getEffectiveDate());
        revenue.setUpdatedAt(TimeNow);
        revenue.setDeleted(false);

        revenueRepository.save(revenue);

        return "Success Updated";
    }

    // Soft Deleted
    @Transactional
    public String delete(Integer id, RevenueDeleted bReq) {
        Revenue revenue = revenueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        revenue.setDeleted(bReq.getDeleted());
        revenueRepository.save(revenue);

        return "Success Deleted";
    }

}
