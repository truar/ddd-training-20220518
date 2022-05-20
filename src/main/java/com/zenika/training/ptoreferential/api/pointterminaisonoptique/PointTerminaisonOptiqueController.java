package com.zenika.training.ptoreferential.api.pointterminaisonoptique;

import com.zenika.training.ptoreferential.application.PointTerminaisonOptiqueService;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.ChangementContratNonValideException;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptique;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptiqueId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/point-terminaison-optiques")
public record PointTerminaisonOptiqueController(PointTerminaisonOptiqueService service) {

    @PostMapping("/louer")
    public PointTerminaisonOptiqueId louerPto(@RequestBody LouerPtoRequest dto) {
        return service.louerUnPto(dto.ptoId(), dto.dateLocation(), dto.idContrat());
    }

    @PostMapping("/{id}/mettre-en-service")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void mettreEnServicePto(@PathVariable String id, @RequestBody MettreEnServiceRequest dto) {
        try {
            service.mettreEnServiceUnPto(new PointTerminaisonOptiqueId(id), dto.dateMiseEnService(), dto.idContrat());
        } catch (ChangementContratNonValideException e) {
            e.printStackTrace(); // log.error(e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public PointTerminaisonOptique readPto(@PathVariable String id) {
        return service.getPto(new PointTerminaisonOptiqueId(id));
    }
}
