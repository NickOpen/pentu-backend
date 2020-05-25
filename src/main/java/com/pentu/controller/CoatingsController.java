package com.pentu.controller;

import com.pentu.controller.base.RestApiResp;
import com.pentu.dto.coatings.ICoatingDTO;
import com.pentu.dto.coatings.ICoatingDeviceDTO;
import com.pentu.dto.coatings.IWorkingGasTypeDTO;
import com.pentu.exception.common.NotFoundException;
import com.pentu.services.CoatingDeviceService;
import com.pentu.services.CoatingService;
import com.pentu.services.WorkingGasTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/coatings")
public class CoatingsController {
    private CoatingService coatingService;
    private CoatingDeviceService coatingDeviceService;
    private WorkingGasTypeService workingGasTypeService;

    @Autowired
    public CoatingsController(CoatingService coatingService,
                              CoatingDeviceService coatingDeviceService,
                              WorkingGasTypeService workingGasTypeService){
        this.coatingService = coatingService;
        this.coatingDeviceService = coatingDeviceService;
        this.workingGasTypeService = workingGasTypeService;
    }

    @GetMapping
    public RestApiResp list(@PageableDefault Pageable pageable){
        return new RestApiResp(this.coatingService.listAllCoatings(pageable, ""));
    }

    @GetMapping(value = "/{id}")
    public RestApiResp get(@PathVariable int id) throws NotFoundException{
        return new RestApiResp(this.coatingService.getCoating(id));
    }

    @PostMapping
    public RestApiResp post(@RequestBody @Valid ICoatingDTO iCoatingDTO) throws NotFoundException {
        return new RestApiResp(this.coatingService.addCoating(iCoatingDTO));
    }

    @PutMapping
    public RestApiResp update(){
        return new RestApiResp();
    }

    @DeleteMapping
    public RestApiResp delete(@RequestParam int id) throws NotFoundException{
        this.coatingService.deleteCoating(id);
        return new RestApiResp();
    }


    /**
     * Device
     */
    @GetMapping(value="/devices")
    public RestApiResp listCoatingsDevices(@PageableDefault Pageable pageable,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) Boolean boolAll){
        return new RestApiResp(this.coatingDeviceService.listCoatingsDevices(pageable, name, boolAll));
    }

    @GetMapping(value="/devices/{id}")
    public RestApiResp getCoatingsDevice(@PathVariable int id){
        return new RestApiResp();
    }

    @PostMapping(value="/devices")
    public RestApiResp addCoatingsDevice(@RequestBody @Valid ICoatingDeviceDTO coatingDeviceDto){
        this.coatingDeviceService.addCoatingDevice(coatingDeviceDto);
        return new RestApiResp();
    }

    @PutMapping(value="/devices")
    public RestApiResp updateCoatingsDevice(){
        return new RestApiResp();
    }

    @DeleteMapping(value = "/devices")
    public RestApiResp deleteCoatingsDevice(@RequestParam int id){
        return new RestApiResp();
    }



    /**
     * Working Gas Types.
     */
    @GetMapping(value="/working-gas")
    public RestApiResp listCoatingWorkingGasTypes(@PageableDefault Pageable pageable,
                                                  @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) Boolean boolAll){
        return new RestApiResp(this.workingGasTypeService.listGasTypes(pageable, name, boolAll));
    }

    @GetMapping(value="/working-gas/{id}")
    public RestApiResp getCoatingsWorkingGasType(@PathVariable int id){
        return new RestApiResp();
    }

    @PostMapping(value="/working-gas")
    public RestApiResp addCoatingWorkingGasType(@RequestBody @Valid IWorkingGasTypeDTO workingGasTypeDTO){
        this.workingGasTypeService.addWorkingGasType(workingGasTypeDTO);
        return new RestApiResp();
    }

    @PutMapping(value="/working-gas")
    public RestApiResp updateCoatingWorkingGasType(){
        return new RestApiResp();
    }

    @DeleteMapping(value="/working-gas")
    public RestApiResp deleteCoatingWorkingGasType(@RequestParam int id){
        return new RestApiResp();
    }
}
