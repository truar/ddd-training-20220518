package com.zenika.training.ptoreferential.domain.pointterminaisonoptique;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PointTerminaisonOptiqueShould {

    @Test
    void loue_un_pto() {
        var id = new PointTerminaisonOptiqueId("id");
        var ptoOiId = "ptoOiId";
        var dateLocation = LocalDateTime.now();
        var dateCreation = LocalDateTime.now();
        var idContrat = "idContrat";

        var ptoWithEvent = PointTerminaisonOptique.loue(id, ptoOiId, dateLocation, dateCreation, idContrat);
        var pto = ptoWithEvent.aggregate();
        var event = ptoWithEvent.event();
        assertThat(pto).isNotNull();
        assertThat(pto.getId()).isEqualTo(id);
        assertThat(pto.getPtoOiId()).isEqualTo(ptoOiId);
        assertThat(pto.getDateLocation()).isEqualTo(dateLocation);
        assertThat(pto.getDateCreation()).isEqualTo(dateCreation);
        assertThat(pto.getIdContrat()).isEqualTo(idContrat);
        assertThat(pto.getStatus()).isEqualTo(PtoStatus.LOUE);

        assertThat(event).isEqualTo(new PtoLoue(id, ptoOiId, dateLocation, dateCreation));
    }

    @Test
    void mettre_en_service_un_pto() throws ChangementContratNonValideException {
        var pto = unPtoLoue();
        var dateMiseEnService = LocalDateTime.now();
        var idContrat = "idContrat";

        pto.mettreEnService(dateMiseEnService, idContrat);

        assertThat(pto.getStatus()).isEqualTo(PtoStatus.MIS_EN_SERVICE);
        assertThat(pto.getDateMiseEnService()).isEqualTo(dateMiseEnService);
        assertThat(pto.getIdContrat()).isEqualTo(idContrat);
    }

    @Test
    void ne_pas_remettre_en_service_un_pto_non_resilie_sur_un_nouveau_contrat() {
        var pto = unPtoLoue();
        var dateMiseEnService = LocalDateTime.now();
        var idContrat = "idNouveauContrat";

        assertThatThrownBy(() -> pto.mettreEnService(dateMiseEnService, idContrat))
                .isInstanceOf(ChangementContratNonValideException.class);

        assertThat(pto.getStatus()).isEqualTo(PtoStatus.LOUE);
        assertThat(pto.getDateMiseEnService()).isNull();
        assertThat(pto.getIdContrat()).isEqualTo("idContrat");
    }

    @Test
    void resilier_un_pto() throws ChangementContratNonValideException {
        var ptoLoue = unPtoMisEnService();
        var dateResiliation = LocalDateTime.now();

        ptoLoue.resilier(dateResiliation);

        assertThat(ptoLoue.getStatus()).isEqualTo(PtoStatus.RESILIE);
        assertThat(ptoLoue.getDateResiliation()).isEqualTo(dateResiliation);
    }

    @Test
    void restituer_un_pto() throws ChangementContratNonValideException {
        var ptoLoue = unPtoResilie();
        var dateRestitution = LocalDateTime.now();

        var event = ptoLoue.restituer(dateRestitution);

        assertThat(ptoLoue.getStatus()).isEqualTo(PtoStatus.RESTITUE);
        assertThat(ptoLoue.getDateRestitution()).isEqualTo(dateRestitution);
        assertThat(event).isEqualTo(new PtoRestitue(new PointTerminaisonOptiqueId("id"), "ptoOiId", "idContrat", dateRestitution));
    }

    private PointTerminaisonOptique unPtoLoue() {
        var id = new PointTerminaisonOptiqueId("id");
        var ptoOiId = "ptoOiId";
        var dateLocation = LocalDateTime.now();
        var dateCreation = LocalDateTime.now();
        var idContrat = "idContrat";
        return PointTerminaisonOptique.loue(id, ptoOiId, dateLocation, dateCreation, idContrat).aggregate();
    }

    private PointTerminaisonOptique unPtoMisEnService() throws ChangementContratNonValideException {
        var pto = unPtoLoue();
        var dateMiseEnService = LocalDateTime.now();
        var idContrat = "idContrat";
        pto.mettreEnService(dateMiseEnService, idContrat);
        return pto;
    }

    private PointTerminaisonOptique unPtoResilie() throws ChangementContratNonValideException {
        var pto = unPtoMisEnService();
        var dateResiliation = LocalDateTime.now();
        pto.resilier(dateResiliation);
        return pto;
    }
}
