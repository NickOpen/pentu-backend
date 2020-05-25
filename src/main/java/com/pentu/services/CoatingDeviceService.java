package com.pentu.services;

import com.pentu.common.dto.PagingList;
import com.pentu.common.jpa.BasePASCrud;
import com.pentu.domain.CoatingDevice;
import com.pentu.dto.coatings.ICoatingDeviceDTO;
import com.pentu.dto.coatings.OCoatingDTO;
import com.pentu.dto.coatings.OCoatingDeviceDTO;
import com.pentu.dto.coatings.OWorkingGasTypeDTO;
import com.pentu.repository.CoatingDeviceRepository;
import com.pentu.specifications.CoatingDeviceSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoatingDeviceService extends BasePASCrud<CoatingDevice, CoatingDeviceRepository> {
    public OCoatingDeviceDTO addCoatingDevice(ICoatingDeviceDTO coatingDeviceDto){
        CoatingDevice coatingDevice =
                (CoatingDevice) CoatingDeviceService.copyNotNullProperties(coatingDeviceDto, CoatingDevice.class);

        CoatingDevice added = this.addDomain(coatingDevice);
        return (OCoatingDeviceDTO)CoatingDeviceService.copyNotNullProperties(added, OCoatingDeviceDTO.class);
    }

    public Object listCoatingsDevices(Pageable pageable, String name, Boolean boolAll){
        PagingList<OCoatingDeviceDTO> result = new PagingList<>();

        CoatingDeviceSpecification coatingDeviceSpecification = new CoatingDeviceSpecification();
        coatingDeviceSpecification.setName(name);

        if (null != boolAll && boolAll) {
            List<CoatingDevice> data = this.getDomainResult(coatingDeviceSpecification);
            List<OCoatingDTO> tmpBuffer = new ArrayList<>();
            this.domain2DtoConvert(data, tmpBuffer, this::domain2DTO);

            return tmpBuffer;
        }else{
            PagingList<CoatingDevice> devices = this.getDomainPagingResult(coatingDeviceSpecification, pageable);

            List<OCoatingDeviceDTO> dtoList = new ArrayList<>();
            this.domain2DtoConvert(devices.getData(), dtoList, this::domain2DTO);

            result.setSize(devices.getSize());
            result.setData(dtoList);

            return result;
        }
    }

    public OCoatingDeviceDTO domain2DTO(CoatingDevice coatingDevice){
        OCoatingDeviceDTO ret =
                (OCoatingDeviceDTO) CoatingDeviceService.copyNotNullProperties(coatingDevice, OCoatingDeviceDTO.class);
        return ret;
    }
}
