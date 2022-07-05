package com.envision.aims.control;

import com.envision.aims.entity.*;
import com.envision.aims.model.*;
import com.envision.aims.service.*;
import com.envision.common.exception.DataNotFoundException;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/inventory")
public class InventoryTransactionController {

    @Autowired
    AimsWhWhOutwardService aimsWhWhOutwardService;

    @Autowired
    AimsWhWhInwardService aimsWhWhInwardService;

    @Autowired
    AimsInwardImportService aimsInwardImportService;

    @Autowired
    AimsOutwardImportService aimsOutwardImportService;

    @Autowired
    AimsOldMeterInwardService aimsOldMeterInwardService;

    @Autowired
    AimsOldMeterOutwardService aimsOldMeterOutwardService;

    @Autowired
    AimsReturnFromSubcontractorService aimsReturnFromSubcontractorService;

    @Autowired
    AimsReturnToSupplierService aimsReturnToSupplierService;

    @Autowired
    AimsWhWhOutwardDetailsService aimsWhWhOutwardDetailsService;

    @Autowired
    AimsInwardImportDetailService aimsInwardImportDetailService;

    @Autowired
    AimsOldMeterInwardDetailService aimsOldMeterInwardDetailService;

    @Autowired
    AimsOldMeterOutwardDetailService aimsOldMeterOutwardDetailService;

    @Autowired
    AimsOutwardImportDetailService aimsOutwardImportDetailService;

    @Autowired
    AimsReturnSubcntrctDetailService aimsReturnSubcntrctDetailService;

    @Autowired
    AimsReturnToSupplierDetailService aimsReturnToSupplierDetailService;

    @Autowired
    AimsWhWhInwardDetailService aimsWhWhInwardDetailService;


    @GetMapping("/warehouseOutward")
    @ResponseBody
    public Page<AimsWhWhOutward> getAimsWhWhOutwardByDiscom(@RequestParam String discom, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) throws DataNotFoundException {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsWhWhOutwardService.getAimsWhWhOutwardByDiscom(discom, pageableObj);
    }

    @GetMapping("/warehouseInward")
    @ResponseBody
    public Page<AimsWhWhInward> getAimsWhWhInwardByDiscom(@RequestParam String discom, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) throws DataNotFoundException {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsWhWhInwardService.getAimsWhWhInwardByDiscom(discom, pageableObj);
    }

    @GetMapping("/inwardImport")
    @ResponseBody
    public Page<AimsInwardImport> getAimsInwardImportByDiscom(@RequestParam String discom, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) throws DataNotFoundException {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsInwardImportService.getAimsInwardImportByDiscom(discom, pageableObj);
    }

    @GetMapping("/outwardImport")
    @ResponseBody
    public Page<AimsOutwardImport> getAimsOutwardImportByDiscom(@RequestParam String discom, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) throws DataNotFoundException {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsOutwardImportService.getAimsOutwardImportByDiscom(discom, pageableObj);
    }

    @GetMapping("/oldMeterInward")
    @ResponseBody
    public Page<AimsOldMeterInward> getAimsOldMeterInwardByDiscom(@RequestParam String discom, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) throws DataNotFoundException {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsOldMeterInwardService.getAimsOldMeterInwardByDiscom(discom, pageableObj);
    }

    @GetMapping("/oldMeterOutward")
    @ResponseBody
    public Page<AimsOldMeterOutward> getAimsOldMeterOutwardByDiscom(@RequestParam String discom, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) throws DataNotFoundException {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsOldMeterOutwardService.getAimsOldMeterOutwardByDiscom(discom, pageableObj);
    }

    @GetMapping("/returnFromSubcontractor")
    @ResponseBody
    public Page<AimsReturnFromSubcontractor> getAimsReturnFromSubcontractorByDiscom(@RequestParam String discom, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) throws DataNotFoundException {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsReturnFromSubcontractorService.getAimsReturnFromSubcontractorByDiscom(discom, pageableObj);
    }

    @GetMapping("/returnToSubcontractor")
    @ResponseBody
    public Page<AimsReturnToSupplierListVO> getAimsReturnToSupplierByDiscom(@RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) throws DataNotFoundException {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsReturnToSupplierService.getAllAimsReturnToSupplier(pageableObj);
    }


