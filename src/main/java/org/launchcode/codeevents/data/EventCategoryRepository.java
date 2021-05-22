package org.launchcode.codeevents.data;

import org.launchcode.codeevents.models.EventCategory;
import org.springframework.data.repository.CrudRepository;

public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer> {
}
