package com.immoGestion.backend.dtos;

public class LocataireLogementAssociationDTO {
    private Long locatairId;
    private Long logementId;

    public Long getLocatairId() {
        return locatairId;
    }

    public void setLocatairId(Long locatairId) {
        this.locatairId = locatairId;
    }

    public Long getLogementId() {
        return logementId;
    }

    public void setLogementId(Long logementId) {
        this.logementId = logementId;
    }
}
