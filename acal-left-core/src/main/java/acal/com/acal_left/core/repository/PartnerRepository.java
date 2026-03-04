package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.Partner;
import acal.com.acal_left.core.model.filter.PartnerFilter;

import java.util.List;

public interface PartnerRepository {
    List<Partner> findByFilter(PartnerFilter filter);
}