    @PostMapping(value = "/warehouseOutward", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AimsWhWhOutwardDto addAimsWhWhOutward(@RequestParam String data, @RequestParam(name = "dataFile", required = false) MultipartFile dataFile) throws DataNotFoundException {
        return  aimsWhWhOutwardService.addAimsWhWhOutward(data, dataFile);
    }

    @PostMapping(value = "/warehouseInward", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AimsWhWhInwardDto addAimsWhWhInward(@RequestParam String data, @RequestParam(name = "dataFile", required = false) MultipartFile dataFile) throws DataNotFoundException {
        return  aimsWhWhInwardService.addAimsWhWhInward(data, dataFile);
    }

    @PostMapping(value = "/inwardImport", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AimsInwardImportDto addAimsInwardImport(@RequestPart String data, @RequestPart(name = "dataFile", required = false) MultipartFile dataFile) throws DataNotFoundException {
        return  aimsInwardImportService.addAimsInwardImport(data, dataFile);
    }

    @PostMapping(value = "/outwardImport", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AimsOutwardImportDto addAimsOutwardImport(@RequestParam String data, @RequestParam(name = "dataFile", required = false) MultipartFile dataFile) throws DataNotFoundException {
        return  aimsOutwardImportService.addAimsOutwardImport(data, dataFile);
    }

    @PostMapping(value = "/oldMeterInward", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AimsOldMeterInwardDto addAimsOldMeterInward(@RequestParam String data, @RequestParam(name = "dataFile", required = false) MultipartFile dataFile) throws DataNotFoundException {
        return  aimsOldMeterInwardService.addAimsOldMeterInward(data, dataFile);
    }

    @PostMapping(value = "/oldMeterOutward", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AimsOldMeterOutwardDto addAimsOldMeterOutward(@RequestParam String data, @RequestParam(name = "dataFile", required = false) MultipartFile dataFile) throws DataNotFoundException {
        return  aimsOldMeterOutwardService.addAimsOldMeterOutward(data, dataFile);
    }

    @PostMapping(value = "/returnFromSubcontractor", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AimsReturnFromSubcontractorDto addAimsReturnFromSubcontractorDto(@RequestParam String data, @RequestParam(name = "dataFile", required = false) MultipartFile dataFile) throws DataNotFoundException {
        return  aimsReturnFromSubcontractorService.addAimsReturnFromSubcontractor(data, dataFile);
    }

    @PostMapping("returnToSupplier")
    @ResponseBody
    public AimsReturnToSupplierDto addAimsReturnToSupplierDto(@RequestPart(value = "data") AimsReturnToSupplier data, @RequestPart(value = "dataFile", required = false) MultipartFile dataFile) throws DataNotFoundException, IOException {
        return  aimsReturnToSupplierService.addAimsReturnToSupplier(data, dataFile);
    }

    /**
     * Get warehouse Outward
     * @param id id
     * @return
     */
    @GetMapping("/warehouseOutward/details/{id}")
    @ResponseBody
    public Page<AimsWhWhOutwardDetail> getAimsWhWhOutwardDetails(@PathVariable String id, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsWhWhOutwardDetailsService.getAimsWhWhOutwardDetail(id, pageableObj);
    }

    @GetMapping("/warehouseInward/details/{id}")
    @ResponseBody
    public Page<AimsWhWhInwardDetail> getAimsWhWhInwardDetails(@PathVariable String id, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsWhWhInwardDetailService.getAimsWhWhInwardDetail(id, pageableObj);
    }

    @GetMapping("/returnToSubcontractor/details/{id}")
    @ResponseBody
    public Page<AimsReturnToSupplierDetail> getAimsReturnToSupplierDetail(@PathVariable String id, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsReturnToSupplierDetailService.getAimsReturnToSupplierDetail(id, pageableObj);
    }

    @GetMapping("/returnFromSubcontractor/details/{id}")
    @ResponseBody
    public Page<AimsReturnSubcntrctDetail> getAimsReturnSubcntrctDetail(@PathVariable String id, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsReturnSubcntrctDetailService.getAimsReturnSubcntrctDetail(id, pageableObj);
    }

    @GetMapping("/oldMeterOutward/details/{id}")
    @ResponseBody
    public Page<AimsOldMeterOutwardDetail> getAimsOldMeterOutwardDetail(@PathVariable String id, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsOldMeterOutwardDetailService.getAimsOldMeterOutwardDetail(id, pageableObj);
    }

    @GetMapping("/oldMeterInward/details/{id}")
    @ResponseBody
    public Page<AimsOldMeterInwardDetail> getAimsOldMeterInwardDetail(@PathVariable String id, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsOldMeterInwardDetailService.getAimsOldMeterInwardDetail(id, pageableObj);
    }

    @GetMapping("/outwardImport/details/{id}")
    @ResponseBody
    public Page<AimsOutwardImportDetail> getAimsOutwardImportDetail(@PathVariable String id, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsOutwardImportDetailService.getAimsOutwardImportDetail(id, pageableObj);
    }

    @GetMapping("/inwardImport/details/{id}")
    @ResponseBody
    public Page<AimsInwardImportDetail> getAimsInwardImportDetail(@PathVariable String id, @RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size) {
        Pageable pageableObj = PageRequest.of(page, size);
        return  aimsInwardImportDetailService.getAimsInwardImportDetail(id, pageableObj);
    }
    
    // List data api
    @GetMapping("fetchInwardSupplierList")
    @ResponseBody
    Page<AimsInwardFromSupplierListVO> fetchInwardSupplierList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
    	Pageable pageObject =PageRequest.of(page, size);
    	return aimsInwardImportService.findInwardSupplierList(pageObject);
    }
    
    
    
  //get item group from ItemModelName
  	@GetMapping("fetchItemGroup")
  	@ResponseBody
  	public List<AimsMastersItemGroupVO> fetchItemGroup(){
  		return aimsInwardImportService.getItemGroup();
  	}
  	
  	//get Item Model Name from ItemModelName
  	@GetMapping("/findItemModelName")
	@ResponseBody
	public List<AimsItemModelNameDto> fetchItemName(@RequestParam("itemGroup") String itemGroup) {
		return aimsInwardImportService.getItemName(itemGroup);
	}
  	
  	@GetMapping("/fetchLookupData")
  	@ResponseBody
  	public AimsSupplierCategoryLookUpDto fetchLookupData(@RequestParam("itemGroup") String itemGroup,@RequestParam("itemModelName") String itemModelName) {
  		return aimsInwardImportService.getLookupData(itemGroup, itemModelName);
  	}
  	
  	@GetMapping("fetchWarehouseName")
  	@ResponseBody
  	public List<AimsMasterWarehouseVO> fetchWarehouseName() {
  		return aimsInwardImportService.getWarehouseName();
  	}
  	
  	@GetMapping("fetchSupplier")
  	@ResponseBody
  	public List<AimsMasterSupplierNameVO> fetchSupplier(){
  		return aimsInwardImportService.getSupplier();
  	}
  	
  	@PostMapping("saveInwardFromSupplier")
  	@ResponseBody
  	public AimsInwardFromSupplierVO saveInwardFromSupplier(
  			@RequestPart(value="data") AimsInwardFromSupplierVO aimsInwardFromSupplierVO,
  			@RequestPart(value="dataFile",required =false) MultipartFile dataFile,
  			@RequestPart(value="dataDocument",required =false) MultipartFile dataDocument
  			) throws IOException, DataNotFoundException {
  		return aimsInwardImportService.saveInwardFromSupplier(aimsInwardFromSupplierVO,dataFile,dataDocument);
  	}
  	
  	@PostMapping("saveReturnToSupplier")
  	@ResponseBody
  	public AimsReturnToSupplierVO saveReturnToSupplier(
  			@RequestPart(value="data") AimsReturnToSupplierVO aimsReturnToSupplierVO,
  			@RequestPart(value="dataFile",required =false) MultipartFile dataFile
  			) throws DataNotFoundException, IOException {
  		return aimsReturnToSupplierService.saveReturnToSupplier(aimsReturnToSupplierVO, dataFile);
  	}

}
