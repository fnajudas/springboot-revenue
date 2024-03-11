package Yipykso.controller;

import Yipykso.model.*;
import Yipykso.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revenue")
public class RevenueController {

    // Used for dependency injection
    @Autowired
    private RevenueService revenueService;

    // Get by ID
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<RevenueResponse> getById(@PathVariable Integer id) {
        RevenueResponse revenueById = revenueService.getById(id);

        if (revenueById != null) {
            return Response.<RevenueResponse>builder().data(revenueById).build();
        } else {
            return Response.<RevenueResponse>builder().message("Not Found").data(null).build();
        }
    }

    // Get all list
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<RevenueResponse>> getAll() {
        List<RevenueResponse> allRevenue = revenueService.getAll();

        if (!allRevenue.isEmpty()) {
            return Response.<List<RevenueResponse>>builder().data(allRevenue).build();
        } else {
            return Response.<List<RevenueResponse>>builder().message("No revenue data found").build();
        }
    }

    // Create
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<RevenueResponse> save(@RequestBody RevenueRequest revenueRequest) {
        revenueService.create(revenueRequest);
        return Response.<RevenueResponse>builder().message("Success").build();
    }

    // Update
    @PutMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<RevenueResponse> update(@PathVariable Integer id, @RequestBody RevenueUpdate bReq) {
        String revenueResponse = revenueService.update(id, bReq);
        return Response.<RevenueResponse>builder().message(revenueResponse).build();
    }

    // Soft deleted
    @PutMapping(
            path = "/deleted/{id}"
    )
    public Response<String> delete(@PathVariable Integer id, @RequestBody RevenueDeleted bReq) {
        String revenueResponse = revenueService.delete(id, bReq);
        return Response.<String>builder().message(revenueResponse).build();
    }
}

