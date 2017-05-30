package com.xerocry.mondrian_maker.endpoints.dtos.mappers.interfaces;

import java.util.List;

public interface IDTOMapper<TDTO, TEntity> {

  TEntity toEntity(TDTO dto);
  TDTO toDTO(TEntity entity);

  Iterable<TEntity> toEntities(List<TDTO> dtos);
  List<TDTO> toDTOs(Iterable<TEntity> entities);
}
