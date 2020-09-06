package local.springframework.sfgpetclinic.services;

import local.springframework.sfgpetclinic.model.PetType;

import java.util.Set;

public interface PetTypeService extends CrudService<PetType, Long> {
    Set<PetType> findAll();
}
