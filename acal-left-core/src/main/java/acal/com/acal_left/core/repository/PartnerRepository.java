package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.Partner;

import java.util.List;

public interface PartnerRepository {
    List<Partner> findOrderByName();
}
