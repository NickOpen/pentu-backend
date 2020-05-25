package com.pentu.services;

import com.pentu.common.dto.PagingList;
import com.pentu.common.jpa.BasePASCrud;
import com.pentu.domain.CoatingDevice;
import com.pentu.domain.Coatings;
import com.pentu.domain.WorkingGasType;
import com.pentu.dto.coatings.ICoatingDTO;
import com.pentu.dto.coatings.ICoatingDeviceDTO;
import com.pentu.dto.coatings.OCoatingDeviceDTO;
import com.pentu.exception.BaseException;
import com.pentu.exception.common.NotFoundException;
import com.pentu.repository.CoatingsRepository;
import com.pentu.specifications.CoatingSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.pentu.dto.coatings.OCoatingDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoatingService extends BasePASCrud<Coatings, CoatingsRepository> {
    private CoatingDeviceService coatingDeviceService;
    private WorkingGasTypeService workingGasTypeService;

    @Autowired
    public CoatingService(CoatingDeviceService coatingDeviceService, WorkingGasTypeService workingGasTypeService){
        this.coatingDeviceService = coatingDeviceService;
        this.workingGasTypeService = workingGasTypeService;
    }

    public PagingList<OCoatingDTO> listAllCoatings(Pageable pageable, String name){
        PagingList<OCoatingDTO> result = new PagingList<>();
        List<OCoatingDTO> dtoList = new ArrayList<>();

        CoatingSpecification specification = new CoatingSpecification();
        if(null != name){
            specification.setName(name);
        }

        PagingList<Coatings> pagingList = this.getDomainPagingResult(specification, pageable);
        List<Coatings> listCoating = pagingList.getData();
        this.domain2DtoConvert(listCoating, dtoList, this::domain2DTO);

        result.setSize(pagingList.getSize());
        result.setData(dtoList);

        return result;
    }

    public OCoatingDTO addCoating(ICoatingDTO iCoatingDTO) throws NotFoundException{
        Integer deviceId = iCoatingDTO.getCoatingDeviceId();
        Integer workingGasId = iCoatingDTO.getWorkingGasId();

        CoatingDevice coatingDevice = coatingDeviceService.findOneDomain(deviceId);
        if(null == coatingDevice){
            throw new NotFoundException("设备不存在");
        }

        WorkingGasType workingGasType = workingGasTypeService.findOneDomain(workingGasId);
        if(null == workingGasType){
            throw new NotFoundException("工作气体类型不存在");
        }

        Coatings coatings =
                (Coatings) CoatingService.copyNotNullProperties(iCoatingDTO, Coatings.class);
        coatings.setCoatingDevice(coatingDevice);
        coatings.setWorkingGasType(workingGasType);

        Coatings saved = this.saveDomain(coatings);
        OCoatingDTO oCoatingDTO = (OCoatingDTO)CoatingService.copyNotNullProperties(saved, OCoatingDTO.class);
        oCoatingDTO.setWorkingGasId(saved.getWorkingGasType().getId());
        oCoatingDTO.setCoatingDeviceId(saved.getCoatingDevice().getId());

        return oCoatingDTO;
    }

    public OCoatingDTO getCoating(Integer id) throws NotFoundException{
        Coatings coating = this.findOneDomain(id);
        if(null == coating){
            throw new NotFoundException("喷涂类型不存在");
        }

        return this.domain2DTO(coating);
    }

    @Transactional(rollbackFor = {BaseException.class})
    public void deleteCoating(Integer id) throws NotFoundException{
        Coatings coating = this.findOneDomain(id);
        if(null == coating){
            throw new NotFoundException("喷涂类型不存在");
        }

        this.deleteDomain(coating);
    }

    public OCoatingDTO domain2DTO(Coatings coatings){
        OCoatingDTO ret = (OCoatingDTO) CoatingService.copyNotNullProperties(coatings, OCoatingDTO.class);
        ret.setWorkingGasId(coatings.getWorkingGasType().getId());
        ret.setWorkingGasName(coatings.getWorkingGasType().getName());
        ret.setCoatingDeviceId(coatings.getCoatingDevice().getId());
        ret.setCoatingDeviceName(coatings.getCoatingDevice().getName());
        return ret;
    }
}
