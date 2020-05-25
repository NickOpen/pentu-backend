package com.pentu.services;

import com.pentu.common.dto.PagingList;
import com.pentu.common.jpa.BasePASCrud;
import com.pentu.domain.WorkingGasType;
import com.pentu.dto.coatings.IWorkingGasTypeDTO;
import com.pentu.dto.coatings.OWorkingGasTypeDTO;
import com.pentu.repository.WorkingGasTypeRepository;
import com.pentu.specifications.WorkingGasTypeSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.*;

@Service
public class WorkingGasTypeService extends BasePASCrud<WorkingGasType, WorkingGasTypeRepository> {
    public OWorkingGasTypeDTO domain2DTO(WorkingGasType workingGasType){
        return (OWorkingGasTypeDTO) WorkingGasTypeService.copyNotNullProperties(workingGasType, OWorkingGasTypeDTO.class);
    }

    public Object listGasTypes(Pageable pageable, String name, Boolean boolAll){
        WorkingGasTypeSpecification workingGasTypeSpecification = new WorkingGasTypeSpecification();
        workingGasTypeSpecification.setName(name);

        if (null != boolAll && boolAll) {
            List<WorkingGasType> gasTypes = this.getDomainResult(workingGasTypeSpecification);
            List<OWorkingGasTypeDTO> tmpBuffer = new ArrayList<>();
            this.domain2DtoConvert(gasTypes, tmpBuffer, this::domain2DTO);

            return tmpBuffer;
        }else{
            PagingList<WorkingGasType> domains =  this.getDomainPagingResult(workingGasTypeSpecification, pageable);
            PagingList<OWorkingGasTypeDTO> result = new PagingList<>();

            List<OWorkingGasTypeDTO> tmpBuffer = new ArrayList<>();
            this.domain2DtoConvert(domains.getData(), tmpBuffer, this::domain2DTO);

            result.setSize(domains.getSize());
            result.setData(tmpBuffer);

            return result;
        }

    }

    public OWorkingGasTypeDTO addWorkingGasType(IWorkingGasTypeDTO workingGasTypeDTO){
        WorkingGasType workingGasType =
                (WorkingGasType) WorkingGasTypeService.copyNotNullProperties(workingGasTypeDTO, WorkingGasType.class);

        WorkingGasType addedWorkingGasType = this.addDomain(workingGasType);

        return (OWorkingGasTypeDTO) WorkingGasTypeService.copyNotNullProperties(addedWorkingGasType, OWorkingGasTypeDTO.class);
    }
}
