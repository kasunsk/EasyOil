package com.oilseller.oilbrocker.platform.adaptor;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractModelAdaptor<MODEL,DTO> {

    ModelMapper modelMapper = new ModelMapper();

    ModelMapper dtoMapper = new ModelMapper();

    private Class<MODEL> modelClass;

    private Class<DTO> dtoClass;

    public AbstractModelAdaptor(Class<MODEL> modelClass, Class<DTO> dtoClass) {

        this.modelClass = modelClass;
        this.dtoClass = dtoClass;
        modelMapper.addMappings(fromModelMappings());
        dtoMapper.addMappings(fromDtoMappings());
    }

    public DTO fromModel(MODEL document) {

        DTO dto = null;
        if(document != null) {
            dto = modelMapper.map(document, dtoClass);
        }
        return dto;
    }

    public MODEL fromDto(DTO dto) {

        MODEL doc = null;
        if(dto != null) {
            doc = dtoMapper.map(dto, modelClass);
        }
        return doc;
    }

    public List<DTO> fromModelList(List<MODEL> modelList) {

        return fromModelList((Collection<MODEL>)modelList);
    }

    public List<MODEL> fromDtoList(List<DTO> dtoList) {

        return fromDtoList((Collection<DTO>)dtoList);
    }

    public List<DTO> fromModelList(Collection<MODEL> modelList) {

        List<DTO> dtoList = new ArrayList<>();

        if(modelList != null) {
            for (MODEL model : modelList) {
                dtoList.add(fromModel(model));
            }
        }

        return dtoList;
    }

    public List<MODEL> fromDtoList(Collection<DTO> dtoList) {

        List<MODEL> modelList = new ArrayList<>();

        if(dtoList != null) {
            for (DTO dto : dtoList) {
                modelList.add(fromDto(dto));
            }
        }

        return modelList;
    }

    protected abstract PropertyMap<MODEL, DTO> fromModelMappings();

    protected abstract PropertyMap<DTO, MODEL> fromDtoMappings();
}
