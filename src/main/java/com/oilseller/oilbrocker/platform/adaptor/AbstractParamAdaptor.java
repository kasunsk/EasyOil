package com.oilseller.oilbrocker.platform.adaptor;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractParamAdaptor<PARAM, DTO> {

    ModelMapper modelMapper = new ModelMapper();

    ModelMapper dtoMapper = new ModelMapper();

    private Class<PARAM> modelClass;

    private Class<DTO> dtoClass;

    public AbstractParamAdaptor(Class<PARAM> modelClass, Class<DTO> dtoClass) {

        this.modelClass = modelClass;
        this.dtoClass = dtoClass;
        modelMapper.addMappings(fromParamMappings());
        dtoMapper.addMappings(fromDtoMappings());
    }

    public DTO fromParam(PARAM document) {

        DTO dto = null;
        if(document != null) {
            dto = modelMapper.map(document, dtoClass);
        }
        return dto;
    }

    public PARAM fromDto(DTO dto) {

        PARAM doc = null;
        if(dto != null) {
            doc = dtoMapper.map(dto, modelClass);
        }
        return doc;
    }

    public List<DTO> fromParamList(Collection<PARAM> modelList) {

        List<DTO> dtoList = new ArrayList<>();

        if(modelList != null) {
            for (PARAM model : modelList) {
                dtoList.add(fromParam(model));
            }
        }

        return dtoList;
    }

    public List<PARAM> fromDtoList(Collection<DTO> dtoList) {

        List<PARAM> paramList = new ArrayList<>();

        if(dtoList != null) {
            for (DTO dto : dtoList) {
                paramList.add(fromDto(dto));
            }
        }

        return paramList;
    }

    protected abstract PropertyMap<PARAM, DTO> fromParamMappings();

    protected abstract PropertyMap<DTO, PARAM> fromDtoMappings();
}
